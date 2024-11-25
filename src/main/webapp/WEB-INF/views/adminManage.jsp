<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员管理</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container {
            margin-top: 40px;
        }
        .table {
            margin-top: 20px;
            background-color: #ffffff;
        }
        .table th, .table td {
            text-align: center;
        }
        .btn {
            font-size: 14px;
        }
        .add-admin-btn {
            margin-bottom: 15px;
        }
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="text-center">管理员管理</h2>

    <!-- 添加管理员按钮 -->
    <div class="text-end add-admin-btn">
        <a href="<c:url value='/admin/addadmin' />" class="btn btn-success">添加管理员</a>
    </div>

    <!-- 删除操作提示 -->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>

    <!-- 管理员列表 -->
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>管理员ID</th>
            <th>管理员名称</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="admin" items="${admins}">
            <tr>
                <td>${admin.aid}</td>
                <td>${admin.adminName}</td>
                <td>${admin.email}</td>
                <td>
                    <c:if test="${admin.aid != currentAdmin.aid}">
                        <a href="<c:url value='/admin/deleteadmin?aid=${admin.aid}' />" class="btn btn-danger btn-sm">删除</a>
                    </c:if>
                    <c:if test="${admin.aid == currentAdmin.aid}">
                        <span class="text-muted">无法删除自己</span>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
