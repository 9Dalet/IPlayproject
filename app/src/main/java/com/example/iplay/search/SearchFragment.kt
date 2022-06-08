package com.example.iplay.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iplay.R
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.callbackFlow
import java.sql.Timestamp

class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val auth = FirebaseAuth.getInstance()
    private val sports = ArrayList<SportEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        sportsData()
    }

    private fun sportsData() {
        FirebaseFirestore.getInstance().collection("1").get().addOnCompleteListener {
            if(it.isSuccessful) {
                for (document in it.result) {
                    val luogo: String = document.data["luogo"].toString()
                    val numPersone: String = document.data["numPersone"].toString()
                    val data: String = document.data["data"].toString()
                    val ora: String = document.data["ora"].toString()
                    val prezzo: String = document.data["prezzo"].toString()
                    val sport: String = document.data["sport"].toString()
                    addSports(luogo, numPersone, data, ora, prezzo, sport)
                }
                loadRecyclerView()
            }
        }
    }

    private fun addSports(luogo: String, numPersone: String, oraData: String, prezzo: String, ora: String, sport: String) {
        val newAddSports = SportEvent(luogo, numPersone, oraData, ora, prezzo, sport)
        sports.add(newAddSports)
    }

    private fun loadRecyclerView() {
        recyclerView.adapter = Adapter(sports, requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}