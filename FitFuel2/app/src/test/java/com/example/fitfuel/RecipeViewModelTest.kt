package com.example.fitfuel

import com.example.fitfuel.Repository.RecipeRepository
import com.example.fitfuel.viewmodel.RecipeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    private lateinit var viewModel: RecipeViewModel
    private lateinit var repository: RecipeRepository
    private lateinit var fakeService: FakeApiService

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        val fakeDao = FakeRecipeDao()
        repository = RecipeRepository(fakeDao)
        fakeService = FakeApiService()
        viewModel = RecipeViewModel(fakeService, false)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchRecipes_success_updatesState() = runTest {
        viewModel.fetchRecipes()
        advanceUntilIdle()

        val state = viewModel.state.value

        assertFalse(state.isLoading)
        assertTrue(state.recipes.isNotEmpty())
    }
}
