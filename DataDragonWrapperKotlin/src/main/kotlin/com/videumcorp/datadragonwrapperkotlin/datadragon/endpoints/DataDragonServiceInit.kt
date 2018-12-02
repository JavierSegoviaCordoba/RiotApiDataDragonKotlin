@file:JvmName("DataDragonServiceKt")

package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ObjectMapperInit
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

fun createDataDragonServiceApi(): DataDragonService {

    val baseUrl = Platform.NA.getHostApi()

    val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapperInit.setConfig()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    return retrofit.create(DataDragonService::class.java)
}

fun createDataDragonServiceCdn(): DataDragonService {

    val baseUrl = Platform.NA.getHostCdn()

    val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapperInit.setConfig()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    return retrofit.create(DataDragonService::class.java)
}

fun createDataDragonServiceCdn(platform: Platform, locale: Locale, version: String): DataDragonService {

    val baseUrl = platform.getHostCdn(locale, version)

    val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapperInit.setConfig()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    return retrofit.create(DataDragonService::class.java)
}

fun createDataDragonServiceRealms(platform: Platform): DataDragonService {

    val baseUrl = platform.getHostRealms()

    val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapperInit.setConfig()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    return retrofit.create(DataDragonService::class.java)
}
