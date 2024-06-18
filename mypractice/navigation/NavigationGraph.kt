package com.example.mypractice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mypractice.domain.repository.AuthRepository
import com.example.mypractice.presentation.screens.HomePage
import com.example.mypractice.presentation.screens.LoadingScreen
import com.example.mypractice.presentation.screens.SignIn
import com.example.mypractice.presentation.screens.SignUp

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LoadingPage.route
    ) {
        composable(route = Screens.SignInScreen.route) {
            SignIn(navController = navController)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUp(navController = navController)
        }
        composable(route = Screens.HomePage.route) {
            HomePage(navController = navController)
        }
        composable(route = Screens.LoadingPage.route) {
            LoadingScreen(navController = navController)
        }
    }
}