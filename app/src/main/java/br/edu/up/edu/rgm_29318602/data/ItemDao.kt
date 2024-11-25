package br.edu.up.edu.rgm_29318602.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface ItemDao {

    @Insert
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItemById(id: Int): Item
}
