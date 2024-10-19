package com.soe.dailynews.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.soe.dailynews.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(sourceString: String): Source {
        val parts = sourceString.split(",")
        return parts.let { Source(it[0], it[1]) }
    }


}