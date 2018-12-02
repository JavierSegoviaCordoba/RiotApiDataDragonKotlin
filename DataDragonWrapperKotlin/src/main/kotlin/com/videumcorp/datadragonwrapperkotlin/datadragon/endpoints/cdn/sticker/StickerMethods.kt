package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.dto.Sticker
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.sticker.dto.StickerDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object StickerMethods {

    //CoroutinesMethods

    suspend fun getStickerListDeferred(platform: Platform, locale: Locale, version: String): List<Sticker>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetStickerDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                val stickerMap = response.body()?.data

                ArrayList(stickerMap?.toSortedMap()?.values)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getStickerDeferred(sticker_id: Int, platform: Platform, locale: Locale, version: String): Sticker? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetStickerDeferred()

        try {
            val response = request.await()

            if (response.isSuccessful) {
                val stickerMap = response.body()?.data
                val stickerList = ArrayList(stickerMap?.values)

                for (sticker in stickerList) {
                    if (sticker_id == sticker.id) {
                        return sticker
                    }
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

    suspend fun getStickerDeferred(
            sticker_name: String,
            platform: Platform,
            locale: Locale,
            version: String
    ): Sticker? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetStickerDeferred()
        try {
            val response = request.await()

            if (response.isSuccessful) {
                val stickerMap = response.body()?.data
                val sticker = stickerMap?.get(sticker_name)

                if (sticker != null) {
                    return sticker
                } else {
                    val errorCode = ErrorCode(404, "Not found")
                    throw DataDragonException(errorCode)
                }
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }


    //SyncMethods_______________________________________________________________________________________________________

    fun getStickerList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<Sticker>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSticker()

        val callbackFun = CallbackFun<List<Sticker>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val stickerMap = response.body()?.data

                callbackFun.onSuccess?.invoke(ArrayList(stickerMap?.toSortedMap()?.values))
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }
    }

    fun getSticker(
            stickerId: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Sticker>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSticker()

        val callbackFun = CallbackFun<Sticker>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {

                val stickerMap = response.body()?.data

                val stickerList = ArrayList(stickerMap?.values)

                val filteredList = stickerList.filter { it.id == stickerId }

                if (filteredList.isNotEmpty()) {
                    val sticker = filteredList.first()
                    callbackFun.onSuccess?.invoke(sticker)
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

    fun getSticker(
            stickerName: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Sticker>.() -> Unit
    ) {
        val call = createDataDragonServiceCdn(platform, locale, version).GetSticker()

        val callbackFun = CallbackFun<Sticker>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {

                val stickerMap = response.body()?.data

                val sticker = stickerMap?.get(stickerName)

                if (sticker != null) {
                    callbackFun.onSuccess?.invoke(sticker)
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

//AsyncMethods______________________________________________________________________________________________________

    fun getStickerListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<Sticker>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSticker()

        val callbackFun = CallbackFun<List<Sticker>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<StickerDto> {
            override fun onResponse(call: Call<StickerDto>, response: Response<StickerDto>) {
                if (response.isSuccessful) {
                    val stickerMap = response.body()?.data

                    callbackFun.onSuccess?.invoke(ArrayList(stickerMap?.toSortedMap()?.values))
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<StickerDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getStickerAsync(
            stickerId: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Sticker>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSticker()

        val callbackFun = CallbackFun<Sticker>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<StickerDto> {
            override fun onResponse(call: Call<StickerDto>, response: Response<StickerDto>) {
                if (response.isSuccessful) {

                    val stickerMap = response.body()?.data

                    val stickerList = ArrayList(stickerMap?.values)

                    val filteredList = stickerList.filter { it.id == stickerId }

                    if (filteredList.isNotEmpty()) {
                        val sticker = filteredList.first()
                        callbackFun.onSuccess?.invoke(sticker)
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<StickerDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getStickerAsync(
            stickerName: String, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Sticker>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetSticker()

        val callbackFun = CallbackFun<Sticker>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<StickerDto> {
            override fun onResponse(call: Call<StickerDto>, response: Response<StickerDto>) {
                if (response.isSuccessful) {

                    val stickerMap = response.body()?.data

                    val sticker = stickerMap?.get(stickerName)

                    if (sticker != null) {
                        callbackFun.onSuccess?.invoke(sticker)
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<StickerDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
