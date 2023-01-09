package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.R
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class AddShoppingListDialog(context : Context, var addListDialogListener : AddListDialogListener) : AppCompatDialog(context){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_list)

        findViewById<TextView>(R.id.tvAdd)?.setOnClickListener{
            val name = findViewById<EditText>(R.id.etName)?.text.toString()

            if(name.isEmpty()){
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val currentDate: String =
                SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

            val list = ShoppingList(name, currentDate)
            addListDialogListener.onAddButtonClicked(list)
            dismiss()
        }

        findViewById<TextView>(R.id.tvCancel)?.setOnClickListener {
            cancel()
        }
    }
}