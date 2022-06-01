package com.example.iplay.login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.iplay.EmailAccess.EmailLogin
import com.example.iplay.R

import com.example.iplay.registrati.RegistratiActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.accediButton)
        val regButton = findViewById<Button>(R.id.regButton)


        button.setOnClickListener {
            val intent = Intent(this, EmailLogin::class.java)
            startActivity(intent)
        }

        regButton.setOnClickListener{
            val intent = Intent(this, RegistratiActivity::class.java)
            startActivity(intent)
        }
    }





}