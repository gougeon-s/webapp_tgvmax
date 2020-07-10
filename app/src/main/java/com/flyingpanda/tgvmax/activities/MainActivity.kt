package com.flyingpanda.tgvmax.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.flyingpanda.tgvmax.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun config(view: View){
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }

    fun home(view: View){
        val intent = Intent(this, TGVMaxActivity::class.java)
        startActivity(intent)
    }

}