package com.example.fitfuel.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitfuel.viewmodel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: RecipeViewModel) {

    val state by viewModel.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.fetchRecipes() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                SnagScreen(
                    message = state.errorMessage ?: "Error",
                    buttonText = "Retry",
                    onRetry = { viewModel.fetchRecipes() }
                )
            }

            state.recipes.isNotEmpty() -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.recipes.size) { index ->
                        val recipe = state.recipes[index]
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    viewModel.selectRecipe(recipe)
                                    navController.navigate("detailsScreen")
                                }
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = recipe.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            else -> {
                SnagScreen(
                    message = "No recipes available",
                    buttonText = "Refresh",
                    onRetry = { viewModel.fetchRecipes() }
                )
            }
        }

        PullRefreshIndicator(
            refreshing = state.isLoading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}


@Composable
fun SnagScreen(
    message: String,
    buttonText: String = "Retry",
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(32.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
            Text(buttonText)
        }
    }
}