package br.edu.up.edu.rgm_29318602.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import br.edu.up.edu.rgm_29318602.ui.viewmodels.InventoryViewModel
import br.edu.up.edu.rgm_29318602.ui.navigation.NavigationDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    viewModel: InventoryViewModel,
    navigateBack: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var price by remember { mutableStateOf(TextFieldValue("")) }
    var quantity by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Novo Item") },
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
                        val priceValue = price.text.toDoubleOrNull() ?: 0.0
                        val quantityValue = quantity.text.toIntOrNull() ?: 0
                        viewModel.addItem(name.text, priceValue, quantityValue)
                        navigateBack() // Voltar para a tela inicial
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Salvar Item")
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
        }
    }
}
