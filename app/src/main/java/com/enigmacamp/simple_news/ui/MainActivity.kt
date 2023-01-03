package com.enigmacamp.simple_news.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.enigmacamp.simple_news.R
import com.enigmacamp.simple_news.ui.newssource.NewsSourceActivity


class MainActivity : AppCompatActivity() {
    private lateinit var btnBusiness: Button
    private lateinit var btnEntertainement: Button
    private lateinit var btnGeneral: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        btnBusiness = findViewById(R.id.btnBusiness)
        btnEntertainement = findViewById(R.id.btnEntertainment)
        btnGeneral = findViewById(R.id.btnGeneral)

        val i = Intent(this, NewsSourceActivity::class.java)
        btnBusiness.setOnClickListener {
            i.putExtra("category", "business")
            startActivity(i)
        }
        btnEntertainement.setOnClickListener {
            i.putExtra("category", "entertainment")
            startActivity(i)
        }
        btnGeneral.setOnClickListener {
            i.putExtra("category", "general")
            startActivity(i)
        }
    }


}