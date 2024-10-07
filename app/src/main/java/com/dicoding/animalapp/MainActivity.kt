package com.dicoding.animalapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.animalapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvAnimals: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvAnimals = findViewById(R.id.rv_animals)
        rvAnimals.setHasFixedSize(true)

        list.addAll(getListAnimals())
        showRecyclerList()
    }

    private fun getListAnimals(): ArrayList<Animal> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listAnimal = ArrayList<Animal>()
        for (i in dataName.indices) {
            val animal = Animal(dataName[i], dataDescription[i], dataPhoto[i])
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showRecyclerList() {
        rvAnimals.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvAnimals.adapter = listAnimalAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveToAbout = Intent(this@MainActivity, AboutMenu::class.java)
                startActivity(moveToAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}