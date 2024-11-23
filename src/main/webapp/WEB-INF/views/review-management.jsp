<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div class="container mt-5">
  <h2 class="text-center mb-4">Review Management</h2>
  <table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
      <th>Reviewer</th>
      <th>Content Title</th>
      <th>Rating</th>
      <th>Review</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="review" items="${reviews}">
      <tr>
        <td>${review.reviewerName}</td>
        <td>${review.contentTitle}</td>
        <td>${review.rating}</td>
        <td>${review.text}</td>
        <td>
          <a href="edit-review?id=${review.id}" class="btn btn-warning btn-sm">Edit</a>
          <a href="delete-review?id=${review.id}" class="btn btn-danger btn-sm">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>