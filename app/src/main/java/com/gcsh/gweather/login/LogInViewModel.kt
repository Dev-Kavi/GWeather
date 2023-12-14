package com.gcsh.gweather.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LogInViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Initial)
    val loginResult: StateFlow<LoginResult> = _loginResult

    fun loginUser(
        username: String, password: String, savedUsername: String?, savedPassword: String?
    ) {
        if (username == savedUsername && password == savedPassword) {
            _loginResult.value = LoginResult.Success
        } else {
            _loginResult.value = LoginResult.Failure("Invalid username or password")
        }
    }
}

sealed class LoginResult {
    data object Initial : LoginResult()
    data object Success : LoginResult()
    data class Failure(val error: String) : LoginResult()
}