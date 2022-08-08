package com.example.iplay.DetailActivity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.iplay.R
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class DeatilActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var luogo: TextView
    private lateinit var sport: TextView
    private lateinit var data: TextView
    private lateinit var numPersone: TextView
    private lateinit var ora: TextView
    private lateinit var image: ImageView
    private lateinit var prezzo: TextView
    private lateinit var descrizione: TextView
    private lateinit var prenota: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatil)

        //prendiamo l'input text che ci è arrivat da serch
        val idDoc = intent.getStringExtra("idDoc")

        //inizializzazione
        luogo = findViewById(R.id.placeString)
        sport = findViewById(R.id.sportString)
        data = findViewById(R.id.dateString)
        numPersone = findViewById(R.id.playersString)
        ora = findViewById(R.id.hourString)
        image = findViewById(R.id.imageView13)
        prezzo = findViewById(R.id.priceString)
        descrizione = findViewById(R.id.descriptionTextView)
        prenota = findViewById(R.id.prenotaButton)
        backButton = findViewById(R.id.backButton)

        //recupero dati da firebase
        var db = Firebase.firestore

        db.collection("1").document(idDoc.toString()).get().addOnSuccessListener {

            //recupero dati da firebase
            luogo.text = it["luogo"].toString()
            numPersone.text = it["numPersone"].toString()
            data.text = it["data"].toString()
            ora.text = it["ora"].toString()
            prezzo.text = it["prezzo"].toString()
            sport.text = it["sport"].toString()
            descrizione.text = it["descrizione"].toString()
            Glide.with(this).load(it["image"]).into(image)

        }



        backButton.setOnClickListener {
            finish()
        }

        prenota.setOnClickListener {
            newDialog()
        }


    }

    private fun returnHome() {
        val intent = Intent(this, NavBarActivity::class.java)
        startActivity(intent)
    }

    //Dialogo che appare quando si schiaccia il button create
    private fun newDialog() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
        builder1.setMessage("Reservation made correctly")
        builder1.setCancelable(true)

        //Richiamiamo la funzione reloadFragment alla pressione del button 'Continua'
        builder1.setPositiveButton(
            "Continue",
            DialogInterface.OnClickListener { dialog, id -> returnHome() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
}



