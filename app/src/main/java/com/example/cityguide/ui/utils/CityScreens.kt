package com.example.cityguide.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.cityguide.model.City
import com.example.cityguide.model.Recommendation

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
        Image(
            painter = painterResource(id = city.imageRes),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun RecommendationListItem(){}

@Composable
private fun RecommendationImageItem(recommendation: Recommendation, modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Image(
            painter = painterResource(id =  recommendation.imageRes),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
            )
    }
}