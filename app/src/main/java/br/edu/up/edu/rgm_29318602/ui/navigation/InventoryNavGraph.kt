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
import br.edu.up.edu.rgm_29318602.ui.item.ItemDetailsViewModel
import br.edu.up.edu.rgm_29318602.ui.item.ItemEditViewModel
import br.edu.up.edu.rgm_29318602.ui.item.ItemEntryViewModel
import br.edu.up.edu.rgm_29318602.ui.home.HomeViewModel

@Composable
fun InventoryNavHost(
    homeViewModel: HomeViewModel,
    itemEntryViewModel: ItemEntryViewModel,
    itemEditViewModel: ItemEditViewModel,
    itemDetailsViewModel: ItemDetailsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDestination.HomeScreen,
        modifier = modifier
    ) {
        // HomeScreen
        composable(route = NavigationDestination.HomeScreen) {
            HomeScreen(
                viewModel = homeViewModel,
                navigateToItemEntry = { navController.navigate(NavigationDestination.ItemEntryScreen) },
                navigateToItemUpdate = { id ->
                    navController.navigate("${NavigationDestination.ItemEditScreen}/$id")
                }
            )
        }

        // ItemEntryScreen
        composable(route = NavigationDestination.ItemEntryScreen) {
            ItemEntryScreen(
                viewModel = itemEntryViewModel,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        // ItemDetailsScreen
        // ItemDetailsScreen
        composable(
            route = "${NavigationDestination.ItemDetailsScreen}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            val itemId = it.arguments?.getInt("itemId") ?: run {
                navController.popBackStack()
                return@composable
            }
            ItemDetailsScreen(
                id = itemId,
                navController = navController, // Adicionando navController como argumento
                navigateBack = { navController.popBackStack() },
                modifier = modifier
            )
        }



        // ItemEditScreen
        composable(
            route = "${NavigationDestination.ItemEditScreen}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            val itemId = it.arguments?.getInt("itemId") ?: run {
                navController.popBackStack()
                return@composable
            }
            ItemEditScreen(
                viewModel = itemEditViewModel,
                itemId,
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
