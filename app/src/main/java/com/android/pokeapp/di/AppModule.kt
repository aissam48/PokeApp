package com.android.pokeapp.di

import android.app.Application
import android.content.Context
import com.android.pokeapp.repository.ApiManager
import com.android.pokeapp.repository.ApiServicesImpl
import com.android.pokeapp.repository.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModel {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(JsonFeature) {
                serializer = GsonSerializer {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

    @Provides
    @Singleton
    fun providePokemonKtorService(apiManager: ApiManager): PokemonService {
        return ApiServicesImpl(apiManager)
    }


    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}

