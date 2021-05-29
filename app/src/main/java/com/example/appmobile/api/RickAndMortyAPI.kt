
package com.example.appmobile.api

import com.example.appmobile.model.CharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getCharacters(
            @Query("page") page: Int?,
            @Query("name") name : String?,
            @Query("status") status: String?,
            @Query("gender") gender : String?
    ) : CharacterListResponse

}