package cn.itcast.caloriestracker02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity

@Dao
interface FoodItemDao {
    // 插入
    @Insert
    suspend fun insert(foodItem: FoodItemEntity)

    // 删除
    @Delete
    suspend fun delete(foodItem: FoodItemEntity)

    @Query("DELETE FROM food_items WHERE id = :foodId")
    suspend fun deleteById(foodId: Int)

    // 更新
    @Update
    suspend fun update(foodItem: FoodItemEntity)

    // 查询（保持原有结构）
    @Query("SELECT * FROM food_items WHERE id = :id")
    suspend fun getFoodItemById(id: Int): FoodItemEntity?

    @Query("SELECT * FROM food_items")
    suspend fun getAllFoodItems(): List<FoodItemEntity>

    // 新增搜索功能
    @Query("SELECT * FROM food_items WHERE name LIKE '%' || :keyword || '%'")
    suspend fun searchFoodItems(keyword: String): List<FoodItemEntity>
}