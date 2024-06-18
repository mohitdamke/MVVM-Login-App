package com.example.mypractice.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mypractice.data.state.SignOutState
import com.example.mypractice.navigation.Screens
import com.example.mypractice.presentation.viewmodel.SignInViewModel
import com.example.mypractice.presentation.viewmodel.SignOutViewModel
import com.google.android.play.integrity.internal.s
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SignOutViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val state = viewModel.signOutState.collectAsState(initial = null)
    val firebase = FirebaseAuth.getInstance()

    LaunchedEffect(key1 = firebase.currentUser) {
        scope.launch {
            if (firebase.currentUser == null) {
                navController.navigate(Screens.SignInScreen.route){
                    popUpTo(Screens.HomePage.route) { inclusive = true }
                }
            }

        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        LaunchedEffect(key1 = state.value?.isSuccess) {
            if (state.value?.isSuccess == "You have successfully logged out") {
                navController.navigate(Screens.SignInScreen.route) {
                    popUpTo(Screens.HomePage.route) { inclusive = true }
                }
            }
        }

        Text(text = "Home Page", fontSize = 30.sp, fontWeight = W700)

        Button(onClick = {
            scope.launch {
                viewModel.SignOutUser()
                Log.d("TAG", "HomePage: ${firebase.signOut()}")
                Log.d("TAG", "lovdu lalit: ${firebase.currentUser}")

            }
        }) {
            Text(text = "Logout", fontSize = 20.sp)
        }
    }
}