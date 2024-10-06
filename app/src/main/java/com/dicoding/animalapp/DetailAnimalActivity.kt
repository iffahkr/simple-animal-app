package com.dicoding.animalapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailAnimalActivity : AppCompatActivity() {

    private lateinit var detailName: TextView
    private lateinit var detailDescription: TextView
    private lateinit var detailPhoto: ImageView

    companion object {
        const val KEY_ANIMAL = "key_animal"
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_animal)

        val dataAnimal = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Animal>("key_animal", Animal::class.java)
        } else {
            intent.getParcelableExtra<Animal>("key_animal")
        }

        detailName = findViewById(R.id.tv_detail_name)
        detailDescription = findViewById(R.id.tv_detail_description)
        detailPhoto = findViewById(R.id.img_detail_photo)

        if (dataAnimal != null) {
            detailName.text = dataAnimal.name
            detailDescription.text = dataAnimal.description
            Glide.with(this)
                .load(dataAnimal.photo)
                .into(detailPhoto)
        } else {
            Log.e("DetailAnimalActivity", "dataAnimal is null")
        }
    }
}