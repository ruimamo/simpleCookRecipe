package com.example.simplecookrecipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun OkazuDetail(okazu: String, navController: NavController) {
    Column {
        Text(text = okazu)
        Button(onClick = { navController.navigate("Home") }) {
            Text("go back.")
        }
    }
}
