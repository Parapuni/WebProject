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
            margin-bottom: 20px;  /* Maintain original spacing for visual consistency */
        }

        .spacing {
            height: 50px;  /* Create a spacing of 500px */
        }

        .btn {
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            font-size: 16px;
            padding: 15px;
            width: 100%;
            transition: background-color 0.3s, border-color 0.3s;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-info {
            background-color: #17a2b8;
            border-color: #17a2b8;
        }

        .icon-left {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container-fluid mt-5">
    <h1 class="text-center mb-4" style="color: #4a90e2;">Welcome to the Reviews System!</h1>
    <div class="spacing"></div> <!-- This div acts as a spacer -->
    <div class="row justify-content-center">
        <div class="col-md-4 mb-3">
            <a href="<c:url value='/submit-review'/>" class="btn btn-primary w-100">
                <i class="fas fa-pencil-alt icon-left"></i>Submit a Review
            </a>
        </div>
        <div class="col-md-4 mb-3">
            <a href="<c:url value='/reviews'/>" class="btn btn-secondary w-100">Manage Your Reviews</a>
        </div>
        <div class="col-md-4 mb-3">
            <a href="<c:url value='/profile'/>" class="btn btn-info w-100">Edit Your Profile</a>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
