package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.Calories;
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
import java.util.ArrayList;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao dao;
    private static final String LIST_MEAL = "/meals.jsp";
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public MealServlet() {
        this.dao = new MealDaoMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward = "";
        String action = request.getParameter("action");


        if (action == null || action.equalsIgnoreCase("listMealTo")) {
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.removeMeal(mealId);
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else {
            forward = INSERT_OR_EDIT;
        }
        requestForward(request, response, forward);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealId = request.getParameter("mealId");
        LocalDateTime dateMeal = LocalDateTime.parse(request.getParameter("dateMeal"), formatter);

        String mealDescription = request.getParameter("mealDescription");
        int caloriesPerMeal = Integer.parseInt(request.getParameter("caloriesPerMeal"));

        if (mealId == null || mealId.isEmpty()) {
            int Id = dao.incrementCounter();
            Meal meal = new Meal(dateMeal, mealDescription, caloriesPerMeal, Id);
            dao.addMeal(meal);
        } else {
            Meal meal = new Meal(dateMeal, mealDescription, caloriesPerMeal, Integer.parseInt(mealId));
            dao.updateMeal(meal);
        }
        requestForward(request, response, LIST_MEAL);
    }

    private void requestForward(HttpServletRequest request, HttpServletResponse response, String forward) throws ServletException, IOException {
        final List<MealTo> listMealTo = MealsUtil.getFilteredWithExcess(new ArrayList<>(dao.getMapMeals().values()), LocalTime.MIN, LocalTime.MAX, Calories.CALORIES_PER_DAY);
        request.setAttribute("listMealTo", listMealTo);
        request.getRequestDispatcher(forward).forward(request, response);
    }


}
