package com.example.niniperfumes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.niniperfumes.R
import com.example.niniperfumes.database.AppDatabase
import com.example.niniperfumes.databinding.ActivityDetalhesProdutoBinding
import com.example.niniperfumes.extensions.formataParaMoedaBrasileira
import com.example.niniperfumes.extensions.tentaCarregarImagem
import com.example.niniperfumes.model.Produto
import com.example.niniperfumes.preferences.dataStore
import com.example.niniperfumes.preferences.usuarioLogadoPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetalhesProdutoActivity : AppCompatActivity() {

    private var produtoId: Long = 0L
    private var produto: Produto? = null
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        AppDatabase.instancia(this).produtoDao()
    }

    private  val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
        lifecycleScope.launch {
            val isAdmin = verificarPermissaoAdministrador()
            if(!isAdmin){
                binding.buttonGostei.visibility = View.VISIBLE
            }
        }

    }

    private suspend fun verificarPermissaoAdministrador(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[usuarioLogadoPreferences]?.let { usuarioId ->
                usuarioDao.buscaPorId(usuarioId).map { usuario ->
                    usuario.isAdmin
                }.firstOrNull() ?: false
            } ?: false
        }.first()
    }




    override fun onResume() {
        super.onResume()
        buscaProduto()
    }

    private fun buscaProduto() {
        lifecycleScope.launch {
            produtoDao.buscaPorId(produtoId).collect { produtoEncontrado ->
                produto = produtoEncontrado
                produto?.let {
                    preencheCampos(it)
                } ?: finish()
            }
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_detalhes_produto_remover -> {
                produto?.let {
                    lifecycleScope.launch {
                        produtoDao.remove(it)
                        finish()
                    }
                }

            }
            R.id.menu_detalhes_produto_editar -> {
                Intent(this, FormularioProdutoActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO_ID, produtoId)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        lifecycleScope.launch {
            val isAdmin = verificarPermissaoAdministrador()
            if(isAdmin){
                   menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
            }
        }
        return true
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra(CHAVE_PRODUTO_ID, 0L)
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }

}