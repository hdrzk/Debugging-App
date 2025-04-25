package com.cnsb.implicitdemoapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private fun openWebsite(url: String) {
        val fixedUrl = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else url

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fixedUrl))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            showToast("Tidak dapat membuka website")
        }
    }

    private fun openLocation(location: String) {
        val uri = Uri.parse("geo:0,0?q=$location")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        // intent.setPackage("com.google.android.apps.maps") // optional
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            showToast("Tidak dapat membuka lokasi")
        }
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        startActivity(Intent.createChooser(intent, "Bagikan teks menggunakan:"))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val websiteEditText: EditText = findViewById(R.id.website_edit_text)
        findViewById<Button>(R.id.open_website_button).setOnClickListener {
            val url = websiteEditText.text.toString()
            if (url.isNotBlank()) openWebsite(url)
            else showToast("URL tidak boleh kosong")
        }

        val locationEditText: EditText = findViewById(R.id.location_edit_text)
        findViewById<Button>(R.id.location_button).setOnClickListener {
            val location = locationEditText.text.toString()
            if (location.isNotBlank()) openLocation(location)
            else showToast("Lokasi tidak boleh kosong")
        }

        val shareEditText: EditText = findViewById(R.id.share_edit_text)
        findViewById<Button>(R.id.share_text_button).setOnClickListener {
            val text = shareEditText.text.toString()
            if (text.isNotBlank()) shareText(text)
            else showToast("Teks tidak boleh kosong")
        }

        val inputEditText: EditText = findViewById(R.id.edit_input)
        val nextPageButton: Button = findViewById(R.id.button_next)

        nextPageButton.setOnClickListener {
            val inputText = inputEditText.text.toString()
            Log.v("check", inputText)

            if (inputText.isNotBlank()) {
                Toast.makeText(this, "Welcome : $inputText", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("text", inputText)
                startActivity(intent)
            } else {
                showToast("Input tidak boleh kosong")
            }
        }

    }
}
