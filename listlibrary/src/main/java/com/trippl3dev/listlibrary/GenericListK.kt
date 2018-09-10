package com.trippl3dev.listlibrary


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trippl3dev.listlibrary.implementation.FullListVM
import com.trippl3dev.listlibrary.implementation.PrettyList
import com.trippl3dev.listlibrary.implementation.RecyclerListIm
import java.util.ArrayList
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.trippl3dev.listlibrary.implementation.VMFactory
import com.trippl3dev.listlibrary.interfaces.IListCallback
import android.view.ViewTreeObserver
import com.trippl3dev.listlibrary.interfaces.States
import kotlinx.android.synthetic.main.list_layout_view.*


class GenericListK : Fragment(), RecyclerListIm.ListCallbackFunctionality {
    override fun getScrollListener(): RecyclerView.OnScrollListener {
        return fullListVM?.getScrollListener(recyclerView.layoutManager)!!
    }

    override fun setState(state: Int) {
        adapter.setState(state)
    }

    override fun resetVMData() {
        fullListVM?.resetData()
    }

    override fun setListVMCallback(listCallback: IListCallback) {
        fullListVM?.setIListCallback(listCallback)

    }

    override fun filter(value: Any) {
        fullListVM?.getAdaptee()?.setList(fullListVM?.filter(value)!!)
        adapter.notifyDataSetChanged()
    }

    override fun swapperCallback(className: String) {
        currentVMclassName = className
        fullListVM?.getLiveDataList()?.removeObservers(this)
        fullListVM?.getListOp()?.setList(ArrayList())
        fullListVM?.getAdaptee()?.setList(ArrayList())
        resetVMData()
        fullListVM = null
        disposable?.dispose()
        fullListVM = ViewModelProviders.of(this,VMFactory()).get<FullListVM<Any,Any,IListCallback>>(getVMClass(className)!!)
//        fullListVM?.setBundle(arguments?.getBundle(Bundle))
        prepareData()
        recyclerCallback?.value?.operation = fullListVM?.getListOp()!!
        recyclerView.clearOnScrollListeners()
       prepareList()
        listReadyCallback?.onListReady(recyclerCallback?.value)
        fullListVM?.fetchData()

    }

    private val listStateKey = "recycler_list_state"

    override fun getRecycler(): RecyclerView {
        return recyclerView
    }

    private lateinit var recyclerView: RecyclerView
    private var currentVMclassName : String = ""
    private lateinit var bundle: PrettyList.ListBundle
    private  var fullListVM: FullListVM<Any,Any,IListCallback>? = null
    private var listReadyCallback: PrettyList.ListReadyCallbak? = null
    private lateinit var adapter: BaseAdapter<Any>
    private var disposable: Disposable? = null
     private var recyclerCallback:MutableLiveData<RecyclerListIm>? = MutableLiveData()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_layout_view,container,false)
        recyclerView = view.findViewById(R.id.list)

//        recyclerView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        retainInstance = true
        if(savedInstanceState != null)
            listState = savedInstanceState.getParcelable(listStateKey)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null)
        getBundle()
        fullListVM = ViewModelProviders.of(this,VMFactory()).get<FullListVM<Any,Any,IListCallback>>(getVMClass(currentVMclassName)!!)
        swipToRefresh.isEnabled = fullListVM?.hasSwiptoRefresh()!!
        swipToRefresh.setOnRefreshListener {
            resetVMData()
        }
        lifecycle.addObserver(fullListVM!!)
