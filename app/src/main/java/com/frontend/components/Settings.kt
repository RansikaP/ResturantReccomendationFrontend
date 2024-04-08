package com.frontend.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frontend.R
import com.frontend.ui.theme.ResturantReccomendationFrontendTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun Settings(padding: PaddingValues) {
    Column (
        Modifier.padding(start = 20.dp, end = 20.dp),
    ) {
        Text (
            text = "Settings",
            fontSize = 25.sp,
            modifier = Modifier
                .padding(bottom = 20.dp, top = 40.dp)
        )

        ProfileImage()

        Column(
            Modifier.padding(top = 20.dp, bottom = 150.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text (
                text = "User Settings",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .weight(1f)
                    .background(
                        Color(0xFF7DCEA0))
            )
            Text (
                text = "Notifications",
                modifier = Modifier
                    .weight(1f)
            )
            Text (
                text = "Security and Privacy",
                modifier = Modifier
                    .weight(1f)
            )
            Text (
                text = "Support",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(Alignment.Top)
            )
        }
    }
}

@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(imageUri.value.ifEmpty { R.drawable.ic_user }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text (text = "Name")
    }
}

@Preview(showBackground = true )
@Composable
fun SettingsPreview() {
    ResturantReccomendationFrontendTheme {
        Settings(padding = PaddingValues(start = 20.dp, end = 20.dp))
    }
}