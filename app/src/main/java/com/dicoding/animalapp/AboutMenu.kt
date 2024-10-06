package com.dicoding.animalapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class AboutMenu : AppCompatActivity() {

    private lateinit var photo: ImageView
    private lateinit var name: TextView
    private lateinit var email: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.about_page)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // menerima data dari intent
        val receivedName = getString(R.string.about_name)
        val receivedEmail = getString(R.string.about_email)

        // inisialisasi data view dari layout
        photo = findViewById(R.id.img_about)
        name = findViewById(R.id.tv_about_name)
        email = findViewById(R.id.tv_about_email)

        name.text = receivedName
        email.text = receivedEmail
    }
}