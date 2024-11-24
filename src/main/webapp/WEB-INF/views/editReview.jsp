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
      margin-top: 20px;
      padding: 20px;
      background-color: #ffffff;
      border: 1px solid #ddd;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .editor-container h3 {
      font-size: 20px;
      font-weight: bold;
      margin-bottom: 15px;
      color: #333;
    }

    .comment-form .form-group {
      margin-bottom: 20px;
    }

    .comment-box {
      width: 100%;
      font-size: 16px;
      padding: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
      background-color: #f8f9fa;
      resize: none; /* 禁止拖动改变大小 */
      transition: border-color 0.3s ease, box-shadow 0.3s ease;
    }

    .comment-box:focus {
      border-color: #007bff;
      box-shadow: 0 0 4px rgba(0, 123, 255, 0.5);
      outline: none;
    }

    .submit-btn {
      background-color: #007bff;
      border: none;
      font-size: 16px;
      padding: 10px 20px;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .submit-btn:hover {
      background-color: #0056b3;
    }

  </style>
</head>
<body>
<div class="container mt-5">
  <!-- Item Header -->
  <div class="item-header">
    <img src="<c:url value='/imagines/${item.coverImagine}' />" alt="${item.title}">
    <div class="item-info">
      <h1>${item.title}</h1>
      <p><strong>Release Date:</strong> <c:out value="${item.releaseDate}" /></p>
      <p><strong>Tags:</strong>
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
    <h3>Write Your Comments</h3>
    <form action="submit-review" method="post" class="comment-form">
      <input type="hidden" name="id" value="${item.iid}">
      <div class="form-group">
        <textarea class="form-control comment-box" name="reviewContent" id="reviewContent" rows="5" placeholder="Write your review here..."></textarea>
      </div>
      <input type="hidden" id="ratingInputHidden" name="rating" value="0">
      <button type="submit" class="btn btn-primary submit-btn">Submit Comment</button>
    </form>
  </div>
</div>

<!-- Modal -->
<c:if test="${commentExists}">
  <div class="modal" tabindex="-1" role="dialog" id="overwriteModal" style="display: block; background: rgba(0, 0, 0, 0.5);">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Existing Comment Found</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="hideModal()">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <p>You have already submitted a comment for this item. Do you want to overwrite it?</p>
        </div>
        <div class="modal-footer">
          <form action="update-review" method="post">
            <input type="hidden" name="id" value="${iid}">
            <input type="hidden" name="rating" value="${rating}">
            <textarea name="reviewContent" hidden>${content}</textarea>
            <button type="submit" class="btn btn-primary">Overwrite</button>
          </form>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideModal()">Cancel</button>
        </div>
      </div>
    </div>
  </div>
</c:if>
<script>
  function hideModal() {
    document.getElementById('overwriteModal').style.display = 'none';
  }
</script>

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
