package br.edu.up.edu.rgm_29318602.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.edu.rgm_29318602.data.Item
import br.edu.up.edu.rgm_29318602.data.ItemsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    // Mudança para StateFlow, que é mais adequado para Jetpack Compose
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            itemsRepository.getAllItemsStream().collect { itemList ->
                _uiState.value = HomeUiState(itemList)
            }
        }
    }
}

data class HomeUiState(val itemList: List<Item> = listOf())
