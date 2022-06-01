package com.example.iplay.registrati

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.iplay.EmailAccess.EmailLogin
import com.example.iplay.R
import com.example.iplay.home.HomeFragment
import com.example.iplay.navbar.NavBarActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
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


        button.setOnClickListener {
            if (nameEditText.text.isNotEmpty() || surnameEditText.text.isNotEmpty() || emptyPassword.text.isNotEmpty() || (emptyEmail.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                name = nameEditText.text.toString()
                surname = surnameEditText.text.toString()
                email = emptyEmail.text.toString()
                password = emptyPassword.text.toString()
                createAccount(email, password)
            } else {
                Toast.makeText(this, "Enter correct data", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }

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

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this, NavBarActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {

    }
}