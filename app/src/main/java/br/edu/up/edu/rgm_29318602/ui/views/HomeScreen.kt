package br.edu.up.edu.rgm_29318602.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.edu.up.edu.rgm_29318602.R
import br.edu.up.edu.rgm_29318602.ui.viewmodels.InventoryViewModel
import br.edu.up.edu.rgm_29318602.data.Item
import br.edu.up.edu.rgm_29318602.ui.navigation.NavigationDestination


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: InventoryViewModel,
    navController: NavController
) {
    val items = viewModel.itemsList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventário") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavigationDestination.ItemEntryScreen)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Item"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items) { item ->
                    ItemCard(item = item, navigateToItemEdit = { id ->
                        navController.navigate("itemEdit/$id")
                    })
                }
            }
        }
    }
}

@Composable
fun ItemCard(item: Item, navigateToItemEdit: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${item.price}€",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = "Quantidade: ${item.quantity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { navigateToItemEdit(item.id) }) {
                Text("Editar")
            }
        }
    }
}
