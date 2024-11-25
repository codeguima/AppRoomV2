package br.edu.up.edu.rgm_29318602.ui

import androidx.lifecycle.SavedStateHandle
import br.edu.up.edu.rgm_29318602.ui.item.ItemEntryViewModel
import br.edu.up.edu.rgm_29318602.ui.item.ItemEditViewModel
import br.edu.up.edu.rgm_29318602.ui.item.ItemDetailsViewModel
import br.edu.up.edu.rgm_29318602.ui.home.HomeViewModel
import br.edu.up.edu.rgm_29318602.InventoryApplication

object AppViewModelProvider {
    fun provideHomeViewModel(app: InventoryApplication): HomeViewModel {
        return HomeViewModel(
            itemsRepository = app.container.itemsRepository
        )
    }

    fun provideItemEntryViewModel(app: InventoryApplication): ItemEntryViewModel {
        return ItemEntryViewModel(app.container.itemsRepository)
    }

    fun provideItemEditViewModel(app: InventoryApplication): ItemEditViewModel {
        return ItemEditViewModel(
            itemsRepository = app.container.itemsRepository
        )
    }

    fun provideItemDetailsViewModel(app: InventoryApplication): ItemDetailsViewModel {
        return ItemDetailsViewModel(
            savedStateHandle = SavedStateHandle(), // O savedStateHandle vai ser gerenciado pelo ViewModel
            itemsRepository = app.container.itemsRepository
        )
    }
}
