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
          //  Produto(nome = "Hipnotic", descricao = "Doce", valor = BigDecimal("450.00"))
            Produto(
                nome = "Hipnotic Poison",
                descricao = "Uma poção mágica moderna e irresistível que hipnotiza e seduz todos ao redor. Esse perfume possui uma dualidade intrigante, com uma face doce e feminina, e outra dominadora e provocativa",
                valor = BigDecimal("599.00"),
                imagem = "https://epocacosmeticos.vteximg.com.br/arquivos/ids/418263-320-320/hypnotic-poison-eau-de-toilette-dior-perfume-feminino-30ml.jpg?v=637471766574900000"
            ),
            Produto(
                nome = "Mont Blanc",
                descricao = "O perfume Montblanc expressa um lado vigoroso, enérgico, decidido e forte do homem moderno. Por isso, expressa em notas aromáticas um olfato que remete a coragem, hombridade, potência e bravura",
                valor = BigDecimal("450.00"),
                imagem = "https://epocacosmeticos.vteximg.com.br/arquivos/ids/407903-500-500/legend-montblanc-perfume-masculino-edp-50ml--3-.jpg?v=637393379451230000"
            ),

        )
    }
}