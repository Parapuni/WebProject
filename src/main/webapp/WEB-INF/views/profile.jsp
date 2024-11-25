<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="banner.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.staticfile.net/font-awesome/4.7.0/css/font-awesome.css">
</head>
  <style>
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }

    .profile-container {
      margin-top: 40px;
    }

    .sidebar {
      background-color: #ffffff;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 10px;
      text-align: center;
    }

    .sidebar img {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      margin-bottom: 10px;
    }

    .user-info {
      text-align: left;
      margin-top: 20px;
    }

    .user-info .info-label {
      font-weight: bold;
      color: #333;
    }

    .user-info .info-value {
      color: #555;
    }

    .comments-section {
      background-color: #ffffff;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 10px;
      margin-top: 20px;
    }

    .comments-section h3 {
      margin-bottom: 20px;
    }

    .comment-item {
      padding: 10px 0;
      border-bottom: 1px solid #ddd;
    }

    .comment-item:last-child {
      border-bottom: none;
    }

    .comment-title {
      font-weight: bold;
      margin-bottom: 5px;
    }

    .comment-actions a {
      margin-right: 10px;
      color: #007bff;
      text-decoration: none;
    }

    .comment-actions a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<div class="container profile-container">
  <div class="row">
    <!-- Left Sidebar -->
    <div class="col-md-3">
      <div class="sidebar">
        <!-- User Avatar -->
        <img src="<c:url value="/imagines/${sessionScope.user.avatar}" />" alt="User Avatar" id="userAvatar" data-bs-toggle="modal" data-bs-target="#changeAvatarModal" style="cursor: pointer;">
        <hr>
        <!-- User Information -->
        <div class="user-info">
          <div><span class="info-label">Nickname:</span> <span class="info-value">${sessionScope.user.nickname}</span></div>
          <div><span class="info-label">Name:</span> <span class="info-value">${sessionScope.user.firstName} ${sessionScope.user.lastName}</span></div>
          <div><span class="info-label">Email:</span> <span class="info-value">${sessionScope.user.email}</span></div>
          <div><span class="info-label">Phone:</span> <span class="info-value">${sessionScope.user.number}</span></div>
          <div><span class="info-label">Birthday:</span> <span class="info-value">${sessionScope.user.birthday}</span></div>
        </div>
        <hr>
        <a href="<c:url value='/edit-profile' />" class="btn btn-primary btn-sm w-100 mt-3">Edit Profile</a>
      </div>
    </div>

    <!-- Right Content -->
    <div class="col-md-9">
      <div class="comments-section">
        <h3>My Comments</h3>
        <c:if test="${empty comments}">
          <p class="text-muted">No comments yet. Be the first to comment!</p>
        </c:if>
        <c:forEach items="${comments}" var="comment">
          <div class="comment p-3 mb-3 border rounded shadow-sm bg-light">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <span class="text-dark fw-bold">${comment.userName}</span>
              <span class="text-muted">${comment.cdate}</span>
            </div>

            <strong><p class="text-secondary">${comment.itemTitle}</p></strong>

            <div class="comment-rating text-warning mb-2">
              <c:forEach var="star" begin="1" end="${comment.rating}">
                ★
              </c:forEach>
            </div>
            <div class="d-flex justify-content-between align-items-center mb-2">
              <p class="text-secondary">${comment.content}</p>
              <form action="delete-comment" method="post" class="mt-2">
                <input type="hidden" name="commentId" value="${comment.iid}">
                <button type="submit" class="btn btn-danger btn-sm">
                  <i class="fas fa-trash-alt"></i> Delete
                </button>
                <button type="submit" class="btn btn-submit btn-sm" >
                  <a href="<c:url value="/toPage?iid=${comment.iid}"/>">
                     Goto
                  </a>
                  <i class="fa fa-mail-forward"></i>
                </button>
              </form>
            </div>

          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</div>

<!-- Change Avatar Modal -->
<div class="modal fade" id="changeAvatarModal" tabindex="-1" aria-labelledby="changeAvatarModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="changeAvatarModalLabel">Change Avatar</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="<c:url value='/change-avatar' />" method="post" enctype="multipart/form-data">
        <div class="modal-body">
          <label for="avatarFile" class="form-label">Choose a new avatar:</label>
          <input type="file" class="form-control" id="avatarFile" name="avatarFile" required>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" class="btn btn-primary">Upload</button>
        </div>
      </form>
    </div>
  </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="footer.jsp" />
</body>
</html>
