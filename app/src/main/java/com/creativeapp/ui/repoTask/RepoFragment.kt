package com.creativeapp.ui.repoTask

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.creative.domain.state.BaseVS
import com.creative.domain.state.Repo_result
import com.creativeapp.R
import com.creativeapp.ui.base.fragment.FragmentList
import com.creativeapp.ui.base.fragment.IListItemSelected
import com.creativeapp.ui.base.fragment.IListListenerSetter
import com.creativeapp.utils.DimensionUtils
import com.creativeapp.utils.EqualSpacingItemDecoration
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

import javax.inject.Inject
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.creative.domain.model.Repo_Domain
import kotlinx.android.synthetic.main.list_container.*


class RepoFragment : FragmentList<Repo_Presenter, Repo_View>(), Repo_View,
        Repo_ItemVM.MyListCallBack, IListListenerSetter {




    override fun acceptRepo(page: Int, num: Int) {
        listRelay.accept(Pair(page, num))
    }

    override fun getRepoIntentList(): Observable<Pair<Int, Int>> {

        return listRelay
    }

    val listRelay: PublishRelay<Pair<Int, Int>> = PublishRelay.create()
    var list: List<Repo_Domain> = ArrayList<Repo_Domain>()

    override fun renderResult(baseVS: BaseVS) {
        when (baseVS) {
            is Repo_result -> {
                list = baseVS.repo
                listHolder?.operation?.addList(baseVS.repo)
            }
        }
    }

    override fun onItemSelected(t: Repo_Model) {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog)


        var repo = dialog.findViewById<Button>(R.id.repo)
        var owner = dialog.findViewById<Button>(R.id.owner)
        repo.setOnClickListener {
            if (t.html_url != null) {
                val url = t.html_url
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            dialog.dismiss()
        }
        owner.setOnClickListener {
            if (t.owner?.html_url != null) {
                val url = t.owner?.html_url
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            dialog.dismiss()
        }

        dialog.show()
    }


    private var listListener: IListItemSelected<Repo_Model>? = null

    override fun setListener(listener: IListItemSelected<*>) {
        this.listListener = listener as IListItemSelected<Repo_Model>
    }


    @Inject
    lateinit var repo_Presenter: Repo_Presenter


    override fun createPresenter(): Repo_Presenter = repo_Presenter


    override fun getContentViewId(): Int = R.layout.list_container

    override fun getVMClassName(): String = Repo_ItemVM::class.java.name

    override fun getListContainerId(): Int = R.id.container


    override fun onListReady() {
        val verticalSpace = DimensionUtils.convertDPToPX(this!!.context!!, 8)
        val mDividerItemDecoration = EqualSpacingItemDecoration(verticalSpace, EqualSpacingItemDecoration.VERTICAL)
        listHolder?.addItemDecoration(mDividerItemDecoration)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        getApplicationCompoent().inject(this)
        super.onCreate(savedInstanceState)
    }






}