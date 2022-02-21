package com.mycode.ticketbookingapp.profilefragment

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mycode.ticketbookingapp.database.AuthRepository

class ProfileViewModel(application:Application,activity: Activity): ViewModel() {
    private var alert:AlertDialog.Builder
    private val _navigateToEditProfile=MutableLiveData<Boolean>()
    val navigateToEditProfile:LiveData<Boolean>
    get()=_navigateToEditProfile
    private var authRepository: AuthRepository
    val loggedUser: LiveData<Boolean?>
        get()=authRepository.getUserLoggedMutableLiveData()

    private val _navigateToSettings=MutableLiveData<Boolean?>()
    val navigateTosettings:LiveData<Boolean?>
        get()=_navigateToSettings

    init {
        authRepository = AuthRepository(application)
        alert = AlertDialog.Builder(activity)
        _navigateToSettings.value=false
    }

    fun logOutAlertDialogBox(){

            alert.setMessage("Are you sure you want to Signout?")
                .setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
                    authRepository.signOut()
                }).setNegativeButton("NO", null)

            val alert1: AlertDialog= alert.create()
                alert1.show()
        }
    fun navigateToEditProfile(){
        _navigateToEditProfile.value=true
        Log.i(String(),"Hi")
    }

    fun navigateToEditProfileDone(){
        _navigateToEditProfile.value=false
    }

    fun navigateTosettings(){
        _navigateToSettings.value=true
    }

    fun navigateTosettingsdone(){
        _navigateToSettings.value=false
    }

}