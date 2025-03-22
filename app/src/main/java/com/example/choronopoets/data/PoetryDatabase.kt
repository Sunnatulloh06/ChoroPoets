package com.example.choronopoets.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.choronopoets.dao.CenturyDao
import com.example.choronopoets.dao.PoemDao
import com.example.choronopoets.dao.PoetDao
import com.example.choronopoets.dataClass.Century
import com.example.choronopoets.dataClass.Poems
import com.example.choronopoets.dataClass.Poet

@Database(
    entities = [Century::class, Poet::class, Poems::class],
    version = 2,
    exportSchema = false
)
abstract class PoetryDatabase : RoomDatabase() {
    abstract fun centuryDao(): CenturyDao
    abstract fun poetDao(): PoetDao
    abstract fun poemDao(): PoemDao

    companion object {
        @Volatile
        private var INSTANCE: PoetryDatabase? = null

        fun getInstance(context: Context): PoetryDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PoetryDatabase::class.java,
                    "poetry_database"
                )
                    .createFromAsset("poetry_database.db")
                    .enableMultiInstanceInvalidation()
                    .fallbackToDestructiveMigration()
                    .build().apply {
                        INSTANCE = this
                    }
            }
        }
    }
}

