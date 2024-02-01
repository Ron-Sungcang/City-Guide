package com.example.cityguide.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cityguide.R
import com.example.cityguide.data.CityRepository
import com.example.cityguide.model.City
import com.example.cityguide.model.Recommendation
import com.example.cityguide.ui.theme.CityGuideTheme
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CityListItem(
    city: City,
    onItemClick: (City) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = {onItemClick(city)}
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.card_image_height))
        ){
            CityImageItem(
                city = city,
                modifier = Modifier.size(dimensionResource(id = R.dimen.card_image_height))
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
                .weight(1f)
            ) {
                Text(
                    text = stringResource(id = city.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(id = city.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}

/*
    This Function is to display images in list item
 */
@Composable
private fun CityImageItem(city: City, modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Image(
            painter = painterResource(id = city.imageRes),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillHeight
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecommendationListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = {onItemClick(recommendation)}
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.card_image_height))
        ){
            RecommendationImageItem(
                recommendation = recommendation,
                modifier = Modifier.size(dimensionResource(id = R.dimen.card_image_height))
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
                .weight(1f)
            ){
                Text(
                    text = stringResource(id = recommendation.nameRes),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(id = recommendation.addressRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun RecommendationImageItem(recommendation: Recommendation, modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Image(
            painter = painterResource(id =  recommendation.imageRes),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
            )
    }
}

@Preview
@Composable
fun CityListItemPreview(){
    CityGuideTheme {
        CityListItem(
            city = CityRepository.defaultCity,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun RecommendationListItemPreview(){
    CityGuideTheme {
        RecommendationListItem(
            recommendation = CityRepository.defaultRecommendation,
            onItemClick = {}
        )
    }
}