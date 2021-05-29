package com.example.appmobile.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.appmobile.Consts.NETWORK_PAGE_SIZE
import com.example.appmobile.api.RickAndMortyAPI
import com.example.appmobile.db.MainDatabase
import com.example.appmobile.model.CharacterModel
import com.example.appmobile.model.CharacterQueryModel


/**
 * Repository class that works with local and remote data sources.
 */
class CharacterRepository(
    private val service: RickAndMortyAPI,
    private val database: MainDatabase
) {

    fun getSearchResultStream(query: CharacterQueryModel?): LiveData<PagingData<CharacterModel>> {

        val pagingSourceFactory = { database.charactersDao().getCharacters() }

        // Normal listing, without any query
        return if (query == null) {
            Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
                remoteMediator = CharacterRemoteMediator(
                    service,
                    database
                ),
                pagingSourceFactory = pagingSourceFactory
            ).liveData
        }
        else {
            Pager(
                config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
                pagingSourceFactory = { CharacterNetworkPaging(service, query) }
            ).liveData
        }


    }


}
