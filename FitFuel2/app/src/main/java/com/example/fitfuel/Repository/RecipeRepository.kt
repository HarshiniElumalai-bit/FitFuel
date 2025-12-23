package com.example.fitfuel.Repository
import com.example.fitfuel.database.Recipedao
import com.example.fitfuel.model.Recipe
import com.example.fitfuel.network.ApiService

class RecipeRepository(
    private val recipeDao: Recipedao,

) {
    suspend fun getAllRecipes(): List<Recipe> {
        return recipeDao.getAllRecipes()

    }
    suspend fun getRecipeById(id: Int): Recipe? {
        return recipeDao.getRecipeById(id)
    }
    suspend fun addRecipe(recipe: Recipe) {
        recipeDao.addRecipe(recipe)
    }
    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
    }
    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }
    suspend fun deleteAllRecipes() {
        recipeDao.deleteAllRecipes()
    }

}