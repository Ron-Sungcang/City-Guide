package com.example.cityguide.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
