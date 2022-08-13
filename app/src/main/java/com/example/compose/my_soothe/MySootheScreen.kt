package com.example.compose.my_soothe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun MySootheApp(){
    SearchBar()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySoothePreview() {
    ComposeTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar()
            AlignYourBodyElement()
            FavoriteCollectionCard()
        }
    }
}