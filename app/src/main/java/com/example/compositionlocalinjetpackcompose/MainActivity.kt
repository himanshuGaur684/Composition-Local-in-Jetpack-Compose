package com.example.compositionlocalinjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compositionlocalinjetpackcompose.ui.theme.CompositionLocalInJetpackComposeTheme


val LocalNavHostController = compositionLocalOf<NavHostController> { error("error") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalInJetpackComposeTheme {

                val navHostController = rememberNavController()

                CompositionLocalProvider(LocalNavHostController provides navHostController) {
                    AppNavigation()
                }

            }
        }
    }
}

@Composable
fun AppNavigation() {

    val navigator = LocalNavHostController.current

    NavHost(navController = navigator, startDestination = firstScreen) {
        composable(route = firstScreen) {
            FirstScreen()
        }
        composable(route = secondScreen) {
            SecondScreen()
        }
    }
}


const val firstScreen = "firstScreen"
const val secondScreen = "secondScreen"


@Composable
fun FirstScreen() {
    val navigator = LocalNavHostController.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "First SCreen")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                navigator.navigate(secondScreen)

            }) {
                Text(text = "Navigate to second screen")
            }
        }
    }

}

@Composable
fun SecondScreen() {
    val navigator = LocalNavHostController.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Second Screen")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                navigator.popBackStack()
            }) {
                Text(text = "Go Back")
            }
        }
    }

}


