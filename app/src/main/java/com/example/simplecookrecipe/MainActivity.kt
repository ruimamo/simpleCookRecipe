package com.example.simplecookrecipe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplecookrecipe.ui.theme.SimpleCookRecipeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCookRecipeTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "Home") {
                        composable("Home") {
                            OkazuList(navController)
                        }
                        composable("detail/{okazu}") {
                                backStackEntry ->
                            val okazu = backStackEntry.arguments?.getString("okazu") ?: ""
                            OkazuDetail(okazu = okazu, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OkazuList(navController: NavController) {
    val okazuList = listOf("ぶりの照り焼き", "回鍋肉", "ポークチャップ", "ソース炒め")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "OkazuList",
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(30.0F, type = TextUnitType.Sp)
            )
        )
        LazyColumn {
            items(okazuList) { okazu ->
                ClickableText(
                    text = AnnotatedString(okazu),
                    modifier = Modifier.padding(15.dp)
                ) { offset ->
                    navController.navigate("detail/$okazu")
                }
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleCookRecipeTheme {
        OkazuList(navController = rememberNavController())
    }
}