package com.hechikasoft.jaaculator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hechikasoft.jaaculator.data.entity.MemberEntity

@Database(entities = [MemberEntity::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun newInstance(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "app_db")
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun memberDao(): MemberDao
}

