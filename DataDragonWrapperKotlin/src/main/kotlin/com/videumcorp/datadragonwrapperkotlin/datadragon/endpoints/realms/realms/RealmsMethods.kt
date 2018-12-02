package com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms

import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.createDataDragonServiceRealms
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms.dto.Realms
import com.videumcorp.datadragonwrapperkotlin.datadragon.utils.*

object RealmsMethods {

    //CoroutinesMethods

    suspend fun getRealmsDeferred(platform: Platform): Realms? {

        val dataDragonService = createDataDragonServiceRealms(platform)

        val request = dataDragonService.GetRealmsDeferred(platform.name.toLowerCase())

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

    fun getRealms(platform: Platform, callbackFun: CallbackFun<Realms>.() -> Unit) {

        val dataDragonService = createDataDragonServiceRealms(platform)

        val call = dataDragonService.GetRealms(platform.name.toLowerCase())

        call.execute {
            callbackFun.invoke(this)
        }
    }

    fun getRealmsAsync(platform: Platform, callback: CallbackFun<Realms>.() -> Unit) {

        val dataDragonService = createDataDragonServiceRealms(platform)

        val call = dataDragonService.GetRealms(platform.name.toLowerCase())

        call.enqueue {
            callback.invoke(this)
        }
    }
}
