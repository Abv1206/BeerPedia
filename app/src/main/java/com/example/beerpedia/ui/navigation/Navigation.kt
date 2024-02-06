package com.example.beerpedia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beerpedia.ui.screens.detail.DetailScreen
import com.example.beerpedia.ui.screens.detail.DetailViewModel
import com.example.beerpedia.ui.screens.main.MainScreen
import com.example.beerpedia.ui.screens.main.MainViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavItem.Main.route) {
        composable(NavItem.Main.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen (viewModel){ beerItem -> navController.navigate(NavItem.Detail.createNavRoute(beerItem.id)) }
        }
        composable(
            route = NavItem.Detail.route,
            arguments = NavItem.Detail.args
        ) {backStackEntry ->
            val viewModel = hiltViewModel<DetailViewModel>()
            val id = backStackEntry.arguments?.getInt(NavArg.BeerId.key)
            requireNotNull(id)
            DetailScreen(onBackClick = { navController.popBackStack() }, viewModel = viewModel)
        }
    }
}