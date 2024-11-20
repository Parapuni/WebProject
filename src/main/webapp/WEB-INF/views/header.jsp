<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <a class="navbar-brand" href="<c:url value="/home"/>">Book, Movie, Music Reviews</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <c:choose>
            <c:when test="${not empty sessionScope.user}">
              <li class="nav-item">
                <a class="nav-link" href="<c:url value="/home"/>">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value="/submit-review"/>">Submit Review</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value="/profile"/>">Profile</a>
              </li>
              <li class="nav-item">
                <a class="nav-link text-danger" href="<c:url value="/logout"/>">Logout</a>
              </li>
            </c:when>

            <c:when test="${not empty sessionScope.admin}">
              <li class="nav-item">
                <a class="nav-link" href="/manager-dashboard.jsp">Dashboard</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/user-management.jsp">Manage Users</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/content-management.jsp">Manage Content</a>
              </li>
              <li class="nav-item">
                <a class="nav-link text-danger" href="<c:url value="/logout"/>">Logout</a>
              </li>
            </c:when>

            <c:otherwise>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value="/login"/>">Login</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<c:url value="/register"/>">Register</a>
              </li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </div>
  </nav>
</header>
