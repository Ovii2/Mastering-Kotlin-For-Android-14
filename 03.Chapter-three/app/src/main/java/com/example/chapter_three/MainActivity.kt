package com.example.chapter_three

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chapter_three.ui.theme.ChapterthreeTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterthreeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Counter: $count", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = { count++ }, modifier = Modifier.padding(end = 8.dp)) {
                Text("Increment")
            }
            Button(onClick = { count-- }, colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)) {
                Text("Decrement")
            }
        }

        Box(modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center) {
            Icon(modifier = Modifier.size(80.dp), imageVector = Icons.Outlined.Notifications, contentDescription = null, tint = Color.Green)
            Text("9")
        }
    }
    LazyVerticalGrid(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)
        .padding(8.dp), columns = GridCells.Fixed(3)) {
        items(100) {
            Text(modifier = Modifier.padding(8.dp), text = "Item number $it")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCounter() {
    MyApp()
}
