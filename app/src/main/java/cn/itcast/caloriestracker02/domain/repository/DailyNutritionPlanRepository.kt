package cn.itcast.caloriestracker02.domain.repository

import cn.itcast.caloriestracker02.data.dao.DailyNutritionPlanWithMeals
import cn.itcast.caloriestracker02.data.entities.DailyNutritionPlanEntity

interface DailyNutritionPlanRepository {
    suspend fun createPlan(plan: DailyNutritionPlanEntity): Long
    suspend fun deletePlan(planId: Int)
    suspend fun getPlanWithMeals(planId: Int): DailyNutritionPlanWithMeals?
    suspend fun getAllPlansWithMeals(): List<DailyNutritionPlanWithMeals>
    suspend fun updatePlan(plan: DailyNutritionPlanEntity)
}