package com.example.shoppinglist.Data.db

import android.content.Context
import androidx.room.*
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.db.entities.ShoppingList

@Database(
    entities = [ShoppingItem::class, ShoppingList:: class],
    version = 2
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao
    abstract fun getShoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").fallbackToDestructiveMigration()
                .build()
    }
}