package com.example.easyerp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.easyerp.database.AssignmentDB
import com.example.easyerp.database.entity.User
import com.example.easyerp.repository.Repository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    //used Android view model to get application context to create Database
    val repository: Repository
    var userLiveData: LiveData<User>

    //one way binding
    var first_name = MutableLiveData("")
    var last_name = MutableLiveData("")
    var phone_num = MutableLiveData("")

    //two way binding
    var _firstNameDetail = MutableLiveData("")
    var _lastNameDetail = MutableLiveData("")
    var _phoneNumDetail = MutableLiveData("")
    var _updatedDate = MutableLiveData("")

    init {
        val db = AssignmentDB.getDatabase(application)
        val dao = db.dao()
        repository = Repository(dao)
        userLiveData = repository.getUser
    }


    //inserting or updating database
    fun update() = viewModelScope.launch (Dispatchers.IO) {
        var user = userLiveData.value ?: User()

        Log.i("gg", "first_name: "+first_name.value)
        Log.i("gg", "last_name: "+last_name.value)
        Log.i("gg", "phone_num: "+phone_num.value)

        user.apply {
            firstName = first_name.value?:""
            lastName = last_name.value?:""
            phoneNo = phone_num.value?:""
            TimeStamp = getDate_Time()
        }

        Log.i("gg", "updatelivedata: "+ Gson().toJson(userLiveData.value))
        Log.i("gg", "update: "+Gson().toJson(user))

        if (userLiveData.value == null) {
            repository.insertUser(user)
        } else {
            repository.updateUser(user)
        }
    }

    //Util to get current date and time
    private fun getDate_Time(): String {
        val currentDate = Date()
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: String = format.format(currentDate)
        return date
    }

}