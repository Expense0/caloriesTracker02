package cn.itcast.caloriestracker02.domain.model

import java.util.Date

data class DailyNutritionPlan(
    val id: String,
    val userId: String,
    val date: Date,
    val remainingCalories: Int,
    val remainingProtein: Int,
    val remainingCarbs: Int,
    val remainingFats: Int,
    val mealIds: List<String>
)
