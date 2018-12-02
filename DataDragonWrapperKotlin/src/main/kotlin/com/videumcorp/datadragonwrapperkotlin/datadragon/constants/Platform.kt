package com.videumcorp.datadragonwrapperkotlin.datadragon.constants

enum class Platform(val id: String, val localeName: String) {
    BR("BR1", "br"),
    EUNE("EUN1", "eune"),
    EUW("EUW1", "euw"),
    JP("JP1", "jp"),
    KR("KR", "kr"),
    LAN("LA1", "lan"),
    LAS("LA2", "las"),
    NA("NA1", "na"),
    OCE("OC1", "oce"),
    RU("RU", "ru"),
    TR("TR1", "tr");

    fun getHostApi(): String {
        return "https://ddragon.leagueoflegends.com/api/"
    }

    fun getHostCdn(): String {
        return "https://ddragon.leagueoflegends.com/cdn/"
    }

    fun getHostCdn(locale: Locale, version: String): String {
        return "https://ddragon.leagueoflegends.com/cdn/$version/data/${locale.id}/"
    }

    fun getHostRealms(): String {
        return "https://ddragon.leagueoflegends.com/realms/"
    }

    companion object {

        fun getPlatformById(id: String): Platform {
            for (platform in Platform.values()) {
                if (platform.id.toLowerCase() == id.toLowerCase()) {
                    return platform
                }
            }
            return NA
        }

        fun getPlatformByName(name: String): Platform {
            for (platform in Platform.values()) {
                if (platform.name.toLowerCase() == name.toLowerCase()) {
                    return platform
                }
            }
            return NA
        }
    }
}
