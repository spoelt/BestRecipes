package com.example.bestrecipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.bestrecipes.R.drawable.happy_meal_small
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent {
            ScrollableColumn(
                modifier = Modifier
                    //.fillMaxSize()
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color(0xFFF2F2F2))
            ) {
                Image(
                    asset = imageResource(
                        id = happy_meal_small
                    ),
                    // add the following lines to restrict the screen real estate that
                    // the image is allowed to occupy
                    // (especially important when using larger images!)
                    modifier = Modifier.height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Happy Meal",
                        style = TextStyle(
                            fontSize = TextUnit.Sp(26)
                        )
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = "800 Calories",
                        style = TextStyle(
                            fontSize = TextUnit.Sp(17)
                        )
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = "$5.99",
                        style = TextStyle(
                            color = Color.Red,
                            fontSize = TextUnit.Sp(17)
                        )
                    )
                }
            }
        }
    }
}