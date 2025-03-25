package cn.itcast.caloriestracker02.domain.model

data class FoodItem(
    val id: String,
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int
)
