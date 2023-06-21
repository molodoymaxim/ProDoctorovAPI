<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ProDoctorov Search</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #ffffff;
            border: 1px solid #cccccc;
            border-radius: 5px;
            padding: 20px;
            text-align: center;
        }

        h1 {
            color: #333333;
            margin-bottom: 20px;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        select {
            padding: 5px;
            width: 200px;
            border: 1px solid #cccccc;
            border-radius: 3px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            cursor: pointer;
            border-radius: 3px;
        }

        .result {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>ProDoctorov Search</h1>
    <form method="post" action="${pageContext.request.contextPath}/search">
        <label>
            <input type="text" name="search" placeholder="Введите запрос">
            <select name="filter">
                <option>Лекарства</option>
                <option>Доктора</option>
                <option>Записи</option>
            </select>
        </label>
        <input type="submit" value="Найти">
    </form>
    <div class="result">
        Результат выполнения запроса: ${sessionScope.get('search')}
    </div>
</div>
</body>
</html>
