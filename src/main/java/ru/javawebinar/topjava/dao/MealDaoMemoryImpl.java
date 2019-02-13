package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoMemoryImpl implements MealDao {
    private static final Logger log = getLogger(MealDaoMemoryImpl.class);

    private AtomicInteger counter = new AtomicInteger(0);
    private ConcurrentMap<Integer, Meal> mapMeals = new ConcurrentHashMap<>();

    public MealDaoMemoryImpl() {
        InitializeConcurrentMap();
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(counter.getAndIncrement());
        Meal nMeal = mapMeals.putIfAbsent(meal.getId(), meal);
        if (nMeal == null) {
            log.debug("Meal successfully added");
        } else {
            log.debug("Meal already was added");
        }
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        Meal nMeal = mapMeals.computeIfPresent(meal.getId(), (k, v) -> meal);
        if (nMeal != null) {
            log.debug("Meal successfully updated");
        } else {
            log.debug("Meal doesn't updated");
        }
        return nMeal;
    }

    @Override
    public void remove(int id) {
        if (mapMeals.containsKey(id)) {
            mapMeals.remove(id);
            log.debug("Meal successfully removed");
        }
    }

    @Override
    public Meal getById(int id) {
        log.debug("get meal by id");
        return mapMeals.get(id);
    }

    @Override
    public List<Meal> getList() {
        log.debug("get list of meal");

        return new ArrayList<>(mapMeals.values());
    }

    private void InitializeConcurrentMap() {

        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));

    }
}
