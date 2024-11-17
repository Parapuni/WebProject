<%@ include file="header.jsp" %>
<div class="container mt-5">
  <h2>Reviews</h2>
  <form action="filter-reviews.jsp" method="post">
    <div class="form-group">
      <label for="type">Select Content Type</label>
      <select id="type" name="type" class="form-control">
        <option value="book">Book</option>
        <option value="movie">Movie</option>
        <option value="music">Music</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Filter</button>
  </form>

  <div class="mt-4">
    <h3>Reviews List</h3>
    <table class="table table-bordered">
      <thead>
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
