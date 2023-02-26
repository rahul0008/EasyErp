package com.example.easyerp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.easyerp.database.entity.User

@Dao
interface DAO {

    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insert(User : User)

    @Update(onConflict =OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Query("Select * from User order by _index DESC LIMIT 1")
    fun getUser() :LiveData<User>

}