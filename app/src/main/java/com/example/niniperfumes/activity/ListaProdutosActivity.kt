package com.example.niniperfumes.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.niniperfumes.database.AppDatabase
import com.example.niniperfumes.databinding.ActivityListaProdutosBinding
import com.example.niniperfumes.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import com.example.niniperfumes.preferences.dataStore
import com.example.niniperfumes.preferences.usuarioLogadoPreferences
import com.example.niniperfumes.preferences.isAdminPreferences
import android.view.View
import androidx.datastore.preferences.core.edit
import com.example.niniperfumes.R
import com.example.niniperfumes.extensions.vaiPara


class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this)
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    private val produtoDao by lazy {
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    private  val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    private var isAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch {
            launch {
                produtoDao.buscaTodos().collect { produtos ->
                    adapter.atualiza(produtos)
                }
            }
            launch {
                dataStore.data.collect { preferences ->
                    preferences[usuarioLogadoPreferences]?.let { usuarioId ->
                        launch {
                            usuarioDao.buscaPorId(usuarioId).collect { usuario ->
                                isAdmin = usuario.isAdmin
                                configuraRecyclerView()
                                if(isAdmin){
                                    configuraFab()
                                }else{
                                    binding.activityListaProdutoTitulo.visibility = View.VISIBLE
                                    binding.activityListaProdutoTitulo.text = "Bem vindo, ${usuario.nome}"
                                    binding.activityListaProdutoFavoritos.visibility = View.VISIBLE
                                }
                            }
                        }
                    } ?: vaiParaLogin()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_lista_produtos_sair_do_app -> {
                lifecycleScope.launch {
                    dataStore.edit {preferences ->
                        preferences.remove(usuarioLogadoPreferences)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun vaiParaLogin() {
        vaiPara(LoginActivity::class.java)
        finish()
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutoFloatingActionButton
        fab.visibility = View.VISIBLE
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutoRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent) // Mova a chamada para startActivity dentro do bloco apply
        }
    }

}

