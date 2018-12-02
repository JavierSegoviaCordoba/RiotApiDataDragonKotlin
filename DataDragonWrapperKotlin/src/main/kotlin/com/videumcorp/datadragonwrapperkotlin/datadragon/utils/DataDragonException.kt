package com.videumcorp.datadragonwrapperkotlin.datadragon.utils

class DataDragonException(error: ErrorCode) : Exception("${error.code}: ${error.message}")