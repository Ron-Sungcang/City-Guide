package com.example.cityguide.ui.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cityguide.model.City

@Composable
private fun CityListItem(
    city: City,
    onItemClick: (City) -> Unit,
    modifier: Modifier = Modifier
){

}

/*
    This Function is to display item in List Items
 */
@Composable
private fun CityImageItem(city: City, modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Row {

        }
    }
}

@Composable
private fun RecommendationListItem(){}