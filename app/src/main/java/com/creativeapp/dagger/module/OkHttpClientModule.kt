package com.creativeapp.dagger.module

import android.content.Context
import com.creative.domain.di_interfaces.AppContext
import dagger.Module
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File


@Module(includes = [ApplicationModule::class])
class OkHttpClientModule{


    @Provides
    fun okHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000) //10 MB
    }

    @Provides
    fun file(@AppContext context: Context): File {
        val file = File(context.getCacheDir(), "Medicta")
        file.mkdirs()
        return file
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}