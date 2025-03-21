package cn.itcast.caloriestracker02.domain.model

import cn.itcast.caloriestracker02.domain.utils.UNKNOWN_ID


/**
 * 每一餐
 */
data class Meal(
    val id: Long = UNKNOWN_ID,
    val mealId: Long = UNKNOWN_ID,
    val name: String,
    val grams: Float,
    val cals: Float = 0f,
    val proteins: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f,
    val caloriesIn100Grams: Float,
    val proteinsIn100Grams: Float,
    val carbsIn100Grams: Float,
    val fatIn100Grams: Float,
)
