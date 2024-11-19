<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div class="container-fluid mt-5">
  <h1 class="text-center mb-4" style="color: #4a90e2;">Welcome to the Reviews System!</h1>

  <c:choose>
    <c:when test="${empty sessionScope.user && empty sessionScope.admin}">
      <p class="text-center text-danger">You need to <a href="login.jsp" class="text-decoration-none">log in</a> to access this feature.</p>
      <div class="row mt-4">
        <div class="col-md-12">
          <h4 class="text-center" style="color: #7f8c8d;">Explore Reviews</h4>
          <a href="reviews.jsp" class="btn btn-secondary btn-lg w-100">View Reviews</a>
        </div>
      </div>
    </c:when>


    <c:when test="${not empty sessionScope.admin}">
      <div class="admin-dashboard text-center">
        <h3 style="color: #27ae60;">Admin Dashboard</h3>
        <div class="row mt-4">
          <div class="col-sm-6 col-md-4 mb-3">
            <a href="user-management.jsp" class="btn btn-success w-100">Manage Users</a>
          </div>
          <div class="col-sm-6 col-md-4 mb-3">
            <a href="item-management.jsp" class="btn btn-info w-100">Manage Items</a>
          </div>
          <div class="col-sm-12 col-md-4 mb-3">
            <a href="review-management.jsp" class="btn btn-warning w-100">Manage Reviews</a>
          </div>
        </div>
      </div>
    </c:when>


    <c:otherwise>
      <div class="user-dashboard text-center">
        <h3 style="color: #e74c3c;">User Dashboard</h3>
        <div class="row mt-4">
          <div class="col-sm-6 mb-3">
            <a href="submit-review.jsp" class="btn btn-primary w-100">Submit a Review</a>
          </div>
          <div class="col-sm-6 mb-3">
            <a href="reviews.jsp" class="btn btn-secondary w-100">Manage Your Reviews</a>
          </div>
        </div>
        <div class="mt-3">
          <a href="profile.jsp" class="btn btn-outline-info w-100">Edit Your Profile</a>
        </div>
      </div>
    </c:otherwise>
  </c:choose>
</div>
<%@ include file="footer.jsp" %>
