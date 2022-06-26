package com.example.iplay.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.core.state.Registry
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.MediaStoreFileLoader
import com.bumptech.glide.module.AppGlideModule
import com.example.iplay.R
import com.example.iplay.detailFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.InputStream
import java.lang.System.load


class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var storage: FirebaseStorage
    private val sports = ArrayList<SportEvent>()
    private lateinit var image: AppCompatImageView

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
        recyclerView.setOnClickListener {
            loadFragment(detailFragment())

        }
    }

    private fun loadFragment(fragment: Fragment){
        val supportFragmentManager = parentFragmentManager
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.nav_fragment, fragment)
        transaction.commit()
    }

    private fun sportsData() {
        storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference

        FirebaseFirestore.getInstance().collection("1").get().addOnCompleteListener {
            if(it.isSuccessful) {
                for (document in it.result) {
                    val luogo: String = document.data["luogo"].toString()
                    val numPersone: String = document.data["numPersone"].toString()
                    val data: String = document.data["data"].toString()
                    val ora: String = document.data["ora"].toString()
                    val prezzo: String = document.data["prezzo"].toString()
                    val sport: String = document.data["sport"].toString()
                    // Create a reference with an initial file path and name
                    val imageurl: StorageReference = storageRef.child("images/corsofrancia.png")

                        addSports(luogo, numPersone, data, ora, prezzo, sport, imageurl)
                }
                loadRecyclerView()
            }
        }
    }

    private fun addSports(luogo: String, numPersone: String, oraData: String, prezzo: String, ora: String, sport: String, image: StorageReference) {
        val newAddSports = SportEvent(luogo, numPersone, oraData, ora, prezzo, sport, image)
        sports.add(newAddSports)
    }

    private fun loadRecyclerView() {
        recyclerView.adapter = Adapter(sports, requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }
}