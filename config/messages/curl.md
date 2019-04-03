get All Meals
http://localhost:8080/topjava/rest/meals

get Meal 100002
http://localhost:8080/topjava/rest/meals/100002

create Meal
http://localhost:8080/topjava/rest/meals
{"dateTime":"2019-04-03T14:00","description":"Created lunch","calories":1000}

delete Meal 100003
http://localhost:8080/topjava/rest/meals/100003

update Meal
http://localhost:8080/topjava/rest/meals
{"dateTime":"2019-04-02T14:00","description":"Created lunch 2","calories":499}

filter Meals
http://localhost:8080/topjava/rest/meals/filter?startDate=2015-05-31&startTime=10:00:00&endDate=2015-05-31&endTime=15:00:00