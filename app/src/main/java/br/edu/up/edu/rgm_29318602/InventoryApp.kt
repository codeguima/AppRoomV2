package br.edu.up.edu.rgm_29318602

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import br.edu.up.edu.rgm_29318602.ui.AppViewModelProvider
import br.edu.up.edu.rgm_29318602.ui.navigation.InventoryNavHost


@Composable
fun InventoryApp(app: InventoryApplication) {
    val navController = rememberNavController() // Criação do NavController

    // Definindo as instâncias dos ViewModels
    val homeViewModel = AppViewModelProvider.provideHomeViewModel(app)
    val itemEntryViewModel = AppViewModelProvider.provideItemEntryViewModel(app)
    val itemEditViewModel = AppViewModelProvider.provideItemEditViewModel(app)
    val itemDetailsViewModel = AppViewModelProvider.provideItemDetailsViewModel(app)

    // Passando os ViewModels para o InventoryNavHost
    InventoryNavHost(
        homeViewModel = homeViewModel,
        itemEntryViewModel = itemEntryViewModel,
        itemEditViewModel = itemEditViewModel,
        itemDetailsViewModel = itemDetailsViewModel,
        navController = navController,
        modifier = Modifier.fillMaxSize() // Modificador para preencher a tela
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        })
}
