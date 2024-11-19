<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div class="container mt-5">
  <h2 class="text-center mb-4">item Management</h2>
  <div class="text-right mb-3">
    <a href="add-item.jsp" class="btn btn-success">Add New Content</a>
  </div>
  <table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
      <th>Title</th>
      <th>Type</th>
      <th>Year</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
      <tr>
        <td>${item.title}</td>
        <td>${item.type}</td>
        <td>${item.year}</td>
        <td>
          <a href="edit-item?id=${item.id}" class="btn btn-warning btn-sm">Edit</a>
          <a href="delete-item?id=${item.id}" class="btn btn-danger btn-sm">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<%@ include file="footer.jsp" %>
