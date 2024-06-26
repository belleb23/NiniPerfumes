package com.example.niniperfumes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuario (
    @PrimaryKey
    val id: String,
    val nome: String,
    val senha: String,
    val isAdmin: Boolean = false,
    val favoritos: List<Long>? = null
)