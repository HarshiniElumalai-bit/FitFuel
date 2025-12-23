package com.example.fitfuel.common

import com.example.fitfuel.model.Recipe

data class RecipeState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val selectedRecipe: Recipe? = null,
    val errorMessage: String? = null
)

sealed class RecipeAction {

    object FetchRecipes : RecipeAction()

    data class FetchSuccess(val recipes: List<Recipe>) : RecipeAction()

    data class FetchFailure(val message: String) : RecipeAction()

    data class SelectRecipe(val recipe: Recipe) : RecipeAction()
}


fun recipeReducer(oldState: RecipeState, action: RecipeAction): RecipeState {

    return when (action) {

        is RecipeAction.FetchRecipes -> {
            oldState.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        is RecipeAction.FetchSuccess -> {
            oldState.copy(
                isLoading = false,
                recipes = action.recipes,
                errorMessage = null
            )
        }

        is RecipeAction.FetchFailure -> {
            oldState.copy(
                isLoading = false,
                errorMessage = action.message
            )
        }

        is RecipeAction.SelectRecipe -> {
            oldState.copy(
                selectedRecipe = action.recipe
            )
        }
    }
}