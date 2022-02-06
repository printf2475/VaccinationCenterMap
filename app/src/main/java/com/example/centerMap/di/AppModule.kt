package com.example.centerMap.di

import android.app.Application
import androidx.room.Room
import com.example.centerMap.data.repository.RepositoryImpl
import com.example.centerMap.data.retrofit.RetrofitVC
import com.example.centerMap.data.room.DataBaseVC
import com.example.centerMap.domain.repository.Repository
import com.example.centerMap.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitVC{
        return Retrofit.Builder().baseUrl(RetrofitVC.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RetrofitVC::class.java)
    }


    @Provides
    @Singleton
    fun provideRoom(application: Application): DataBaseVC {
        return  Room.databaseBuilder(
            application,
            DataBaseVC::class.java,
            DataBaseVC.DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideVCRepository(dao: DataBaseVC, retrofitVC: RetrofitVC) : Repository {
        return RepositoryImpl(dao, retrofitVC)
    }



    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            deleteAll = DeleteAll(repository),
            getAllData = GetAllData(repository),
            getVCData = GetVCData(repository),
            insertData = InsertData(repository))
    }




}