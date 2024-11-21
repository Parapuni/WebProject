<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Welcome to the Reviews System</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap');

    body {
      font-family: 'Lato', sans-serif;
      background: linear-gradient(to right, #ffffff, #f4f4f4);
      color: #333;
      min-height: 100vh;
      margin: 0;
      padding: 0;
    }

    .container-fluid {
      padding: 50px 20px;
      margin-top: 40px;
    }

    h1 {
      color: #333;
      margin-bottom: 20px;
    }

    .btn {
      border-radius: 8px;
      box-shadow: 0 2px 6px rgba(0,0,0,0.08);
      font-size: 16px;
      transition: background-color 0.3s, border-color 0.3s;
      width: 100%;
    }

    .btn-primary:hover, .btn-secondary:hover {
      opacity: 0.9;
    }

    .text-danger a {
      color: #e74c3c;
      text-decoration: none;
    }
  </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container-fluid mt-5">
  <h1 class="text-center mb-4" style="color: #4a90e2;">Welcome to the Reviews System!</h1>
  <p class="text-center text-danger">You need to <a href="<c:url value='/login'/>">log in</a> or <a href="<c:url value='/register'/>">register</a> to access full features.</p>
  <div class="row mt-4 justify-content-center">
    <div class="col-md-6">
      <a href="<c:url value='/login'/>" class="btn btn-primary btn-lg w-100">Login</a>
    </div>
    <div class="col-md-6">
      <a href="<c:url value='/register'/>" class="btn btn-secondary btn-lg w-100">Register</a>
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
