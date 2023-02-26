package com.example.easyerp.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {
    @PrimaryKey
    var _index:Int = 0
    var firstName: String =""
    var lastName : String =""
    var phoneNo : String =""
    var TimeStamp : String =""
}