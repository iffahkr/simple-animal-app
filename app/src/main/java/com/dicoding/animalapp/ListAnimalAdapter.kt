package com.dicoding.animalapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.animalapp.databinding.ItemAnimalBinding

class ListAnimalAdapter(private val listAnimal: ArrayList<Animal>) :
    RecyclerView.Adapter<ListAnimalAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listAnimal.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listAnimal[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description

        // click listener untuk tiap view
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailAnimalActivity::class.java)
            // kirim data hewan yang di click ke DetailAnimalActivity
            intentDetail.putExtra(DetailAnimalActivity.KEY_ANIMAL, listAnimal[holder.adapterPosition])
            holder.itemView.context.startActivity((intentDetail))
        }
    }
}