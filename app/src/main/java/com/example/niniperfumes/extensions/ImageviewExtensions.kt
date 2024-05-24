package com.example.niniperfumes.extensions

import android.widget.ImageView
import coil.load
import com.example.niniperfumes.R

fun ImageView.tentaCarregarImagem(url:String?= null, fallback: Int = R.drawable.imagem_padrao){
    load(url){
        fallback(com.example.niniperfumes.R.drawable.erro)
        error(com.example.niniperfumes.R.drawable.erro)
        placeholder(com.example.niniperfumes.R.drawable.placeholder)
    }
}