package com.example.iplay.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iplay.R
import com.example.iplay.home.HomeFragment
import com.example.iplay.navbar.NavBarActivity
import com.example.iplay.registrati.RegistratiActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.accediButton)
        val regButton = findViewById<Button>(R.id.regButton)


        button.setOnClickListener {
            val intent = Intent(this, NavBarActivity::class.java)
            startActivity(intent)
            finish()
        }

        regButton.setOnClickListener{
            val intent = Intent(this, RegistratiActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}