<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome to the Reviews System</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f4f4;
    }
    .content-section .row {
      margin-top: 20px;
    }
    .section-header {
      font-size: 24px;
      font-weight: bold;
      margin-bottom: 10px;
      position: relative;
      padding-bottom: 10px;
    }
    .section-header::after {
      content: '';
      position: absolute;
      bottom: 0;
      left: 0;
      width: 100%;
      height: 2px;
      background-color: #007bff;
    }
    .more-link {
      position: absolute;
      right: 0;
      bottom: 0;
      font-size: 16px;
      text-decoration: none;
      color: #007bff;
    }
    .card {
      text-align: center;
    }
    .card img {
      width: 100%;
      height: auto;
      display: block;
    }
    .book-section {
      margin-bottom: 40px; /* Added bottom margin for spacing */
    }
    .sidebar {
      position: fixed;
      width: 200px;
      top: 100px;
      right: -50px; /* Adjust right position for more space */
    }
    .sidebar h3 {
      font-size: 20px;
      margin-bottom: 15px;
    }
    .list-group-item {
      cursor: pointer;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <div class="row">
    <div class="col-md-9">
      <!-- New Books Section -->
      <div class="book-section">
        <div class="section-header">New Books<a href="<c:url value='/books' />" class="more-link">More...</a></div>
        <div class="row">
          <c:forEach var="i" begin="1" end="4">
            <div class="col-md-3">
              <div class="card">
                <img src="book_image${i}.jpg" alt="Book ${i}">
              </div>
            </div>
          </c:forEach>
        </div>
      </div>

      <!-- Now Showing Section -->
      <div class="section-header">Now Showing<a href="<c:url value='/movies' />" class="more-link">More...</a></div>
      <div class="row">
        <c:forEach var="i" begin="1" end="4">
          <div class="col-md-3">
            <div class="card">
              <img src="movie_image${i}.jpg" alt="Movie ${i}">
            </div>
          </div>
        </c:forEach>
      </div>
    </div>

    <!-- Sidebar for Weekly Recommendations -->
    <div class="col-md-3 sidebar">
      <h3>This Week's Top Picks</h3>
      <ul class="list-group">
        <c:forEach var="i" begin="1" end="10">
          <li class="list-group-item" onclick="location.href='<c:url value='/details?id=${i}' />'">
            Top Pick ${i}
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
