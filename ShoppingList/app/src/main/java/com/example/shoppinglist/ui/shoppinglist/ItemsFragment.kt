package com.example.shoppinglist.ui.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.Data.db.ShoppingDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingItem
import com.example.shoppinglist.Data.repositories.ShoppingRepository
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.example.shoppinglist.databinding.FragmentItemsBinding


class ItemsFragment(listId : Int, listName : String) : Fragment() {

    private var _binding: FragmentItemsBinding? = null

    private val binding get() = _binding!!

    private var listID = listId
    private var listName = listName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val database = ShoppingDatabase(requireContext())
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(requireActivity(), factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = listName
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val rv = binding.rvShoppingItems
        val fab = binding.fab

        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        viewModel.getItemsInList(listID).observe(viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(listID, requireContext(),
                object : AddDialogListener{
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }

        return root
    }

    override fun onPause() {
        super.onPause()
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = "Lists"
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}