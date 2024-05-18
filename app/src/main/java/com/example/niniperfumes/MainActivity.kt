package com.example.niniperfumes

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

class MainActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem vindo a loja Nini Perfume", Toast.LENGTH_SHORT).show()
    }

}