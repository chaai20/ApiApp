package com.example.appmobile.client

import android.content.Context
import com.example.appmobile.Consts
import com.example.appmobile.api.RickAndMortyAPI
import com.example.appmobile.data.CharacterRepository
import com.example.appmobile.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewmodelModule {


    @Provides
    fun provideCharacterRepo(service: RickAndMortyAPI, database : MainDatabase) : CharacterRepository {
        return CharacterRepository(service , database)
    }

    @Provides
    fun provideMainDatabase(@ApplicationContext context: Context): MainDatabase {
        return MainDatabase.getInstance(context)
    }

    @Provides
    fun provideRickAndMortyAPI(client : OkHttpClient, gsonConverterFactory: GsonConverterFactory) : RickAndMortyAPI {

        return Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(RickAndMortyAPI::class.java)
    }

}