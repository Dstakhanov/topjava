package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;


public interface MealDao {
    Meal add(Meal meal);

    Meal update(Meal meal);

    void remove(int id);

    Meal getById(int id);

    List<Meal> getList();

}
