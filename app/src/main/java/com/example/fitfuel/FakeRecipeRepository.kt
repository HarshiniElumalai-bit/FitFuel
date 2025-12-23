package com.example.fitfuel

import com.example.fitfuel.Repository.RecipeRepository
import com.example.fitfuel.database.Recipedao
import com.example.fitfuel.model.Recipe
import kotlin.collections.firstOrNull
import kotlin.collections.indexOfFirst

class FakeRecipeDao : Recipedao {

    private val recipes = mutableListOf<Recipe>()

    override suspend fun getAllRecipes(): List<Recipe> = recipes

    override suspend fun getRecipeById(id: Int): Recipe? =
        recipes.firstOrNull { it.id == id }

    override suspend fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        val index = recipes.indexOfFirst { it.id == recipe.id }
        if (index != -1) recipes[index] = recipe
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipes.remove(recipe)
    }

    override suspend fun deleteAllRecipes() {
        recipes.clear()
    }
}
