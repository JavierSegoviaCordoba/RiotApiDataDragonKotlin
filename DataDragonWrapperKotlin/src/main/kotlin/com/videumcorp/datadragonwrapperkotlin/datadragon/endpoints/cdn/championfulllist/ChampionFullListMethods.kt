package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.dto.ChampionFull
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championfulllist.dto.ChampionFullDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

object ChampionFullListMethods {

    //CoroutinesMethods
    suspend fun getChampionFullListDeferred(
            platform: Platform, locale: Locale, version: String
    ): List<ChampionFull>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetChampionFullListDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                val championFullListMap = response.body()?.data
                ArrayList(championFullListMap?.toSortedMap()?.values)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods_______________________________________________________________________________________________________

    fun getChampionFullList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<ChampionFull>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionFullList()

        val callbackFun = CallbackFun<List<ChampionFull>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val championFullListMap = response.body()?.data
                val championFullList = ArrayList(championFullListMap?.values)
                championFullList.sortWith(compareBy { it.id })

                callbackFun.onSuccess?.invoke(championFullList)
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    //AsyncMethods______________________________________________________________________________________________________

    fun getChampionFullListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<ChampionFull>>.() -> Unit
    ) {

        val championFullCall =
                createDataDragonServiceCdn(platform, locale, version).GetChampionFullList()

        val callbackFun = CallbackFun<List<ChampionFull>>()
        callback.invoke(callbackFun)

        championFullCall.enqueue(object : Callback<ChampionFullDto> {
            override fun onResponse(call: Call<ChampionFullDto>, response: Response<ChampionFullDto>) {
                if (response.isSuccessful) {
                    val championFullListMap = response.body()?.data
                    val championFullList = ArrayList(championFullListMap?.toSortedMap()?.values)

                    callbackFun.onSuccess?.invoke(championFullList)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ChampionFullDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
