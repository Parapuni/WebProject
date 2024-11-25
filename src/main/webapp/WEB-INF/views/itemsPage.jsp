<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="banner.jsp" %>
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
      <div class="sidebar p-3 mb-4 border rounded shadow-sm">
        <h4 class="mb-3 ">选择作品种类</h4>
        <form action="<c:url value='/filt_items' />" method="get" id="categoryTagForm">

            <c:forEach var="category" items="${categories}">
              <div class="form-check">
              <input
                      class="form-check-input category-checkbox"
                      type="radio"
                      name="category"
                      value="${category}"
                      id="category-${category}"
                      <c:if test="${currentCategory == category}">checked</c:if>>
              <label class="form-check-label" for="category-${category}">
                  ${category}
              </label>
              </div>
            </c:forEach>


          <h4 class="mb-3 mt-4">选择标签</h4>
          <input type="hidden" name="category" value="${currentCategory}" />
          <div class="form-check-container" style="max-height: 200px; overflow-y: auto;">
            <c:forEach var="tag" items="${tags}">
              <div class="form-check">
                <input
                        class="form-check-input"
                        type="checkbox"
                        name="tags"
                        value="${tag}"
                        id="tag-${tag}"
                        <c:if test="${fn:contains(selectedTags, tag)}">checked</c:if>>
                <label class="form-check-label" for="tag-${tag}">
                    ${tag}
                </label>
              </div>
            </c:forEach>
          </div>

          <button type="submit" class="btn btn-primary mt-3 w-100">筛选</button>
        </form>
      </div>

    </div>


    <!-- Item List -->
    <div class="col-md-9">
      <div class="row">
        <c:forEach var="item" items="${items}">
          <div class="col-md-4">
            <div class="card">
              <img src="<c:url value='/imagines/${item.coverImagine}' />" alt="${item.title}" class="card-img-top">
              <div class="card-body">
                <h5><a href="<c:url value='/item-details?id=${item.iid}&category=${category}' />" class="text-dark">${item.title}</a></h5>
                <p>${item.releaseDate}</p>
                <!-- Display Rating -->
                <div class="rating"><fmt:formatNumber value="${item.rating}" pattern="#.0" type="number"/></div>
                <cs:rating rating="${item.rating}"/>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
    <!-- Pagination -->
    <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
      <cs:page index="${currentPage}" pageNum="${totalPages}" maxPagesVisible="3"/>
      <ul class="pagination">
        <c:if test="${hasLast == true}">
          <a class="page-link" href="<c:url value='/items?page=${currentPage-1}&category=${category}' />"><c:out value="<" escapeXml="true"/></a>
        </c:if>
        <c:forEach var="i" begin="${start}" end="${end}">
          <li class="page-item ${i == currentPage ? 'active' : ''}">
            <a class="page-link" href="<c:url value='/items?page=${i}&category=${category}' />">${i}</a>
          </li>
        </c:forEach>
        <c:if test="${hasNext == true}">
          <a class="page-link" href="<c:url value='/items?page=${currentPage+1}&category=${category}' />"><c:out value=">" escapeXml="true"/></a>
        </c:if>
      </ul>
    </nav>

  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="footer.jsp" />
</body>
</html>
