package com.example.iplay.navbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import com.example.iplay.R
import com.example.iplay.chat.ChatActivity
import com.example.iplay.create.CreateActivity
import com.example.iplay.home.HomeActivity

class NavBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)


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