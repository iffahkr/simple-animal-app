package com.dicoding.animalapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailAnimalActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var detailName: TextView
    private lateinit var detailDescription: TextView
    private lateinit var detailPhoto: ImageView
    private lateinit var detailSource: TextView
    private lateinit var detailLinkSource1: TextView
    private lateinit var detailLinkSource2: TextView

    companion object {
        const val KEY_ANIMAL = "key_animal"
    }

    @SuppressLint("MissingInflatedId")
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_animal)

        val btnShare: Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener(this)

        val dataAnimal = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Animal>("key_animal", Animal::class.java)
        } else {
            intent.getParcelableExtra<Animal>("key_animal")
        }

        detailName = findViewById(R.id.tv_detail_name)
        detailDescription = findViewById(R.id.tv_detail_description)
        detailSource = findViewById(R.id.tv_source)
        detailLinkSource1 = findViewById(R.id.tv_link_source1)
        detailLinkSource2 = findViewById(R.id.tv_link_source2)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_share -> {
                val shareArticle: Intent =
                    Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "https://github.com/iffahkr/simple-animal-app"
                        )
                        type = "text/plain"
                    }
                startActivity(shareArticle)
            }
        }
    }
}