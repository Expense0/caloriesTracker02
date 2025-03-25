package cn.itcast.caloriestracker02

import cn.itcast.caloriestracker02.data.dao.MealDao
import cn.itcast.caloriestracker02.data.dao.MealWithFoodItems
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity
import cn.itcast.caloriestracker02.data.entities.MealEntity
import cn.itcast.caloriestracker02.data.repositoryImpl.MealRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Date

class MealRepositoryImplTest {

    // 模拟 MealDao
    private lateinit var mockMealDao: MealDao
    private lateinit var repository: MealRepositoryImpl

    @Before
    fun setup() {
        mockMealDao = mock()
        repository = MealRepositoryImpl(mockMealDao)
    }

    // 测试 createMeal
    @Test
    fun createMeal_CallsDaoInsert() = runTest {
        // 准备测试数据
        val meal = MealEntity(
            id = 1,
            userId = "user123",
            mealTime = Date(),
            foodItemIds = "1,2,3",
            totalCalories = 500,
            totalProtein = 30,
            totalCarbs = 50,
            totalFats = 20
        )

        // 调用 Repository 方法
        repository.createMeal(meal)

        // 验证 Dao 的 insert 方法被调用，且参数正确
        verify(mockMealDao).insert(meal)
    }

    // 测试 deleteMeal
    @Test
    fun deleteMeal_CallsDaoDeleteById() = runTest {
        // 调用 Repository 方法
        repository.deleteMeal(mealId = 1)

        // 验证 Dao 的 deleteById 方法被调用，且参数正确
        verify(mockMealDao).deleteById(1)
    }

    // 测试 getMealWithFoods
    @Test
    fun getMealWithFoods_ReturnsDaoResult() = runTest {
        // 准备模拟数据
        val mealWithFoods = MealWithFoodItems(
            mealEntity = MealEntity(
                id = 1,
                userId = "user123",
                mealTime = Date(),
                foodItemIds = "1,2,3",
                totalCalories = 500,
                totalProtein = 30,
                totalCarbs = 50,
                totalFats = 20
            ),
            foodItems = listOf(
                FoodItemEntity(id = 1, name = "Apple", calories = 95, protein = 0, carbs = 25, fats = 0),
                FoodItemEntity(id = 2, name = "Banana", calories = 105, protein = 1, carbs = 27, fats = 0)
            )
        )
        `when`(mockMealDao.getMealWithFoodItemsById(1)).thenReturn(mealWithFoods)

        // 调用 Repository 方法并获取结果
        val result = repository.getMealWithFoods(1)

        // 验证返回结果与模拟数据一致
        assertEquals(mealWithFoods, result)
    }

    // 测试 getAllMealsWithFoods
    @Test
    fun getAllMealsWithFoods_ReturnsDaoResult() = runTest {
        // 准备模拟数据
        val mealsWithFoods = listOf(
            MealWithFoodItems(
                mealEntity = MealEntity(
                    id = 1,
                    userId = "user123",
                    mealTime = Date(),
                    foodItemIds = "1,2,3",
                    totalCalories = 500,
                    totalProtein = 30,
                    totalCarbs = 50,
                    totalFats = 20
                ),
                foodItems = listOf(
                    FoodItemEntity(id = 1, name = "Apple", calories = 95, protein = 0, carbs = 25, fats = 0),
                    FoodItemEntity(id = 2, name = "Banana", calories = 105, protein = 1, carbs = 27, fats = 0)
                )
            ),
            MealWithFoodItems(
                mealEntity = MealEntity(
                    id = 2,
                    userId = "user456",
                    mealTime = Date(),
                    foodItemIds = "4,5",
                    totalCalories = 300,
                    totalProtein = 20,
                    totalCarbs = 40,
                    totalFats = 10
                ),
                foodItems = listOf(
                    FoodItemEntity(id = 4, name = "Orange", calories = 62, protein = 1, carbs = 15, fats = 0),
                    FoodItemEntity(id = 5, name = "Grapes", calories = 69, protein = 0, carbs = 18, fats = 0)
                )
            )
        )
        `when`(mockMealDao.getAllMealsWithFoodItems()).thenReturn(mealsWithFoods)

        // 调用 Repository 方法并获取结果
        val result = repository.getAllMealsWithFoods()

        // 验证返回结果与模拟数据一致
        assertEquals(mealsWithFoods, result)
    }

    // 测试 updateMeal
    @Test
    fun updateMeal_CallsDaoUpdate() = runTest {
        // 准备测试数据
        val meal = MealEntity(
            id = 1,
            userId = "user123",
            mealTime = Date(),
            foodItemIds = "1,2,3",
            totalCalories = 500,
            totalProtein = 30,
            totalCarbs = 50,
            totalFats = 20
        )

        // 调用 Repository 方法
        repository.updateMeal(meal)

        // 验证 Dao 的 update 方法被调用，且参数正确
        verify(mockMealDao).update(meal)
    }
}