package com.jop.pokedexjetpack.ui.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jop.pokedexjetpack.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLoading(
    isLoading: Boolean = false
){
    if (isLoading){
        val rotation = remember { Animatable(0f) }
        LaunchedEffect(Unit) {
            while (true) {
                rotation.animateTo(
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1000),
                        repeatMode = RepeatMode.Restart
                    )
                )
                rotation.snapTo(0f)
            }
        }
        AlertDialog(
            onDismissRequest = {  }
        ) {
            Image(
                modifier = Modifier.size(100.dp).rotate(rotation.value),
                painter = painterResource(id = R.drawable.ic_app_foreground),
                contentDescription = ""
            )
        }
    }
}