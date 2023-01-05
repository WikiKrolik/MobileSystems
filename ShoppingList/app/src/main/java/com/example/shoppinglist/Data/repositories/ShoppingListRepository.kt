package com.example.shoppinglist.Data.repositories

import com.example.shoppinglist.Data.db.ShoppingDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingList

class ShoppingListRepository (
    private val db: ShoppingDatabase
) {
    suspend fun upsert(list : ShoppingList) = db.getShoppingListDao().upsert(list)

    suspend fun delete(list: ShoppingList) = db.getShoppingListDao().delete(list)

    fun getAllLists() = db.getShoppingListDao().getAllLists()
}
