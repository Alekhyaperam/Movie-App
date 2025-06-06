package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.details.DetailScreen
import com.example.movieapp.ui.home.HomeScreen



@Composable
fun WatchAppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToDetails = { contentId, contentType ->
                    navController.navigate(Screen.Details.createRoute(contentId, contentType))
                }
            )
        }

        composable(
            route = Screen.Details.route,  // Now uses "details/{contentId}/{contentType}"
            arguments = listOf(
                navArgument("contentId") { type = NavType.StringType },
                navArgument("contentType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailScreen(
                contentId = backStackEntry.arguments?.getString("contentId") ?: "",
                contentType = backStackEntry.arguments?.getString("contentType") ?: "movie",
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDetails = { id, type ->
                    navController.navigate(Screen.Details.createRoute(id, type))
                }
            )
        }
    }
}


//navController controls screen changes.
//rememberNavController() provides the navigation state.
//NavHost sets up the navigation graph.

