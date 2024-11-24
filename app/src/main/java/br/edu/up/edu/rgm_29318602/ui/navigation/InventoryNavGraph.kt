package br.edu.up.edu.rgm_29318602.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.edu.up.edu.rgm_29318602.ui.item.ItemDetailsScreen
import br.edu.up.edu.rgm_29318602.ui.item.ItemEditScreen
import br.edu.up.edu.rgm_29318602.ui.item.ItemEntryScreen
import br.edu.up.edu.rgm_29318602.ui.home.HomeScreen


@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = NavigationDestination.HomeScreen, modifier = modifier
    ) {
        composable(route = NavigationDestination.HomeScreen) {
            HomeScreen(navigateToItemEntry = { navController.navigate(NavigationDestination.ItemEntryScreen) },
                navigateToItemUpdate = {
                    navController.navigate("${NavigationDestination.ItemEntryScreen}/${it}")
                })
        }
        composable(route = NavigationDestination.ItemEntryScreen) {
            ItemEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = NavigationDestination.ItemDetailsScreen,
            arguments = listOf(navArgument(NavigationDestination.ItemDetailsScreen) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem =
                {
                    navController.navigate("${NavigationDestination.ItemEditScreen}/$it")
                },
                navigateBack = { navController.navigateUp() })
        }
        composable(
            route = NavigationDestination.ItemEditScreen,
            arguments = listOf(navArgument(NavigationDestination.ItemEditScreen) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}
