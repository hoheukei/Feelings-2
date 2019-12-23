package com.example.feelings

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(Feeling::class), version = 1)
public abstract class FeelingDatabase : RoomDatabase(){

    abstract fun feelingdao(): Feelingdao

    companion object{
        @Volatile
        private var INSTANSE : FeelingDatabase? = null
    }

    fun getDatabase(context: Context): FeelingDatabase{
        var tempDB = INSTANSE
        if(tempDB != null){
            return tempDB
        }
        synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FeelingDatabase::class.java,
                "feeling_db"
            ).build()
            INSTANSE = instance
            return instance
        }
    }
}