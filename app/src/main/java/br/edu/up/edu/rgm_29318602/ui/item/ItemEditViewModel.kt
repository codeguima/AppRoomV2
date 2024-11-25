package br.edu.up.edu.rgm_29318602.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.edu.rgm_29318602.data.Item
import br.edu.up.edu.rgm_29318602.data.ItemsRepository
import kotlinx.coroutines.launch

class ItemEditViewModel(
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Carrega os dados do item com base no ID.
     */
    fun loadItem(id: Int) {
        viewModelScope.launch {
            itemsRepository.getItemStream(id).collect { item ->
                item?.let {
                    itemUiState = itemUiState.copy(
                        itemDetails = it.toItemDetails()
                    )
                }
            }
        }
    }


    /**
     * Atualiza o estado do formulário de entrada.
     */
    fun updateUiState(newDetails: ItemDetails) {
        itemUiState = itemUiState.copy(itemDetails = newDetails)
    }

    /**
     * Salva as alterações feitas no item.
     */
    fun updateItem(id: Int) {
        viewModelScope.launch {
            val updatedItem = itemUiState.itemDetails.toItem()
            itemsRepository.updateItem(updatedItem)
        }
    }

}


data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: throw IllegalArgumentException("Preço inválido"),
    quantity = quantity.toIntOrNull() ?: throw IllegalArgumentException("Quantidade inválida")
)


fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
