package com.jop.pokedexjetpack.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.geometry.Size
import com.jop.pokedexjetpack.R

@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    urlImage: String,
) {
    var isLoading by remember { mutableStateOf(true) }
    Box (
        modifier = modifier
    ){
        AsyncImage(
            modifier = modifier,
            model = urlImage,
            onLoading = {
                isLoading = true
            },
            onSuccess = {
                isLoading = false
            },
            onError = {
                isLoading = false
            },
            error = painterResource(id = R.drawable.ic_image_error),
            contentDescription = "",
        )
        if (isLoading){
            CircularProgressIndicator(
                modifier = modifier.padding(15.dp)
            )
        }
    }
}