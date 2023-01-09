package com.example.shoppinglist.Data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @ColumnInfo(name = "list_name")
    var name: String,
    @ColumnInfo(name = "date")
    var date: String
){

    @PrimaryKey(autoGenerate = true)
    var listID : Int? = null
}