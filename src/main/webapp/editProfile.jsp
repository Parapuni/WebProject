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
  <style>
    /* 自定义容器样式 */
    .container {
      max-width: 800px;
      padding: 20px;
      background-color: #f8f9fa; /* 柔和的背景色 */
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
    }
    /* 标题样式 */
    h2 {
      color: #007bff; /* 使用蓝色标题 */
      text-align: center;
      margin-bottom: 30px;
    }
    /* 表单控件的样式 */
    .form-label {
      font-weight: bold;
    }
    .form-control:focus {
      border-color: #007bff;
      box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
    /* 按钮样式 */
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
      width: 100%;
      transition: background-color 0.3s;
    }
    .btn-primary:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-lg-8 col-md-10">
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
