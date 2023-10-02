<%--
  Created by IntelliJ IDEA.
  User: tron5
  Date: 02.10.2023
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SETTINGS</title>
</head>
<body>
<h1 style="color: ${cookie.get("color").value}">Hello!</h1>
<div>
    <form action="/setting" method="post">
        <select size="8" name="color">
            <option value="red">Red</option>
            <option value="green">Green</option>
            <option value="blue">Blue</option>
            <option value="black">Black</option>
        </select>
        <input type="submit" value="Save.color">
    </form>
</div>
</body>
</html>
