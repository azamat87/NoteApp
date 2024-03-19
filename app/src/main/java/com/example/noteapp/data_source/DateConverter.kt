package com.example.noteapp.data_source

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromDate(date: Date) : Long = date.time

    @TypeConverter
    fun toDate(time: Long): Date = Date(time)

}