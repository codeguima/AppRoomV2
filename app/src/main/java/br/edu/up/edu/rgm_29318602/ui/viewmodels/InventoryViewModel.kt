package br.edu.up.edu.rgm_29318602.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import br.edu.up.edu.rgm_29318602.data.Item
import br.edu.up.edu.rgm_29318602.data.ItemDao

class InventoryViewModel(private val itemDao: ItemDao) : ViewModel() {

    private val _items = mutableStateListOf<Item>()
    val itemsList: List<Item> get() = _items

    private val _itemDetails = mutableStateOf<Item?>(null)
    val itemDetails: State<Item?> get() = _itemDetails

    private val _itemId = mutableStateOf<Int?>(null)
    val itemId: State<Int?> get() = _itemId

    init {
        getAllItems()
    }

    private fun getAllItems() {
        viewModelScope.launch {
            _items.clear()
            _items.addAll(itemDao.getAllItems())
        }
    }

    fun addItem(name: String, price: Double, quantity: Int) {
        val newItem = Item(name = name, price = price, quantity = quantity)
        viewModelScope.launch {
            itemDao.insertItem(newItem)
            getAllItems()  // Atualiza a lista de itens
        }
    }

    fun updateItem(id: Int, name: String, price: Double, quantity: Int) {
        val updatedItem = Item(id = id, name = name, price = price, quantity = quantity)
        viewModelScope.launch {
            itemDao.updateItem(updatedItem)
            getAllItems()  // Atualiza a lista de itens
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
            getAllItems()  // Atualiza a lista de itens
        }
    }

    // Método para obter os detalhes do item
    fun getItemById(id: Int) {
        viewModelScope.launch {
            val item = itemDao.getItemById(id)
            _itemDetails.value = item // Armazena o item no estado
        }
    }

    fun setItemId(id: Int?) {
        _itemId.value = id
    }
}
