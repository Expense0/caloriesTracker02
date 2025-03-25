package cn.itcast.caloriestracker02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import cn.itcast.caloriestracker02.data.entities.MealEntity

@Dao
interface MealDao {
    // 插入
    @Insert
    suspend fun insert(meal: MealEntity): Long

    // 删除
    @Delete
    suspend fun delete(meal: MealEntity)

    @Query("DELETE FROM meals WHERE id = :mealId")
    suspend fun deleteById(mealId: Int)

    // 更新
    @Update
    suspend fun update(meal: MealEntity)

    // 查询（保持原有关联查询）
    @Transaction
    @Query("SELECT * FROM meals WHERE id = :id")
    suspend fun getMealWithFoodItemsById(id: Int): MealWithFoodItems

    @Transaction
    @Query("SELECT * FROM meals")
    suspend fun getAllMealsWithFoodItems(): List<MealWithFoodItems>

    // 新增基础查询
    @Query("SELECT * FROM meals WHERE id = :mealId")
    suspend fun getMealById(mealId: Int): MealEntity?

    @Query("SELECT * FROM meals WHERE userId = :userId")
    suspend fun getMealsByUser(userId: String): List<MealEntity>
}