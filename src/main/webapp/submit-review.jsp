<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Submit Review</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* 页面背景色 */
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }
    /* 容器样式 */
    .container {
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
      padding: 30px;
      margin-top: 50px;
      max-width: 600px;
    }
    h2 {
      color: #007bff;
      margin-bottom: 20px;
    }
    /* 按钮样式 */
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
      transition: background-color 0.3s, box-shadow 0.3s;
    }
    .btn-primary:hover {
      background-color: #0056b3;
      box-shadow: 0 0 8px rgba(0, 123, 255, 0.3);
    }
  </style>
</head>
<body>
<div class="container">
  <h2 class="text-center my-4">Submit a Review</h2>
  <form action="submit-review" method="post">
    <div class="mb-3">
      <label for="itemType" class="form-label">Type</label>
      <select id="itemType" name="itemType" class="form-select">
        <option value="book">Book</option>
        <option value="movie">Movie</option>
        <option value="music">Music</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="title" class="form-label">Title</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>
    <div class="mb-3">
      <label for="reviewText" class="form-label">Review</label>
      <textarea id="reviewText" name="reviewText" class="form-control" rows="4" required></textarea>
    </div>
    <div class="mb-3">
      <label for="rating" class="form-label">Rating</label>
      <input type="number" id="rating" name="rating" min="1" max="5" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary w-100">Submit Review</button>
  </form>
</div>
</body>
</html>
