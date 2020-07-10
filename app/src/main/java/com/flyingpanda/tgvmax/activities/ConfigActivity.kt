package com.flyingpanda.tgvmax.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.flyingpanda.tgvmax.R

class ConfigActivity : AppCompatActivity() {

    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = getSharedPreferences("Config",Context.MODE_PRIVATE)
        setContentView(R.layout.activity_config)
        val username = prefs?.getString("username", "")
        if (username!!.isNotEmpty()) {
            findViewById<EditText>(R.id.username).apply {
                hint = username
            }
        } else {
            Log.i("Config", "username empty")
        }
    }

    fun save(view: View){
        val username = findViewById<EditText>(R.id.username).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        Log.i("save",username)
        val editor = prefs!!.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.commit()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
