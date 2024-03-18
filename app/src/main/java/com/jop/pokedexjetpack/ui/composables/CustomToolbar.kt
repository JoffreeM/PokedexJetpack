package com.jop.pokedexjetpack.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jop.pokedexjetpack.AppPokedex
import com.jop.pokedexjetpack.R
import com.jop.pokedexjetpack.ui.navigation.Screens
import com.jop.pokedexjetpack.ui.theme.ModeLight

@Composable
fun CustomToolBar(
    navController: NavController,
    @StringRes title: Int,
    showReturnBack: Boolean = false,
    showIconFavorite: Boolean = false,
    onIsDark: (Boolean) -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showReturnBack){
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = ""
                        )
                    }
                }else{
                    val isDark = remember { mutableStateOf(AppPokedex.sharedPreferences.isDarkTheme) }
                    IconButton(onClick = {
                        AppPokedex.sharedPreferences.isDarkTheme = !AppPokedex.sharedPreferences.isDarkTheme
                        isDark.value = AppPokedex.sharedPreferences.isDarkTheme
                        onIsDark(AppPokedex.sharedPreferences.isDarkTheme)
                    }) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id =
                                if(isDark.value) R.drawable.ic_light_mode else R.drawable.ic_dark_mode
                            ),
                            tint = if(isDark.value) MaterialTheme.colorScheme.secondary else ModeLight,
                            contentDescription = ""
                        )
                    }
                }
                CustomText(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(1f),
                    fontSize = 28,
                    colorText = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    text = stringResource(id = title)
                )
                if (showIconFavorite){
                    IconButton(onClick = { navController.navigate(Screens.FAVORITE) }) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(id = R.drawable.ic_stars),
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = ""
                        )
                    }
                }else{
                    Image(
                        modifier = Modifier.size(55.dp),
                        painter = painterResource(id = R.drawable.ic_app_foreground),
                        contentDescription = ""
                    )
                }
            }
        }
    ) {
        Box (
            modifier = Modifier.padding(it)
        ){
            Image(
                painter = painterResource(id = R.drawable.grama),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                content()
            }
        }

    }
}