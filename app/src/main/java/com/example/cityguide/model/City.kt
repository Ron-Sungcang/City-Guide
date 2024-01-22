package com.example.cityguide.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    val id: Int,
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int
)
