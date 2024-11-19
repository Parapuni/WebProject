<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 响应式设计 */
        .container {
            max-width: 1200px;
        }
        /* 自定义渐变背景 */
        body {
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            color: #343a40;
            min-height: 100vh;
            margin: 0;
            padding: 0;
        }
        /* 卡片样式优化 */
        .card {
            border: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        /* 按钮颜色调整 */
        .btn-warning {
            background-color: #ffcc00;
            border: none;
        }
        .btn-danger {
            background-color: #ff4d4d;
            border: none;
        }
        .btn-warning:hover {
            background-color: #e6b800;
        }
        .btn-danger:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>
<div class="container my-5">
    <h2 class="text-center mb-4">Admin Dashboard</h2>
    <div class="mt-4">
        <h3>User Management</h3>
        <c:forEach var="user" items="${users}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${user.username}</h5>
                    <p class="card-text">${user.email}</p>
                    <div class="d-flex justify-content-end">
                        <a href="/editUser?id=${user.id}" class="btn btn-warning me-2">Edit</a>
                        <a href="/deleteUser?id=${user.id}" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="mt-4">
        <h3>Review Management</h3>
        <c:forEach var="review" items="${reviews}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${review.itemName}</h5>
                    <p class="card-text">${review.reviewText}</p>
                    <div class="d-flex justify-content-end">
                        <a href="/editReview?id=${review.id}" class="btn btn-warning me-2">Edit</a>
                        <a href="/deleteReview?id=${review.id}" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
