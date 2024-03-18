package com.jop.pokedexjetpack.ui.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.jop.pokedexjetpack.R

@Composable
fun CustomFavorite(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    isActive: Boolean = true,
    onClickFavorite:() -> Unit
){
    IconButton(
        onClick = {
            onClickFavorite()
        },
        enabled = isActive
    ) {
        Icon(
            modifier = modifier,
            painter = painterResource(id =
                if (isFavorite)R.drawable.ic_favorite_yes else R.drawable.ic_favorite_not
            ),
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = ""
        )
    }

}