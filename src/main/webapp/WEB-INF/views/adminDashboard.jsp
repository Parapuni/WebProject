<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h2>Admin Dashboard</h2>
    <div class="mt-4">
        <h3>User Management</h3>
        <c:forEach var="user" items="${users}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${user.username}</h5>
                    <p class="card-text">${user.email}</p>
                    <a href="/editUser?id=${user.id}" class="btn btn-warning">Edit</a>
                    <a href="/deleteUser?id=${user.id}" class="btn btn-danger">Delete</a>
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
                    <a href="/editReview?id=${review.id}" class="btn btn-warning">Edit</a>
                    <a href="/deleteReview?id=${review.id}" class="btn btn-danger">Delete</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
