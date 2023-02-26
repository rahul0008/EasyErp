package com.example.easyerp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.easyerp.database.dao.DAO
import com.example.easyerp.database.entity.User


@Database(entities = [User::class], version = 1, exportSchema = false)

abstract class AssignmentDB : RoomDatabase() {
    abstract fun dao(): DAO


    companion object {
        @Volatile
        private var INSTANCE: AssignmentDB? = null

        //create database
        fun getDatabase(context: Context): AssignmentDB {
            // if the INSTANCE is not null then return INSTANCE,
            // if it is, then create the database

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AssignmentDB::class.java,
                    AssignmentDB::class.java.name
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}

