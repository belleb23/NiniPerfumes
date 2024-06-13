package com.example.niniperfumes.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun deDouble(valor: Double?) : BigDecimal{
        return valor?.let {BigDecimal(valor.toString())} ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalParaDouble(valor: BigDecimal?) : Double? {
        return valor?.let { valor.toDouble() }
    }

    @TypeConverter
    fun fromLongList(value: List<Long>?): String? {
        return value?.joinToString(separator = ",")
    }

    @TypeConverter
    fun toLongList(value: String?): List<Long>? {
        return value?.split(",")?.map { it.toLong() }
    }

}