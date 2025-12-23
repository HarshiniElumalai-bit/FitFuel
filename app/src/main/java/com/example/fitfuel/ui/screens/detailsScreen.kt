package com.example.fitfuel.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fitfuel.viewmodel.RecipeViewModel

@Composable
fun DetailsScreen(navController: NavHostController, viewModel: RecipeViewModel) {

    val state by viewModel.state.collectAsState()
    val recipe = state.selectedRecipe

    if (recipe == null) {
        Text("No recipe selected")
        return
    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        item {

            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {


                    Text(text = recipe.name)

                    Spacer(Modifier.height(12.dp))

                    Text(text = "Ingredients:")
                    recipe.ingredients.forEach {
                        Text(text = "- ${it.original}")
                    }

                    Spacer(Modifier.height(12.dp))


                    Text(text = "Instructions:")
                    recipe.instructions.forEach { instruction ->
                        instruction.steps.forEach { step ->
                            Text(text = "${step.number}. ${step.step}")
                        }
                    }
                }
            }
        }
    }
}
