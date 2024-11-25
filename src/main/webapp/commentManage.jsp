<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ include file="/WEB-INF/views/banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的评论</title>
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
        .pagination {
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">我的评论</h2>

    <!-- 添加评论按钮 -->
    <div class="mb-3 text-end">
        <a href="<c:url value='/user/addComment' />" class="btn btn-success">添加评论</a>
    </div>

    <!-- 评论表格 -->
    <table class="table table-striped table-bordered" id="commentsTable">
        <thead>
        <tr>
            <th>评论ID</th>
            <th>作品名称</th>
            <th>评分</th>
            <th>评论时间</th>
            <th>评论内容</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>${comment.iid}</td>
                <td>${comment.itemTitle}</td>
                <td>${comment.rating} 星</td>
                <td>${comment.cdate}</td>
                <td>${comment.content}</td>
                <td>
                    <a href="<c:url value='/user/editComment?id=${comment.iid}' />" class="btn btn-primary btn-sm">编辑</a>
                    <a href="<c:url value='/user/deleteComment?id=${comment.iid}' />" class="btn btn-danger btn-sm">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 分页 -->
    <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
        <cs:page index="${currentPage}" pageNum="${totalPages}" maxPagesVisible="5"/>
        <ul class="pagination">
            <!-- 上一页 -->
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='/user/commentManage?page=${currentPage - 1}' />">&laquo;</a>
                </li>
            </c:if>

            <!-- 页码 -->
            <c:forEach var="i" begin="${start}" end="${end}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/user/commentManage?page=${i}' />">${i}</a>
                </li>
            </c:forEach>

            <!-- 下一页 -->
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='/user/commentManage?page=${currentPage + 1}' />">&raquo;</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</body>
</html>
