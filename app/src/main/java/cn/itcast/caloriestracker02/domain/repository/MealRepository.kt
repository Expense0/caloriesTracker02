package cn.itcast.caloriestracker02.domain.repository

import cn.itcast.caloriestracker02.data.dao.MealWithFoodItems
import cn.itcast.caloriestracker02.data.entities.MealEntity

interface MealRepository {
    suspend fun createMeal(meal: MealEntity): Long
    suspend fun deleteMeal(mealId: Int)
    suspend fun getMealWithFoods(mealId: Int): MealWithFoodItems?
    suspend fun getAllMealsWithFoods(): List<MealWithFoodItems>
    suspend fun updateMeal(meal: MealEntity)
}