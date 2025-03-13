package com.example.cubtest.data.api

import com.example.cubtest.data.model.AttractionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("Accept: application/json")
    @GET("{language}/Attractions/All")
    suspend fun getAttractions(@Path("language") language: String): Response<AttractionResponse>
}