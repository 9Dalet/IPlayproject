package com.example.iplay.DetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.iplay.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DeatilActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var luogo : TextView
    private lateinit var sport: TextView
    private lateinit var data: TextView
    private lateinit var numPersone: TextView
    private lateinit var ora: TextView
    private lateinit var image: ImageView
    private lateinit var prezzo: TextView
    private lateinit var descrizione: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatil)

        //prendiamo l'input text che ci è arrivat da serch
        val idDoc=intent.getStringExtra("idDoc")

        //inizializzazione
        luogo = findViewById(R.id.placeString)
        sport = findViewById(R.id.sportString)
        data = findViewById(R.id.dateString)
        numPersone = findViewById(R.id.playersString)
        ora = findViewById(R.id.hourString)
        image = findViewById(R.id.imageView13)
        prezzo = findViewById(R.id.priceString)
        descrizione = findViewById(R.id.descriptionTextView)

        //recupero dati da firebase
        var db = Firebase.firestore

        db.collection("1").document(idDoc.toString()).get().addOnSuccessListener {

            //recupero dati da firebase
             luogo.text = it["luogo"].toString()
             numPersone.text = it["numPersone"].toString()
             data.text= it["data"].toString()
             ora.text= it["ora"].toString()
             prezzo.text= it["prezzo"].toString()
             sport.text= it["sport"].toString()
            descrizione.text= it["descrizione"].toString()
            Glide.with(this).load(it["image"]).into(image)

        }

        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener{
            finish()}
        }
    }
