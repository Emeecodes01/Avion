package com.mobigod.avin.utils.network

import okhttp3.HttpUrl

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 10:07*/
object HttpUrlFactory {

    const val HTTP_SCHEME = "https"

    fun buildHttpUrl(host: String, paths: List<String>, queryParams: Map<String, String>): HttpUrl {
        val httpUrl = HttpUrl.Builder()
        httpUrl.scheme(HTTP_SCHEME)
        httpUrl.host(host)

        for (pathSegment in paths){
            httpUrl.addPathSegment(pathSegment)
        }

        for (entry in queryParams.entries) {
            httpUrl.addEncodedQueryParameter(entry.key, entry.value)
        }

        return httpUrl.build()
    }
}