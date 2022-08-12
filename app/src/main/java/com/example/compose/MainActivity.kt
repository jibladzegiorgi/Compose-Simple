package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.screen.OnboardingPreview
import com.example.compose.screen.OnboardingScreen
import com.example.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                   MyApp()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

@Composable
fun MyApp() {
    var showOnboarding by remember { mutableStateOf(true) }
    if (showOnboarding) {
        OnboardingScreen(onClick = {
            showOnboarding = false
        })
    } else {
        Greeting("Android")
        Greeting("World")
    }
}

@Composable
fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        var expanded by remember{ mutableStateOf(false) }
        val extraPadding = if (expanded) 48.dp else 0.dp
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello $name!")
                Text(text = "Hello Compose!")
            }
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(if (expanded)"Show less" else "Show more")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Greeting("Android")
            Greeting("asdasd")
        }
    }
}