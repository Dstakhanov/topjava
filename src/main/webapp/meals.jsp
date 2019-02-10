<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }


        .colorExceed {
            background-color: #FA8072;
        }

        .colorNotExceed {
            background-color: #90EE90;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<c:if test="${!empty listMealTo}">
    <table class="tg">
        <tr>
            <th width="120">Date</th>
            <th width="120">Description</th>
            <th width="120">Calories</th>
            <th width="120">Excees</th>
        </tr>
        <c:forEach items="${listMealTo}" var="mealTo">
            <c:set var="isExcess" scope="session" value="${mealTo.isExcess()}"/>
            <tr class="${isExcess ? 'colorExceed' : 'colorNotExceed'}">
                <td>
                    <fmt:parseDate value="${ mealTo.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" /></td>
                <td>${mealTo.getDescription()}</td>
                <td>${mealTo.getCalories()}</td>
                <td>${isExcess}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>