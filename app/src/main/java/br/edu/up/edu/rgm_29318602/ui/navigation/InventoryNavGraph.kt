package br.edu.up.edu.rgm_29318602.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.edu.rgm_29318602.ui.screens.HomeScreen
import br.edu.up.edu.rgm_29318602.ui.viewmodels.InventoryViewModel
import br.edu.up.edu.rgm_29318602.ui.views.ItemEditScreen
import br.edu.up.edu.rgm_29318602.ui.views.ItemEntryScreen


@Composable
fun InventoryNavHost(
    viewModel: InventoryViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationDestination.HomeScreen,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(NavigationDestination.HomeScreen) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        // Rota para a tela de criação de itens
        composable(NavigationDestination.ItemEntryScreen) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                viewModel = viewModel,
            )
        }

        // Rota para a edição de itens com ID como argumento
        composable("itemEdit/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toInt()
            itemId?.let {
                ItemEditScreen(
                    viewModel = viewModel,
                    itemId = it,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    }

}
