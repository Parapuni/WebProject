<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/csTag.tld" prefix="cs" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>作品管理</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .table {
      margin-top: 20px;
      background-color: #ffffff;
    }
    .table th, .table td {
      text-align: center;
    }
    .btn {
      font-size: 14px;
    }
    .add-item-btn {
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center">作品管理</h2>

  <!-- 添加作品按钮 -->
  <div class="text-end add-item-btn">
    <a href="<c:url value='/admin/addMusic' />" class="btn btn-success">添加作品</a>
  </div>

  <!-- 删除/操作提示信息 -->
  <c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success">
        ${sessionScope.message}
      <c:set var="message" value="" scope="session" />
    </div>
  </c:if>

  <!-- 音乐表格 -->
  <table class="table table-striped table-bordered">
    <thead>
    <tr>
      <th>ID</th>
      <th>标题</th>
      <th>类别</th>
      <th>发布日期</th>
      <th>评分</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
      <tr>
        <td>${item.iid}</td>
        <td>${item.title}</td>
        <td>${item.categories[0]}</td>
        <td>${item.releaseDate}</td>
        <td>${item.rating}</td>
        <td>
          <a href="<c:url value='/admin/editMusic?id=${item.iid}' />" class="btn btn-primary btn-sm">编辑</a>
          <a href="<c:url value='/admin/deleteMusic?id=${item.iid}' />" class="btn btn-danger btn-sm">删除</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Pagination -->
  <nav aria-label="Page navigation" class="d-flex justify-content-center mt-4">
    <cs:page index="${currentPage}" pageNum="${totalPages}" maxPagesVisible="3"/>
    <ul class="pagination">
      <c:if test="${hasLast == true}">
        <a class="page-link" href="<c:url value='/admin/manageMusic?page=${currentPage-1}' />"><c:out value="<" escapeXml="true"/></a>
      </c:if>
      <c:forEach var="i" begin="${start}" end="${end}">
        <li class="page-item ${i == currentPage ? 'active' : ''}">
          <a class="page-link" href="<c:url value='/admin/manageMusic?page=${i}' />">${i}</a>
        </li>
      </c:forEach>
      <c:if test="${hasNext == true}">
        <a class="page-link" href="<c:url value='/admin/manageMusic?page=${currentPage+1}' />"><c:out value=">" escapeXml="true"/></a>
      </c:if>
    </ul>
  </nav>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../footer.jsp"/>
</body>
</html>
