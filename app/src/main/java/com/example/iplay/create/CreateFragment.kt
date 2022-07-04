package com.example.iplay.create

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import com.example.iplay.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CreateFragment : Fragment() {
    private lateinit var spinnerSports: Spinner
    private lateinit var whenEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var numPersonEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var createButton: Button
    private lateinit var eventString: String
    private lateinit var whenString: String
    private lateinit var locationString: String
    private lateinit var timeString: String
    private lateinit var numPersonString: String
    private lateinit var priceString: String
    private lateinit var spinnerString: String
    private lateinit var standardImage: String

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
        spinnerSports = view.findViewById(R.id.sportsSpinner)
        whenEditText = view.findViewById(R.id.editTextDate)
        locationEditText = view.findViewById(R.id.locationEditText)
        timeEditText = view.findViewById(R.id.editTextTime)
        numPersonEditText = view.findViewById(R.id.numPersoneEditText)
        priceEditText = view.findViewById(R.id.priceEditText)
        eventString = ""
        whenString = ""
        locationString = ""
        timeString = ""
        numPersonString = ""
        priceString = ""
        standardImage = "https://www.geekslab.it/wp-content/uploads/2021/05/problema-webcam-lenovo.jpg"



        //visualizzazione dello spinner (è un array di stringhe)
        ArrayAdapter.createFromResource(view.context, R.array.spinnerSports, android.R.layout.simple_dropdown_item_1line)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
                spinnerSports.adapter = adapter
            }

        //quando si schiaccia il button 'create' controlla se le EditText sono piene, poi chiama la funzione saveEventData e poi
        //appare il dialog
        createButton.setOnClickListener {
            if (whenEditText.text.isNotEmpty() && locationEditText.text.isNotEmpty() && timeEditText.text.isNotEmpty() && numPersonEditText.text.isNotEmpty() && priceEditText.text.isNotEmpty()) {
                saveEventData()
                newDialog()
            } else {
                Toast.makeText(activity, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }

    //carica i dati inseriti su Firebase
    private fun saveEventData() {
        val db = Firebase.firestore

        //converte le EditText in stringhe
        whenString = whenEditText.text.toString()
        locationString = locationEditText.text.toString()
        timeString = timeEditText.text.toString()
        numPersonString = numPersonEditText.text.toString()
        priceString = priceEditText.text.toString()
        spinnerString = spinnerSports.selectedItem.toString()

        //qua diciamo con che voce salvare le diverse stringhe nel database
        val eventData = hashMapOf(
            "data" to whenString,
            "luogo" to locationString,
            "numPersone" to numPersonString,
            "ora" to timeString,
            "prezzo" to priceString,
            "sport" to spinnerString,
            "image" to standardImage
        )

        //qua diciamo a quale raccolta devono essere aggiunti i nostri campi
        db.collection("1")
            .add(eventData)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        }

    private fun reloadFragment() {
        // Ogni volta che si schiaccia il button continua i campi vengono
        whenEditText.text.clear()
        locationEditText.text.clear()
        timeEditText.text.clear()
        numPersonEditText.text.clear()
        priceEditText.text.clear()
        }

    //Dialogo che appare quando si schiaccia il button create
    private fun newDialog() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setMessage("Event created successfully")
        builder1.setCancelable(true)

        //Richiamiamo la funzione reloadFragment alla pressione del button 'Continua'
        builder1.setPositiveButton(
            "Continue",
            DialogInterface.OnClickListener { dialog, id -> reloadFragment()})

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}
