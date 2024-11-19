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
  <style>
    /* 自定义导航栏样式 */
    .navbar {
      background-color: #007bff; /* 深蓝色背景 */
    }
    .navbar-brand, .nav-link {
      color: #ffffff !important; /* 白色文字 */
    }
    .nav-link:hover {
      color: #ffdd57 !important; /* 悬停时黄色文字 */
    }
    /* 页脚样式 */
    footer {
      background-color: #f8f9fa; /* 浅灰色背景 */
      color: #6c757d; /* 灰色文字 */
      border-top: 1px solid #dee2e6; /* 分割线 */
    }
  </style>
</head>
<body>
<header>
  <nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
      <a class="navbar-brand" href="/">Book, Movie, Music Reviews</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
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

<footer class="text-center py-3">
  <p>&copy; 2024 Book, Movie, Music Reviews. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
