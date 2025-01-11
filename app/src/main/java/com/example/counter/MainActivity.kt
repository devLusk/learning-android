package com.example.counter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name: EditText = findViewById(R.id.nameEditText)
        val saveButton: Button = findViewById(R.id.saveButton)
        val resultText: TextView = findViewById(R.id.resultTextView)

        val sharedPreference = getSharedPreferences("values", Context.MODE_PRIVATE)
        val savedValue = sharedPreference.getString("value", "")

        resultText.text = savedValue

        saveButton.setOnClickListener {
            val newName = name.text.toString()

            val editor = sharedPreference.edit()
            editor.putString("value", newName)
            editor.clear()
            editor.apply()

            resultText.text = newName
        }
    }
}