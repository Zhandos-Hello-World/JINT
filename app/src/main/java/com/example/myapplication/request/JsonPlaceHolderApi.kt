package com.example.myapplication.request

import retrofit2.Response
import retrofit2.http.GET


interface JsonPlaceHolderApi {
//    @GET("/daily_json.js")
//    suspend fun getAllValuteCall(): Call<ResponseData>


    @GET("/daily_json.js")
    suspend fun getAllValuteResponse(): Response<ResponseData>
}