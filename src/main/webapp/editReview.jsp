<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${item.title} - Write a Review</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/quill/dist/quill.snow.css">
  <style>
    body {
      font-family: 'Roboto', sans-serif;
      background-color: #f4f4f4;
    }

    .item-header {
      display: flex;
      gap: 20px;
      margin-bottom: 30px;
    }

    .item-header img {
      width: 200px;
      height: 300px;
      object-fit: cover;
      border-radius: 8px;
    }

    .item-info h1 {
      font-size: 28px;
      font-weight: bold;
    }

    .item-info p {
      margin: 5px 0;
    }

    .rating-container {
      margin-top: 20px;
    }

    .star-rating {
      font-size: 24px;
      color: #ffc107;
    }

    .star-rating i {
      cursor: pointer;
    }

    .editor-container {
      margin-top: 30px;
    }

    .quill {
      background-color: white;
      border-radius: 8px;
      min-height: 200px;
    }

    .submit-btn {
      margin-top: 20px;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <!-- Item Header -->
  <div class="item-header">
    <img src="${item.coverImage}" alt="${item.title}">
    <div class="item-info">
      <h1>${item.title}</h1>
      <p><strong>Release Date:</strong> <c:out value="${item.releaseDate}" /></p>
      <p><strong>Categories:</strong>
        <c:forEach var="category" items="${item.categories}">
          <span class="badge bg-secondary">${category}</span>
        </c:forEach>
      </p>
    </div>
  </div>

  <!-- Rating Section -->
  <div class="rating-container">
    <h3>Your Rating</h3>
    <div class="star-rating" id="starRating">
      <c:forEach begin="1" end="5" var="star">
        <i class="bi bi-star" onclick="setRating(${star})" data-rating="${star}"></i>
      </c:forEach>
    </div>
    <input type="hidden" id="ratingInput" name="rating" value="0">
  </div>

  <!-- Review Editor -->
  <div class="editor-container">
    <h3>Write Your Review</h3>
    <form action="<c:url value='/submit-review' />" method="post">
      <input type="hidden" name="iid" value="${item.iid}">
      <div id="editor" class="quill"></div>
      <textarea name="reviewContent" id="reviewContent" hidden></textarea>
      <input type="hidden" id="ratingInputHidden" name="rating" value="0">
      <button type="submit" class="btn btn-primary submit-btn">Submit Review</button>
    </form>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quill/dist/quill.min.js"></script>
<script>
  // Initialize Quill editor
  const quill = new Quill('#editor', {
    theme: 'snow',
    placeholder: 'Write your review here...',
    modules: {
      toolbar: [
        ['bold', 'italic', 'underline'],
        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
        ['link', 'image']
      ]
    }
  });

  // Sync Quill content with the hidden textarea
  const form = document.querySelector('form');
  const reviewContent = document.querySelector('#reviewContent');
  form.addEventListener('submit', () => {
    reviewContent.value = quill.root.innerHTML;
  });

  // Star Rating Interaction
  function setRating(rating) {
    const stars = document.querySelectorAll('.star-rating i');
    const ratingInputHidden = document.getElementById('ratingInputHidden');

    stars.forEach((star, index) => {
      if (index < rating) {
        star.classList.remove('bi-star');
        star.classList.add('bi-star-fill');
      } else {
        star.classList.remove('bi-star-fill');
        star.classList.add('bi-star');
      }
    });

    ratingInputHidden.value = rating;
  }
</script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</body>
</html>
