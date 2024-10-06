package com.dicoding.animalapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable
