package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoMemoryImpl implements MealDao {
    private static final Logger log = getLogger(MealDaoMemoryImpl.class);

    private static AtomicInteger counter = new AtomicInteger(6);
    private static ConcurrentMap<Integer, Meal> mapMeals = new ConcurrentHashMap<>();

    public MealDaoMemoryImpl() {
        mapMeals = MealsUtil.InitializeMealList().stream()
                .collect(Collectors.toConcurrentMap(Meal::getId, meal -> meal));
    }

    @Override
    public void addMeal(Meal meal) {
        mapMeals.putIfAbsent(meal.getId(), meal);
        log.debug("Meal successfully added");
    }

    @Override
    public void updateMeal(Meal meal) {
        mapMeals.computeIfPresent(meal.getId(), (k, v) -> meal);
        log.debug("Meal successfully updated");

    }

    @Override
    public void removeMeal(int id) {

        if (mapMeals.containsKey(id)) {
            mapMeals.remove(id);
            log.debug("Meal successfully removed");
        }

    }

    @Override
    public Meal getMealById(int id) {
        log.debug("get meal by id");
        return mapMeals.get(id);
    }

    @Override
    public ConcurrentMap<Integer, Meal> getMapMeals() {
        log.debug("get map meal");
        return mapMeals;
    }

    @Override
    public int incrementCounter() {
        return counter.getAndIncrement();
    }
}
