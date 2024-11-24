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
    body {
      background-color: #f4f4f4;
      font-family: 'Roboto', sans-serif;
    }

    .container {
      max-width: 800px;
      margin-top: 50px;
      padding: 20px;
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }
    body {
      margin: 0;
      padding: 0;
    }

    h2 {
      color: #007bff;
      text-align: center;
      margin-bottom: 30px;
    }

    .form-label {
      font-weight: bold;
    }

    .form-control:focus {
      border-color: #007bff;
      box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }

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
<div class="container">
  <h2>Edit Profile</h2>
  <form action="update-profile" method="post">
    <!-- Nickname -->
    <div class="mb-3">
      <label for="nickname" class="form-label">Nickname</label>
      <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}" required />
    </div>

    <!-- Email -->
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" id="email" name="email" value="${user.email}" required />
    </div>

    <!-- First Name -->
    <div class="mb-3">
      <label for="firstName" class="form-label">First Name</label>
      <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}" />
    </div>

    <!-- Last Name -->
    <div class="mb-3">
      <label for="lastName" class="form-label">Last Name</label>
      <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}" />
    </div>

    <!-- Phone Number -->
    <div class="mb-3">
      <label for="number" class="form-label">Phone Number</label>
      <input type="text" class="form-control" id="number" name="number" value="${user.number}" />
    </div>

    <!-- Birthday -->
    <div class="mb-3">
      <label for="birthday" class="form-label">Birthday</label>
      <input type="date" class="form-control" id="birthday" name="birthday" value="${user.birthday}" />
    </div>

    <!-- Password -->
    <div class="mb-3">
      <label for="password" class="form-label">New Password</label>
      <input type="password" class="form-control" id="password" name="password" />
    </div>

    <!-- Submit Button -->
    <button type="submit" class="btn btn-primary">Save Changes</button>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
