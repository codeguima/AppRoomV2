package br.edu.up.edu.rgm_29318602.ui.item

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.up.edu.rgm_29318602.InventoryTopAppBar
import br.edu.up.edu.rgm_29318602.R
import br.edu.up.edu.rgm_29318602.ui.AppViewModelProvider
import br.edu.up.edu.rgm_29318602.ui.theme.InventoryTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    viewModel: ItemEditViewModel,
    itemId: Int,
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit
) {
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
            itemUiState = viewModel.itemUiState,
            onItemValueChange = { },
            onSaveClick = { },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

