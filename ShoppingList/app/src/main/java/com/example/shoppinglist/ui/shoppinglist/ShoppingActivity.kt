package com.example.shoppinglist.ui.shoppinglist

import com.example.shoppinglist.Data.db.ShoppingDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.Data.repositories.ShoppingListRepository
import com.example.shoppinglist.Data.repositories.ShoppingRepository
import com.example.shoppinglist.R
import com.example.shoppinglist.other.ShoppingItemAdapter
import kotlinx.android.synthetic.main.fragment_lists.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)

        val database = ShoppingDatabase(this)
        val repository = ShoppingListRepository(database)
        val factory = ShoppingListViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ShoppingListViewModel::class.java]
        val adapter = ShoppingListAdapter(listOf(), viewModel)


//       rv_ShoppingLists.layoutManager = LinearLayoutManager(this)
//        rv_ShoppingLists.adapter = adapter
//
//        viewModel.getAllLists().observe(this, Observer{
//            adapter.lists = it
//            adapter.notifyDataSetChanged()
//        })
//
//        fab_addList.setOnClickListener{
//            AddShoppingListDialog(this, object : AddListDialogListener{
//                override fun onAddButtonClicked(list: ShoppingList) {
//                    viewModel.upsert(list)
//                }
//            }).show()
//        }
    }
}