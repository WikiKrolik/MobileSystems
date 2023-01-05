package com.example.shoppinglist.Data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist2.Data.db.entities.ListWithItems

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: ShoppingList)

    @Delete
    suspend fun delete(list: ShoppingList)

    @Transaction
    @Query("SELECT * FROM shopping_lists")
    fun getAllLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists WHERE listID = :listID")
    fun getListWithItems(listID : Int): List<ListWithItems>
}