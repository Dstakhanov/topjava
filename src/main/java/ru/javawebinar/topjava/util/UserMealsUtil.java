package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumOfCalories = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDate = userMeal.getDateTime().toLocalDate();
            sumOfCalories.put(mealDate, sumOfCalories.getOrDefault(mealDate, 0) + userMeal.getCalories());
        }

        List<UserMealWithExceed> mealWithExceeded = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            LocalDateTime userMealDateTime = userMeal.getDateTime();

            if (TimeUtil.isBetween(userMealDateTime.toLocalTime(), startTime, endTime)) {
                mealWithExceeded.add(new UserMealWithExceed(userMealDateTime, userMeal.getDescription(), userMeal.getCalories(),
                        sumOfCalories.get(userMealDateTime.toLocalDate()) > caloriesPerDay));
            }
        }

        return mealWithExceeded;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByStreamAPI(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> mealMap = mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(userMeal -> (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)))
                .map(userMeal -> new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), mealMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());

    }
}
