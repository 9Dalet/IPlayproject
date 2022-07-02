package com.example.iplay.DetailActivity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.iplay.R
import com.example.iplay.home.HomeFragment
import com.example.iplay.navbar.NavBarActivity


class DeatilActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var prenota: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatil)

        backButton = findViewById(R.id.backButton)
        prenota = findViewById(R.id.prenotaButton)

        backButton.setOnClickListener{
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

    private fun newDialog() {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(this)
        builder1.setMessage("Prenotazione effettuata correttamente")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Torna alla home",
            DialogInterface.OnClickListener { dialog, id -> returnHome()})

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }
    }
