<%@ include file="header.jsp" %>
<div class="container mt-5">
  <h2 class="text-center mb-4">Reviews</h2>

  <!-- 筛选表单 -->
  <form action="filter-reviews.jsp" method="post" class="mb-4">
    <div class="form-group">
      <label for="type">Select Content Type</label>
      <select id="type" name="type" class="form-control">
        <option value="book">Book</option>
        <option value="movie">Movie</option>
        <option value="music">Music</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary mt-2">Filter</button>
  </form>

  <!-- 评论列表 -->
  <div>
    <h3 class="mb-3">Reviews List</h3>
    <table class="table table-striped table-hover">
      <thead class="thead-dark">
      <tr>
        <th>Title</th>
        <th>Review</th>
        <th>Rating</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="review" items="${reviews}">
        <tr>
          <td>${review.title}</td>
          <td>${review.text}</td>
          <td>${review.rating}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<%@ include file="footer.jsp" %>
