package com.enigmacamp.simple_news.ui

import android.content.Intent
import android.os.Bundle
import com.enigmacamp.simple_news.databinding.ActivityMainBinding
import com.enigmacamp.simple_news.ui.newssource.NewsSourceActivity
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = Intent(this, NewsSourceActivity::class.java)
        binding.apply {
            btnBusiness.setOnClickListener {
                i.putExtra("category", "business")
                startActivity(i)
            }
            btnEntertainment.setOnClickListener {
                i.putExtra("category", "entertainment")
                startActivity(i)
            }
            btnGeneral.setOnClickListener {
                i.putExtra("category", "general")
                startActivity(i)
            }
        }

    }


}