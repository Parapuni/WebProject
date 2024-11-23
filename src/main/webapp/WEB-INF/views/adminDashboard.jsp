<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 引入Google字体 */
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

        body {
            background: linear-gradient(135deg, #f8f9fa, #dfe3e8);
            color: #343a40;
            font-family: 'Roboto', sans-serif;
            min-height: 100vh;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            padding: 20px;
        }

        .card {
            margin-bottom: 1rem;
            border-radius: 8px; /* 添加圆角 */
            border: 1px solid #e0e0e0; /* 添加轻微边框 */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.05); /* 调整阴影效果 */
        }

        .btn-warning, .btn-danger {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 为按钮添加阴影 */
            border-radius: 4px; /* 统一按钮圆角 */
        }

        .btn-warning:hover {
            background-color: #f0b800;
        }

        .btn-danger:hover {
            background-color: #e60000;
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
        <c:forEach var="comment" items="${reviews}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${comment.itemName}</h5>
                    <p class="card-text">${comment.reviewText}</p>
                    <div class="d-flex justify-content-end">
                        <a href="/editReview?id=${comment.id}" class="btn btn-warning me-2">Edit</a>
                        <a href="/deleteReview?id=${comment.id}" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
