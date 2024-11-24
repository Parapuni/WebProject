<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Comments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Manage Comments</h1>
    <form action="<c:url value='/admin/managecomments' />" method="get" class="mb-4">
        <div class="input-group">
            <input type="number" class="form-control" name="uid" placeholder="Enter User ID" value="${uid}">
            <button class="btn btn-primary" type="submit">Search</button>
        </div>
    </form>

    <c:if test="${not empty comments}">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Comment ID</th>
                <th>Item Title</th>
                <th>User Name</th>
                <th>Content</th>
                <th>Rating</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="comment" items="${comments}">
                <tr>
                    <td>${comment.iid}</td>
                    <td>${comment.itemTitle}</td>
                    <td>${comment.userName}</td>
                    <td>${comment.content}</td>
                    <td>${comment.rating}</td>
                    <td>${comment.cdate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <nav>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="<c:url value='/admin/managecomments?uid=${uid}&page=${currentPage - 1}' />">Previous</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="<c:url value='/admin/managecomments?uid=${uid}&page=${i}' />">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="<c:url value='/admin/managecomments?uid=${uid}&page=${currentPage + 1}' />">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </c:if>

    <c:if test="${empty comments && uid != null}">
        <p class="text-danger">No comments found for User ID: ${uid}</p>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
