package com.creativeapp.ui.designtask.myfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import com.creative.domain.model.List_Domain
import com.creative.domain.state.BaseVS
import com.creative.domain.state.List_result
import com.creativeapp.R
import com.creativeapp.ui.base.fragment.FragmentList
import com.creativeapp.ui.base.fragment.IListItemSelected
import com.creativeapp.ui.base.fragment.IListListenerSetter
import com.creativeapp.utils.DimensionUtils
import com.creativeapp.utils.EqualSpacingItemDecoration

import javax.inject.Inject

class MyList_Fragment : FragmentList<My_List_Presenter, MyList_View>(), MyList_View,
        List_ItemVM.MyListCallBack, IListListenerSetter {
    override fun getPageNumber(): Int {
        var bundle: Bundle = this!!.arguments!!
        tabnumber = bundle.getInt("tabnumber",0)
        return tabnumber
    }


    var tabnumber: Int = 0
    var radionumber: Int = 1

    companion object {
        fun newInstance(tabnum: Int, radioNum: Int): Fragment {
            var frag: MyList_Fragment = MyList_Fragment()
            val args = Bundle()
            args.putInt("tabnumber", tabnum)
            args.putInt("radionumber", radioNum)
            frag.arguments = args
            return frag

        }
    }
    override fun renderResult(baseVS: BaseVS) {
        when (baseVS) {
            is List_result -> {
                var bundle: Bundle = this!!.arguments!!
                var mylist: MutableList<List_Domain> = ArrayList<List_Domain>()
                tabnumber = bundle.getInt("tabnumber",0)
                radionumber = bundle.getInt("radionumber",1)

                if (radionumber == 1) {
                    when (tabnumber) {
                        0 -> {
                            for (n in 0..1) {
                                mylist.add(baseVS.items[n])
                            }
                        }
                        1 -> {
                            for (n in 2..3) {
                                mylist.add(baseVS.items[n])
                            }
                        }
                        2 -> {
                            for (n in 4..5) {
                                mylist.add(baseVS.items[n])
                            }
                        }
                    }
                }
                else if (radionumber == 2){
                    when (tabnumber) {
                        0 -> {
                            for (n in 2..5) {
                                mylist.add(baseVS.items[n])
                            }
                        }
                        1 -> {
                            for (n in 1..3) {
                                mylist.add(baseVS.items[n])
                            }
                        }
                       2 -> {
                            for (n in 3..5) {
                                mylist.add(baseVS.items[n])
                            }
                        }
                    }
                }
                listHolder?.operation?.addList(mylist)
            }
        }
    }

    override fun onItemSelected(t: List_Model) {
    }

    override fun onResume() {
        super.onResume()
    }

    private var listListener: IListItemSelected<List_Model>? = null

    override fun setListener(listener: IListItemSelected<*>) {
        this.listListener = listener as IListItemSelected<List_Model>
    }


    @Inject
    lateinit var myList_Presenter: My_List_Presenter


    override fun createPresenter(): My_List_Presenter = myList_Presenter


    override fun getContentViewId(): Int = R.layout.list_container

    override fun getVMClassName(): String = List_ItemVM::class.java.name

    override fun getListContainerId(): Int = R.id.container


    override fun onListReady() {
        val verticalSpace = DimensionUtils.convertDPToPX(this.context!!, 8)
        val mDividerItemDecoration = EqualSpacingItemDecoration(verticalSpace, EqualSpacingItemDecoration.VERTICAL)
        listHolder?.addItemDecoration(mDividerItemDecoration)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationCompoent().inject(this)
        super.onCreate(savedInstanceState)
    }

}