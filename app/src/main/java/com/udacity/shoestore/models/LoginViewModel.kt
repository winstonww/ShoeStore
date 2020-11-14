package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class LoginState() {
    LOGIN,
    REGISTER,
    NOOP
}
class LoginViewModel() : ViewModel() {
    private var _emailText = MutableLiveData<String>()
    val emailText : LiveData<String>
        get() = _emailText

    private var _passwordText = MutableLiveData<String>()
    val passwordText : LiveData<String>
        get() = _passwordText

    private var _loginState = MutableLiveData<LoginState>()
    val loginState : LiveData<LoginState>
        get() = _loginState

    init {
        _emailText.value = ""
        _passwordText.value = ""
        _loginState.value = LoginState.NOOP
    }

    fun onRegister(email: String, password : String) {
        _emailText.value = email
        _passwordText.value = password
        _loginState.value = LoginState.REGISTER
    }

    fun onLogin(email: String? = null, password: String? = null) {
        _emailText.value = email ?: _emailText.value
        _passwordText.value = password ?: _passwordText.value
        _loginState.value = LoginState.LOGIN
    }

    fun onEventLoginComplete() {
        _loginState.value = LoginState.NOOP
    }
}