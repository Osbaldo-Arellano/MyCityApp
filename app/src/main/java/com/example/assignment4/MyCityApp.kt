package com.example.assignment4

import androidx.annotation.StringRes

enum class CupcakeScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Category(title = R.string.choose_category),
    Recommendation(title = R.string.choose_recommendation),
    Summary(title = R.string.recommendation_summary)
}

class MyCityApp {
}