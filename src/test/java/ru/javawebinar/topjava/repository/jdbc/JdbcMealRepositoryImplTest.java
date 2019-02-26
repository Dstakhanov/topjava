package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@Repository
public class JdbcMealRepositoryImplTest {
    private static final Logger log = LoggerFactory.getLogger(JdbcMealRepositoryImplTest.class);

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }


    @Test
    public void delete() {
        boolean res = jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", MEAL3, ADMIN_ID) != 0;
        assertMatch(res, true);
    }

    @Test
    public void get() {

        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, MEAL_ID4, ADMIN_ID);
        assertMatch(DataAccessUtils.singleResult(meals), MEAL4);
    }

    @Test
    public void getAll() {
        List<Meal> all = jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY datetime DESC", ROW_MAPPER, ADMIN_ID);
        assertMatch(all, MEAL2, MEAL3, MEAL4, MEAL5);
    }

    @Test
    public void getBetween() {
        List<Meal> between = jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? AND datetime BETWEEN ? AND ? ORDER BY datetime DESC", ROW_MAPPER, ADMIN_ID, START_DATE_TIME, END_DATE_TIME);
        assertMatch(between, MEAL3, MEAL4, MEAL5);
    }
}