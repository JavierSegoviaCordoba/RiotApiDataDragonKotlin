package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionShort
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionShortDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

object ChampionShortListMethods {

    //CoroutinesMethods
    suspend fun getChampionShortListDeferred(platform: Platform, locale: Locale, version: String): List<ChampionShort>? {
        val request = createDataDragonServiceCdn(platform, locale, version).GetChampionShortListDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val championShortListMap = response.body()?.data
                val championShortList = championShortListMap?.values?.toList()
                return championShortList?.sortedWith(compareBy { it.key?.toInt() })
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods___________________________________________________________________________________

    fun getChampionShortList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<ChampionShort>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionShortList()

        val callbackFun = CallbackFun<List<ChampionShort>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val championShortListMap = response.body()?.data

                val championShortList = ArrayList(championShortListMap?.values)

                championShortList.sortWith(compareBy { it.key?.toInt() })

                callbackFun.onSuccess?.invoke(championShortList)

            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    //AsyncMethods__________________________________________________________________________________

    fun getChampionShortListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<ChampionShort>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionShortList()

        val callbackFun = CallbackFun<List<ChampionShort>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<ChampionShortDto> {
            override fun onResponse(call: Call<ChampionShortDto>, response: Response<ChampionShortDto>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {

                        val championShortList = ArrayList(response.body()?.data?.values)

                        championShortList.sortWith(compareBy { it.key?.toInt() })

                        callbackFun.onSuccess?.invoke(championShortList)
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ChampionShortDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
