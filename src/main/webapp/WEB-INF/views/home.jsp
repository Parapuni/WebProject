<%@ include file="header.jsp" %>
<div class="container mt-5">
  <h1>${welcomeMessage}</h1>
  <p>${platformDescription}</p>
  <div class="row">
      <div class="col-md-4">
        <div class="card mb-4 shadow-sm">
          <div class="card-body">
            <a href="/submit-review.jsp" class="btn btn-primary btn-block">Submit a Review</a>
            <a href="/reviews.jsp" class="btn btn-secondary btn-block">View Reviews</a>
          </div>
        </div>
      </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
