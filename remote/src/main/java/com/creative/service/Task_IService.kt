package com.creative.service

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface Task_IService {

    // add drug
//    @POST("")
//    fun addDrug(drug_EntryEntity: Drug_EntryEntity): Observable<Response<Drug_EntryEntity>>

    @GET
    fun getAllRepo(pageIndex: Int)


}