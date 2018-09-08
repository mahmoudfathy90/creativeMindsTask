package com.creativeapp.ui.repoTask

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.creative.domain.model.List_Domain
import com.creative.domain.model.RepoOwner_domain
import com.creative.domain.model.Repo_Domain
import com.creativeapp.R
import com.creativeapp.ui.base.activity.ListCallback
import com.creativeapp.ui.designtask.myfragment.List_Model
import com.google.gson.Gson

import com.trippl3dev.listlibrary.implementation.FullListVM


class Repo_ItemVM : FullListVM<Repo_Domain, Repo_Model, Repo_ItemVM.MyListCallBack>() {
    override fun mapFrom(it: Repo_Domain): Repo_Model {
        return Repo_Model(it.name,it.description,it.html_url,mapOwner(it.owner!!),it.fork)
    }




    fun mapOwner(it:List<RepoOwner_domain>):List<RepoOwner_Model>{
        var gson:Gson= Gson()
        return gson.fromJson(gson.toJson(it), Array<RepoOwner_Model>::class.java).toList()

    }


    override fun getViewId(type: Int): Int = R.layout.repo_list_item


    override fun onViewSnapped(view: View, position: Int) {
    }


    interface MyListCallBack : ListCallback {
        fun onItemSelected(t: Repo_Model)
    }
    override fun hasLoadMore(): Boolean = true

    override fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun fetchData() {
        super.fetchData()
        listCallback.accept(0)
    }

    override fun onBindView(root: View?, position: Int) {
        super.onBindView(root, position)
        root?.setOnClickListener {
            listCallback.onItemSelected(getListOp().getList()?.get(position)!!)
        }
    }


}