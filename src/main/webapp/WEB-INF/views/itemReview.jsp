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
      background-color: #f8f9fa;
    }

    .container {
      background-color: #ffffff;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .rate-section {
      display: flex;
      flex-wrap: wrap;
      align-items: flex-start;
      gap: 20px;
      margin-top: 20px;
    }

    .poster img {
      max-width: 100%;
      width: 300px;
      height: auto;
      border-radius: 8px;
    }

    .rating-info {
      flex: 1;
      min-width: 300px;
    }

    .rating-distribution .bar-container {
      display: flex;
      align-items: center;
      gap: 10px;
      margin-bottom: 10px;
    }

    .bar {
      flex: 1;
      height: 10px;
      background-color: #007bff;
      border-radius: 5px;
    }

    .rate-button {
      margin-top: 10px;
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border-radius: 5px;
      font-size: 16px;
      cursor: pointer;
      border: none;
      text-align: center;
    }

    .rate-button:hover {
      background-color: #0056b3;
    }

    .reviews-section {
      margin-top: 40px;
    }

    .review-list {
      margin-top: 20px;
    }

    .review {
      background-color: #f9f9f9;
      padding: 15px;
      border-radius: 8px;
      margin-bottom: 10px;
      border: 1px solid #ddd;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .review p {
      margin: 0 0 10px;
    }

    .review-date {
      font-size: 14px;
      color: #666;
      margin-left: 10px;
    }

    .review-rating {
      color: #ffcc00; /* Gold for stars */
      font-size: 18px;
      margin-bottom: 10px;
    }

    @media (max-width: 768px) {
      .rate-section {
        flex-direction: column;
      }

      .poster img {
        max-width: 100%;
      }
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center mb-4">${item.title}</h1>
  <div class="rate-section">
    <!-- Poster -->
    <div class="poster">
      <img src="${item.coverImagine}" alt="${item.title}" class="img-fluid shadow-lg rounded">
      <div class="release-date">Release Date: ${item.releaseDate}</div>
    </div>

    <!-- Rating Information -->
    <div class="rating-info">
      <h3>Overall Rating: ${item.rating}</h3>
      <div class="rating-details">
        <c:choose>
          <c:when test="${category == 'Book'}">
            <p><strong>Authors:</strong> ${item.authors}</p>
            <p><strong>Publisher:</strong> ${item.publisher}</p>
            <p><strong>Introduction:</strong> ${item.introduction}</p>
          </c:when>
          <c:when test="${category == 'Movie'}">
            <p><strong>Director:</strong> ${item.director}</p>
            <p><strong>Writers:</strong> ${item.writers}</p>
            <p><strong>Cast:</strong> ${item.cast}</p>
            <p><strong>Introduction:</strong> ${item.introduction}</p>
          </c:when>
          <c:when test="${category == 'Music'}">
            <p><strong>Artists:</strong> ${item.artists}</p>
            <p><strong>Album:</strong> ${item.album}</p>
            <p><strong>Lyrics:</strong> ${item.lyrics}</p>
          </c:when>
        </c:choose>
      </div>

      <!-- Star Rating -->
      <div class="rating-distribution mt-3">
        <c:forEach var="i" begin="1" end="5">
          <c:set var="star" value="${6 - i}" />
          <div class="bar-container">
            <span>${star}★</span>
            <div class="bar" style="width: ${item.rating}%;"></div>
            <span>${item.rating}%</span>
          </div>
        </c:forEach>
      </div>

      <!-- Rate Button -->
      <c:choose>
        <c:when test="${empty sessionScope.user}">
          <button class="rate-button" onclick="alert('Please log in to rate this item'); location.href='<c:url value='/login' />';">Rate</button>
        </c:when>
        <c:otherwise>
          <a href="<c:url value='/editreview?id=${item.iid}&category=${category}' />" class="rate-button">Rate</a>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <!-- User Reviews Section -->
  <div class="reviews-section mt-5">
    <h3>User Comments</h3>
    <c:if test="${empty comments}">
      <p class="text-muted">No comments yet. Be the first to review!</p>
    </c:if>
    <div class="review-list">
      <c:forEach items="${comments}" var="comment">
        <div class="review">
          <p><strong>${comment.userName}</strong> <span class="review-date">${comment.cdate}</span></p>
          <div class="review-rating">
            <c:forEach var="star" begin="1" end="${comment.rating}">
              ★
            </c:forEach>
          </div>
          <p>${comment.content}</p>
        </div>
      </c:forEach>
    </div>
  </div>

  <!-- Pagination -->
  <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
    <ul class="pagination">
      <c:forEach var="i" begin="${start}" end="${end}">
        <li class="page-item ${i == currentPage ? 'active' : ''}">
          <a class="page-link" href="<c:url value='/item-details?page=${i}&category=${category}&id=${item.iid}' />">${i}</a>
        </li>
      </c:forEach>
    </ul>
  </nav>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
