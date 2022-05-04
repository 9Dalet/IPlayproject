package com.example.iplay.navbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.iplay.R

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