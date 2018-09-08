package com.creative.repository

import com.creative.dataStore.Task_DataStoreFactory
import com.creative.domain.model.List_Domain
import com.creative.domain.repository.Task_IRepository
import com.creative.module.AssetModule
import com.google.gson.Gson
import io.reactivex.Observable

import javax.inject.Inject

class Task_Repository @Inject constructor(var task_DataStoreFactory: Task_DataStoreFactory, var  gson: Gson,
                                          var assetModule: AssetModule


) : Task_IRepository {

    override fun getMyList(pageIndex: Int): Observable<List<List_Domain>> {
        val lists: List<List_Domain> = gson.fromJson(assetModule.getData("listItems"), Array<List_Domain>::class.java).toList()
        return Observable.just(lists)    }


//    override fun getAllConutry(pageIndex: Int): Observable<List<Country_Domain>> {
//        return task_DataStoreFactory.retrieveRemoteDataStore().getAllConutry(pageIndex)
//                .map {
//                    patientMapper.country_Mapper.mapListFromEntity(it)}     }


}