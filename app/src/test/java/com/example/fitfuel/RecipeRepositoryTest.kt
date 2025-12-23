package com.example.fitfuel

import com.example.fitfuel.Repository.RecipeRepository
import com.example.fitfuel.model.Recipe
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RecipeRepositoryTest {

    private lateinit var fakeDao: FakeRecipeDao
    private lateinit var repository: RecipeRepository

    @Before
    fun setup() {
        fakeDao = FakeRecipeDao()
        repository = RecipeRepository(fakeDao)
    }

    @Test
    fun `addRecipe stores item in DAO`() = runBlocking {
        val recipe = Recipe(1, "Test", emptyList(), emptyList(), "url")

        repository.addRecipe(recipe)
        val all = repository.getAllRecipes()

        assertEquals(1, all.size)
        assertEquals("Test", all[0].name)
    }

    @Test
    fun `deleteRecipe removes item`() = runBlocking {
        val recipe = Recipe(2, "DeleteMe", emptyList(), emptyList(), "url")

        repository.addRecipe(recipe)
        repository.deleteRecipe(recipe)
        val all = repository.getAllRecipes()

        assertTrue(all.isEmpty())
    }

    @Test
    fun `getRecipeById returns correct item`() = runBlocking {
        val recipe = Recipe(5, "FindMe", emptyList(), emptyList(), "url")

        repository.addRecipe(recipe)
        val result = repository.getRecipeById(5)

        assertNotNull(result)
        assertEquals("FindMe", result?.name)
    }
}
