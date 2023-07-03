package com.example.project.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.HomeScreen


@Composable
fun SecondActivity() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Rock, paper, scissors",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            // modifier = Modifier.padding(vertical = 100.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 200.dp)


        ) {
            Text(
                text = "Choose the action:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 50.dp)
            )
            Button(
                onClick = {},
                modifier = Modifier.padding(bottom = 10.dp),
            ) {
                Text(text = "Siema Eniu")
            }
            Button(
                onClick = { /* Akcja sprawdzenia historii */ }
            ) {
                Text(text = "BUhahaha!")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SecondActivityPreview() {
    SecondActivity()
}