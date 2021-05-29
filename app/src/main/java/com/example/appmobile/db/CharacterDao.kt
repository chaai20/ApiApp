package com.example.appmobile.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appmobile.model.CharacterModel

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<CharacterModel>)


    @Query("SELECT * FROM characters")
    fun getCharacters(): PagingSource<Int, CharacterModel>


    @Query("DELETE FROM characters")
    suspend fun clearCharacters()

}