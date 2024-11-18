<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edit Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container">
  <div class="row justify-content-center mt-5">
    <div class="col-md-8">
      <h2>Edit Profile</h2>
      <form action="/updateProfile" method="post">
        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" name="username" value="${user.username}" disabled />
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">Email</label>
          <input type="email" class="form-control" id="email" name="email" value="${user.email}" required />
        </div>
        <div class="mb-3">
          <label for="phoneNo" class="form-label">Phone Number</label>
          <input type="text" class="form-control" id="phoneNo" name="phoneNo" value="${user.phoneNo}" />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">New Password</label>
          <input type="password" class="form-control" id="password" name="password" />
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>
