package br.edu.up.edu.rgm_29318602

import android.app.Application
import br.edu.up.edu.rgm_29318602.data.AppContainer
import br.edu.up.edu.rgm_29318602.data.AppDataContainer


class InventoryApplication : Application() {


    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