//        fullListVM?.setBundle(arguments?.getBundle(Bundle))
        prepareData()
        prepareList()
        if (listReadyCallback != null) {
            listReadyCallback!!.onListReady(recyclerCallback?.value)
//            fullListVM?.resetData()
//            Handler().postDelayed({
//                this.fullListVM?.fetchData()
//            },500)
        }
    }


    override fun onResume() {

        super.onResume()
        if(recyclerCallback?.value?.getLayoutManager() != null)
            recyclerView.layoutManager = recyclerCallback?.value?.getLayoutManager()
        if (listState != null && recyclerView.layoutManager !=null) {
            recyclerView.layoutManager.onRestoreInstanceState(listState)
            if(fullListVM?.hasLoadMore()!! && !fullListVM?.isInNestedScroll()!!){
                    recyclerView.clearOnScrollListeners()
                    recyclerView.addOnScrollListener(fullListVM?.getScrollListener(recyclerView.layoutManager))
                }
        }
    }

    private fun prepareData(){
        fullListVM?.getLiveDataList()?.observe(this, Observer<ArrayList<Any>> { objects ->
            val oldList: ArrayList<Any> = ArrayList(fullListVM?.getAdaptee()?.getList())
            fullListVM?.getAdaptee()?.setList(ArrayList(objects))
            val result = DiffUtil.calculateDiff(GenericlistDiffUtils(oldList, objects!!))
            if (fullListVM?.currentPage == fullListVM?.setPageStartIndex()) {
                if(fullListVM?.hasLoadMore()!! && !fullListVM?.isInNestedScroll()!!){
                    recyclerView.clearOnScrollListeners()
                    recyclerView.addOnScrollListener(fullListVM?.getScrollListener(recyclerView.layoutManager))
                }
                swipToRefresh.isRefreshing = false
//                result.dispatchUpdatesTo(adapter)
                            adapter.notifyDataSetChanged()
            } else {
                recyclerView.recycledViewPool.clear()
//                            adapter.notifyDataSetChanged()
                result.dispatchUpdatesTo(adapter)
            }
//            disposable = Flowable.fromArray<ArrayList<Any>>(objects)
//                    .map<DiffUtil.DiffResult> { it ->
//                            DiffUtil.calculateDiff(GenericlistDiffUtils(oldList, it))
//                    }
//                    .subscribeOn(Schedulers.computation())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        it.printStackTrace()
//                    }
//                    .subscribe { diffResult ->
//                        if (fullListVM?.currentPage == fullListVM?.setPageStartIndex()) {
//                            if(fullListVM?.hasLoadMore()!! && !fullListVM?.isInNestedScroll()!!){
//                                recyclerView.clearOnScrollListeners()
//                                recyclerView.addOnScrollListener(fullListVM?.getScrollListener(recyclerView.layoutManager))
//                            }
//                            swipToRefresh.isRefreshing = false
//                            diffResult.dispatchUpdatesTo(adapter)
////                            adapter.notifyDataSetChanged()
//                        } else {
//                            recyclerView.recycledViewPool.clear()
////                            adapter.notifyDataSetChanged()
//                            diffResult.dispatchUpdatesTo(adapter)
//                        }
//                    }
        })
        if (listState == null) {
            recyclerCallback?.value = RecyclerListIm(this, fullListVM?.getListOp()!!)
            recyclerCallback?.value?.setLayoutManager(this.fullListVM?.getListLayoutManager(context!!)!!)
        }

    }

    private fun prepareList() {
        if (fullListVM?.attachSnapHelper()!!) {
//            recyclerView.onFlingListener = null
            fullListVM?.getSnapHelper()
                    ?.attachToRecyclerView(recyclerView)


        }
        adapter = BaseAdapter(fullListVM?.getAdaptee(), context)
        recyclerView.adapter = adapter


        if(bundle.list != null)
            fullListVM?.getListOp()?.setList(bundle.list!!)
        if(recyclerCallback?.value?.getLayoutManager() != null)
            recyclerView.layoutManager = recyclerCallback?.value?.getLayoutManager()
    }
    private fun getBundle() {
        bundle = arguments!!.getSerializable(dataKey) as PrettyList.ListBundle
        currentVMclassName = bundle.className


    }

    @Suppress("UNCHECKED_CAST")
    private fun getVMClass(name: String): Class<FullListVM<Any,Any,IListCallback>>? {
        val clazz: Class<*>?
        try {
            clazz = Class.forName(name)

            return clazz as Class<FullListVM<Any,Any,IListCallback>>?
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }catch (e:Exception){
            e.printStackTrace()
        }

        return null
    }

    fun setListReadyCallback(callback: PrettyList.ListReadyCallbak) {
        this.listReadyCallback = callback

    }

    companion object {
        const val dataKey = "dataKey"
        val Bundle: String = "Bundle"
    }

    override fun onStop() {
        disposable?.dispose()
        recyclerView.layoutManager = null
        super.onStop()
    }

    private var listState: Parcelable? = null
    override fun onSaveInstanceState(outState: Bundle) {
        if (recyclerView.layoutManager == null){
            super.onSaveInstanceState(outState)
            return
        }
        listState =  recyclerView.layoutManager.onSaveInstanceState()
        outState.putParcelable(listStateKey,listState)
        super.onSaveInstanceState(outState)


    }




}
