package br.edu.up.edu.rgm_29318602.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.reflect.KParameter

@Database(
    entities = [
        Item::class
    ],
    version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

}

// 4) Abrir o banco de dados
fun abrirBanco(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        name = "inventory_db"
    ).build()
}

