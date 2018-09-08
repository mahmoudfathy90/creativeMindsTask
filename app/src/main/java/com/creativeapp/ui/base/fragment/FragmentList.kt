package com.creativeapp.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import com.jakewharton.rxrelay2.PublishRelay
import com.creative.domain.state.BaseVS
import com.creativeapp.ui.base.ActivityList_View
import com.creativeapp.application.MyApplication
import com.creativeapp.dagger.component.ApplicationComponent
import com.creativeapp.ui.base.activity.ListCallback
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState
import com.trippl3dev.listlibrary.implementation.PrettyList
import com.trippl3dev.listlibrary.implementation.RecyclerListIm
import com.trippl3dev.listlibrary.interfaces.States
import io.reactivex.Observable

abstract class  FragmentList<P,V> : BaseFragment<V, P>(), ActivityList_View, ListCallback where P : MviPresenter<V,*>, V:ActivityList_View{

     val intentRelay: PublishRelay<Int> = PublishRelay.create()
      var listHolder:RecyclerListIm? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setContentView(inflater,container)
    }

    fun setContentView(inflater: LayoutInflater, container: ViewGroup?):View{
        return inflater.inflate(getContentViewId(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addList()
    }

    override fun getIntentList(): Observable<Int>{
        return intentRelay    
    }

    override fun render(baseVS: BaseVS){
        when(baseVS){
            is BaseVS.Loading -> listHolder?.setState(States.LOADING)
            is BaseVS.Error -> listHolder?.setState(States.LOADING)
            is BaseVS.Empty -> if (listHolder?.operation?.getList()?.isEmpty()!!)view?.findViewById<View>(getListContainerId())?.setState(StatesConstants.EMPTY_STATE)
            else -> {
                view?.findViewById<View>(getListContainerId())?.setState(StatesConstants.NORMAL_STATE)
                listHolder?.setState(States.NORMAL)
                renderResult(baseVS)
            }
        }
    }
    
    fun addList(){
        PrettyList.get(childFragmentManager)
                .init()
                .setVM(getVMClassName())
                .putListInContainerWithId(getListContainerId())
                .addListener(object : PrettyList.ListReadyCallbak {
                    override fun onListReady(baseListVM: RecyclerListIm?) {
                        listHolder = baseListVM
                        listHolder?.setListVMCallback(this@FragmentList)
                        onListReady()
                    }
                })
    }
    fun getApplicationCompoent(): ApplicationComponent {
        return  (context!!.applicationContext as MyApplication).applicationComponent
    }
    override fun accept(currentPage: Int) {
        intentRelay.accept(currentPage)
    }
    abstract fun onListReady()
    abstract fun renderResult(baseVS: BaseVS)
    abstract fun getContentViewId():Int
    abstract fun getVMClassName():String
    abstract fun getListContainerId():Int
    
}

