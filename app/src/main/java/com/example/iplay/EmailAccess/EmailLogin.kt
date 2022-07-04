package com.example.iplay.EmailAccess

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.iplay.MainActivity
import com.example.iplay.R
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class EmailLogin : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var button: Button
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        email = ""
        password = ""
        auth = Firebase.auth
        emailLogin = findViewById(R.id.editTextTextEmailAddress)
        passwordLogin = findViewById(R.id.editTextTextPassword)
        button = findViewById(R.id.buttonLogin)
        val backButton = findViewById<ImageView>(R.id.backButton)

        button.setOnClickListener {
            if (emailLogin.text.isNotEmpty() || passwordLogin.text.isNotEmpty()) {
                email = emailLogin.text.toString()
                password = passwordLogin.text.toString()
                signIn(email, password)
            } else {
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, NavBarActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //funzione per il login
    private fun signIn(email: String, password: String) {

        //usiamo il metodo signInWithEmailAndPassword per fare il login
        auth.signInWithEmailAndPassword(email, password)
                //se ha successo ci apre la home e l'utente resta salvato
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("aaaaa", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this, NavBarActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    //se il login fallisce ci viene fuori un toast
                    Log.w("bbbbb", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun reload() {

    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }
}



