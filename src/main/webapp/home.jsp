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
      padding-bottom: 60px; /* 避免内容与页脚冲突 */
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
      cursor: pointer; /* 鼠标悬停时显示手形指针 */
    }
    .card img {
      width: 100%;
      height: auto;
      display: block;
    }
    .book-section {
      margin-bottom: 40px; /* 增加间距 */
    }

    .movie-section {
      margin-bottom: 40px; /* 增加间距 */
    }
    .sidebar {
      position: sticky;
      top: 100px; /* 调整位置以适应头部导航 */
      height: calc(100vh - 120px); /* 计算高度以防止覆盖页脚 */
      overflow-y: auto; /* 添加滚动 */
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
    <!-- Main Content -->
    <div class="col-md-9">
      <!-- New Books Section -->
      <div class="book-section">
        <div class="section-header">New Books
          <a href="<c:url value='/items?category=Books' />" class="more-link">More...</a>
        </div>
        <div class="row">
          <c:forEach items="${books}" var="book">
            <div class="col-md-3">
              <div class="card" onclick="location.href='<c:url value='/itemReview?type=book&id=${book.iid}' />'">
                <img src="${book.coverImagine}" alt="${book.title}">
              </div>
            </div>
          </c:forEach>
        </div>
      </div>

      <!-- Now Showing Section -->
      <div class="movie-section">
      <div class="section-header">Now Showing
        <a href="<c:url value='/items?category=Movies' />" class="more-link">More...</a>
      </div>
      <div class="row">
        <c:forEach items="${movies}" var="movie">
          <div class="col-md-3">
            <div class="card" onclick="location.href='<c:url value='/itemReview?type=movie&id=${movie.iid}' />'">
              <img src="${movie.coverImagine}" alt="${movie.title}">
            </div>
          </div>
        </c:forEach>
      </div>
      </div>

      <!-- New Music Section -->
      <div class="section-header">New Music
        <a href="<c:url value='/items?category=Music' />" class="more-link">More...</a>
      </div>
      <div class="row">
        <c:forEach items="${music}" var="track">
          <div class="col-md-3">
            <div class="card" onclick="location.href='<c:url value='/itemReview?type=music&id=${track.iid}' />'">
              <img src="${track.coverImagine}" alt="${track.title}">
            </div>
          </div>
        </c:forEach>
      </div>
    </div>

    <!-- Sidebar for Weekly Recommendations -->
    <div class="col-md-3 sidebar">
      <h3>This Week's Top Picks</h3>
      <ul class="list-group">
        <c:forEach items="${topPicks}" var="pick">
          <li class="list-group-item" onclick="location.href='<c:url value='/itemReview?id=${pick.iid}' />'">
              ${pick.title}
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
