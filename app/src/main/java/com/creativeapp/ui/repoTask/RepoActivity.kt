package com.creativeapp.ui.repoTask

import android.os.Bundle
import com.creative.domain.state.BaseVS
import com.creative.domain.state.List_result
import com.creative.domain.state.Repo_result
import com.creativeapp.R
import com.creativeapp.ui.base.activity.ActivityList
import com.creativeapp.ui.base.fragment.IListItemSelected
import com.creativeapp.ui.base.fragment.IListListenerSetter
import com.creativeapp.utils.DimensionUtils
import com.creativeapp.utils.EqualSpacingItemDecoration

import javax.inject.Inject
class RepoActivity : ActivityList<Repo_Presenter, Repo_View>(), Repo_View,
         Repo_ItemVM.MyListCallBack, IListListenerSetter {


    override fun getScreenTitle(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderResult(baseVS: BaseVS) {
        when (baseVS) {
            is Repo_result -> {
                listHolder?.operation?.addList(baseVS.repo)
            }
        }    }

    override fun onItemSelected(t: Repo_Model) {
    }

    private  var listListener: IListItemSelected<Repo_Model>? = null

    override fun setListener(listener: IListItemSelected<*>) {
        this.listListener = listener as IListItemSelected<Repo_Model>
    }



    @Inject
    lateinit var repo_Presenter: Repo_Presenter


    override fun createPresenter(): Repo_Presenter =repo_Presenter


    override fun getContentViewId(): Int = R.layout.list_container

    override fun getVMClassName(): String = Repo_ItemVM::class.java.name

    override fun getListContainerId(): Int = R.id.container


    override fun onListReady() {
        val verticalSpace = DimensionUtils.convertDPToPX(this, 8)
        val mDividerItemDecoration = EqualSpacingItemDecoration(verticalSpace, EqualSpacingItemDecoration.VERTICAL)
        listHolder?.addItemDecoration(mDividerItemDecoration)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
       getApplicationCompoent().inject(this)
        super.onCreate(savedInstanceState)
    }

}