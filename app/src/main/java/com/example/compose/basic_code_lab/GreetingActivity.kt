package com.example.compose.basic_code_lab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.MyAppTheme

fun startGreetingActivity(context: Context) {
    context.startActivity(Intent(context, GreetingActivity::class.java))
}

class GreetingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var showOnboarding by rememberSaveable { mutableStateOf(true) }
    if (showOnboarding) {
        OnboardingScreen(onClick = {
            showOnboarding = false
        })
    } else {
        GreetingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    MyAppTheme {
        Greeting()
    }
}