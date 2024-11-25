<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>作品管理</title>
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
        .add-item-btn {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">作品管理</h2>

    <!-- 添加作品按钮 -->
    <div class="text-end add-item-btn">
        <a href="<c:url value='/admin/additem' />" class="btn btn-success">添加作品</a>
    </div>

    <!-- 筛选 -->
    <form action="<c:url value='/admin/itemmanage' />" method="get" class="d-flex justify-content-end mb-3">
        <select name="category" class="form-select me-2" style="width: auto;">
            <option value="All" ${param.category == 'All' ? 'selected' : ''}>所有类别</option>
            <option value="Movie" ${param.category == 'Movie' ? 'selected' : ''}>电影</option>
            <option value="Book" ${param.category == 'Book' ? 'selected' : ''}>书籍</option>
            <option value="Music" ${param.category == 'Music' ? 'selected' : ''}>音乐</option>
        </select>
        <button type="submit" class="btn btn-primary">筛选</button>
    </form>

    <!-- 作品表格 -->
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>标题</th>
            <th>类别</th>
            <th>发布日期</th>
            <th>评分</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.iid}</td>
                <td>${item.title}</td>
                <td>${item.categories[0]}</td>
                <td>${item.releaseDate}</td>
                <td>${item.rating}</td>
                <td>
                    <a href="<c:url value='/admin/edititem?id=${item.iid}' />" class="btn btn-primary btn-sm">编辑</a>
                    <a href="<c:url value='/admin/deleteitem?id=${item.iid}' />" class="btn btn-danger btn-sm">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 分页 -->
    <nav aria-label="分页导航" class="d-flex justify-content-center mt-4">
        <ul class="pagination">
            <!-- 上一页 -->
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='/admin/itemmanage?page=${currentPage - 1}&category=${param.category}' />">&laquo;</a>
                </li>
            </c:if>

            <!-- 页码 -->
            <c:forEach var="i" begin="${start}" end="${end}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/admin/itemmanage?page=${i}&category=${param.category}' />">${i}</a>
                </li>
            </c:forEach>

            <!-- 下一页 -->
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='/admin/itemmanage?page=${currentPage + 1}&category=${param.category}' />">&raquo;</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../footer.jsp"/>
</body>
</html>
