<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<form method="POST" action='meals' name="frmAddMeal" class="meal_form">
    <h2>${meal.id==null ? 'Add' : 'Edit'} meal</h2>
    <ul>
        <li>
            <label for="dateMeal">Date : </label>
            <input type="datetime-local" name="dateMeal"
                   value="<c:out value="${meal.dateTime}" />"/>
        </li>
        <li>
            <label for="mealDescription">Description :</label>
            <input type="text" name="mealDescription"
                   value="<c:out value="${meal.description}" />"/>
        </li>
        <li>
            <label for="caloriesPerMeal"> Calories :</label>
            <input type="text" name="caloriesPerMeal"
                   value="<c:out value="${meal.calories}" />"/>
        </li>
        <li class="novisible">
            <label for="mealId"> Meal ID :</label>
            <input type="text" readonly="readonly" name="mealId"
                   value="<c:out value="${meal.id}" />"/>
        </li>
    </ul>
    <p>
        <button class="submit" type="submit">Submit</button>
    </p>


</form>
</body>
</html>
