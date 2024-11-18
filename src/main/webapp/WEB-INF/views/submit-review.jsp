<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Submit Review</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <h2 class="my-4">Submit a Review</h2>
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
      <textarea id="reviewText" name="reviewText" class="form-control" required></textarea>
    </div>
    <div class="mb-3">
      <label for="rating" class="form-label">Rating</label>
      <input type="number" id="rating" name="rating" min="1" max="5" class="form-control" required>
    </div>
    <button type="submit" class="btn btn-primary">Submit Review</button>
  </form>
</div>
</body>
</html>
