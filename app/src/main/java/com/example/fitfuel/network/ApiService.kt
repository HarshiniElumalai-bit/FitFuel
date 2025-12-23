package com.example.fitfuel.network

import com.example.fitfuel.ResponseModel.AllRecipe
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {
    @GET("recipes/random?number=10")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String = "4308b95cbdc14cd29c4f371f154446d2"
    ): AllRecipe
}

val service: ApiService = Retrofit.Builder()
    .baseUrl("https://api.spoonacular.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)

