package com.example.chapter_four

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.chapter_four.ui.theme.ChapterFourTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChapterFourTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MyApp(modifier = Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = { MyCenterAlignedTopBar() }, floatingActionButton = { MyFABbutton() },
        modifier = Modifier.fillMaxWidth(),
        bottomBar = { NewBottomBar() },
        snackbarHost = {},
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Color.White,
        contentColor = Color.Black,
        content = { paddingValues ->
            Text(
                text = "Test", modifier = Modifier
                    .padding(paddingValues)
                    .clickable(onClick = {}, onClickLabel = "Click me")
            )
        }
    )


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCenterAlignedTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Test")
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Yellow)
    )
}

@Composable
fun MyFABbutton() {
    FloatingActionButton(onClick = {}) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "new chat")
    }
}

@Composable
fun MyBottomBar() {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        containerColor = Color.Magenta,
        contentColor = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavigationItem(icon = Icons.Rounded.Home, text = "Home", onClick = {})
            BottomNavigationItem(icon = Icons.Rounded.ShoppingCart, text = "Cart", onClick = { })
            BottomNavigationItem(icon = Icons.Rounded.AccountCircle, text = "Account", onClick = { })
        }
    }
}

@Composable
fun BottomNavigationItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = text)
        Text(text = text)
    }
}


@Composable
fun NewBottomBar() {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(75.dp)
    ) {
        NavigationBarItem(
            selected = false, onClick = {}, icon = {
                BadgedBox(badge = {
                    Badge() {
                        Text(text = "5")
                    }
                }) {
                    Icon(imageVector = Icons.Rounded.Home, contentDescription = null)
                }
            }, label = { Text("Home") }
        )
        NavigationBarItem(
            selected = false, onClick = {}, icon = { Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = null) },
            label = { Text("Shop") }
        )
        NavigationBarItem(
            selected = false, onClick = {}, icon = { Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = null) },
            label = { Text("Account") }
        )
    }
}