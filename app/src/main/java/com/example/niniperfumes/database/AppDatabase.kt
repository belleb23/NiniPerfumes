package com.example.niniperfumes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.niniperfumes.database.converter.Converters
import com.example.niniperfumes.database.dao.ProdutoDao
import com.example.niniperfumes.model.Produto

@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun ProdutoDao(): ProdutoDao
}