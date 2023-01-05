package com.example.shoppinglist2.Data.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.db.entities.ShoppingList


data class ListWithItems(
    @Embedded val list : ShoppingList,
    @Relation(
        parentColumn = "listID",
        entityColumn = "list_id"
    )
    val items : List<ShoppingItem>
)