package com.example.niniperfumes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.niniperfumes.databinding.ActivityFormularioProdutoBinding
import com.example.niniperfumes.model.Produto
import java.math.BigDecimal
import com.example.niniperfumes.database.AppDatabase
import com.example.niniperfumes.dialog.FormularioImagemDialog
import com.example.niniperfumes.extensions.tentaCarregarImagem

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null
    private var idProduto = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Perfume"

        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this).mostra(url){
                imagem ->
                url = imagem
                binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)
            }
        }
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let{ produtoCarregado ->
            title = "Alterar Perfume"
            idProduto = produtoCarregado.id
            url = produtoCarregado.imagem
            binding.activityFormularioProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            binding.activityFormularioProdutoNome.setText(produtoCarregado.nome)
            binding.activityFormularioProdutoDescricao.setText(produtoCarregado.descricao)
            binding.activityFormularioProdutoValor.setText(produtoCarregado.valor.toPlainString())


        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val db = AppDatabase.instancia(this)
        val produtoDao = db.produtoDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if(idProduto > 0){
                produtoDao.altera(produtoNovo)
            }else{
                produtoDao.salva(produtoNovo)
            }
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            id = idProduto,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }

}