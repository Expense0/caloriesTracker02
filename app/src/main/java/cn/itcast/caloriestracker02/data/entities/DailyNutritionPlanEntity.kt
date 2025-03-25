package cn.itcast.caloriestracker02.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "daily_nutrition_plans")
data class DailyNutritionPlanEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val date: Date,
    val remainingCalories: Int,
    val remainingProtein: Int,
    val remainingCarbs: Int,
    val remainingFats: Int,
    val mealIds: String // 存储餐食ID列表，可以用逗号分隔的字符串
)