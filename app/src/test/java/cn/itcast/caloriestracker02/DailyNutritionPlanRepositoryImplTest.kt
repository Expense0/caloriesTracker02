package cn.itcast.caloriestracker02

import cn.itcast.caloriestracker02.data.dao.DailyNutritionPlanDao
import cn.itcast.caloriestracker02.data.dao.DailyNutritionPlanWithMeals
import cn.itcast.caloriestracker02.data.dao.MealWithFoodItems
import cn.itcast.caloriestracker02.data.entities.DailyNutritionPlanEntity
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity
import cn.itcast.caloriestracker02.data.entities.MealEntity
import cn.itcast.caloriestracker02.data.repositoryImpl.DailyNutritionPlanRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.util.Date

class DailyNutritionPlanRepositoryImplTest {

    // 模拟 DailyNutritionPlanDao
    private lateinit var mockPlanDao: DailyNutritionPlanDao
    private lateinit var repository: DailyNutritionPlanRepositoryImpl

    @Before
    fun setup() {
        mockPlanDao = mock()
        repository = DailyNutritionPlanRepositoryImpl(mockPlanDao)
    }

    // 测试 createPlan
    @Test
    fun createPlan_CallsDaoInsert() = runTest {
        // 准备测试数据
        val plan = DailyNutritionPlanEntity(
            userId = "user123",
            date = Date(),
            remainingCalories = 2000,
            remainingProtein = 150,
            remainingCarbs = 300,
            remainingFats = 70,
            mealIds = "1,2,3"
        )

        // 调用 Repository 方法
        repository.createPlan(plan)

        // 验证 Dao 的 insert 方法被调用，且参数正确
        verify(mockPlanDao).insert(plan)
    }

    // 测试 deletePlan
    @Test
    fun deletePlan_CallsDaoDeleteById() = runTest {
        // 调用 Repository 方法
        repository.deletePlan(planId = 1)

        // 验证 Dao 的 deleteById 方法被调用，且参数正确
        verify(mockPlanDao).deleteById(1)
    }

    // 测试 getPlanWithMeals
    @Test
    fun getPlanWithMeals_ReturnsDaoResult() = runTest {
        // 准备模拟数据
        val planWithMeals = DailyNutritionPlanWithMeals(
            dailyNutritionPlanEntity = DailyNutritionPlanEntity(
                id = 1,
                userId = "user123",
                date = Date(),
                remainingCalories = 2000,
                remainingProtein = 150,
                remainingCarbs = 300,
                remainingFats = 70,
                mealIds = "1,2,3"
            ),
            meals = listOf(
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
                        userId = "user123",
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
        )
        `when`(mockPlanDao.getDailyPlanWithMealsById(1)).thenReturn(planWithMeals)

        // 调用 Repository 方法并获取结果
        val result = repository.getPlanWithMeals(1)

        // 验证返回结果与模拟数据一致
        assertEquals(planWithMeals, result)
    }

    // 测试 getAllPlansWithMeals
    @Test
    fun getAllPlansWithMeals_ReturnsDaoResult() = runTest {
        // 准备模拟数据
        val plansWithMeals = listOf(
            DailyNutritionPlanWithMeals(
                dailyNutritionPlanEntity = DailyNutritionPlanEntity(
                    id = 1,
                    userId = "user123",
                    date = Date(),
                    remainingCalories = 2000,
                    remainingProtein = 150,
                    remainingCarbs = 300,
                    remainingFats = 70,
                    mealIds = "1,2,3"
                ),
                meals = listOf(
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
                    )
                )
            ),
            DailyNutritionPlanWithMeals(
                dailyNutritionPlanEntity = DailyNutritionPlanEntity(
                    id = 2,
                    userId = "user456",
                    date = Date(),
                    remainingCalories = 1800,
                    remainingProtein = 120,
                    remainingCarbs = 250,
                    remainingFats = 60,
                    mealIds = "4,5"
                ),
                meals = listOf(
                    MealWithFoodItems(
                        mealEntity = MealEntity(
                            id = 4,
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
            )
        )
        `when`(mockPlanDao.getAllDailyPlansWithMeals()).thenReturn(plansWithMeals)

        // 调用 Repository 方法并获取结果
        val result = repository.getAllPlansWithMeals()

        // 验证返回结果与模拟数据一致
        assertEquals(plansWithMeals, result)
    }

    // 测试 updatePlan
    @Test
    fun updatePlan_CallsDaoUpdate() = runTest {
        // 准备测试数据
        val plan = DailyNutritionPlanEntity(
            id = 1,
            userId = "user123",
            date = Date(),
            remainingCalories = 2000,
            remainingProtein = 150,
            remainingCarbs = 300,
            remainingFats = 70,
            mealIds = "1,2,3"
        )

        // 调用 Repository 方法
        repository.updatePlan(plan)

        // 验证 Dao 的 update 方法被调用，且参数正确
        verify(mockPlanDao).update(plan)
    }
}