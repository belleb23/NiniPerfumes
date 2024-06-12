package com.example.niniperfumes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.niniperfumes.databinding.ActivityLoginBinding
import com.example.niniperfumes.extensions.vaiPara
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.niniperfumes.database.AppDatabase
import com.example.niniperfumes.preferences.dataStore
import com.example.niniperfumes.preferences.usuarioLogadoPreferences
import com.example.niniperfumes.preferences.isAdminPreferences
import kotlinx.coroutines.launch
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.activityLoginBotaoEntrar.setOnClickListener {
            val usuario = binding.activityLoginUsuario.text.toString()
            val senha = binding.activityLoginSenha.text.toString()
            Log.i("LoginActivity", "onCreate: $usuario - $senha")
            lifecycleScope.launch {
                usuarioDao.autentica(usuario, senha)?.let { usuario ->
                    dataStore.edit { preferences ->
                        preferences[usuarioLogadoPreferences] = usuario.id
                        preferences[isAdminPreferences] = usuario.isAdmin
                    }
                    vaiPara(ListaProdutosActivity::class.java)
                    finish()
                } ?: Toast.makeText(
                    this@LoginActivity,
                    "Falha na autenticação",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            vaiPara(FormularioCadastroUsuarioActivity::class.java)
        }
    }

}