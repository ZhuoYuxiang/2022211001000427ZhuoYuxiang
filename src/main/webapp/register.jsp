<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <style>
        #registeredUsersTable {
            width: 100%;
            overflow-x: auto;
        }
        #registeredUsersTable table {
            width: 100%;
            border-collapse: collapse;
        }
    </style>
</head>
    <meta charset="UTF-8">
    <title>用户注册</title>
</head>
<body>
<h2>用户注册表单</h2>
<form action="<%=request.getContextPath()%>/register" method="post">
    <label for="id">学号：</label>
    <input type="text" name="id" id="id" required>
    <br>

    <label for="username">用户名：</label>
    <input type="text" name="username" id="username" required>
    <br>

    <label for="password">密码：</label>
    <input type="password" name="password" id="password" required>
    <br>

    <label for="email">邮箱：</label>
    <input type="email" name="email" id="email" required>
    <br>

    <label for="gender">性别：</label>
    <select name="gender" id="gender" required>
        <option value="">请选择性别</option>
        <option value="male">male</option>
        <option value="female">female</option>
    </select>
    <br>

    <label for="birthdate">出生日期（格式：YYYY-MM-DD）：</label>
    <input type="text" name="birthdate" id="birthdate" required>
    <!-- 注意：此处假设birthDate是文本输入，实际应用中可能希望使用日期选择器控件 -->
    <br>

    <input type="submit" value="注册">
</form>

<p>已注册用户列表：</p>
<div id="registeredUsersTable"></div>

<script>
    window.onload = function () {
        fetch("<%=request.getContextPath()%>/registered-users")
            .then(response => response.text())
            .then(htmlContent => document.getElementById('registeredUsersTable').innerHTML = htmlContent);
    };
</script>

</body>
</html>