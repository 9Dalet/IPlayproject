package com.example.iplay.EmailAccess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iplay.R
import com.example.iplay.navbar.NavBarActivity

class EmailLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        val button = findViewById<Button>(R.id.buttonLogin)
        button.setOnClickListener {
            val intent = Intent(this, NavBarActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}