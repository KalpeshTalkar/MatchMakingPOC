package com.kalpesh.matchmakingpoc.networking

import com.kalpesh.matchmakingpoc.BuildConfig

enum class Domain(val value: String) {
    PRODUCTION("https://randomuser.me/"),
    DEVELOPMENT("https://randomuser.me/")
}

private fun getApiPrefix(): String {
    return (if (BuildConfig.DEBUG) Domain.DEVELOPMENT.value else Domain.PRODUCTION.value) + "/api/"
}

class Endpoint {

    companion object {

        /**
         * Matches endpoint.
         * `?results=10` can also be added as query parameter when using this endpoint.
         * `?results=10` is a part of the endpoint here for simplicity.
         */
        val MATCHES = "${getApiPrefix()}?results=10"
    }
}
