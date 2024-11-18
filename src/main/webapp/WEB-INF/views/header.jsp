<%--suppress ALL --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
  <title>Book, Movie, and Music Reviews</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Reviews System</a>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="home.jsp">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="submit-review.jsp">Submit Review</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="reviews.jsp">View Reviews</a>
      </li>
      <c:choose>
        <c:when test="${not empty session.user}">
          <li class="nav-item">
            <a class="nav-link" href="logout.jsp">Logout</a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="nav-item">
            <a class="nav-link" href="login.jsp">Login</a>
          </li>
        </c:otherwise>
      </c:choose>
      <c:choose>
        <c:when test="${not empty session.user}">
          <p>Welcome, ${session.user.username}!</p>
        </c:when>
        <c:otherwise>
          <p>Welcome, Guest!</p>
        </c:otherwise>
      </c:choose>

    </ul>
  </div>
</nav>
</body>
</html>
