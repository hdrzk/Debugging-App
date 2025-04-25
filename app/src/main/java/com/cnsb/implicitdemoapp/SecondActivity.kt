package com.cnsb.implicitdemoapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textFromMain = intent.getStringExtra("text")
        val resultTextView: TextView = findViewById(R.id.text_result)
        resultTextView.text = textFromMain ?: "Teks kosong"

        val backButton: Button = findViewById(R.id.button_back)
        backButton.setOnClickListener {
            Toast.makeText(this, "Back To Main", Toast.LENGTH_SHORT).show()
            finish() // Menutup SecondActivity dan kembali ke MainActivity
        }
    }

}