package com.jop.pokedexjetpack.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    style: TextStyle = TextStyle(),
    colorText: Color = Color.Gray,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = modifier,
            text = stringResource(id = text),
            color = colorText,
            style = style,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontSize = fontSize.sp
        )
    }
}

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TextStyle(),
    colorText: Color = Color.Gray,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontSize: Int = 16
) {
    Row(
        modifier = modifier
    ) {
        Text(
            modifier = modifier,
            style = style,
            text = text,
            color = colorText,
            fontWeight = fontWeight,
            textAlign = textAlign,
            fontSize = fontSize.sp
        )
    }
}