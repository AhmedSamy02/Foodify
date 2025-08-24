package com.example.foodify.presentation.screens.add_recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.components.ValidatedOutlinedTextField


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddRecipeSelectorWithChip(
    onItemSelected: (Int) -> Unit = {},
    title: String,
    list: List<String>,
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SelectorCard {
            FlowRow(
                Modifier.padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                list.forEachIndexed { index, item ->
                    SelectorChip(
                        text = item,
                        isSelected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            onItemSelected(index)
                        }
                    )
                }
            }
        }
        SelectorCardLabel(title)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddRecipeSelectorWithChipMultiple(
    viewModelList: MutableList<String>,
    title: String,
    list: List<String>,
) {
    val selectedIndices = remember { mutableStateListOf<Int>() }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SelectorCard {
            FlowRow(
                Modifier.padding(top = 40.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                list.forEachIndexed { index, item ->
                    SelectorChip(
                        text = item,
                        isSelected = index in selectedIndices,
                        onClick = {
                            if (index in selectedIndices) {
                                selectedIndices.remove(index)
                                viewModelList.remove(list[index])
                            } else {
                                selectedIndices.add((index))
                                viewModelList.add(list[index])

                            }
                        }
                    )
                }
            }
        }
        SelectorCardLabel(title)
    }
}

@Composable
fun AddRecipeSelectorWithTF(
    title: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    validator: (String) -> String?,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SelectorCard {
            ValidatedOutlinedTextField(onValueChange, validator, placeHolderText, modifier = Modifier.padding(top=36.dp).fillMaxWidth())
        }
        SelectorCardLabel(title)
    }
}
