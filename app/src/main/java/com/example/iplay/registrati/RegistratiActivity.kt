package com.example.iplay.registrati

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
import com.example.iplay.EmailAccess.EmailLogin
import com.example.iplay.R
import com.example.iplay.home.HomeFragment
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistratiActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emptyEmail: EditText
    private lateinit var emptyPassword: EditText
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var button: Button
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var surname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrati)

        nameEditText = findViewById(R.id.nameEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
        email = ""
        password = ""
        name = ""
        surname = ""
        auth = Firebase.auth
        emptyEmail = findViewById(R.id.editTextTextEmailAddress2)
        emptyPassword = findViewById(R.id.editTextTextPassword2)
        button = findViewById(R.id.button2)
        val backButton = findViewById<ImageView>(R.id.backButton)
        val db = Firebase.firestore


        //se si schiaccia il button 'registrati' si attiva la procedura di controllo dei campi
        button.setOnClickListener {
            if (nameEditText.text.isNotEmpty() || surnameEditText.text.isNotEmpty() || emptyPassword.text.isNotEmpty() || (emptyEmail.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(
                    email
                ).matches())
            ) {
                //convertiamo in stringhe i campi dell'editText
                name = nameEditText.text.toString()
                surname = surnameEditText.text.toString()
                email = emptyEmail.text.toString()
                password = emptyPassword.text.toString()
                //usiamo il metodo createUserWithEmailAndPassword per fare la registrazione
                auth.createUserWithEmailAndPassword(email, password)
                        //se ha successo salviamo tutto nel database con l'identificativo uguale sia per il firestore sia per l'authentication
                    .addOnCompleteListener(this) {
                        val user = auth.currentUser
                        val userData = hashMapOf(
                            "name" to nameEditText.text.toString(),
                            "surname" to surnameEditText.text.toString()
                        )
                        if (user != null) {
                            db.collection("userData").document(user.uid).set(userData)
                                .addOnCompleteListener {
                                    val intent = Intent(this, NavBarActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                    //se fallisce compare un toast
                                .addOnFailureListener {
                                    Toast.makeText(
                                        baseContext,
                                        "Authentication failed.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                        }
                    }
            } else {
                Toast.makeText(
                    baseContext,
                    "Fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        backButton.setOnClickListener {
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
    }

}
