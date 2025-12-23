package com.example.fitfuel.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.example.fitfuel.viewmodel.RecipeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitfuel.ui.screens.HomeScreen
import com.example.fitfuel.ui.screens.DetailsScreen

@Composable
fun NavGraph(modifier : Modifier = Modifier){

    val context = LocalContext.current
    val viewModel: RecipeViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(
            context.applicationContext as Application
        )
    )
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "HomeScreen"
    ) {
        composable("HomeScreen") {
            HomeScreen(navController = navController,viewModel = viewModel)
        }

        composable("detailsScreen") {
            DetailsScreen(navController = navController,viewModel = viewModel)
        }
    }
}

