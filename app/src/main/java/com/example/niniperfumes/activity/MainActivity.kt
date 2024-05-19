package com.example.niniperfumes.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.niniperfumes.R
import com.example.niniperfumes.model.Produto
import com.example.niniperfumes.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)  // Setting the LayoutManager
        recyclerView.adapter = ListaProdutosAdapter(this, listOf(
            Produto(nome="Perfume 1", descricao = "Perfume desc", valor = BigDecimal("189.00")),
            Produto(nome="Perfume 2", descricao = "Perfume 2 desc", valor = BigDecimal("19.00")),
            Produto(nome="Perfume 3", descricao = "Perfume 3 desc", valor = BigDecimal("19.00")),
            Produto(nome="Perfume 4", descricao = "Perfume 4 desc", valor = BigDecimal("19.00")),

            ))


    }

}