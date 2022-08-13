package com.example.compose.my_soothe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.example.compose.ui.theme.ComposeTheme

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(text = "Search")
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            contentDescription = null,
            painter = painterResource(R.drawable.workout),
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Inversions",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FavoriteCollectionCard(modifier: Modifier = Modifier) {
    Surface(shape = MaterialTheme.shapes.small, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(192.dp)) {
            Image(
                painter = painterResource(id = R.drawable.nature),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Text(
                text = "Nature meditation",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun Preview() {
    ComposeTheme {
        Column {
            FavoriteCollectionCard(modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CardPreview() {
    ComposeTheme {
        Column {
            AlignYourBodyElement(modifier = Modifier.padding(8.dp))
        }
    }
}