package com.unknownsystem.movienot.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.unknownsystem.movienot.db.dao.ResultDao
import com.unknownsystem.movienot.models.Result

@Database(entities = [Result::class], exportSchema = false, version = 1)
@TypeConverters(
    IdsToStringConverter::class
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun resultDao(): ResultDao
}