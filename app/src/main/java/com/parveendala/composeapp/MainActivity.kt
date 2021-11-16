package com.parveendala.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parveendala.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
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
    val nameListState = remember { mutableStateListOf<String>("Name 1", "Name 2") }
    val nameTextFieldContent = remember { mutableStateOf<String>("") }
    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.wrapContentSize(align = Alignment.TopCenter)
        ) {
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
                    NameList(nameList = nameListState)
                }
                NameTextField(value = nameTextFieldContent.value) { newValue -> nameTextFieldContent.value = newValue }
                SubmitButton { nameListState.add(nameTextFieldContent.value) }
            }
        }
    }
}

@Composable
fun NameList(nameList: List<String>) {
    nameList.forEach { name ->
        NameListItem(name)
    }
}

@Composable
fun NameListItem(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .padding(16.dp)
            .clickable { println("Item CLicked") },
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun NameTextField(value: String, onValueChange: (newValue: String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange)
}

@Composable
fun SubmitButton(onButtonCLicked: () -> Unit) {
    Button(
        onClick = onButtonCLicked,
        modifier = Modifier
            .wrapContentSize(align = Alignment.Center)
            .padding(16.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = "Add New Item",
            modifier = Modifier.padding(32.dp, 12.dp),
            fontSize = 16.sp
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