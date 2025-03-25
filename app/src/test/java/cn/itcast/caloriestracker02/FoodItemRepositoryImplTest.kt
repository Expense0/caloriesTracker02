package cn.itcast.caloriestracker02

import cn.itcast.caloriestracker02.data.dao.FoodItemDao
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity
import cn.itcast.caloriestracker02.data.repositoryImpl.FoodItemRepositoryImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class FoodItemRepositoryImplTest {

    // 模拟 FoodItemDao
    private lateinit var mockFoodDao: FoodItemDao
    private lateinit var repository: FoodItemRepositoryImpl

    @Before
    fun setup() {
        mockFoodDao = mock()
        repository = FoodItemRepositoryImpl(mockFoodDao)
    }

    // 测试 createFood
    @Test
    fun createFood_CallsDaoInsert() = runTest {
        // 准备测试数据
        val food = FoodItemEntity(name = "Apple", calories = 95, protein = 0, carbs = 25, fats = 0)

        // 调用 Repository 方法
        repository.createFood(food)

        // 验证 Dao 的 insert 方法被调用，且参数正确
        verify(mockFoodDao).insert(food)
    }

    // 测试 deleteFood
    @Test
    fun deleteFood_CallsDaoDeleteById() = runTest {
        // 调用 Repository 方法
        repository.deleteFood(foodId = 1)

        // 验证 Dao 的 deleteById 方法被调用，且参数正确
        verify(mockFoodDao).deleteById(1)
    }

    // 测试 getFood
    @Test
    fun getFood_ReturnsDaoResult() = runTest {
        // 准备模拟数据
        val foodEntity = FoodItemEntity(id = 1, name = "Banana", calories = 105, protein = 1, carbs = 27, fats = 0)
        `when`(mockFoodDao.getFoodItemById(1)).thenReturn(foodEntity)

        // 调用 Repository 方法并获取结果
        val result = repository.getFood(1)

        // 验证返回结果与模拟数据一致
        assertEquals(foodEntity, result)
    }

    // 测试 getAllFoods
    @Test
    fun getAllFoods_ReturnsDaoResult() = runTest {
        // 准备模拟数据
        val foodList = listOf(
            FoodItemEntity(id = 1, name = "Apple", calories = 95, protein = 0, carbs = 25, fats = 0),
            FoodItemEntity(id = 2, name = "Banana", calories = 105, protein = 1, carbs = 27, fats = 0)
        )
        `when`(mockFoodDao.getAllFoodItems()).thenReturn(foodList)

        // 调用 Repository 方法并获取结果
        val result = repository.getAllFoods()

        // 验证返回结果与模拟数据一致
        assertEquals(foodList, result)
    }

    // 测试 updateFood
    @Test
    fun updateFood_CallsDaoUpdate() = runTest {
        // 准备测试数据
        val food = FoodItemEntity(id = 1, name = "Apple", calories = 100, protein = 0, carbs = 25, fats = 0)

        // 调用 Repository 方法
        repository.updateFood(food)

        // 验证 Dao 的 update 方法被调用，且参数正确
        verify(mockFoodDao).update(food)
    }
}