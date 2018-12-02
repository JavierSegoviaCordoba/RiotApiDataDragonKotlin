package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.dto.Language
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.language.dto.LanguageDto
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.CallbackFun
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.DataDragonException
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.ErrorCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

object LanguageMethods {

    //CoroutinesMethods

    suspend fun getLanguageDeferred(platform: Platform, locale: Locale, version: String): Language? {

        val request = createDataDragonServiceCdn(platform, locale, version).GetLanguageDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                response.body()?.language
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods___________________________________________________________________________________

    fun getLanguage(platform: Platform, locale: Locale, version: String,
                    callback: CallbackFun<Language>.() -> Unit
    ) {

        val call = createDataDragonServiceCdn(platform, locale, version).GetLanguage()

        val callbackFun = CallbackFun<Language>()
        callback.invoke(callbackFun)

        try {
            val languageDtoResponse = call.execute()

            if (languageDtoResponse.isSuccessful) {
                callbackFun.onSuccess?.invoke(languageDtoResponse.body()?.language)
            } else {
                callbackFun.onErrorCode?.invoke(ErrorCode(languageDtoResponse.code(), languageDtoResponse.message()))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callbackFun.onFailure?.invoke(e)
        }

    }

    //AsyncMethods______________________________________________________________________________________________________

    fun getLanguageAsync(
            platform: Platform, locale: Locale, version: String,
            callback: CallbackFun<Language>.() -> Unit
    ) {

        val languageCall = createDataDragonServiceCdn(platform, locale, version).GetLanguage()

        val callbackFun = CallbackFun<Language>()
        callback.invoke(callbackFun)

        languageCall.enqueue(object : Callback<LanguageDto> {
            override fun onResponse(call: Call<LanguageDto>, response: Response<LanguageDto>) {
                if (response.isSuccessful) {
                    callbackFun.onSuccess?.invoke(response.body()?.language)
                } else {
                    callbackFun.onErrorCode?.invoke(ErrorCode(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<LanguageDto>, t: Throwable) {
                callbackFun.onFailure?.invoke(t)
            }
        })
    }
}
