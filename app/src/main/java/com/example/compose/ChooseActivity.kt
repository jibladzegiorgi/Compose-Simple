package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.basic_code_lab.startGreetingActivity
import com.example.compose.my_soothe.startMySoothActivity
import com.example.compose.ui.theme.MyAppTheme

class ChooseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val ctx = LocalContext.current
        Button(onClick = {
            startGreetingActivity(ctx)
        }) {
            Text(text = "Greeting Screen")
        }
        Button(onClick = { startMySoothActivity(ctx) }) {
            Text(text = "My Soothe Screen")
        }
        Button(onClick = { }) {
            Text(text = "Crane Screen")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MyApp()
        }
    }
}