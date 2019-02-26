package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ;
    public static final int MEAL_ID1 = START_SEQ + 1;
    public static final int MEAL_ID2 = START_SEQ + 2;
    public static final int MEAL_ID3 = START_SEQ + 3;
    public static final int MEAL_ID4 = START_SEQ + 4;
    public static final int MEAL_ID5 = START_SEQ + 5;
    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL1 = new Meal(MEAL_ID1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL2 = new Meal(MEAL_ID2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL3 = new Meal(MEAL_ID3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL4 = new Meal(MEAL_ID4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL5 = new Meal(MEAL_ID5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 31, 0, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 31, 23, 59);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "calories", "description");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("calories", "description").isEqualTo(expected);
    }

    public static void assertMatch(Boolean actual, Boolean expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
