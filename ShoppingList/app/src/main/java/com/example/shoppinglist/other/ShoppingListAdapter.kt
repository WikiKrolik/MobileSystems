package com.example.shoppinglist.ui.shoppinglist

import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.R
import com.google.android.material.internal.ContextUtils
import com.google.android.material.internal.ContextUtils.getActivity


class ShoppingListAdapter(
    var lists : List<ShoppingList>,
    private val viewModel : ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>(){

    inner class ShoppingListViewHolder(listView : View) : RecyclerView.ViewHolder(listView), View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

//        public fun  onBackPressed(){
//            var fm: FragmentManager
//            fm = supportFragmentManager
//            fm.popBackStack()
//
//        }

        override fun onClick(view : View){
            val positionOfRecyclerVieElement = adapterPosition
            val listId = positionOfRecyclerVieElement + 1
            val fragmentTransaction = (itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
            val fragment = ItemsFragment(listId, lists[positionOfRecyclerVieElement].name)
//            fragmentTransaction.setCustomAnimations(
//                R.anim.slide_in_right,
//                R.anim.slide_out_left,
//                R.anim.slide_in_left,
//                R.anim.slide_out_right
//            )

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }


    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list, parent, false)
        return ShoppingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val curList = lists[position]

        holder.itemView.findViewById<TextView>(R.id.tv_ListName).text = curList.name

        holder.itemView.findViewById<ImageView>(R.id.tv_Delete).setOnClickListener {
            viewModel.delete(curList)
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }
}