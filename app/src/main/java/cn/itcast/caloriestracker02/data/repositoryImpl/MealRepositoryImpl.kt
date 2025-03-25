package cn.itcast.caloriestracker02.data.repositoryImpl

import cn.itcast.caloriestracker02.data.dao.MealDao
import cn.itcast.caloriestracker02.data.entities.MealEntity
import cn.itcast.caloriestracker02.domain.repository.MealRepository

class MealRepositoryImpl(
    private val mealDao: MealDao
) : MealRepository {

    override suspend fun createMeal(meal: MealEntity) = mealDao.insert(meal)

    override suspend fun deleteMeal(mealId: Int) = mealDao.deleteById(mealId)

    override suspend fun getMealWithFoods(mealId: Int) = mealDao.getMealWithFoodItemsById(mealId)

    override suspend fun getAllMealsWithFoods() = mealDao.getAllMealsWithFoodItems()

    override suspend fun updateMeal(meal: MealEntity) = mealDao.update(meal)
}