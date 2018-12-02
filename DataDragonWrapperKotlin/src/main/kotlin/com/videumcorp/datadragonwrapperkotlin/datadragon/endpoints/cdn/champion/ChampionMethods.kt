package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto.Champion
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.champion.dto.ChampionDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.championshortlist.dto.ChampionKeyId
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

object ChampionMethods {

    //CoroutinesMethods
    suspend fun getChampionDeferred(
            championName: String,
            platform: Platform,
            locale: Locale,
            version: String
    ): Champion? {
        val request = createDataDragonServiceCdn(platform, locale, version).GetChampionDeferred(championName)

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                response.body()?.data?.get(championName)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getChampionDeferred(
            championKey: Int,
            platform: Platform,
            locale: Locale,
            version: String
    ): Champion? {
        val request = createDataDragonServiceCdn(platform, locale, version).GetChampionShortListDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val championShortListDto = response.body()

                val championNameMap = championShortListDto?.data

                val championName: String? = championNameMap?.filter {
                    it.value.key?.toInt() == championKey
                }?.values?.firstOrNull()?.name

                if (championName != null) {
                    return getChampionDeferred(championName, platform, locale, version)
                }

                val errorCode = ErrorCode(404, "Not found")
                throw DataDragonException(errorCode)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getChampionKeyDeferred(
            championId: String, platform: Platform, locale: Locale, version: String
    ): Int? {

        try {
            val championKeyIdList = getChampionKeyIdListDeferred(platform, locale, version)

            val championKeyId = championKeyIdList.filter { championKeyId ->
                championKeyId.id == championId
            }.getOrNull(0)

            if (championKeyId != null) {
                return championKeyId.key
            }
            val errorCode = ErrorCode(404, "Not found")
            throw DataDragonException(errorCode)

        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getChampionIdDeferred(
            championKey: Int,
            platform: Platform,
            locale: Locale,
            version: String
    ): String? {

        try {
            val championKeyIdList = getChampionKeyIdListDeferred(platform, locale, version)

            val championKeyId = championKeyIdList.filter { championKeyId ->
                championKeyId.key == championKey
            }.getOrNull(0)

            if (championKeyId != null) {
                return championKeyId.id
            }
            val errorCode = ErrorCode(404, "Not found")
            throw DataDragonException(errorCode)
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }


    suspend fun getChampionKeyIdListDeferred(
            platform: Platform,
            locale: Locale,
            version: String
    ): List<ChampionKeyId> {
        val request = createDataDragonServiceCdn(platform, locale, version).GetChampionShortListDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val championShortListDto = response.body()
                val championShortMap = championShortListDto?.data
                val championKeyIdList = ArrayList<ChampionKeyId>()

                if (championShortMap != null) {
                    for ((key) in championShortMap) {
                        val championKey = championShortMap[key]?.key
                        val championId = championShortMap[key]?.id
                        val championKeyId = ChampionKeyId(championKey?.toInt(), championId)

                        championKeyIdList.add(championKeyId)
                    }
                }

                championKeyIdList.sortWith(compareBy { it.key })

                return championKeyIdList
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

//SyncMethods_______________________________________________________________________________________

    fun getChampion(
            championName: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Champion>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampion(championName)

        val callbackFun = CallbackFun<Champion>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                callbackFun.onSuccess?.invoke(response.body()?.data?.get(championName))
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    fun getChampion(
            championKey: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Champion>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionShortList()

        val callbackFun = CallbackFun<Champion>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val championShortListDto = response.body()

                val championNameMap = championShortListDto?.data

                val championName: String? = championNameMap?.filter {
                    it.value.key?.toInt() == championKey
                }?.values?.firstOrNull()?.name

                if (championName != null) {
                    getChampion(championName, platform, locale, version) {
                        onSuccess = { champion -> callbackFun.onSuccess?.invoke(champion) }
                    }
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

    fun getChampionKey(
            championId: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Int>.() -> Unit
    ) {

        val callbackFun = CallbackFun<Int>()
        callback.invoke(callbackFun)

        getChampionKeyIdList(platform, locale, version) {

            onSuccess = {


                val championKeyId = it?.filter { championKeyId ->
                    championKeyId.id == championId
                }?.getOrNull(0)

                if (championKeyId != null) {
                    callbackFun.onSuccess?.invoke(championKeyId.key)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }

        }
    }

    fun getChampionId(
            championKey: Int,
            platform: Platform,
            locale: Locale,
            version: String,
            callback: CallbackFun<String>.() -> Unit
    ) {

        val callbackFun = CallbackFun<String>()
        callback.invoke(callbackFun)

        getChampionKeyIdList(platform, locale, version) {

            onSuccess = {
                val championKeyId = it?.filter { championKeyId ->
                    championKeyId.key == championKey
                }?.getOrNull(0)

                if (championKeyId != null) {
                    callbackFun.onSuccess?.invoke(championKeyId.id)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }

        }
    }

    fun getChampionKeyIdList(
            platform: Platform,
            locale: Locale,
            version: String,
            callback: CallbackFun<List<ChampionKeyId>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionShortList()

        val callbackFun = CallbackFun<List<ChampionKeyId>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {

                val championShortListDto = response.body()

                val championShortMap = championShortListDto?.data

                val championKeyIdList = ArrayList<ChampionKeyId>()

                if (championShortMap != null) {
                    for ((key) in championShortMap) {
                        val championKey = championShortMap[key]?.key
                        val championId = championShortMap[key]?.id
                        val championKeyId = ChampionKeyId(championKey?.toInt(), championId)

                        championKeyIdList.add(championKeyId)
                    }
                }

                championKeyIdList.sortWith(compareBy { it.key })

                callbackFun.onSuccess?.invoke(championKeyIdList)

            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    //AsyncMethods__________________________________________________________________________________

    fun getChampionAsync(
            championName: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Champion>.() -> Unit
    ) {

        val championCall = createDataDragonServiceCdn(platform, locale, version).GetChampion(championName)

        val callbackFun = CallbackFun<Champion>()
        callback.invoke(callbackFun)

        championCall.enqueue(object : Callback<ChampionDto> {
            override fun onResponse(call: Call<ChampionDto>, response: Response<ChampionDto>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        callbackFun.onSuccess?.invoke(response.body()?.data?.get(championName))
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ChampionDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getChampionAsync(
            championKey: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Champion>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionShortList()

        val callbackFun = CallbackFun<Champion>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<ChampionShortDto> {
            override fun onResponse(call: Call<ChampionShortDto>, response: Response<ChampionShortDto>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {

                        if (response.isSuccessful) {
                            val championShortListDto = response.body()

                            val championNameMap = championShortListDto?.data

                            val championName: String? = championNameMap?.filter {
                                it.value.key?.toInt() == championKey
                            }?.values?.firstOrNull()?.name

                            if (championName != null) {
                                getChampionAsync(championName, platform, locale, version) {
                                    onSuccess = { champion -> callbackFun.onSuccess?.invoke(champion) }
                                }
                            } else {
                                callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                            }

                        } else {
                            callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                        }
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

    fun getChampionKeyAsync(
            championId: String, platform: Platform,
            locale: Locale, version: String,
            callback: CallbackFun<Int>.() -> Unit
    ) {

        val callbackFun = CallbackFun<Int>()
        callback.invoke(callbackFun)

        getChampionKeyIdListAsync(platform, locale, version) {
            onSuccess = {

                val championKeyId = it?.filter { championKeyId ->
                    championKeyId.id == championId
                }?.getOrNull(0)

                if (championKeyId != null) {
                    callbackFun.onSuccess?.invoke(championKeyId.key)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }

            }

            onErrorCode = { callbackFun.onErrorCode?.invoke(it) }

            onFailure = { callbackFun.onFailure?.invoke(it) }
        }
    }

    fun getChampionIdAsync(
            championKey: Int, platform: Platform,
            locale: Locale, version: String,
            callback: CallbackFun<String>.() -> Unit
    ) {

        val callbackFun = CallbackFun<String>()
        callback.invoke(callbackFun)

        getChampionKeyIdListAsync(platform, locale, version) {
            onSuccess = {

                val championKeyId = it?.filter { championKeyId ->
                    championKeyId.key == championKey
                }?.getOrNull(0)

                if (championKeyId != null) {
                    callbackFun.onSuccess?.invoke(championKeyId.id)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                }
            }

            onErrorCode = {
                callbackFun.onErrorCode?.invoke(it)
            }

            onFailure = {
                callbackFun.onFailure?.invoke(it)
            }
        }

    }

    fun getChampionKeyIdListAsync(
            platform: Platform,
            locale: Locale, version: String,
            callback: CallbackFun<List<ChampionKeyId>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetChampionShortList()

        val callbackFun = CallbackFun<List<ChampionKeyId>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<ChampionShortDto> {
            override fun onResponse(call: Call<ChampionShortDto>, response: Response<ChampionShortDto>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {

                        val championShortMap = response.body()?.data

                        val championKeyIdList = ArrayList<ChampionKeyId>()

                        if (championShortMap != null) {
                            for ((key) in championShortMap) {
                                val championKey = championShortMap[key]?.key
                                val championId = championShortMap[key]?.id
                                val championKeyId = ChampionKeyId(championKey?.toInt(), championId)

                                championKeyIdList.add(championKeyId)
                            }
                        }

                        championKeyIdList.sortWith(compareBy { it.key })

                        callbackFun.onSuccess?.invoke(championKeyIdList)
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
