package com.creativeapp.dagger.module

import android.app.Application
import android.content.Context
import com.creative.domain.di_interfaces.AppContext
import com.creativeapp.utils.NetworkUtils
import dagger.Module
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module(includes = [ApplicationModule::class])
class OkHttpClientModule{


    @Provides
    @Singleton
    internal fun provideOkHttpClient(application: Application, cache: Cache): OkHttpClient {
        //Log.i("INFODATA",interceptor==null?"TRUE INTER NuLL":"NOT NULL INTER");

        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    var request = chain.request()
                    if (NetworkUtils.isNetworkOnline(application)) {
                        val maxAge = 60 * 60 // read from cache for 1 minute
                        request = request.newBuilder().header("Cache-Control", "public, max-age=$maxAge").build()
                    } else {
                        request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                    }
                    chain.proceed(request)
                }
                .build()
    }



//    @Provides
//    fun okHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient()
//                .newBuilder()
//                .cache(cache)
//                .addInterceptor(httpLoggingInterceptor)
//                .build()
//    }

//    @Provides
//    fun cache(cacheFile: File): Cache {
//        return Cache(cacheFile, 10 * 1000 * 1000) //10 MB
//    }

    @Provides
    fun file(@AppContext context: Context): File {
        val file = File(context.getCacheDir(), "creative")
        file.mkdirs()
        return file
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.d(message) })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    internal fun provideOKHttpCache(application: Application): Cache {
        val SIZE_OF_CACHE = (40 * 1024 * 1024).toLong() // 10 MiB
        return Cache(File(application.cacheDir, "http"), SIZE_OF_CACHE)
    }


    @Provides
    @Singleton
    fun provideInterceptor(application: Application): Interceptor {
        return Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (com.creativeapp.utils.NetworkUtils.isNetworkOnline(application)) {
                val maxAge = 60 // read from cache for 1 minute
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
            } else {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
            }
        }
    }



    inner class CachingControlInterceptor(internal var application: Application) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()

            // Add Cache Control only for GET methods
            if (request.method() == "GET") {
                if (com.creativeapp.utils.NetworkUtils.isNetworkOnline(application)) {
                    // 1 day
                    request = request.newBuilder()
                            .header("Cache-Control", "only-if-cached")
                            .build()
                } else {
                    // 4 weeks stale

                    request = request.newBuilder()
                            .header("Cache-Control", "public, max-stale=2419200")
                            .build()
                }
            }

            val originalResponse = chain.proceed(request)
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=600")
                    .build()
        }
    }

}