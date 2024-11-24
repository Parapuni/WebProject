<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>欢迎来到评论系统</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f4f4;
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

    .card img {
      width: 100%;
      height: 180px;
      object-fit: cover;
    }

    .card p {
      text-align: center;
      font-size: 14px;
      margin-top: 5px;
      font-weight: bold;
    }

    .more-link {
      font-size: 14px;
      float: right;
      text-decoration: none;
      color: #007bff;
    }

    .more-link:hover {
      text-decoration: underline;
    }

    .weekly-recommendation ul {
      list-style: none;
      padding: 0;
    }

    .weekly-recommendation li {
      margin-bottom: 5px;
    }
  </style>
</head>
<body>
<div class="container mt-4">
  <div class="row">
    <div class="col-md-9">
      <!-- 最新书籍 -->
      <div class="section-header">
        最新书籍
        <a href="<c:url value='/items?category=Book' />" class="more-link">更多...</a>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <img src="<c:url value="/imagines/IID1cover.jpg" />" alt="奶龙">
            <!-- 为 book1 添加超链接到 itemReview.jsp -->
            <p><a href="<c:url value='/item-details?id=1&category=Book' />">奶龙娘</a></p>
          </div>
        </div>
        <c:forEach var="book" items="${recentBooks}">
          <div class="col-md-3">
            <div class="card">
              <img src="<c:url value="/imagines/${book.coverImagine}" /> " alt="奶龙">
              <p><a href="<c:url value='/item-details?id=${book.iid}&category=Book' />">${book.title}</a></p>
              <p>${book.releaseDate}</p>
            </div>
          </div>
        </c:forEach>
      </div>

      <!-- 正在上映 -->
      <div class="section-header">
        正在热映
        <a href="<c:url value='/items?category=Movie' />" class="more-link">更多...</a>
      </div>
      <div class="row">
        <c:forEach var="movie" items="${recentMovies}">
        <div class="col-md-3">
          <div class="card">
            <img src="<c:url value="/imagines/${movie.coverImagine}" /> " alt="奶龙">
            <p><a href="<c:url value='/item-details?id=${movie.iid}&category=Movie' />">${movie.title}</a></p>
            <p>${movie.releaseDate}</p>
          </div>
        </div>
        </c:forEach>
      </div>

      <!-- 最新音乐 -->
      <div class="section-header">
        最新音乐
        <a href="<c:url value='/items?category=Music' />" class="more-link">更多...</a>
      </div>
      <div class="row">
        <c:forEach var="music" items="${recentMusics}">
          <div class="col-md-3">
            <div class="card">
              <img src="<c:url value="/imagines/${music.coverImagine}"/>" alt="MUSIC">
              <p><a href="<c:url value='/item-details?id=${music.iid}&category=Movie' />">${music.title}</a></p>
              <p>${music.releaseDate}</p>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <div class="col-md-3">
      <h3>每周推荐</h3>
      <div class="weekly-recommendation">
        <ul>
          <div class="card">
            <c:forEach var="item" items="${recommended}">
              <li>
                <a href="<c:url value='/item-details?id=${item.iid}&category=${item.type}' />">${item.title}</a>
                <br>
              </li>
            </c:forEach>
          </div>
        </ul>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
