package com.creativeapp.ui.base.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.hannesdorfmann.mosby3.mvi.MviPresenter
import com.jakewharton.rxrelay2.PublishRelay
import com.creative.domain.state.BaseVS
import com.creativeapp.R
import com.creativeapp.application.MyApplication
import com.creativeapp.dagger.component.ApplicationComponent
import com.creativeapp.ui.base.ActivityList_View
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState
import com.trippl3dev.listlibrary.implementation.PrettyList
import com.trippl3dev.listlibrary.implementation.RecyclerListIm
import com.trippl3dev.listlibrary.interfaces.States
import io.reactivex.Observable

abstract class ActivityList<P, V> : BaseMVIActivity<V, P>(), ActivityList_View, ListCallback where P : MviPresenter<V, *>, V : ActivityList_View {
    private val intentRelay: PublishRelay<Int> = PublishRelay.create()
    var listHolder: RecyclerListIm? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (getContentViewId() >= 0)
        setTheContent()
        setToolbar(getToolbarId())
        addList()
    }

    fun getApplicationCompoent():ApplicationComponent{
         return  (application as MyApplication).applicationComponent
    }

    open fun setTheContent() {
        setContentView(getContentViewId())
    }

    override fun getIntentList(): Observable<Int> {
        return intentRelay
    }


    override fun setToolbar(toolbarId: Int) {
        super.setToolbar(toolbarId)
        findViewById<TextView>(R.id.title)?.text = getScreenTitle()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getToolbarId(): Int {
        return 1
    }

    abstract override fun getScreenTitle(): String

    override fun render(baseVS: BaseVS) {
        when (baseVS) {
            is BaseVS.Loading -> listHolder?.setState(States.LOADING)
            is BaseVS.Error -> listHolder?.setState(States.ERROR)
            is BaseVS.Empty -> {
                if (listHolder?.operation?.getList()?.isEmpty()!!)
                    findViewById<View>(getListContainerId()).setState(StatesConstants.EMPTY_STATE)
                else listHolder?.setState(States.NORMAL)
            }
            else -> {
                findViewById<View>(getListContainerId()).setState(StatesConstants.NORMAL_STATE)
                listHolder?.setState(States.NORMAL)
                renderResult(baseVS)
            }
        }
    }

    fun addList() {
        PrettyList.get(supportFragmentManager)
                .init()
                .setVM(getVMClassName())
                .putListInContainerWithId(getListContainerId())
                .addListener(object : PrettyList.ListReadyCallbak {
                    override fun onListReady(baseListVM: RecyclerListIm?) {
                        listHolder = baseListVM
                        listHolder?.setListVMCallback(this@ActivityList)
                        onListReady()
                    }
                })
    }

    override fun accept(currentPage: Int) {
        intentRelay.accept(currentPage)
    }

    override fun onStop() {
        super.onStop()
    }


    abstract fun onListReady()
    abstract fun renderResult(baseVS: BaseVS)
    abstract fun getContentViewId(): Int
    abstract fun getVMClassName(): String
    abstract fun getListContainerId(): Int

}

