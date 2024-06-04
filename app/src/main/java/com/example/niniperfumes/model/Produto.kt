package com.example.niniperfumes.model

import java.math.BigDecimal
import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null
) : Parcelable
