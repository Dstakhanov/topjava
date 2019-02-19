<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <div>
        <form id="filter" method="get" action="meals">
            <div class="row">
                <div class="offset-1 col-2">
                    <label for="startDate">От даты</label>
                    <input class="form-control" type="date" value="<c:out value="${param.startDate}"/>" name="startDate"
                           id="startDate">
                </div>
                <div class="col-2">
                    <label for="endDate">До даты</label>
                    <input class="form-control" type="date" value="<c:out value="${param.endDate}"/>" name="endDate"
                           id="endDate">
                </div>
                <div class="offset-2 col-2">
                    <label for="startTime">От времени</label>
                    <input class="form-control" type="time" value="<c:out value="${param.startTime}"/>" name="startTime"
                           id="startTime">
                </div>
                <div class="col-2">
                    <label for="endTime">До времени</label>
                    <input class="form-control" type="time" value="<c:out value="${param.endTime}"/>" name="endTime"
                           id="endTime">
                </div>
            </div>
            <div>
                <br>
                <button class="btn btn-primary" type="submit" name="sdf">
                    <span class="fa fa-filter"></span>
                    Отфильтровать
                </button>
            </div>
        </form>
    </div>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <div>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr class="${meal.excess ? 'excess' : 'normal'}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>