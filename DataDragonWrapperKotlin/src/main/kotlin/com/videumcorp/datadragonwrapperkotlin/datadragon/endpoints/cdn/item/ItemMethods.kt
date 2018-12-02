package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.dto.Item
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.item.dto.ItemDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object ItemMethods {

    //CoroutinesMethods

    suspend fun getItemDeferred(item_id: Int, platform: Platform, locale: Locale, version: String): Item? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetItemDeferred()
        return try {
            val response = request.await()

            if (response.isSuccessful) {
                response.body()?.data?.get(item_id)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getItemListDeferred(platform: Platform, locale: Locale, version: String): List<Item?>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetItemDeferred()
        try {
            val response = request.await()

            if (response.isSuccessful) {
                val itemMap = response.body()?.data
                itemMap?.toSortedMap()

                return itemMap?.values?.toList()
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods_______________________________________________________________________________________________________

    fun getItem(
            item_id: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Item>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetItem()

        val callbackFun = CallbackFun<Item>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val item = response.body()?.data?.get(item_id)
                if (item != null) {
                    callbackFun.onSuccess?.invoke(item)
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

    fun getItemList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<Item?>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetItem()

        val callbackFun = CallbackFun<List<Item?>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val itemMap = response.body()?.data
                itemMap?.toSortedMap()
                val itemList = itemMap?.values?.toList()

                callbackFun.onSuccess?.invoke(itemList)
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    //AsyncMethods______________________________________________________________________________________________________

    fun getItemAsync(
            item_id: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Item>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetItem()

        val callbackFun = CallbackFun<Item>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<ItemDto> {
            override fun onResponse(call: Call<ItemDto>, response: Response<ItemDto>) {
                if (response.isSuccessful) {
                    val item = response.body()?.data?.get(item_id)
                    if (item != null) {
                        callbackFun.onSuccess?.invoke(item)
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ItemDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getItemListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<Item?>>.() -> Unit
    ) {

        val championShortCall = createDataDragonServiceCdn(platform, locale, version).GetItem()

        val callbackFun = CallbackFun<List<Item?>>()
        callback.invoke(callbackFun)

        championShortCall.enqueue(object : Callback<ItemDto> {
            override fun onResponse(call: Call<ItemDto>, response: Response<ItemDto>) {
                if (response.isSuccessful) {

                    val itemMap = response.body()?.data
                    itemMap?.toSortedMap()
                    val itemList = itemMap?.values?.toList()

                    callbackFun.onSuccess?.invoke(itemList)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ItemDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
