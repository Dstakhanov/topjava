package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao dao;
    private static final String LIST_MEAL = "/meals.jsp";
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final int CALORIES_PER_DAY = 2000;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dao = new MealDaoMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward = "";
        List<MealTo> listMealTo;
        String action = request.getParameter("action");
        int mealId = 0;
        action = (action == null) ? "" : action;
        switch (action) {

            case ("edit"):
                forward = INSERT_OR_EDIT;
                mealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = dao.getById(mealId);
                request.setAttribute("meal", meal);
                break;
            case ("insert"):
                forward = INSERT_OR_EDIT;
                break;
            case ("delete"):
                mealId = Integer.parseInt(request.getParameter("mealId"));
                dao.remove(mealId);
            default:
                forward = LIST_MEAL;
                listMealTo = MealsUtil.getFilteredWithExcess(dao.getList(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
                request.setAttribute("listMealTo", listMealTo);
                break;

        }

        request.getRequestDispatcher(forward).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealId = request.getParameter("mealId");
        LocalDateTime dateMeal = LocalDateTime.parse(request.getParameter("dateMeal"), formatter);

        String mealDescription = request.getParameter("mealDescription");
        int caloriesPerMeal = Integer.parseInt(request.getParameter("caloriesPerMeal"));

        if (mealId == null || mealId.isEmpty()) {
            Meal meal = new Meal(dateMeal, mealDescription, caloriesPerMeal);
            dao.add(meal);
        } else {
            Meal meal = new Meal(dateMeal, mealDescription, caloriesPerMeal);
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
        }
        List<MealTo> listMealTo = MealsUtil.getFilteredWithExcess(dao.getList(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY);
        request.setAttribute("listMealTo", listMealTo);
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}
