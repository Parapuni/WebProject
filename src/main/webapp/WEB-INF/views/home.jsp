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
            <img src="book1.jpg" alt="奶龙">
            <!-- 为 book1 添加超链接到 itemReview.jsp -->
            <p><a href="<c:url value='/item-details?id=1&' />">奶龙娘</a></p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="book2.jpg" alt="书籍封面">
            <p>书名代号2</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="book3.jpg" alt="书籍封面">
            <p>书名代号3</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="book4.jpg" alt="书籍封面">
            <p>书名代号4</p>
          </div>
        </div>
      </div>

      <!-- 正在上映 -->
      <div class="section-header">
        正在上映
        <a href="<c:url value='/items?category=Movie' />" class="more-link">更多...</a>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <img src="movie1.jpg" alt="电影海报">
            <p>电影代号1</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="movie2.jpg" alt="电影海报">
            <p>电影代号2</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="movie3.jpg" alt="电影海报">
            <p>电影代号3</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="movie4.jpg" alt="电影海报">
            <p>电影代号4</p>
          </div>
        </div>
      </div>

      <!-- 最新音乐 -->
      <div class="section-header">
        最新音乐
        <a href="<c:url value='/items?category=Music' />" class="more-link">更多...</a>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <img src="music1.jpg" alt="音乐封面">
            <p>音乐代号1</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="music2.jpg" alt="音乐封面">
            <p>音乐代号2</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="music3.jpg" alt="音乐封面">
            <p>音乐代号3</p>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card">
            <img src="music4.jpg" alt="音乐封面">
            <p>音乐代号4</p>
          </div>
        </div>
      </div>
    </div>

    <div class="col-md-3">
      <h3>每周推荐</h3>
      <div class="weekly-recommendation">
        <ul>
          <!-- 将第一个推荐项改为 book1，并添加超链接到 itemReview.jsp -->
          <li><a href="<c:url value='/itemReview.jsp?itemId=book1' />">奶龙娘</a></li>
          <li>推荐2</li>
          <li>推荐3</li>
          <li>推荐4</li>
          <li>推荐5</li>
          <li>推荐6</li>
          <li>推荐7</li>
          <li>推荐8</li>
          <li>推荐9</li>
          <li>推荐10</li>
        </ul>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
