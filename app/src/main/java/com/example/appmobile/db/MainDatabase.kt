package com.example.appmobile.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appmobile.Consts
import com.example.appmobile.model.CharacterModel
import com.example.appmobile.model.OriginTypeConverter

@Database(
        entities = [CharacterModel::class],
        version = 4,
        exportSchema = false
)
@TypeConverters(OriginTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharacterDao
    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MainDatabase::class.java, Consts.DATABASE_NAME)
                        .build()
    }
}
