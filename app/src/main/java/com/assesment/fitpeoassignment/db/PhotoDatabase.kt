package com.assesment.fitpeoassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assesment.fitpeoassignment.model.PhotoDetail

@Database(entities = [PhotoDetail::class], version = 1)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao

    companion object {
        private var INSTANCE: PhotoDatabase? = null

        fun getDatabase(context: Context): PhotoDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    PhotoDatabase::class.java,
                    "photoDb"
                ).build()
            }
            return INSTANCE!!
        }
    }

}