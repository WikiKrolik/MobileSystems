package com.example.shoppinglist.other

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items : List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.itemView.findViewById<TextView>(R.id.tvName).text = curShoppingItem.name
        holder.itemView.findViewById<TextView>(R.id.tvAmount).text = "${curShoppingItem.amount}"
        holder.itemView.findViewById<CheckBox>(R.id.checkBox).isChecked = curShoppingItem.isBought

        holder.itemView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener{
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivPlus).setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.findViewById<ImageView>(R.id.ivMinus).setOnClickListener{
            if(curShoppingItem.amount > 0)
                curShoppingItem.amount--
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.findViewById<CheckBox>(R.id.checkBox).setOnClickListener {
            curShoppingItem.isBought = !curShoppingItem.isBought
            viewModel.upsert(curShoppingItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}