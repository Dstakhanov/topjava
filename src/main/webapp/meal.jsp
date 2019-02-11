<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meal</title>
    <style type="text/css">
        .meal_form ul {
            width: 750px;
            list-style-type: none;
            list-style-position: outside;
            margin: 0px;
            padding: 0px;
        }

        .meal_form li {
            padding: 12px;
            border-bottom: 1px solid #eee;
            position: relative;
        }

        .meal_form label {
            width: 150px;
            margin-top: 3px;
            display: inline-block;
            float: left;
            padding: 3px;
        }

        .meal_form input {
            height: 20px;
            width: 220px;
            padding: 5px 8px;
        }

        .meal_form textarea {
            padding: 8px;
            width: 300px;
        }

        .meal_form button {
            margin-left: 300px;
        }

        .meal_form h2 {
            margin: 0;
            display: inline;
        }

        .meal_form input, .meal_form textarea {
            border: 1px solid #aaa;
            box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
            border-radius: 2px;
        }

        .meal_form input:focus, .meal_form textarea:focus {
            background: #fff;
            border: 1px solid #555;
            box-shadow: 0 0 3px #aaa;
        }

        /* Button Style */
        button.submit {
            background-color: #68b12f;
            background: -webkit-gradient(linear, left top, left bottom, from(#68b12f), to(#50911e));
            background: -webkit-linear-gradient(top, #68b12f, #50911e);
            background: -moz-linear-gradient(top, #68b12f, #50911e);
            background: -ms-linear-gradient(top, #68b12f, #50911e);
            background: -o-linear-gradient(top, #68b12f, #50911e);
            background: linear-gradient(top, #68b12f, #50911e);
            border: 1px solid #509111;
            border-bottom: 1px solid #5b992b;
            border-radius: 3px;
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            -ms-border-radius: 3px;
            -o-border-radius: 3px;
            box-shadow: inset 0 1px 0 0 #9fd574;
            -webkit-box-shadow: 0 1px 0 0 #9fd574 inset;
            -moz-box-shadow: 0 1px 0 0 #9fd574 inset;
            -ms-box-shadow: 0 1px 0 0 #9fd574 inset;
            -o-box-shadow: 0 1px 0 0 #9fd574 inset;
            color: white;
            font-weight: bold;
            padding: 6px 20px;
            text-align: center;
            text-shadow: 0 -1px 0 #396715;
        }

        button.submit:hover {
            opacity: .85;
            cursor: pointer;
        }

        button.submit:active {
            border: 1px solid #20911e;
            box-shadow: 0 0 10px 5px #356b0b inset;
            -webkit-box-shadow: 0 0 10px 5px #356b0b inset;
            -moz-box-shadow: 0 0 10px 5px #356b0b inset;
            -ms-box-shadow: 0 0 10px 5px #356b0b inset;
            -o-box-shadow: 0 0 10px 5px #356b0b inset;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>

<form method="POST" action='meals' name="frmAddMeal" class="meal_form">
    <h2>${meal.id==null ? 'Add' : 'Edit'} meal</h2>
    <ul>
        <li>
            <label for="mealId"> Meal ID :</label>
            <input type="text" readonly="readonly" name="mealId"
                   value="<c:out value="${meal.id}" />"/>
        </li>
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
    </ul>
    <p>
        <button class="submit" type="submit">Submit</button>
    </p>


</form>
</body>
</html>
