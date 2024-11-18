<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Header and Footer</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<header>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="/">Book, Movie, Music Reviews</a>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
          <c:choose>
            <c:when test="${empty user}">
              <li class="nav-item"><a class="nav-link" href="/login">Login</a></li>
              <li class="nav-item"><a class="nav-link" href="/register">Register</a></li>
            </c:when>
            <c:otherwise>
              <li class="nav-item"><a class="nav-link" href="/dashboard">Dashboard</a></li>
              <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </div>
  </nav>
</header>

<!-- Page Content Here -->

<footer class="bg-light text-center py-3">
  <p>&copy; 2024 Book, Movie, Music Reviews. All rights reserved.</p>
</footer>
</body>
</html>
