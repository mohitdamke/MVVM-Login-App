package com.example.mypractice.data.state

data class SignOutState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
)