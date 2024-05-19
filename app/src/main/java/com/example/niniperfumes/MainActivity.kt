package com.example.niniperfumes

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nome = findViewById<TextView>(R.id.nome)
        nome.text = "Hipnotic"
        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "Doce e marcante"
        val valor = findViewById<TextView>(R.id.valor)
        valor.text = "399.00"


    }

}