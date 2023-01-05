package com.example.shoppinglist.ui.shoppinglist

import com.example.shoppinglist.Data.db.entities.ShoppingList

interface AddListDialogListener {
    fun onAddButtonClicked(list : ShoppingList)
}