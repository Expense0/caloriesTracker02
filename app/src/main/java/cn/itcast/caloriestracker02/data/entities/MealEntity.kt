package cn.itcast.caloriestracker02.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val mealTime: Date,
    val foodItemIds: String, // 存储食物项ID列表，可以用逗号分隔的字符串
    val totalCalories: Int,
    val totalProtein: Int,
    val totalCarbs: Int,
    val totalFats: Int
)
