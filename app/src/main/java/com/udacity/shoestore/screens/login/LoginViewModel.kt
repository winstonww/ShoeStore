package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {
    private var _emailText = MutableLiveData<String>()
    val emailText : LiveData<String>
        get() = _emailText

    private var _passwordText = MutableLiveData<String>()
    val passwordText : LiveData<String>
        get() = _passwordText

    private var _loginState = MutableLiveData<Boolean>()
    val loginState : LiveData<Boolean>
        get() = _loginState

    init {
        _emailText.value = ""
        _passwordText.value = ""
        _loginState.value = false
    }

    fun onLogin(email: String, password : String) {
        _emailText.value = email
        _passwordText.value = password
        _loginState.value = true
    }

    fun onRegister(email: String, password: String) {
        _emailText.value = email
        _passwordText.value = password
        _loginState.value = true
    }

    fun onEventLoginComplete() {
        _loginState.value = false
    }
}