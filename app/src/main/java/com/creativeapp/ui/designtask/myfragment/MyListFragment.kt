package com.creativeapp.ui.designtask.myfragment

import android.os.Bundle
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
    override fun renderResult(baseVS: BaseVS) {
        when (baseVS) {
            is List_result -> {
                listHolder?.operation?.addList(baseVS.items)
            }
        }    }

    override fun onItemSelected(t: List_Model) {
    }

    private  var listListener: IListItemSelected<List_Model>? = null

    override fun setListener(listener: IListItemSelected<*>) {
        this.listListener = listener as IListItemSelected<List_Model>
    }



    @Inject
    lateinit var myList_Presenter: My_List_Presenter


    override fun createPresenter(): My_List_Presenter =myList_Presenter


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