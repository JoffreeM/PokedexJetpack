package com.jop.pokedexjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jop.pokedexjetpack.ui.theme.PokedexJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokedexJetpackTheme {
        var modalVisible by remember { mutableStateOf(false) }

        Button(onClick = { modalVisible = true }) {
            Text("Show Modal")
        }

        ModalWithAnimation(showModal = modalVisible) {
            modalVisible = false
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalWithAnimation(
    showModal: Boolean,
    onClose: () -> Unit
) {
    if (showModal) {
        AlertDialog(
            onDismissRequest = onClose,
            modifier = Modifier.background(Color.Transparent),
        ) {
            CardWithFlipAnimation(onClose)
        }
    }
}

@Composable
fun CardWithFlipAnimation(onClose: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    val offsetY by animateDpAsState(
        if (visible) 0.dp else 300.dp,
        tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    val rotationY by animateFloatAsState(
        targetValue = if (visible) 0f else 180f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    Box(
        modifier = Modifier
            .offset(y = offsetY)
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.White)
            .graphicsLayer(
                rotationY = rotationY,
                transformOrigin = TransformOrigin.Center
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "This is a modal")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onClose) {
                Text("Close")
            }
        }
    }
}