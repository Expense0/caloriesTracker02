package cn.itcast.caloriestracker02.domain.repository

import cn.itcast.caloriestracker02.data.entities.FoodItemEntity

interface FoodItemRepository {
    suspend fun createFood(food: FoodItemEntity)
    suspend fun deleteFood(foodId: Int)
    suspend fun getFood(foodId: Int): FoodItemEntity?
    suspend fun getAllFoods(): List<FoodItemEntity>
    suspend fun updateFood(food: FoodItemEntity)
}