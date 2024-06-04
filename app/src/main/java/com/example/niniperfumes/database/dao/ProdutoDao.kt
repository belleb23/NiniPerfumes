package com.example.niniperfumes.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.niniperfumes.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos() : List<Produto>

    @Insert
    fun salva(vararg produto: Produto)
}