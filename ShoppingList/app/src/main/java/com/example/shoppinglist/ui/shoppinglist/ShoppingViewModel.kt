package com.example.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
): ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

    fun getItemsInList(listId : Int) = repository.getItemsInList(listId)

}