<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
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
        .add-user-btn {
            margin-bottom: 15px;
        }
        .alert {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">用户管理</h2>

    <!-- 添加用户按钮 -->
    <div class="text-end add-user-btn">
        <a href="<c:url value='/admin/adduser' />" class="btn btn-success">添加用户</a>
    </div>

    <!-- 删除/操作提示信息 -->
    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-success">
                ${sessionScope.message}
            <c:set var="message" value="" scope="session" />
        </div>
    </c:if>

    <!-- 用户表格 -->
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>用户ID</th>
            <th>昵称</th>
            <th>邮箱</th>
            <th>电话号码</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.uid}</td>
                <td>${user.nickname}</td>
                <td>${user.email}</td>
                <td>${user.number}</td>
                <td>
                    <a href="<c:url value='/admin/edituser?uid=${user.uid}' />" class="btn btn-primary btn-sm">编辑</a>
                    <a href="<c:url value='/admin/deleteuser?uid=${user.uid}' />" class="btn btn-danger btn-sm">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
        <cs:page index="${currentPage}" pageNum="${totalPages}" maxPagesVisible="3"/>
        <ul class="pagination">
            <c:if test="${hasLast == true}">
                <a class="page-link" href="<c:url value='/admin/manageUsers?page=${currentPage-1}' />"><c:out value="<" escapeXml="true"/></a>
            </c:if>
            <c:forEach var="i" begin="${start}" end="${end}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/admin/manageUsers?page=${i}' />">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${hasNext == true}">
                <a class="page-link" href="<c:url value='/admin/manageUsers?page=${currentPage+1}' />"><c:out value=">" escapeXml="true"/></a>
            </c:if>
        </ul>
    </nav>
</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
