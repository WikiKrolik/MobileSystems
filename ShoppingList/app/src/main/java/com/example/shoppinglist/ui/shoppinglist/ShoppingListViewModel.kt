package com.example.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.Data.repositories.ShoppingListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(
    private val repository: ShoppingListRepository
) : ViewModel() {

    fun upsert(list: ShoppingList) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(list)
    }

    fun delete(list: ShoppingList) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(list)
    }

    fun getAllLists() = repository.getAllLists()
}