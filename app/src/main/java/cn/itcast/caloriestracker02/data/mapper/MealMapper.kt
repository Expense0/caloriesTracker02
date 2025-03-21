package cn.itcast.caloriestracker02.data.mapper

import cn.itcast.caloriestracker02.data.remote.Dto.MealDto
import cn.itcast.caloriestracker02.domain.model.Meal

fun Meal.toMealDto() = MealDto(

)

//fun MealDto.toMeal() = Meal(
//
//)