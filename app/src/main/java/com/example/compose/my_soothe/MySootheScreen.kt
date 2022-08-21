package com.example.compose.my_soothe

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.example.compose.my_soothe.BottomPage.*
import com.example.compose.ui.theme.MyAppTheme
import com.example.compose.ui.theme.Red700
import com.example.compose.ui.theme.Teal200
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MySootheApp(modifier: Modifier = Modifier) {
    var activeAnimation by remember { mutableStateOf(false) }
    var activeFloatingAnimation by remember { mutableStateOf(false) }
    var tabPage by remember { mutableStateOf(Home) }

    val backgroundColor = if (activeAnimation) {
        animateColorAsState(if (tabPage == Home) Red700 else Teal200).value
    } else {
        animateColorAsState(Color.White).value
    }

    MyAppTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    extended = activeFloatingAnimation,
                    onClick = {
                        activeFloatingAnimation = !activeFloatingAnimation
                    }
                )
            },
            bottomBar = {
                SootheBottomNavigation { tabPage = it }
            },
            backgroundColor = backgroundColor,
            modifier = Modifier.fillMaxHeight()
        ) { padding ->
            when (tabPage) {
                Home -> {
                    MySootheScreen(Modifier.padding(padding))
                }
                Profile -> {
                    Profile {
                        activeAnimation = it
                    }
                }
                Animation -> {
                    AnimationScreen()
                }
            }
        }
    }
}

@Composable
private fun FloatingActionButton(
    extended: Boolean,
    onClick: () -> Unit
) {

    FloatingActionButton(onClick = onClick) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )

            AnimatedVisibility(extended) {
                Text(
                    text = "edit",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 3.dp)
                )
            }
        }
    }
}

@Composable
fun Profile(onCheckedChange: (Boolean) -> Unit) {
    var checked by rememberSaveable { mutableStateOf(false) }
    Column {
        Checkbox(
            checked = checked, onCheckedChange = {
                checked = it
                onCheckedChange(it)
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = MaterialTheme.colors.primary,
                uncheckedColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationScreen() {
    var tabPage by remember { mutableStateOf(TabPage.Home) }
    var loadWeather by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    // Simulates loading weather data. This takes 3 seconds.
    suspend fun loadWeather() {
        if (loadWeather) {
            loadWeather = false
            delay(3000L)
            loadWeather = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .animateContentSize()
    ) {
        TabRow(tabPage = tabPage, onTabSelected = { tabPage = it })
        Spacer(Modifier.height(16.dp))

        if (loadWeather)
            Weather {
                coroutineScope.launch {
                    loadWeather()
                }
            }
        else LoadingWeather()
    }
}

@Composable
fun MySootheScreen(modifier: Modifier = Modifier) {
    var showNotSupportFeature by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {
        Spacer(Modifier.height(16.dp))
        NotSupportFeature(showNotSupportFeature)
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_title) {
            AlignYourBodyRow(modifier = Modifier.padding(8.dp)) {
                showNotSupportFeature = !showNotSupportFeature
            }
        }
        HomeSection(title = R.string.align_title) {
            FavoriteCollectionGrid(modifier = Modifier.padding(8.dp))
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title).uppercase(),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySoothePreview() {
    MyAppTheme {
        MySootheApp()
    }
}