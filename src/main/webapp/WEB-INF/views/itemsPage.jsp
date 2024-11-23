<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${pageTitle}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f4f4;
    }
    .sidebar {
      background-color: #ffffff;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 8px;
      margin-bottom: 20px;
    }
    .sidebar h4 {
      font-size: 18px;
      margin-bottom: 15px;
    }
    .sidebar ul {
      list-style: none;
      padding: 0;
    }
    .sidebar ul li {
      padding: 8px 0;
      cursor: pointer;
      color: #007bff;
    }
    .sidebar ul li:hover {
      text-decoration: underline;
    }
    .card {
      margin-bottom: 20px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }
    .card img {
      height: 300px;
      object-fit: cover;
    }
    .card-body h5 {
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 10px;
    }
    .card-body p {
      font-size: 14px;
      color: #666;
    }
    .rating {
      font-size: 16px;
      color: #ffc107;
      margin-top: 5px;
    }
  </style>
</head>
<body>
<div class="container mt-4">
  <div class="row">
    <!-- Sidebar for Categories -->
    <div class="col-md-3">
      <div class="sidebar">
        <h4>Categories</h4>
        <ul>
          <c:forEach var="category" items="${categories}">
            <li onclick="location.href='<c:url value='/items?category=${category}' />'">
                ${category}
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>

    <!-- Item List -->
    <div class="col-md-9">
      <div class="row">
        <c:forEach var="item" items="${items}">
          <div class="col-md-4">
            <div class="card">
              <img src="<c:url value='/images/${item.coverImagine}' />" alt="${item.title}" class="card-img-top">
              <div class="card-body">
                <h5><a href="<c:url value='/item-details?id=${item.iid}&category=${category}' />" class="text-dark">${item.title}</a></h5>
                <p>${item.releaseDate}</p>
                <!-- Display Rating -->
                <div class="rating">${item.rating}</div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <!-- Pagination -->
    <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
      <ul class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
          <li class="page-item ${i == currentPage ? 'active' : ''}">
            <a class="page-link" href="<c:url value='/items?page=${i}&category=${category}' />">${i}</a>
          </li>
        </c:forEach>
        <%--        <cs:page index="${currentPage}" pageNum="${totalPage}" category="${category}"/>--%>
      </ul>
    </nav>

  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
