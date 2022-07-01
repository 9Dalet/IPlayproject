package com.example.iplay.DetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.iplay.R


class DeatilActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatil)

        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener{
            finish()}
        }
    }
