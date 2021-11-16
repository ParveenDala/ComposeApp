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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.parveendala.composeapp.ui.theme.ComposeAppTheme

class ProfileTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                UserListScreen(userProfileList = userProfileList)
            }
        }
    }
}

@Composable
private fun UserListScreen(userProfileList: List<UserProfile>) {
    Scaffold(topBar = { AppBar("User List") }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(userProfileList) { userProfileTest ->
                    ProfileCard(userProfileTest)
                }
            }
        }
    }
}

@Composable
private fun UserDetailsScreen(userId: Int = 0) {
    val userProfile = userProfileList.first { userId == it.id }
    Scaffold(topBar = { AppBar("User Details") }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfilePicture(userProfile.pictureUrl, userProfile.onlineStatus, 250.dp)
                ProfileContent(userProfile.name, userProfile.onlineStatus, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar(
        navigationIcon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home Button", modifier = Modifier.padding(horizontal = 16.dp)) },
        title = { Text(text = title) },
        elevation = 8.dp
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card(
        modifier = Modifier
            .padding(16.dp, 12.dp, 16.dp, 4.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Top),
        backgroundColor = Color.White,
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.onlineStatus, 72.dp)
            ProfileContent(userProfile.name, userProfile.onlineStatus, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
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
            modifier = Modifier.size(imageSize)
        )
    }
}

@Composable
fun ProfileContent(name: String, onlineStatus: Boolean, horizontalAlignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(4.dp),
        horizontalAlignment = horizontalAlignment
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
fun UserListScreenPreview() {
    ComposeAppTheme {
        UserListScreen(userProfileList)
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailsScreenPreview() {
    ComposeAppTheme {
        UserDetailsScreen(1)
    }
}