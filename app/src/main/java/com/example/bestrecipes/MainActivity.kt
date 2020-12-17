package com.example.bestrecipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.bestrecipes.R.drawable.happy_meal_small
import androidx.compose.material.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent {
            Column {
                Image(asset = imageResource(
                    id = happy_meal_small)
                )
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Happy Meal")
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "800 Calories")
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(text = "$5.99")
                }
            }
        }
    }
}