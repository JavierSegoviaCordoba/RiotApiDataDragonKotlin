package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon.dto.ProfileIcon
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.profileicon.dto.ProfileIconDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object ProfileIconMethods {

    //CoroutinesMethods
    suspend fun getProfileIconDeferred(
            icon_id: Int,
            platform: Platform,
            locale: Locale,
            version: String
    ): ProfileIcon? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetProfileIconDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                response.body()?.profileIcon?.get(icon_id)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    suspend fun getProfileIconListDeferred(
            platform: Platform,
            locale: Locale,
            version: String
    ): List<ProfileIcon?>? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetProfileIconDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                val profileIconMap = response.body()?.profileIcon

                ArrayList(profileIconMap?.toSortedMap()?.values)
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods_______________________________________________________________________________________________________

    fun getProfileIcon(
            icon_id: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<ProfileIcon>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetProfileIcon()

        val callbackFun = CallbackFun<ProfileIcon>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val profileIconMap = response.body()?.profileIcon

                val profileIcon = profileIconMap?.get(icon_id)

                if (profileIcon != null) {
                    callbackFun.onSuccess?.invoke(profileIcon)
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

    fun getProfileIconList(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<ProfileIcon?>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetProfileIcon()

        val callbackFun = CallbackFun<List<ProfileIcon?>>()
        callback.invoke(callbackFun)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val profileIconMap = response.body()?.profileIcon
                val profileIconList = profileIconMap?.toSortedMap()?.values?.toList()

                callbackFun.onSuccess?.invoke(profileIconList)
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }
    }

    //AsyncMethods______________________________________________________________________________________________________

    fun getProfileIconAsync(
            icon_id: Int, platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<ProfileIcon>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetProfileIcon()

        val callbackFun = CallbackFun<ProfileIcon>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<ProfileIconDto> {
            override fun onResponse(call: Call<ProfileIconDto>, response: Response<ProfileIconDto>) {
                if (response.isSuccessful) {

                    val profileIcon = response.body()?.profileIcon?.get(icon_id)

                    if (profileIcon != null) {
                        callbackFun.onSuccess?.invoke(profileIcon)
                    } else {
                        callbackFun.onErrorCode?.invoke(ErrorCode(404, "Not found"))
                    }
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ProfileIconDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }

    fun getProfileIconListAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<List<ProfileIcon?>>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetProfileIcon()

        val callbackFun = CallbackFun<List<ProfileIcon?>>()
        callback.invoke(callbackFun)

        call.enqueue(object : Callback<ProfileIconDto> {
            override fun onResponse(call: Call<ProfileIconDto>, response: Response<ProfileIconDto>) {
                if (response.isSuccessful) {

                    val profileIconMap = response.body()?.profileIcon

                    val profileIconList = ArrayList(profileIconMap?.toSortedMap()?.values)

                    callbackFun.onSuccess?.invoke(profileIconList)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ProfileIconDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
