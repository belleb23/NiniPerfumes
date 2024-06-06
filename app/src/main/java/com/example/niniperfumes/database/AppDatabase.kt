package com.example.niniperfumes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.niniperfumes.database.converter.Converters
import com.example.niniperfumes.database.dao.ProdutoDao
import com.example.niniperfumes.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun instancia(context: Context) : AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "niniperfumes.db"
            ).allowMainThreadQueries()
                .build()
        }
    }
}