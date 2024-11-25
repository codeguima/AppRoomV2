package br.edu.up.edu.rgm_29318602

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.up.edu.rgm_29318602.data.AppDatabase
import br.edu.up.edu.rgm_29318602.data.abrirBanco
import br.edu.up.edu.rgm_29318602.ui.navigation.InventoryNavHost
import br.edu.up.edu.rgm_29318602.ui.viewmodels.InventoryViewModel


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = abrirBanco(this)

        val inventoryViewModel = InventoryViewModel(db.itemDao())

        setContent {


            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    InventoryNavHost(inventoryViewModel)
                }
            }
        }
    }
}

