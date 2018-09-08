package com.creative.dataStore

import com.creative.dataSourse.Task_ICach
import com.creative.dataSourse.Task_IDataStore
import javax.inject.Inject

class Task_DataStoreFactory @Inject constructor(private val task_ICach: Task_ICach,
                                                private val task_CachDataStore: Task_CachDataStore,
                                                private val task_RemoteDataStore: Task_RemoteDataStore
                                                  ){

    fun retrieveDataStore(): Task_IDataStore {
        if (task_ICach.isCached() && !task_ICach.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cach Data Store
     */
     fun retrieveCacheDataStore(): Task_CachDataStore {
        return task_CachDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
     fun retrieveRemoteDataStore(): Task_RemoteDataStore {
        return task_RemoteDataStore
    }


}