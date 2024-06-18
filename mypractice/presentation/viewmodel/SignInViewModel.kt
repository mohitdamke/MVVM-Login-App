package com.example.mypractice.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypractice.data.state.SignInState
import com.example.mypractice.domain.repository.AuthRepository
import com.example.mypractice.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        repository.loginUser(email, password).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "You have successfully login into account"))
                }


                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message.toString()))
                }

                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
            }

        }
    }
}