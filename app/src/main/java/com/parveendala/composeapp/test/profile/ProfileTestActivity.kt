package com.parveendala.composeapp.test.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
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
private fun MainScreen(userProfileTestList: List<UserProfileTest> = userProfileList) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(align = Alignment.TopCenter)
            ) {
                LazyColumn {
                    items(userProfileTestList) { userProfileTest ->
                        ProfileCard(userProfileTest)
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
fun ProfileCard(userProfileTest: UserProfileTest) {
    Card(
        modifier = Modifier
            .padding(16.dp, 12.dp, 16.dp, 4.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Top),
        backgroundColor = Color.White,
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture(userProfileTest.pictureUrl, userProfileTest.onlineStatus)
            ProfileContent(userProfileTest.name, userProfileTest.onlineStatus)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean) {
    Card(
        shape = CircleShape,
        border = BorderStroke(1.5.dp, color = if (onlineStatus) Color.Green else Color.Red),
        modifier = Modifier.padding(16.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Image(
            painter = rememberImagePainter(pictureUrl,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(72.dp)
        )
    }
}

@Composable
fun ProfileContent(name: String, onlineStatus: Boolean) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = if (onlineStatus) "Available" else "Offline",
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