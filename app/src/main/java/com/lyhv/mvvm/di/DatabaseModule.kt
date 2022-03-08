package com.lyhv.mvvm.di

import android.app.Application
import androidx.room.Room
import com.lyhv.mvvm.common.utils.SPUtils
import com.lyhv.mvvm.core.data.AppDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module(includes = [CoreDataModule::class])
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    internal fun provideSPUtils(
        app: Application,
        @MoshiBase moshi: Moshi
    ): SPUtils {
        return SPUtils(app, moshi)
    }

    @Provides
    fun provideDb(app: Application, @MoshiBase moshi: Moshi): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
            .apply { AppDatabase.moshi = moshi }
    }
}
