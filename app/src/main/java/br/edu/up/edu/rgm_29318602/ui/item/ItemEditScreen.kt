package br.edu.up.edu.rgm_29318602.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.edu.up.edu.rgm_29318602.InventoryTopAppBar
import br.edu.up.edu.rgm_29318602.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    viewModel: ItemEditViewModel,
    id: Int,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit
) {
    LaunchedEffect(id) {
        viewModel.loadItem(id) // Carrega os dados do item
    }

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(R.string.edit_item_title),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = Modifier
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState, // Estado do item
            onItemValueChange = viewModel::updateUiState, // Atualiza o estado
            onSaveClick = {
                viewModel.updateItem(id) // Salva as alterações
                navigateBack() // Volta para a tela anterior
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}


