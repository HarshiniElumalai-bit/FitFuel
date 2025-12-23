package com.example.fitfuel

import com.example.fitfuel.ResponseModel.AllRecipe
import com.example.fitfuel.ResponseModel.Recipe
import com.example.fitfuel.network.ApiService

class FakeApiService : ApiService {

    var fakeRecipes: List<Recipe> = emptyList()
    var shouldThrowError = false

    override suspend fun getRecipes(apiKey: String): AllRecipe {
        if (shouldThrowError) throw Exception("Network error")
        return AllRecipe(fakeRecipes)
    }
}
