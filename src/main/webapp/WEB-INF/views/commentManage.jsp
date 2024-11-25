<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>评论管理</title>
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
        .filter-user {
            margin-bottom: 15px;
        }
        .pagination {
            justify-content: center;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">评论管理</h2>

    <!-- 筛选用户评论 -->
    <form id="filterForm" class="filter-user d-flex justify-content-end">
        <input type="text" class="form-control w-25 me-2" id="filterUserId" placeholder="输入用户ID">
        <button type="button" class="btn btn-primary" onclick="filterComments()">筛选</button>
    </form>

    <!-- 评论表格 -->
    <table class="table table-striped table-bordered" id="commentsTable">
        <thead>
        <tr>
            <th>评论ID</th>
            <th>用户ID</th>
            <th>用户名</th>
            <th>作品名称</th>
            <th>评分</th>
            <th>评论时间</th>
            <th>评论内容</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${comments}">
            <tr class="comment-row" data-user-id="${comment.uid}">
                <td>${comment.iid}</td>
                <td>${comment.uid}</td>
                <td>${comment.userName}</td>
                <td>${comment.itemTitle}</td>
                <td>${comment.rating} 星</td>
                <td>${comment.cdate}</td>
                <td>${comment.content}</td>
                <td>
                    <a href="<c:url value='/admin/deleteComment?id=${comment.iid}' />" class="btn btn-danger btn-sm">删除</a>
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
            <c:if test="${hasLast == true}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='/admin/commentManage?page=${currentPage - 1}' />">&laquo;</a>
                </li>
            </c:if>

            <!-- 页码 -->
            <c:forEach var="i" begin="${start}" end="${end}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='/admin/commentManage?page=${i}' />">${i}</a>
                </li>
            </c:forEach>

            <c:if test="${hasNext == true}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='/admin/commentManage?page=${currentPage + 1}' />">&raquo;</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<script>
    // 前端筛选评论
    function filterComments() {
        const filterUserId = document.getElementById('filterUserId').value.trim(); // 获取输入的用户ID
        const rows = document.querySelectorAll('.comment-row'); // 获取所有评论行

        rows.forEach(row => {
            const userId = row.getAttribute('data-user-id'); // 获取行中用户ID
            if (filterUserId === "" || userId.includes(filterUserId)) {
                row.style.display = ""; // 显示匹配的行
            } else {
                row.style.display = "none"; // 隐藏不匹配的行
            }
        });
    }
</script>
</body>
</html>
