package cn.itcast.caloriestracker02.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cn.itcast.caloriestracker02.data.dao.DailyNutritionPlanDao
import cn.itcast.caloriestracker02.data.dao.FoodItemDao
import cn.itcast.caloriestracker02.data.dao.MealDao
import cn.itcast.caloriestracker02.data.entities.DailyNutritionPlanEntity
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity
import cn.itcast.caloriestracker02.data.entities.MealEntity

@Database(
    entities = [FoodItemEntity::class, MealEntity::class, DailyNutritionPlanEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodItemDao(): FoodItemDao
    abstract fun mealDao(): MealDao
    abstract fun dailyNutritionPlanDao(): DailyNutritionPlanDao
}