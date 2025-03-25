package cn.itcast.caloriestracker02.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import cn.itcast.caloriestracker02.data.entities.DailyNutritionPlanEntity
import cn.itcast.caloriestracker02.data.entities.FoodItemEntity
import cn.itcast.caloriestracker02.data.entities.MealEntity

@Dao
interface DailyNutritionPlanDao {
    // 插入
    @Insert
    suspend fun insert(dailyPlan: DailyNutritionPlanEntity): Long

    // 删除
    @Delete
    suspend fun delete(dailyPlan: DailyNutritionPlanEntity)

    @Query("DELETE FROM daily_nutrition_plans WHERE id = :planId")
    suspend fun deleteById(planId: Int)

    // 更新
    @Update
    suspend fun update(dailyPlan: DailyNutritionPlanEntity)

    // 查询（保持原有关联查询）
    @Transaction
    @Query("SELECT * FROM daily_nutrition_plans WHERE id = :id")
    suspend fun getDailyPlanWithMealsById(id: Int): DailyNutritionPlanWithMeals?

    @Transaction
    @Query("SELECT * FROM daily_nutrition_plans")
    suspend fun getAllDailyPlansWithMeals(): List<DailyNutritionPlanWithMeals>

    // 新增基础查询
    @Query("SELECT * FROM daily_nutrition_plans WHERE id = :planId")
    suspend fun getPlanById(planId: Int): DailyNutritionPlanEntity?

    @Query("SELECT * FROM daily_nutrition_plans WHERE userId = :userId")
    suspend fun getPlansByUser(userId: String): List<DailyNutritionPlanEntity>
}

data class DailyNutritionPlanWithMeals(
    val dailyNutritionPlanEntity: DailyNutritionPlanEntity,
    val meals: List<MealWithFoodItems>
)

data class MealWithFoodItems(
    val mealEntity: MealEntity,
    val foodItems: List<FoodItemEntity>
)