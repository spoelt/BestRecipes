package com.example.bestrecipes.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        // check logically if we're in portrait or landscape mode
        // (only use when custom layouts are needed for certain screen sizes)
        /*WithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val constraints = if (minWidth < 600.dp) {
                // we are in portrait mode
                myDecoupledConstraints(0.3f)
            } else {
                myDecoupledConstraints(0.7f)
            }*/

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
            // constraintSet = constraints
        ) {
            // how to give something an ID with Compose:
            val progressBar = createRef()
            // val (progressBar, text) = createRefs()

            // you can also easily create guidelines for your layout:
            // val topGuideline = createGuidelineFromTop(0.3f) // 30% from top

            CircularProgressIndicator(
                // add layout ID if you're using decoupled constraints:
                // modifier = Modifier.layoutId("progressBar"),
                color = MaterialTheme.colors.primary,
                modifier = Modifier.constrainAs(progressBar) {
                    // top.linkTo(topGuideline) -- when using guideline
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            /*Text(
                modifier = Modifier.layoutId("text"),
                text = "Loading...",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = TextUnit.Companion.Sp(15),
                )
                *//*modifier = Modifier.constrainAs(text) {
                        top.linkTo(progressBar.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }*//*
                )*/
        }
        //}

        // old layout now replaced with ConstraintLayout
        /*Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }*/
    }
}

/*
private fun myDecoupledConstraints(verticalBias: Float): ConstraintSet {
    return ConstraintSet {
        val guideline = createGuidelineFromTop(verticalBias)
        val progressBar = createRefFor("progressBar")
        val text = createRefFor("text")

        constrain(progressBar) {
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(text) {
            top.linkTo(progressBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}*/
