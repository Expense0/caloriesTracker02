package cn.itcast.caloriestracker02.data.repositoryImpl

import cn.itcast.caloriestracker02.data.dao.DailyNutritionPlanDao
import cn.itcast.caloriestracker02.data.entities.DailyNutritionPlanEntity
import cn.itcast.caloriestracker02.domain.repository.DailyNutritionPlanRepository

class DailyNutritionPlanRepositoryImpl(
    private val planDao: DailyNutritionPlanDao
) : DailyNutritionPlanRepository {

    override suspend fun createPlan(plan: DailyNutritionPlanEntity) = planDao.insert(plan)

    override suspend fun deletePlan(planId: Int) = planDao.deleteById(planId)

    override suspend fun getPlanWithMeals(planId: Int) = planDao.getDailyPlanWithMealsById(planId)

    override suspend fun getAllPlansWithMeals() = planDao.getAllDailyPlansWithMeals()

    override suspend fun updatePlan(plan: DailyNutritionPlanEntity) = planDao.update(plan)
}