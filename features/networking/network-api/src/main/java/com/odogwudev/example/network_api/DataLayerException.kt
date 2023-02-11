package com.odogwudev.example.network_api

class DataLayerException(override val message: String?, override val cause: Throwable? = null) :
    RuntimeException(message, cause)