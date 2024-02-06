package com.example.beerpedia.di

import android.app.Application
import androidx.room.Room
import com.example.beerpedia.data.database.BeerDatabase
import com.example.beerpedia.data.database.BeerDatabaseDataSource
import com.example.beerpedia.data.datasource.BeerLocalDataSource
import com.example.beerpedia.data.datasource.BeerRemoteDataSource
import com.example.beerpedia.data.server.BeerServerDataSource
import com.example.beerpedia.data.server.RemoteService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        BeerDatabase::class.java,
        "beer-db"
    ).build()

    @Provides
    @Singleton
    fun provideBeerDao(db: BeerDatabase) = db.beerDao()

    @Provides
    @Singleton
    fun provideRemoteService(): RemoteService {
        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(this).build()
        }

        return Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: BeerDatabaseDataSource): BeerLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: BeerServerDataSource): BeerRemoteDataSource
}