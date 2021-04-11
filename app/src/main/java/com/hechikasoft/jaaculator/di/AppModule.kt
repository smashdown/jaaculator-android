package com.hechikasoft.jaaculator.di

import android.content.Context
import com.hechikasoft.jaaculator.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.newInstance(appContext)

    @Singleton
    @Provides
    fun provideMemberDao(db: AppDatabase) = db.memberDao()

//    @Singleton
//    @Provides
//    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
//        .baseUrl("https://rickandmortyapi.com/api/")
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()
//
//    @Provides
//    fun provideGson(): Gson = GsonBuilder().create()
//
//    @Provides
//    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)
//
//    @Singleton
//    @Provides
//    fun provideCharacterRemoteDataSource(characterService: CharacterService) = CharacterRemoteDataSource(characterService)

//
//    @Singleton
//    @Provides
//    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
//                          localDataSource: CharacterDao) =
//        CharacterRepository(remoteDataSource, localDataSource)
}