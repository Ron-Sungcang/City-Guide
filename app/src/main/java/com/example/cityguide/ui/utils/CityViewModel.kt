package com.example.cityguide.ui.utils

import androidx.lifecycle.ViewModel
import com.example.cityguide.data.CityRepository
import com.example.cityguide.model.City
import com.example.cityguide.model.Recommendation

class CityViewModel: ViewModel() {

}

data class CityUiState(
    val categoryList: List<City> = emptyList(),
    val recommendationList: List<Recommendation> = CityRepository.defaultRecommendationList,
    val currentRecommendation: Recommendation = CityRepository.defaultRecommendation
)