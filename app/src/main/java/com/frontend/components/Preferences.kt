package com.frontend.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frontend.ui.theme.ResturantReccomendationFrontendTheme
import java.util.prefs.Preferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Preferences(padding: PaddingValues) {
    Column (
        Modifier.padding(start = 20.dp, end = 20.dp),
    ) {
        Text(
            text = "Preferences",
            fontSize = 25.sp,
            modifier = Modifier
                .padding(bottom = 20.dp, top = 40.dp)
        )

        var sliderPosition by remember { mutableFloatStateOf(0.5f) }
        Text(
            text = "Distance",
            fontSize = 20.sp,
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
        )
        Text(
            text = "Price Range",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 10.dp)

        )
        Row (
            Modifier
                .padding(start = 7.dp, end = 7.dp)
        ) {
            Text(
                text = "$",
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = "$",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
            Text(
                text = "$",
                modifier = Modifier
                    .weight(2f)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(
                text = "$",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Text(
                text = "$",
                modifier = Modifier
                    .wrapContentWidth(Alignment.End)
            )
        }
        Text(
            text = "Dietary Restrictions",
            fontSize = 20.sp,
            modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp)
        )
        Column (
            verticalArrangement = Arrangement.Center
        ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Halal"
                )
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Vegetarian"
                )
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Vegan"
                )
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Gluten-Free"
                )
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
        }
    }
}

@Preview(showBackground = true )
@Composable
fun PreferencesPreview() {
    ResturantReccomendationFrontendTheme {
        Preferences(padding = PaddingValues(start = 20.dp, end = 20.dp))
    }
}