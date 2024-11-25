<%@ include file="banner.jsp" %>
<div class="container mt-5">
  <h2 class="text-center mb-4">comments</h2>

  <!-- 筛选表单 -->
  <form action="filter-comments.jsp" method="post" class="mb-4">
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
    <h3 class="mb-3">comments List</h3>
    <table class="table table-striped table-hover">
      <thead class="thead-dark">
      <tr>
        <th>Title</th>
        <th>comment</th>
        <th>Rating</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="comment" items="${comments}">
        <tr>
          <td>${comment.title}</td>
          <td>${comment.text}</td>
          <td>${comment.rating}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

