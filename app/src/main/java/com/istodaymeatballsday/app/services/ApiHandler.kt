package com.istodaymeatballsday.app.services

import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

object ApiHandler {
    const val apiUrl = "https://api.istodaymeatballsday.com"

    fun getStatus(handler: ApiResponseHandler) {
        val request = Request.Builder().url(apiUrl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                val responseObject = gson.fromJson(body, ResponseObject::class.java)
                handler.onResponse(responseObject.msg)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failure when fetching from API")
            }
        })
    }
}

class ResponseObject(val msg: String, val code: Int)

interface ApiResponseHandler {
    fun onResponse(value: String)
}
