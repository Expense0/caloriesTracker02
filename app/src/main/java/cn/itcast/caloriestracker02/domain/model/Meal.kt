package cn.itcast.caloriestracker02.domain.model

import cn.itcast.caloriestracker02.domain.utils.UNKNOWN_ID
import java.util.Date


/**
 * 每一餐
 */
data class Meal(
    val id: String,
    val userId: String,
    val mealTime: Date,
    val foodItemIds: List<String>,
    val totalCalories: Int,
    val totalProtein: Int,
    val totalCarbs: Int,
    val totalFats: Int
)
