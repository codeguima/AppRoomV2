package br.edu.up.edu.rgm_29318602.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import br.edu.up.edu.rgm_29318602.ui.viewmodels.InventoryViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    viewModel: InventoryViewModel,
    itemId: Int,
    navigateBack: () -> Unit
) {
    // Carregar detalhes do item
    val item by remember { mutableStateOf(viewModel.itemDetails.value) }

    LaunchedEffect(itemId) {
        viewModel.getItemById(itemId)
    }

    item?.let { item ->
        var name by remember { mutableStateOf(TextFieldValue(item.name)) }
        var price by remember { mutableStateOf(TextFieldValue(item.price.toString())) }
        var quantity by remember { mutableStateOf(TextFieldValue(item.quantity.toString())) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Editar Item") },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (name.text.isNotBlank() && price.text.isNotBlank() && quantity.text.isNotBlank()) {
                            val priceValue = price.text.toDoubleOrNull() ?: item.price
                            val quantityValue = quantity.text.toIntOrNull() ?: item.quantity
                            viewModel.updateItem(item.id, name.text, priceValue, quantityValue)
                            navigateBack() // Voltar para a tela inicial
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Salvar Alterações")
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome do Item") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Preço") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantidade") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        viewModel.deleteItem(item)
                        navigateBack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Excluir Item")
                }
            }
        }
    }
}

