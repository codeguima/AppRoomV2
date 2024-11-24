package br.edu.up.edu.rgm_29318602.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.edu.rgm_29318602.data.ItemsRepository
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository // Adicionando o repositório para obter dados
) : ViewModel() {

    private val itemId: Int = savedStateHandle["itemId"] ?: throw IllegalStateException("itemId não pode ser nulo")

    var uiState by mutableStateOf(ItemDetailsUiState())
        private set

    init {
        loadItemDetails(itemId)
    }

    private fun loadItemDetails(itemId: Int) {
        viewModelScope.launch {
            itemsRepository.getItemStream(itemId).collect { item ->
                uiState = ItemDetailsUiState(
                    outOfStock = item?.quantity == 0,
                    itemDetails = item?.toItemDetails() ?: ItemDetails()
                )
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)
