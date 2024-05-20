package com.example.niniperfumes.dao

import com.example.niniperfumes.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(nome = "Hipnotic", descricao = "Doce", valor = BigDecimal("450.00"))
        )
    }
}