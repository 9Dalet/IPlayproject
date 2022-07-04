package com.example.iplay.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iplay.DetailActivity.DeatilActivity
import com.example.iplay.R
import com.google.android.gms.location.DetectedActivity
import com.google.firebase.firestore.FirebaseFirestore


class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val sports = ArrayList<SportEvent>()
    private lateinit var image: ImageView

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
        image = view.findViewById(R.id.imageSport)

        sportsData()
    }

    private fun sportsData() {
        FirebaseFirestore.getInstance().collection("1").get().addOnCompleteListener {
                for (document in it.result) {
                    val luogo: String = document.data["luogo"].toString()
                    val numPersone: String = document.data["numPersone"].toString()
                    val data: String = document.data["data"].toString()
                    val ora: String = document.data["ora"].toString()
                    val prezzo: String = document.data["prezzo"].toString()
                    val sport: String = document.data["sport"].toString()
                    val newImage = document.data["image"].toString()
                    //Toast.makeText(requireContext(),document.id,Toast.LENGTH_SHORT).show()

                    //passaggio di id del documento da firebase
                    val idDoc = document.id

                    addSports(luogo, numPersone, data, ora, prezzo, sport, newImage,idDoc)
                    loadRecyclerView()
            }
        }
    }

    private fun addSports(luogo: String, numPersone: String, oraData: String, prezzo: String, ora: String, sport: String, image: String, idDoc:String) {
        val newAddSports = SportEvent(luogo, numPersone, oraData, ora, prezzo, sport, image, idDoc)
        sports.add(newAddSports)
    }

    private fun loadRecyclerView() {
        val adapter = Adapter(sports, requireActivity())
        adapter.setOnCallback(object : Adapter.AdapterCallback{
            override fun selectItem(idDoc: String) {

                    //passaggio di parametro di idDoc al detailActivity
                    val intent = Intent(this@SearchFragment .requireContext(), DeatilActivity::class.java)
                    intent.putExtra("idDoc",idDoc)
                    startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }
}