<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Profile</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center">Your Profile</h2>

  <!-- Profile Information -->
  <h4>Account Information</h4>
  <form action="update-profile" method="POST">
    <div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" name="username" value="${sessionScope.username}" readonly>
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" name="email" value="${sessionScope.email}" required>
    </div>
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" name="password">
    </div>
    <button type="submit" class="btn btn-primary btn-block">Update Profile</button>
  </form>

  <!-- Reviews Section -->
  <h4 class="mt-5">Your Reviews</h4>
  <table class="table">
    <thead>
    <tr>
      <th scope="col">Content Type</th>
      <th scope="col">Title</th>
      <th scope="col">Rating</th>
      <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="review" items="${reviews}">
      <tr>
        <td>${review.contentType}</td>
        <td>${review.contentTitle}</td>
        <td>${review.rating}</td>
        <td>
          <a href="edit-review?id=${review.id}" class="btn btn-warning">Edit</a>
          <a href="delete-review?id=${review.id}" class="btn btn-danger">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
