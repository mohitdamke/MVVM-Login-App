package com.example.mypractice.data.state

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    object Failure : AuthState()


}