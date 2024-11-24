<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>添加管理员</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .container {
      max-width: 600px;
      margin-top: 50px;
      background-color: #f8f9fa;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
    }
  </style>
</head>
<body>
<div class="container">
  <h2 class="text-center">添加管理员</h2>
  <form action="<c:url value='/saveadmin' />" method="post">
    <div class="mb-3">
      <label for="nickname" class="form-label">昵称</label>
      <input type="text" class="form-control" id="nickname" name="nickname" required>
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">邮箱</label>
      <input type="email" class="form-control" id="email" name="email" required>
    </div>
    <div class="mb-3">
      <label for="number" class="form-label">电话号码</label>
      <input type="text" class="form-control" id="number" name="number">
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">密码</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>
    <div class="mb-3">
      <label for="firstName" class="form-label">名</label>
      <input type="text" class="form-control" id="firstName" name="firstName">
    </div>
    <div class="mb-3">
      <label for="lastName" class="form-label">姓</label>
      <input type="text" class="form-control" id="lastName" name="lastName">
    </div>
    <div class="mb-3">
      <label for="birthday" class="form-label">生日</label>
      <input type="date" class="form-control" id="birthday" name="birthday">
    </div>
    <button type="submit" class="btn btn-primary w-100">添加管理员</button>
  </form>
</div>
</body>
</html>
