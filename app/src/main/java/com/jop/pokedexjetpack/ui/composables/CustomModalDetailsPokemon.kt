package com.jop.pokedexjetpack.ui.composables

import androidx.annotation.StringRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jop.domain.models.response.details.PokemonDetails
import com.jop.pokedexjetpack.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalDetailsPokemon(
    showModal: Boolean,
    pokemonDetails: PokemonDetails? = null,
    onClose: () -> Unit = {}
){
    if (showModal) {
        AlertDialog(
            onDismissRequest = onClose,
            modifier = Modifier.background(Color.Transparent),
        ) {
            var visible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { visible = true }
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
            Card(
                modifier = Modifier
                    .offset(y = offsetY)
                    .fillMaxWidth()
                    .graphicsLayer(
                        rotationY = rotationY,
                        transformOrigin = TransformOrigin.Center
                    ),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                elevation = CardDefaults.cardElevation(10.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Box (
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.grama),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        CustomText(
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            colorText = Color.White,
                            fontSize = 28,
                            text = pokemonDetails?.name.toString().capitalize()
                        )
                        CustomImage(
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.CenterHorizontally),
                            urlImage = pokemonDetails?.imageUrl.toString()
                        )
                        DetailsView(label = R.string.details_type, valor = pokemonDetails?.type.toString())
                        DetailsView(label = R.string.details_hp, valor = pokemonDetails?.hp.toString())
                        DetailsView(label = R.string.details_speed, valor = pokemonDetails?.speed.toString())
                        DetailsView(label = R.string.details_attack, valor = pokemonDetails?.attack.toString())
                        DetailsView(label = R.string.details_defense, valor = pokemonDetails?.defense.toString())
                        DetailsView(label = R.string.details_specialAttack, valor = pokemonDetails?.specialAttack.toString())
                        DetailsView(label = R.string.details_specialDefense, valor = pokemonDetails?.specialDefense.toString())
                        DetailsView(label = R.string.details_height, valor = pokemonDetails?.height.toString())
                        DetailsView(label = R.string.details_weight, valor = pokemonDetails?.weight.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun DetailsView(
    @StringRes label: Int,
    valor: String
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalArrangement = Arrangement.Center
    ){
        CustomText(fontSize = 18,colorText = Color.White,fontWeight = FontWeight.Bold,text = label)
        CustomSpace(width = 5)
        CustomText(fontSize = 18,colorText = Color.White, text = valor)
    }
}