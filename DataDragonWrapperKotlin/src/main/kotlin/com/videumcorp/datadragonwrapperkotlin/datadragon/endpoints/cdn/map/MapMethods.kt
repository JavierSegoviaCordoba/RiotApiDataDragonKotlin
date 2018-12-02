package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.dto.Map
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.map.dto.MapDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object MapMethods {

    //CoroutinesMethods
    suspend fun getMapDeferred(map_id: Int, platform: Platform, locale: Locale, version: String): Map? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetMapDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                response.body()?.data?.get(map_id)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getMapListDeferred(platform: Platform, locale: Locale, version: String): List<Map>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetMapDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                val mapMap = response.body()?.data
                ArrayList(mapMap?.toSortedMap()?.values)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods___________________________________________________________________________________

    fun getMap(map_id: Int, platform: Platform, locale: Locale, version: String,
               callback: CallbackFun<Map>.() -> Unit) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetMap()

        val callbackFun = CallbackFun<Map>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val mapMap = response.body()?.data

                val map = mapMap?.get(map_id)

                if (map != null) {
                    callbackFun.onSuccess?.invoke(mapMap[map_id])
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }

            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }
    }

    fun getMapList(platform: Platform, locale: Locale, version: String,
                   callback: CallbackFun<List<Map>>.() -> Unit) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetMap()

        val callbackFun = CallbackFun<List<Map>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            val mapMap = response.body()?.data

            if (response.isSuccessful) {
                callbackFun.onSuccess?.invoke(ArrayList(mapMap?.toSortedMap()?.values))
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }
    }

    //AsyncMethods__________________________________________________________________________________

    fun getMapAsync(
            map_id: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Map>.() -> Unit) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetMap()

        val callbackFun = CallbackFun<Map>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<MapDto> {
            override fun onResponse(call: Call<MapDto>, response: Response<MapDto>) {
                if (response.isSuccessful) {
                    val mapMap = response.body()?.data

                    val map = mapMap?.get(map_id)
                    if (map != null) {
                        callbackFun.onSuccess?.invoke(mapMap[map_id])
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<MapDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getMapListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<Map>>.() -> Unit) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetMap()

        val callbackFun = CallbackFun<List<Map>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<MapDto> {
            override fun onResponse(call: Call<MapDto>, response: Response<MapDto>) {
                if (response.isSuccessful) {

                    val mapMap = response.body()?.data

                    callbackFun.onSuccess?.invoke(ArrayList(mapMap?.toSortedMap()?.values))
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<MapDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
