package cn.itcast.caloriestracker02.data.repositoryImpl

import cn.itcast.caloriestracker02.data.dao.FoodItemDao
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity
import cn.itcast.caloriestracker02.domain.repository.FoodItemRepository

class FoodItemRepositoryImpl(
    private val foodDao: FoodItemDao
) : FoodItemRepository {

    override suspend fun createFood(food: FoodItemEntity) = foodDao.insert(food)

    override suspend fun deleteFood(foodId: Int) = foodDao.deleteById(foodId)

    override suspend fun getFood(foodId: Int) = foodDao.getFoodItemById(foodId)

    override suspend fun getAllFoods() = foodDao.getAllFoodItems()

    override suspend fun updateFood(food: FoodItemEntity) = foodDao.update(food)
}