package com.example.fitfuel

import com.example.fitfuel.common.*
import com.example.fitfuel.model.Recipe
import org.junit.Assert.*
import org.junit.Test

class RecipeReducerTest {

    private val emptyState = RecipeState()

    @Test
    fun `FetchRecipes sets loading true`() {
        val newState = recipeReducer(emptyState, RecipeAction.FetchRecipes)

        assertTrue(newState.isLoading)
        assertNull(newState.errorMessage)
    }

    @Test
    fun `FetchSuccess sets recipes and loading false`() {
        val recipes = listOf(
            Recipe(1, "Test", emptyList(), emptyList(), "url")
        )

        val newState = recipeReducer(emptyState, RecipeAction.FetchSuccess(recipes))

        assertFalse(newState.isLoading)
        assertEquals(1, newState.recipes.size)
    }

    @Test
    fun `FetchFailure sets error message and loading false`() {
        val newState = recipeReducer(emptyState, RecipeAction.FetchFailure("Error"))

        assertFalse(newState.isLoading)
        assertEquals("Error", newState.errorMessage)
    }

    @Test
    fun `SelectRecipe updates selectedRecipe`() {
        val recipe = Recipe(1, "Test", emptyList(), emptyList(), "url")

        val newState = recipeReducer(emptyState, RecipeAction.SelectRecipe(recipe))

        assertEquals(recipe, newState.selectedRecipe)
    }
}
