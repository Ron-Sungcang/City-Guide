package com.example.cityguide.ui

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cityguide.R
import com.example.cityguide.data.CityRepository
import com.example.cityguide.model.City
import com.example.cityguide.model.Recommendation
import com.example.cityguide.ui.theme.CityGuideTheme
import com.example.cityguide.ui.utils.CityContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
) {
    val viewModel: CityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> CityContentType.ListOnly

        WindowWidthSizeClass.Expanded -> CityContentType.ListAndDetail
        else -> CityContentType.ListOnly
    }

    Scaffold(
        topBar = {
            CityAppBar(
                currentScreenName = uiState.currentScreen,
                onBackButtonClick = {
                    if (uiState.isShowingRecommendationListPage) {
                        viewModel.navigateToCityListPage()
                    } else if (!uiState.isShowingCityListPage && !uiState.isShowingRecommendationListPage) {
                        viewModel.navigateToRecommendedListPage()
                    }
                },
                isShowingCityList = uiState.isShowingCityListPage
            )
        }
    ) { innerPadding ->
        if (uiState.isShowingCityListPage && !uiState.isShowingRecommendationListPage) {
            CityList(
                cities = uiState.categoryList,
                onClick = {
                    viewModel.updateCurrentRecommendationsList(
                        uiState.listOfRecommendations[it.id],
                        it
                    )
                    viewModel.navigateToRecommendedListPage()
                },
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        } else if (uiState.isShowingRecommendationListPage) {
            if (contentType == CityContentType.ListAndDetail) {
                RecommendationListAndDetails(
                    recommendations = uiState.recommendationList,
                    selectedRecommendation = uiState.currentRecommendation,
                    onClick = {
                        viewModel.updateCurrentRecommendation(it)
                    },
                    onBackButtonClick = {
                        viewModel.navigateToCityListPage()
                    })
            } else {
                if (contentType == CityContentType.ListOnly){
                    RecommendationList(
                        recommendations = uiState.recommendationList,
                        onClick = {
                            viewModel.updateCurrentRecommendation(it)
                            viewModel.navigateToDetailPage()
                        },
                        onBackButtonClick = {
                            viewModel.navigateToCityListPage()
                        },
                        contentPadding = innerPadding,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(id = R.dimen.padding_medium),
                                start = dimensionResource(id = R.dimen.padding_medium),
                                end = dimensionResource(id = R.dimen.padding_medium)
                            )
                    )
                }
                else {
                    RecommendationDetails(
                        selectedRecommendation = uiState.currentRecommendation,
                        onBackButtonClick = { viewModel.navigateToRecommendedListPage() },
                        contentPadding = innerPadding
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CityAppBar(
    @StringRes currentScreenName: Int,
    onBackButtonClick:() -> Unit,
    isShowingCityList: Boolean,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreenName))},
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if(!isShowingCityList){
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
private fun CityList(
    cities: List<City>,
    onClick: (City) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier,
        ) {
        items(cities, key = {city -> city.id }){city ->
            CityListItem(
                city = city,
                onItemClick = onClick)
        }
    }

}

@Composable
private fun RecommendationList(
    recommendations: List<Recommendation>,
    onClick: (Recommendation) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    BackHandler {
        onBackButtonClick()
    }
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ){
        items(recommendations,key = {recommendation -> recommendation.id}){recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onItemClick = onClick
            )
        }
    }
}

@Composable
fun RecommendationDetails(
    selectedRecommendation: Recommendation,
    onBackButtonClick: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackButtonClick()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ){
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box {
                    Image(
                        painter = painterResource(id = selectedRecommendation.imageRes),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ){
                    Text(
                        text = stringResource(selectedRecommendation.nameRes),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                    Text(
                        text = stringResource(selectedRecommendation.addressRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    )
                }
            }
            Text(
                text = stringResource(selectedRecommendation.descriptionRes),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
        }
    }
}

@Composable
fun RecommendationListAndDetails(
    recommendations: List<Recommendation>,
    selectedRecommendation: Recommendation,
    onClick: (Recommendation) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    Row(
        modifier = modifier
    ){
        RecommendationList(
            recommendations = recommendations,
            onClick = onClick,
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding()
            ),
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium)),
            onBackButtonClick = onBackButtonClick
        )
        RecommendationDetails(
            selectedRecommendation = selectedRecommendation,
            modifier = Modifier.weight(3f),
            onBackButtonClick = onBackButtonClick,
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding()
            )
        )
    }
}

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
                .size(dimensionResource(id = R.dimen.city_card_image_height))
        ){
            CityImageItem(
                city = city,
                modifier = Modifier.width(140.dp)
            )
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
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
                .size(dimensionResource(id = R.dimen.recommendation_card_image_height))
        ){
            RecommendationImageItem(
                recommendation = recommendation,
                modifier = Modifier.width(dimensionResource(id = R.dimen.recommendation_card_image_height))
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
            contentScale = ContentScale.FillHeight
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

@Preview
@Composable
fun CityListPreview() {
    CityGuideTheme {
        Surface {
            CityList(
                cities = CityRepository.getCategories(),
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun RecommendationListPreview(){
    CityGuideTheme {
        Surface {
            RecommendationList(
                recommendations = CityRepository.defaultRecommendationList,
                onClick = {},
                onBackButtonClick = {}
            )
        }
    }
}

@Preview
@Composable
fun previewRecommendedDetails(){
    CityGuideTheme {
        Surface {
            RecommendationDetails(
                selectedRecommendation = CityRepository.defaultRecommendation,
                onBackButtonClick = {},
                contentPadding = PaddingValues(0.dp)
            )
        }
    }
}

