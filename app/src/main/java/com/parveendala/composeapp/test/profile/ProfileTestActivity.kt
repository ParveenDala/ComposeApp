package com.parveendala.composeapp.test.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parveendala.composeapp.R
import com.parveendala.composeapp.ui.theme.ComposeAppTheme

class ProfileTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            color = Color.LightGray,
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                color = Color.LightGray,
                modifier = Modifier.wrapContentSize(align = Alignment.TopCenter)
            ) {
                ProfileCard()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.background(Color.Gray),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    }
                }
            }
        }
    }
}

@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home Button", modifier = Modifier.padding(horizontal = 16.dp)) },
        title = { Text(text = "Home") },
        elevation = 8.dp
    )
}

@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Top),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture()
            ProfileContent()
        }
    }
}

@Composable
fun ProfilePicture() {
    Card(
        shape = CircleShape,
        border = BorderStroke(1.5.dp, color = Color.Green),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.pp1),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Profile Name",
            style = MaterialTheme.typography.h5
        )
        Text(
            text = "Online Status",
            style = MaterialTheme.typography.subtitle2,
            color = Color.Gray
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        MainScreen()
    }
}