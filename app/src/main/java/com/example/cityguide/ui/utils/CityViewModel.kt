package com.example.cityguide.ui.utils

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.example.cityguide.R
import com.example.cityguide.data.CityRepository
import com.example.cityguide.model.City
import com.example.cityguide.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(
        CityUiState(
            categoryList = CityRepository.getCategories(),
            listOfRecommendations = CityRepository.getRecommendationData(),
            recommendationList = CityRepository.getRecommendationData().getOrElse(0){
                CityRepository.defaultRecommendationList
            },
            currentRecommendation = CityRepository.getRecommendationData().getOrElse(0){
                CityRepository.defaultRecommendationList
            }.getOrElse(0){
                CityRepository.defaultRecommendation
            },
            currentScreen = R.string.app_name
        )
    )
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentRecommendationsList(recommendations: List<Recommendation>, curCit: City){
        _uiState.update {
            it.copy(
                recommendationList = recommendations,
                currentCity = curCit,
                currentScreen = curCit.nameRes
                )
        }
    }

    fun updateCurrentRecommendation(recommendation: Recommendation){
        _uiState.update {
            it.copy(
                currentRecommendation = recommendation,
                currentScreen = recommendation.nameRes
                )
        }
    }

    fun navigateToCityListPage(){
        _uiState.update {
            it.copy(
                isShowingCityListPage = true,
                isShowingRecommendationListPage = false,
                currentScreen = R.string.app_name
            )
        }
    }

    fun navigateToRecommendedListPage() {
        _uiState.update {
            it.copy(
                isShowingCityListPage = false,
                isShowingRecommendationListPage = true
            )
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(
                isShowingCityListPage = false,
                isShowingRecommendationListPage = false
                )
        }
    }

}

data class CityUiState(
    val categoryList: List<City> = emptyList(),
    val currentCity: City = CityRepository.defaultCity,
    val listOfRecommendations: List<List<Recommendation>> = emptyList(),
    val recommendationList: List<Recommendation> = CityRepository.defaultRecommendationList,
    val currentRecommendation: Recommendation = CityRepository.defaultRecommendation,
    val isShowingCityListPage: Boolean = true,
    val isShowingRecommendationListPage: Boolean = false,
    val currentScreen: Int
)