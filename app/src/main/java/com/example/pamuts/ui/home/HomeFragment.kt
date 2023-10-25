package com.example.pamuts.ui.home

import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pamuts.R
import kotlin.collections.ArrayList
import java.util.*

private const val t10 = "t1"
private const val t20 = "t2"

class HomeFragment : Fragment() {
    private var t1: String? = null

    private lateinit var adapter : MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var skillArrayList : ArrayList<User>
    private lateinit var imageId : TypedArray
    private lateinit var heading : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            t1 = it.getString(t10)
            t1 = it.getString(t20)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        dataInitialize()
        adapter = MyAdapter(skillArrayList)
        recyclerView.adapter = adapter
        searchView = view.findViewById(R.id.search_action)
        adapter.onItemClick = {
            navigateToDetail(it.heading)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
                Handler(Looper.getMainLooper()).postDelayed({
                    filterList(newText)
                }, 1250)

                return false
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        t1 = null
    }

    private fun navigateToDetail(extraName: String){
        findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavUserDetail(extraName))
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<User>()
            for (i in skillArrayList) {
                if (i.heading.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun getUserData() {

        for (i in 0..<imageId.length()) {
            val skill = User(imageId.getResourceId(i,0), heading[i])
            skillArrayList.add(skill)
        }

    }

    private fun dataInitialize(){
        skillArrayList = arrayListOf<User>()
        imageId = resources.obtainTypedArray(R.array.integer_skill_array)
        heading = resources.getStringArray(R.array.string_user_array)
        getUserData()
        imageId.recycle()
    }

}