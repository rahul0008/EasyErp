package com.example.easyerp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.easyerp.R
import com.example.easyerp.database.entity.User
import com.example.easyerp.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity(){

    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        binding.mainViewModel = viewModel
        binding.lifecycleOwner=this

        viewModel.userLiveData.observe(this, userObserver)
    }

    //observer of livedata
    private var userObserver: Observer<User> = Observer {
        val user = it?:User() as User
        Log.i("gg", "userObserver: "+ Gson().toJson(user))
        viewModel._firstNameDetail.value=user.firstName ?: "-"
        viewModel._lastNameDetail.value=user.lastName ?: "-"
        viewModel._phoneNumDetail.value=user.phoneNo ?: "-"
        viewModel._updatedDate.value=user.TimeStamp ?: "-"
    }


}