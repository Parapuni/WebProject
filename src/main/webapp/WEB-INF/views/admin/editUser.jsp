<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>编辑用户</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f4f4f4;
      font-family: Arial, sans-serif;
    }
    .container {
      margin-top: 40px;
      max-width: 600px;
      background-color: #ffffff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<div class="container">
  <c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success">
        ${sessionScope.message}
      <c:set var="message" value="" scope="session" />
    </div>
  </c:if>
  <h1 class="mb-4">编辑用户</h1>
  <!-- 用户编辑表单 -->
  <form action="<c:url value='/admin/updateuser' />" method="post">
    <input type="hidden" class="form-control" id="uid" name="uid" value="${user.uid}">
    <!-- 昵称 -->
    <div class="mb-3">
      <label for="nickname" class="form-label">昵称</label>
      <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" required>
    </div>
    <!-- 名 -->
    <div class="mb-3">
      <label for="firstName" class="form-label">名</label>
      <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}">
    </div>
    <!-- 姓 -->
    <div class="mb-3">
      <label for="lastName" class="form-label">姓</label>
      <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}">
    </div>
    <!-- 邮箱 -->
    <div class="mb-3">
      <label for="email" class="form-label">邮箱</label>
      <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
    </div>
    <!-- 电话号码 -->
    <div class="mb-3">
      <label for="number" class="form-label">电话号码</label>
      <input type="text" class="form-control" id="number" name="number" value="${user.number}">
    </div>
    <!-- 生日 -->
    <div class="mb-3">
      <label for="birthday" class="form-label">生日</label>
      <input type="date" class="form-control" id="birthday" name="birthday" value="${user.birthday}">
    </div>
    <!-- 提交按钮 -->
    <button type="submit" class="btn btn-primary w-100">更新用户</button>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../footer.jsp"/>
</body>
</html>
