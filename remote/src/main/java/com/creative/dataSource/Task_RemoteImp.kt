package com.creative.dataSource

import com.creative.Entity.Repo_Entity
import com.google.gson.Gson
import com.creative.dataSourse.Task_IRemote
import com.creative.module.AssetModule
import com.creative.module.LanguageModule
import com.creative.module.PreferenceModule
import com.creative.service.Task_IService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class Task_RemoteImp @Inject constructor(
        var task_IService: Task_IService
) : Task_IRemote {

    override fun getAllRepo(pageIndex: Int,num:Int): Observable<List<Repo_Entity>> {
        return task_IService.getAllRepo(pageIndex,num)
                .map {
                    if (it.isSuccessful){
                            it.body()!!
                    }else{
                        throw Throwable(it.message())
                    }
                }
                .subscribeOn(Schedulers.io())
    }



}