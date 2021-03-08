package com.example.bestrecipes.presentation.components

import android.util.Log
import androidx.compose.animation.transition
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bestrecipes.presentation.components.util.ShimmerAnimationDefinitions
import com.example.bestrecipes.presentation.components.util.ShimmerAnimationDefinitions.AnimationState.END
import com.example.bestrecipes.presentation.components.util.ShimmerAnimationDefinitions.AnimationState.START

@Composable
fun LoadingRecipeListShimmer(
    imageHeight: Dp,
    padding: Dp = 16.dp
) {
    WithConstraints {

        val cardWidthPx = with(AmbientDensity.current) {
            ((maxWidth - (padding * 2)).toPx())
        }

        val cardHeightPx = with(AmbientDensity.current) {
            ((imageHeight - (padding)).toPx())
        }

        val cardAnimationDefinition = remember {
            ShimmerAnimationDefinitions(
                widthPx = cardWidthPx,
                heightPx = cardHeightPx
            )
        }

        val cardShimmerTranslateAnim = transition(
            definition = cardAnimationDefinition
                .shimmerTransitionDefinition,
            initState = START,
            toState = END
        )

        val colors = listOf(
            Color.LightGray.copy(alpha = 0.9f),
            Color.LightGray.copy(alpha = 0.2f),
            Color.LightGray.copy(alpha = 0.9f)
        )

        val xCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.xShimmerPropKey]
        val yCardShimmer = cardShimmerTranslateAnim[cardAnimationDefinition.yShimmerPropKey]
        Log.d("Shimmer", "x: $xCardShimmer, y: $yCardShimmer")

        ScrollableColumn() {
            repeat(5) {
                ShimmerRecipeCardItem(
                    colors = colors,
                    cardHeight = imageHeight,
                    xShimmer = xCardShimmer,
                    yShimmer = yCardShimmer,
                    padding = padding,
                    gradientWidth = cardAnimationDefinition.gradientWidth
                )
            }
        }
    }
}