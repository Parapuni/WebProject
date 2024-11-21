<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Comment System</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f4f4;
    }
    .navbar-custom {
      background-color: #343a40;
      color: #ffffff;
    }
    .navbar-custom .navbar-brand, .navbar-custom .nav-link {
      color: #ffffff; /* Ensure text is visible */
      font-weight: 400; /* Adjusted for visual balance */
    }
    .nav-link {
      margin-right: 15px; /* Consistent spacing */
    }
    .form-control {
      margin-right: 10px; /* Space before the search button */
    }
  </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom">
  <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value='/' />">Comment System</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/items?category=Movies' />">Movies</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/items?category=Books' />">Books</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<c:url value='/items?category=Music' />">Music</a>
        </li>

      </ul>
      <form class="d-flex" action="<c:url value='/search' />" method="get">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="query">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>
      <ul class="navbar-nav ms-auto">
        <c:choose>
          <c:when test="${not empty sessionScope.user}">
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/profile' />">${sessionScope.user.nickname}</a>
            </li>
          </c:when>
          <c:when test="${not empty sessionScope.admin}">
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/admin-dashboard' />">Admin Dashboard</a>
            </li>
          </c:when>
          <c:otherwise>
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/login' />">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="<c:url value='/register' />">Register</a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<div class="container mt-4">
  <!-- Content and links specific to books, movies, or other categories can be dynamically loaded here -->
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
