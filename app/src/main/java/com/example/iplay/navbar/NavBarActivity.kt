package com.example.iplay.navbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.iplay.R
import com.example.iplay.create.CreateFragment
import com.example.iplay.home.HomeFragment
import com.example.iplay.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class NavBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_bar)

        //Initialize the bottom navigation view
        //create bottom navigation view object
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
       // val navController = findNavController(R.id.nav_fragment)
        //bottomNavigationView.setupWithNavController(navController)

        loadFragment(HomeFragment())

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.searchFragment -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.createFragment -> {
                    loadFragment(CreateFragment())
                    true
                }
                else -> {
                    false
                }

            }
        }


    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.nav_fragment, fragment)
        transaction.commit()
    }
}

//functions for every state of the app while being used
//    override fun onStart() {
//        super.onStart()
//        Log.d("activitystatus", "onStart")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("activitystatus", "onResume")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("activitystatus", "onPause")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("activitystatus", "onDestroy")
//    }
