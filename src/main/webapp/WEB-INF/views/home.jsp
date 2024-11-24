<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>欢迎来到评论系统</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="WEB-INF/css/itemCard.css" rel="stylesheet">
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
        <c:forEach var="item" items="${recentBooks}">
          <div class="col-md-3">
            <div class="card">
              <img src="<c:url value="/imagines/${item.coverImagine}" /> " alt="奶龙">
              <div class="card-body">
                <h5><a href="<c:url value='/item-details?id=${item.iid}&category=Book' />" class="text-dark">${item.title}</a></h5>
                <p>发行时间 ${item.releaseDate}</p>
                <!-- Display Rating -->
                <div class="rating">评分 <fmt:formatNumber value="${item.rating}" pattern="#.0" type="number"/></div>
                <cs:rating rating="${item.rating}"/>
              </div>
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
        <c:forEach var="item" items="${recentMovies}">
        <div class="col-md-3">
          <div class="card">
            <img src="<c:url value="/imagines/${item.coverImagine}" /> " alt="奶龙">
            <div class="card-body">
              <h5><a href="<c:url value='/item-details?id=${item.iid}&category=Movie' />" class="text-dark">${item.title}</a></h5>
              <p>发行时间 ${item.releaseDate}</p>
              <!-- Display Rating -->
              <div class="rating">评分 <fmt:formatNumber value="${item.rating}" pattern="#.0" type="number"/></div>
              <cs:rating rating="${item.rating}"/>
            </div>
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
        <c:forEach var="item" items="${recentMusics}">
          <div class="col-md-3">
            <div class="card">
              <img src="<c:url value="/imagines/${item.coverImagine}" /> " alt="奶龙">
              <div class="card-body">
                <h5><a href="<c:url value='/item-details?id=${item.iid}&category=Music' />" class="text-dark">${item.title}</a></h5>
                <p>发行时间 ${item.releaseDate}</p>
                <!-- Display Rating -->
                <div class="rating">评分 <fmt:formatNumber value="${item.rating}" pattern="#.0" type="number"/></div>
                <cs:rating rating="${item.rating}"/>
              </div>
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
