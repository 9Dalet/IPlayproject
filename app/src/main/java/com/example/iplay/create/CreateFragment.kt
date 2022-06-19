package com.example.iplay.create

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.iplay.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateFragment : Fragment() {
    private lateinit var eventEditText: EditText
    private lateinit var whenEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var numPersonEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var loadingTextView: TextView
    private lateinit var okButton: Button
    private lateinit var createButton: Button
    private lateinit var eventString: String
    private lateinit var whenString: String
    private lateinit var locationString: String
    private lateinit var timeString: String
    private lateinit var numPersonString: String
    private lateinit var priceString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton = view.findViewById(R.id.createButton)
        eventEditText = view.findViewById(R.id.eventEditText)
        whenEditText = view.findViewById(R.id.editTextDate)
        locationEditText = view.findViewById(R.id.locationEditText)
        timeEditText = view.findViewById(R.id.editTextTime)
        numPersonEditText = view.findViewById(R.id.numPersoneEditText)
        priceEditText = view.findViewById(R.id.priceEditText)
        progressBar = view.findViewById(R.id.progressBar)
        loadingTextView = view.findViewById(R.id.loadingTextView)
        okButton = view.findViewById(R.id.okButton)
        eventString = ""
        whenString = ""
        locationString = ""
        timeString = ""
        numPersonString = ""
        priceString = ""

        createButton.setOnClickListener {
            if (eventEditText.text.isNotEmpty() && whenEditText.text.isNotEmpty() && locationEditText.text.isNotEmpty() && timeEditText.text.isNotEmpty() && numPersonEditText.text.isNotEmpty() && priceEditText.text.isNotEmpty()) {
                saveEventData()
            } else {
                Toast.makeText(activity, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }

    private fun saveEventData() {
        progressBar.visibility = View.VISIBLE
        loadingTextView = "Loading"
        loadingTextView.visibility = View.VISIBLE

        val db = Firebase.firestore
        eventString = eventEditText.text.toString()
        whenString = whenEditText.text.toString()
        locationString = locationEditText.text.toString()
        timeString = timeEditText.text.toString()
        numPersonString = numPersonEditText.text.toString()
        priceString = priceEditText.text.toString()

        val eventData = hashMapOf(
            "data" to whenString,
            "luogo" to locationString,
            "numPersone" to numPersonString,
            "ora" to timeString,
            "prezzo" to priceString,
            "sport" to eventString
        )

        db.collection("1")
            .add(eventData)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                progressBar.visibility = View.GONE
                Log.w(TAG, "Error adding document", e)
            }
        }
}