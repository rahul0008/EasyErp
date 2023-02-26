package com.example.easyerp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.easyerp.database.dao.DAO
import com.example.easyerp.database.entity.User
import com.google.gson.Gson

class Repository(var dao: DAO) {

    //get live data from db
    val getUser: LiveData<User> = dao.getUser()

    //insert
    suspend fun insertUser(user: User) {
        Log.i("gg", "insertUser: "+Gson().toJson(user))
        dao.insert(user)
    }

    //update
    suspend fun updateUser(user: User) {
        Log.i("gg", "updateUser: "+Gson().toJson(user))
        dao.update(user)
    }

}