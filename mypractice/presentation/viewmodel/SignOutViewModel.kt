package com.example.mypractice.presentation.viewmodel

import androidx.core.app.PendingIntentCompat.send
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypractice.data.state.SignInState
import com.example.mypractice.data.state.SignOutState
import com.example.mypractice.domain.repository.AuthRepository
import com.example.mypractice.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignOutViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _signOutState = Channel<SignOutState>()
    val signOutState = _signOutState.receiveAsFlow()

    fun SignOutUser() = viewModelScope.launch {
        repository.signOutUser().collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signOutState.send(SignOutState(isSuccess = "You have successfully Sign Out of account"))
                }

                is Resource.Error -> {
                    _signOutState.send(SignOutState(isError = result.message.toString()))
                }

                is Resource.Loading -> {
                    _signOutState.send(SignOutState(isLoading = true))
                }
            }

        }
    }
}