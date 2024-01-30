package com.example.cityguide.ui.utils

import androidx.lifecycle.ViewModel
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
            recommendationList = CityRepository.getRecommendationData().getOrElse(0){
                CityRepository.defaultRecommendationList
            },
            currentRecommendation = CityRepository.getRecommendationData().getOrElse(0){
                CityRepository.defaultRecommendationList
            }.getOrElse(0){
                CityRepository.defaultRecommendation
            }
        )
    )
    val uiState: StateFlow<CityUiState> = _uiState

    fun updateCurrentRecommendationsList(recommendations: List<Recommendation>){
        _uiState.update {
            it.copy(recommendationList = recommendations)
        }
    }

    fun updateCurrentRecommendation(recommendation: Recommendation){
        _uiState.update {
            it.copy(currentRecommendation = recommendation)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }

}

data class CityUiState(
    val categoryList: List<City> = emptyList(),
    val recommendationList: List<Recommendation> = CityRepository.defaultRecommendationList,
    val currentRecommendation: Recommendation = CityRepository.defaultRecommendation,
    val isShowingListPage: Boolean = true
)