package com.creative.dataSource

import com.google.gson.Gson
import com.creative.dataSourse.Task_IRemote
import com.creative.module.AssetModule
import com.creative.module.LanguageModule
import com.creative.module.PreferenceModule
import com.creative.service.Task_IService
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class Task_RemoteImp @Inject constructor(
        var task_IService: Task_IService,
        var assetModule: AssetModule,
        val languageModule: LanguageModule,
        var gson: Gson,
        val preferenceModule: PreferenceModule

) : Task_IRemote {

//    override fun getAllConutry(pageIndex: Int): Observable<List<Country_Entity>> {
//        val country: List<Country_Entity> = gson.fromJson(assetModule.getData("countrys"), Array<Country_Entity>::class.java).toList()
//        return Observable.just(country)
//    }


}