package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModelFactory


class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(ShoppingItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.binding.tvName.text = curShoppingItem.name

        holder.binding.tvAmount.text = "${curShoppingItem.amount}"

        holder.binding.ivDelete.setOnClickListener{
            viewModel.delete(curShoppingItem)
        }

        holder.binding.ivPlus.setOnClickListener{
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.binding.ivMinus.setOnClickListener{
            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(val binding: ShoppingItemBinding): RecyclerView.ViewHolder(binding.root)
}