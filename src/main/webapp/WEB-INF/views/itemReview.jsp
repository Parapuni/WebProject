<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

    .progress-bar {
      flex: 1;
      height: 10px;
      background-color: #007bff;
      border-radius: 5px;
      width: 100%;
      appearance: none;
    }
    .progress-bar::-webkit-progress-bar {
      background-color: #f0f0f0; /* 背景色 */
      border-radius: 8px;
    }

    .progress-bar::-webkit-progress-value {
      background-color: #007bff; /* 进度条颜色 */
      border-radius: 8px;
      transition: width 0.3s ease; /* 动画效果 */
    }

    .progress-bar::-moz-progress-bar {
      background-color: #007bff;
      border-radius: 8px;
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
<div class="container mt-5 p-4 shadow rounded bg-white">
  <!-- Title -->
  <h1 class="text-center text-primary mb-5">${item.title}</h1>

  <div class="row">
    <!-- Poster Section -->
    <div class="col-md-4 text-center mb-4">
      <img src="<c:url value='/imagines/${item.coverImagine}' />" alt="${item.title}" class="img-fluid shadow rounded">
      <div class="mt-3">
        <span class="text-muted"><strong>Release Date:</strong> ${item.releaseDate}</span>
      </div>
      <div class="mt-2">
        <strong>Tags:</strong>
        <c:forEach var="category" items="${item.categories}">
          <span class="badge bg-secondary me-1">${category}</span>
        </c:forEach>
      </div>
      <div class="mt-3">
        <h4 class="text-success"><strong>Overall Rating:</strong> <fmt:formatNumber value="${item.rating}" pattern="#.0" type="number"/></h4>
        <cs:rating rating="${item.rating}"/>
      </div>
    </div>

    <!-- Details and Rating Section -->
    <div class="col-md-8">
      <div class="card border-0 shadow-sm mb-4">
        <div class="card-body">
          <h5 class="card-title text-primary">Details</h5>
          <div class="card-text">
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
        </div>
      </div>

      <div class="card border-0 shadow-sm">
        <div class="card-body">
          <h5 class="card-title text-primary">Rating Distribution</h5>
          <div class="rating-distribution">
            <c:forEach var="i" begin="1" end="5">
              <c:set var="star" value="${6 - i}" />
              <c:set var="percentage" value="${percentages[star - 1]}" />
              <div class="d-flex align-items-center mb-3">
                <span class="me-3">${star}★</span>
                <progress class="progress-bar flex-grow-1 me-3" value="${percentage}" max="100"></progress>
                <span>${percentage}%</span>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>

      <!-- Rate Button -->
      <div class="text-center mt-4">
        <c:choose>
          <c:when test="${empty sessionScope.user}">
            <button class="btn btn-warning w-50" onclick="alert('Please log in to rate this item'); location.href='<c:url value='/login' />';">Rate</button>
          </c:when>
          <c:otherwise>
            <a href="<c:url value='/editreview?id=${item.iid}&category=${category}' />" class="btn btn-primary w-50">Rate</a>
          </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>

  <!-- User Reviews Section -->
  <div class="mt-5">
    <h3 class="text-primary">User Comments</h3>
    <div class="review-list">
      <c:if test="${empty comments}">
        <p class="text-muted">No comments yet. Be the first to review!</p>
      </c:if>
      <c:forEach items="${comments}" var="comment">
        <div class="review p-3 mb-3 border rounded shadow-sm bg-light">
          <div class="d-flex justify-content-between align-items-center mb-2">
            <span class="text-dark fw-bold">${comment.userName}</span>
            <span class="text-muted">${comment.cdate}</span>
          </div>
          <div class="review-rating text-warning mb-2">
            <c:forEach var="star" begin="1" end="${comment.rating}">
              ★
            </c:forEach>
          </div>
          <p class="text-secondary">${comment.content}</p>
        </div>
      </c:forEach>
    </div>
  </div>

  <!-- Pagination -->
  <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
    <cs:page index="${currentPage}" pageNum="${totalPages}" maxPagesVisible="2"/>
    <ul class="pagination">
      <c:if test="${hasLast == true}">
        <li class="page-item">
          <a class="page-link" href="<c:url value='/items-details?page=${currentPage-1}&category=${category}&id=${item.iid}' />">&lt;</a>
        </li>
      </c:if>
      <c:forEach var="i" begin="${start}" end="${end}">
        <li class="page-item ${i == currentPage ? 'active' : ''}">
          <a class="page-link" href="<c:url value='/item-details?page=${i}&category=${category}&id=${item.iid}' />">${i}</a>
        </li>
      </c:forEach>
      <c:if test="${hasLast == true}">
        <li class="page-item">
          <a class="page-link" href="<c:url value='/items-details?page=${currentPage+1}&category=${category}&id=${item.iid}' />">&gt;</a>
        </li>
      </c:if>
    </ul>
  </nav>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
