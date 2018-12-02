package com.videumcorp.datadragonwrapperkotlin

import com.videumcorp.datadragonwrapperkotlin.datadragon.DataDragon
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Locale
import com.videumcorp.datadragonwrapperkotlin.datadragon.constants.Platform
import com.videumcorp.datadragonwrapperkotlin.datadragon.endpoints.realms.realms.dto.Realms
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Examples {
    @JvmStatic
    fun main(args: Array<String>) {

        //Configure DataDragon to use NA platform
        val dataDragon = DataDragon(Platform.NA)

        GlobalScope.launch {
            val realms: Realms? = dataDragon.getRealmsDeferred()
            println(realms.toString())
        }

        //Get a champion
        //The last parameter is always new...Interface
        //This help you to controle the problems caused by DataDragon, in this case:
        //You get a champion, you get a ErrorCode (example: 403 forbidden) from DataDragon servers, or you get a Exception
        dataDragon.getChampion("Graves", Locale.EN_US, "8.21.1") {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }

        //All methods have their double asynchronous
        dataDragon.getChampionAsync("Yasuo", Locale.EN_US, "8.21.1") {
            onSuccess = { println(it.toString()) }

            onErrorCode = { println(it.toString()) }

            onFailure = { println(it.message) }
        }
    }
}