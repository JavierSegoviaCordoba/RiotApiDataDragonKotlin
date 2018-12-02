package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.cdn.languages

import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceCdn
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.*

object LanguagesMethods {

    //CoroutinesMethods
    suspend fun getLanguagesListDeferred(): List<String>? {

        val request = createDataDragonServiceCdn().GetLanguagesDeferred()

        return try {
            val response = request.await()

            if (response.isSuccessful) {
                response.body()
            } else {
                val errorCode = ErrorCode(response.code(), response.message())
                throw DataDragonException(errorCode)
            }
        } catch (t: Throwable) {
            throw Throwable(t)
        }
    }

    //SyncMethods___________________________________________________________________________________

    fun getLanguagesList(callback: CallbackFun<List<String>>.() -> Unit) {

        val call = createDataDragonServiceCdn().GetLanguages()

        call.execute {
            callback.invoke(this)
        }
    }

    //AsyncMethods__________________________________________________________________________________

    fun getLanguagesListAsync(callback: CallbackFun<List<String>>.() -> Unit) {

        val dataDragonService = createDataDragonServiceCdn()

        val call = dataDragonService.GetLanguages()

        call.enqueue {
            callback.invoke(this)
        }
    }
}
