package com.binar.challengechapter4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class DataBaseNote: RoomDatabase() {

    abstract fun noteDao(): NoteDAO

    companion object{

        private var INSTANCE: DataBaseNote? = null

        fun getInstance(context : Context): DataBaseNote?{
            if (INSTANCE == null){
                synchronized(DataBaseNote::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    DataBaseNote::class.java, "Note.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInsrance(){
            INSTANCE = null
        }

    }
}