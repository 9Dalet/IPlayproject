package com.example.iplay.navbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.iplay.R
import com.example.iplay.chat.ChatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class NavBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)

        //Initialize the bottom navigation view
        //create bottom navigation view object
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)
    }

//functions for every state of the app while being used
    override fun onStart() {
        super.onStart()
        Log.d("activitystatus", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("activitystatus", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("activitystatus", "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("activitystatus", "onDestroy")
    }
}