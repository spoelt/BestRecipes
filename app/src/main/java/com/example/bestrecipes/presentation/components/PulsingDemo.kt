package com.example.bestrecipes.presentation.components

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.bestrecipes.presentation.components.PulseAnimationDefinitions.PulseState.FINAL
import com.example.bestrecipes.presentation.components.PulseAnimationDefinitions.PulseState.INITIAL
import com.example.bestrecipes.presentation.components.PulseAnimationDefinitions.pulseDefinition
import com.example.bestrecipes.presentation.components.PulseAnimationDefinitions.pulsePropKey


@Composable
fun PulsingDemo() {
    val color = MaterialTheme.colors.primary

    val pulseAnim = transition(
        definition = pulseDefinition,
        initState = INITIAL,
        toState = FINAL
    )

    val pulseMagnitude = pulseAnim[pulsePropKey]
    Log.d("PulsingDemo", "PulsingDemo: $pulseMagnitude")

    Row(
        modifier = Modifier.fillMaxWidth().height(55.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(pulseMagnitude.dp)
                .width(pulseMagnitude.dp),
            imageVector = Icons.Default.Favorite
        )
    }

    Canvas(
        modifier = Modifier.fillMaxWidth().height(55.dp)
    ) {
        drawCircle(
            radius = pulseMagnitude,
            brush = SolidColor(color)
        )
    }
}

object PulseAnimationDefinitions {

    enum class PulseState {
        INITIAL, FINAL
    }

    val pulsePropKey = FloatPropKey("pulseKey")

    val pulseDefinition = transitionDefinition<PulseState> {
        state(INITIAL) { this[pulsePropKey] = 40f }
        state(FINAL) { this[pulsePropKey] = 50f }

        transition(
            INITIAL, FINAL
        ) {
            pulsePropKey using infiniteRepeatable(
                animation = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        }
    }
}