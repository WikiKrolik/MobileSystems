package com.example.shoppinglist.ui.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.Data.db.ShoppingDatabase
import com.example.shoppinglist.Data.db.entities.ShoppingList
import com.example.shoppinglist.Data.repositories.ShoppingListRepository
import com.example.shoppinglist.databinding.FragmentListsBinding
import java.text.SimpleDateFormat
import java.util.*

class ListsFragment : Fragment() {

    private val REQUEST_CODE_SPEECH_INPUT = 100
    private var _binding: FragmentListsBinding? = null
    private var fragLists : List<ShoppingList> = listOf()

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
        val fabVoice = binding.voiceBtn
        val root: View = binding.root

        fabVoice.setOnClickListener{
            speak()
        }

        val rv = binding.rvShoppingLists
        val fab = binding.fabAddList


        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        viewModel.getAllLists().observe(viewLifecycleOwner) {
            adapter.lists = it
            adapter.notifyDataSetChanged()
        }

        viewModel.getAllLists().observe(viewLifecycleOwner) {
            fragLists = it
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

    private fun speak() {
        val mIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something")
        startActivityForResult(mIntent, REQUEST_CODE_SPEECH_INPUT)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        val database = ShoppingDatabase(requireContext())
        val repository = ShoppingListRepository(database)
        val factory = ShoppingListViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(requireActivity(), factory).get(ShoppingListViewModel::class.java)
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data){
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    var resultString = result.toString().replace("[", "")
                    resultString = resultString.replace("]", "")
                    //Toast.makeText(requireContext(), resultString, Toast.LENGTH_SHORT).show()
                    val words = resultString.split(" ", limit = 2)
                    if (words.size < 2) {
                        Toast.makeText(requireContext(), "Say a valid command", Toast.LENGTH_SHORT).show()
                        return
                    }
                    val action = words[0]
                    val name = words[1]
                    val currentDate: String =
                        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())


                    if (action == "add") {
                        var list = ShoppingList(name, currentDate)
                        viewModel.upsert(list)
                    } else if (action == "delete") {
                        println(fragLists.get(0))
                        for(i: ShoppingList in fragLists){
                            if (i.name == name){
                                viewModel.delete(i)
                                break;
                            }
                        }
                    }
                    else{
                        Toast.makeText(requireContext(), "say a valid command", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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


