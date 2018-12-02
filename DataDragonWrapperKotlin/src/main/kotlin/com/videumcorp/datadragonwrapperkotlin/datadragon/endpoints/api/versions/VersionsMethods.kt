package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.api.versions

import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceApi
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.*

object VersionsMethods {


    //CoroutinesMethods
    suspend fun getVersionListDeferred(): List<String>? {

        val dataDragonService = createDataDragonServiceApi()
        val request = dataDragonService.GetVersionsListDeferred()

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

    //SyncMethods_______________________________________________________________________________________________________

    fun getVersionsList(callbackFun: CallbackFun<List<String>>.() -> Unit) {

        val dataDragonService = createDataDragonServiceApi()

        val call = dataDragonService.GetVersionsList()

        call.execute {
            callbackFun.invoke(this)
        }
    }

    //AsyncMethods______________________________________________________________________________________________________

    fun getVersionsListAsync(callback: CallbackFun<List<String>>.() -> Unit) {

        val dataDragonService = createDataDragonServiceApi()

        val call = dataDragonService.GetVersionsList()

        call.enqueue {
            callback.invoke(this)
        }
    }
}

