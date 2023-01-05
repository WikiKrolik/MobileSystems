package com.example.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.Data.repositories.ShoppingListRepository

class ShoppingListViewModelFactory(
    private val repository: ShoppingListRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        return ShoppingListViewModel(repository) as T
    }
}