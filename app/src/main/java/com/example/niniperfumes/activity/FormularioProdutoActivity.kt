package com.example.niniperfumes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.niniperfumes.dao.ProdutosDao
import com.example.niniperfumes.databinding.ActivityFormularioProdutoBinding
import com.example.niniperfumes.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val dao = ProdutosDao()
        binding.activityFormularioProdutoBotaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val nome = binding.activityFormularioProdutoNome.text.toString()
        val descricao = binding.activityFormularioProdutoDescricao.text.toString()
        val valorEmTexto = binding.activityFormularioProdutoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }
}
