<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${item.title} - Item Review</title>
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
    .rate-section {
      display: flex;
      align-items: flex-start;
      gap: 20px;
      margin-top: 20px;
    }
    .rating-info {
      max-width: 300px;
      position: relative;
    }
    .rating-info p {
      margin: 0;
    }
    .rating-distribution {
      width: 100%;
    }
    .bar-container {
      display: flex;
      align-items: center;
      gap: 10px;
      margin-bottom: 5px;
    }
    .bar {
      height: 10px;
      background-color: #007bff;
      border-radius: 5px;
    }
    .rate-button {
      margin-top: 50px;
      background-color: #007bff;
      color: white;
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      cursor: pointer;
      text-align: center;
      display: inline-block;
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
    .review-rating {
      color: #ffcc00; /* Gold for stars */
      font-size: 18px;
      margin-bottom: 5px;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h1>${item.title}</h1>
  <div class="rate-section">
    <!-- Poster -->
    <div class="poster">
      <img src="${item.coverImage}" alt="Poster for ${item.title}">
      <div class="release-date">Release Date: ${item.releaseDate}</div>
    </div>

    <!-- Rating Information -->
    <div class="rating-info">
      <h3>Overall Rating: ${item.rating.toFixed(1)} / 5</h3>
      <p>Rated by ${item.totalRatings} users</p>
      <div class="rating-distribution">
        <c:forEach var="i" begin="1" end="5">
          <c:set var="star" value="${6 - i}" />
          <div class="bar-container">
            <span>${star}★</span>
            <div class="bar" style="width: ${item.stars[star - 1] / item.totalRatings * 100}%;"></div>
            <span>${item.stars[star - 1] / item.totalRatings * 100}%</span>
          </div>
        </c:forEach>
      </div>
      <a href="<c:url value='/editreview?itemId=${item.iid}' />" class="rate-button">Rate</a>
    </div>
  </div>

  <!-- User Reviews Section -->
  <div class="reviews-section">
    <h3>User Reviews</h3>
    <c:forEach items="${item.reviews}" var="review">
      <div class="review">
        <p><strong>${review.username}</strong></p>
        <div class="review-rating">
          <c:forEach var="star" begin="1" end="${review.rating}">
            ★
          </c:forEach>
        </div>
        <p>${review.comment}</p>
      </div>
    </c:forEach>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
