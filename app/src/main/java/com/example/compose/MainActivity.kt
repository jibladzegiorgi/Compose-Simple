package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
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
}

@Composable
fun MyApp() {
    var showOnboarding by rememberSaveable { mutableStateOf(true) }
    if (showOnboarding) {
        OnboardingScreen(onClick = {
            showOnboarding = false
        })
    } else {
        Greeting()
    }
}

@Composable
fun Greeting(nameList: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 5.dp)) {
        items(items = nameList) { name ->
            Surface(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ) {

                var expanded by rememberSaveable { mutableStateOf(false) }
                val extraPadding by animateDpAsState(
                    targetValue = if (expanded) 78.dp else 0.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )

                Row(modifier = Modifier.padding(12.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                    ) {
                        Text(text = "Hello, ")
                        Text(
                            text = name,
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                        if (expanded) {
                            Text(
                                text = ("Composem ipsum color sit lazy, " +
                                        "padding theme elit, sed do bouncy. ").repeat(4),
                            )
                        }
                    }

                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = null
                        )
                    }
                }
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
            Greeting()
        }
    }
}