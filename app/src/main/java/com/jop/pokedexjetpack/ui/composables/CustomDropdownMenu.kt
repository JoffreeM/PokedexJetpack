package com.jop.pokedexjetpack.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.jop.pokedexjetpack.R
import com.jop.pokedexjetpack.ui.items.ItemDropdownMenu

@Composable
fun CustomDropdownMenu(
    modifier: Modifier,
    placeholderDropdownMenu: String = stringResource(id = R.string.select_text_default),
    dataItems:List<ItemDropdownMenu>,
    optionAllItem: Boolean = false,
    onValueData:((ItemDropdownMenu) -> Unit)?=null,
    value: String? =null,
){
    var expand by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(Size.Zero) }

    Box (
        modifier = modifier.fillMaxWidth()
    ){
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = { expand = !expand },
                modifier = modifier
                    .onGloballyPositioned { coordinates ->
                        size = coordinates.size.toSize()
                    }
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
            ) {
                CustomText(
                    modifier = Modifier.fillMaxWidth(),
                    text = value.takeIf { it?.isNotBlank() == true } ?: placeholderDropdownMenu,
                    fontSize = 17,
                    colorText = Color.DarkGray
                )
            }
        }
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { size.width.toDp() })
                .height(IntrinsicSize.Max)
                .background(
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(10.dp)
                ),
            expanded = expand,
            onDismissRequest = { expand = false }
        ) {
            AnimatedVisibility(visible = expand) {
                Column {
                    val newList = dataItems.toMutableList()
                    if (optionAllItem) newList.add(0, ItemDropdownMenu("","Todos"))
                    newList.toList().forEach { items ->
                        DropdownMenuItem(
                            text = {
                                CustomText(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = items.name,
                                    colorText = Color.Black,
                                    fontSize = 17,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            onClick = {
                                expand = false
                                onValueData?.invoke(items)
                            },
                            modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
                        )
                    }
                }
            }
        }
    }

}