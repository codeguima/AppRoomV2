package br.edu.up.edu.rgm_29318602.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.edu.rgm_29318602.data.Item
import br.edu.up.edu.rgm_29318602.data.ItemsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            itemsRepository.getAllItemsStream().collect { itemList ->
                uiState = HomeUiState(itemList)
            }
        }
    }
}

data class HomeUiState(val itemList: List<Item> = listOf())
