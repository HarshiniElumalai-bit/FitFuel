package com.example.fitfuel.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.fitfuel.common.RecipeAction
import com.example.fitfuel.common.RecipeState
import com.example.fitfuel.Repository.RecipeRepository
import com.example.fitfuel.database.RecipeDatabase

import com.example.fitfuel.model.Recipe
import com.example.fitfuel.network.service
import com.example.fitfuel.common.recipeReducer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RecipeRepository(
        recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
    )

    private val _state = MutableStateFlow(RecipeState())
    val state: StateFlow<RecipeState> = _state

    private fun dispatch(action: RecipeAction) {
        _state.value = recipeReducer(_state.value, action)
    }

    fun fetchRecipes() {
        dispatch(RecipeAction.FetchRecipes)

        viewModelScope.launch {
            try {
                val result = service.getRecipes()

                val recipeList = result.recipes.map { api ->
                    Recipe(
                        id = api.id?.toInt() ?: 0,
                        name = api.title.toString(),
                        instructions = api.analyzedInstructions ?: emptyList(),
                        ingredients = api.extendedIngredients ?: emptyList(),
                        youtubeLink = api.sourceUrl.toString()
                    )
                }

                repository.deleteAllRecipes()
                recipeList.forEach { repository.addRecipe(it) }

                dispatch(RecipeAction.FetchSuccess(recipeList))

            } catch (e: Exception) {
                dispatch(RecipeAction.FetchFailure(e.message ?: "Unknown error"))
            }
        }
    }

    fun selectRecipe(recipe: Recipe) {
        dispatch(RecipeAction.SelectRecipe(recipe))
    }

    init {
        fetchRecipes()
    }
}
