package com.example.niniperfumes.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.niniperfumes.databinding.FormularioImagemBinding
import com.example.niniperfumes.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(urlPadrao: String?= null, quandoImagemCarregada: (imagem: String) -> Unit ){

        val binding = FormularioImagemBinding
            .inflate(LayoutInflater.from(context))

        urlPadrao?.let {
            binding.formularioImagemImageview.tentaCarregarImagem(it)
            binding.formularioImagemUrl.setText(it)
        }

        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.formularioImagemUrl.text.toString()
            binding.formularioImagemImageview.tentaCarregarImagem(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formularioImagemUrl.text.toString()
                quandoImagemCarregada(url)

            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
    }

}