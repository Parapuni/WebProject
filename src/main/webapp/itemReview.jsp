<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Item Review</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f4f4;
    }
    .poster {
      position: relative;
      display: inline-block;
      margin-bottom: 20px;
    }
    .poster img {
      width: 300px;
      height: auto;
    }
    .release-date {
      position: absolute;
      bottom: 10px;
      left: 10px;
      background: rgba(0, 0, 0, 0.5);
      color: white;
      padding: 5px;
      border-radius: 3px;
    }
    .rate-button {
      width: 300px;
      height: 50px;
      font-size: 18px;
      margin-top: 15px;
      background-color: #007bff;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
      display: block;
      text-align: center;
    }
    .rate-button:hover {
      background-color: #0056b3;
    }
    .reviews-section {
      margin-top: 40px;
    }
    .review {
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 5px;
      margin-bottom: 10px;
      background-color: white;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h1>${movie.title}</h1>
  <div class="poster">
    <img src="${movie.imageUrl}" alt="Poster for ${movie.title}">
    <div class="release-date">${movie.releaseDate}</div>
  </div>
  <button class="rate-button" id="rateButton">Rate This</button>

  <!-- User Reviews Section -->
  <div class="reviews-section">
    <h3>User Reviews</h3>
    <c:forEach items="${movie.reviews}" var="review">
      <div class="review">
        <p><strong>${review.username}</strong></p>
        <p>${review.comment}</p>
      </div>
    </c:forEach>
  </div>
</div>

<script>
  document.getElementById('rateButton').addEventListener('click', function() {
    <% if (session.getAttribute("user") == null) { %>
    window.location.href = "<c:url value='/login' />"; // Redirect to login
    <% } else { %>
    alert('Show rating input'); // Placeholder for rating input modal
    <% } %>
  });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
