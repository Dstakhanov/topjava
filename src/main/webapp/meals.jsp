<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
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
            <th width="80">Edit</th>
            <th width="80">Delete</th>
        </tr>
        <c:forEach items="${listMealTo}" var="mealTo">
            <tr class="${mealTo.excess ? 'colorExceed' : 'colorNotExceed'}">
                <td>
                    <fmt:parseDate value="${ mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action=edit&mealId=<c:out value="${mealTo.id}"/>">Edit</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${mealTo.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<p><a href="meals?action=insert">Add meal</a></p>
</body>
</html>