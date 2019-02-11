package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;


public interface MealDao {
    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void removeMeal(int id);

    Meal getMealById(int id);

    ConcurrentMap<Integer, Meal> getMapMeals();

    int incrementCounter();

}
