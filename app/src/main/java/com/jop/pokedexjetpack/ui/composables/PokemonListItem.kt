package com.jop.pokedexjetpack.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jop.domain.entities.PokemonEntity
import com.jop.pokedexjetpack.ui.screen.home.view.model.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListItem(
    pokemon: PokemonEntity,
    viewModel: HomeViewModel? = null,
    isActiveFavorite: Boolean = true,
    onClickIdPokemon: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .heightIn(min = 40.dp),
        onClick = { onClickIdPokemon(pokemon.idPokemon) },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomImage(
                modifier = Modifier.size(80.dp).weight(.5f),
                urlImage = pokemon.imageUrl
            )
            CustomText(
                modifier = Modifier.weight(1f),
                fontSize = 19,
                colorText = Color.Black,
                fontWeight = FontWeight.Bold,
                text = pokemon.name.capitalize()
            )
            val isFavorite = remember { mutableStateOf(pokemon.isFavorite) }
            CustomFavorite(
                modifier = Modifier.size(40.dp).weight(.5f),
                isFavorite = isFavorite.value,
                isActive = isActiveFavorite,
                onClickFavorite = {
                    isFavorite.value = !isFavorite.value
                    viewModel?.updateFavorite(pokemonId = pokemon.idPokemon, isFavorite = isFavorite.value)
                }
            )
        }
    }
}