<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div class="container mt-5">
  <h2 class="text-center mb-4">User Management</h2>
  <table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
      <th>Username</th>
      <th>Email</th>
      <th>Phone Number</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
      <tr>
        <td>${user.nickname}</td>
        <td>${user.email}</td>
        <td>${user.number}</td>
        <td>
          <a href="edit-user?id=${user.uid}" class="btn btn-warning btn-sm">Edit</a>
          <a href="delete-user?id=${user.uid}" class="btn btn-danger btn-sm">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

