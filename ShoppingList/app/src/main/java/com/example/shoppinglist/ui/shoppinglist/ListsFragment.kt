package com.example.shoppinglist.ui.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.Data.db.ShoppingDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.Data.repositories.ShoppingListRepository
import com.example.shoppinglist.databinding.FragmentListsBinding

class ListsFragment : Fragment() {

    private var _binding: FragmentListsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val database = ShoppingDatabase(requireContext())
        val repository = ShoppingListRepository(database)
        val factory = ShoppingListViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(requireActivity(), factory).get(ShoppingListViewModel::class.java)
        val adapter = ShoppingListAdapter(listOf(), viewModel)

        _binding = FragmentListsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rv = binding.rvShoppingLists
        val fab = binding.fabAddList


        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        viewModel.getAllLists().observe(viewLifecycleOwner) {
            adapter.lists = it
            adapter.notifyDataSetChanged()
        }

        fab.setOnClickListener {
            AddShoppingListDialog(requireContext(),
                object : AddListDialogListener {
                    override fun onAddButtonClicked(list: ShoppingList) {
                        viewModel.upsert(list)
                    }
                }).show()
        }

        return root
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                onBackPressed()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}