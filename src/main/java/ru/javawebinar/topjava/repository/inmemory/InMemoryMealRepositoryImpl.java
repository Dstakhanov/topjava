package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Integer, Meal>> userRepo = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            userRepo.put(userId, repository);
            return meal;
        }
        // treat case: update, but absent in storage
        if (userRepo.containsKey(userId)) {
            return userRepo.get(userId).computeIfPresent(meal.getId(), (k, oldMeal) -> meal);
        }
        return null;

    }

    @Override
    public boolean delete(int id, int userId) {

        if (userRepo.containsKey(userId)) {
            return userRepo.get(userId).entrySet().removeIf(entry -> (entry.getKey().equals(id)));
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (userRepo.containsKey(userId)) {
            return userRepo.get(userId).getOrDefault(id, null);
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId, LocalDate startDate, LocalDate endDate) {
        List<Meal> listMeal = getAll(userId);
        if (listMeal != null) {
            return getAll(userId).stream()
                    .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime().toLocalDate(), startDate, endDate))
                    .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                    .collect(toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Meal> getAll(int userId) {
        if (userRepo.containsKey(userId)) {
            return userRepo.get(userId).values().stream()
                    .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                    .collect(toList());
        }
        return new ArrayList<>();
    }

}

