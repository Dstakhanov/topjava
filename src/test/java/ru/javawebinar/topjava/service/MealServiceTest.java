package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test(expected = NotFoundException.class)
    public void get() throws Exception {
        Meal meal = service.get(MEAL_ID3, 100);
        assertMatch(meal, MEAL3);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(MEAL_ID, 10);
    }

    @Test(expected = NotFoundException.class)
    public void update() throws Exception {
        Meal updated = MEAL1;
        updated.setDescription("UpdatedNameMeal");
        updated.setCalories(330);
        service.update(updated, 5);
        assertMatch(service.get(MEAL_ID1, 5), updated);
    }


}