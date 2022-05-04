package com.example.iplay.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iplay.R
import com.example.iplay.home.HomeFragment
import com.example.iplay.navbar.NavBarActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.accediButton)
        button.setOnClickListener {
            val intent = Intent(this, NavBarActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}