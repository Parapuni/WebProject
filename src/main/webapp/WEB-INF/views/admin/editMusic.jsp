<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>编辑书籍</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
      font-family: 'Arial', sans-serif;
    }
    .container {
      margin-top: 40px;
      max-width: 700px;
      background-color: #ffffff;
      padding: 30px;
      border-radius: 15px;
      box-shadow: 0 6px 25px rgba(0, 0, 0, 0.1);
    }
    h1 {
      font-size: 2.2rem;
      font-weight: bold;
      margin-bottom: 30px;
      color: #333;
    }
    .form-label {
      font-weight: 600;
      color: #495057;
    }
    .form-control {
      border-radius: 8px;
      box-shadow: inset 0 2px 5px rgba(0, 0, 0, 0.1);
    }
    .form-control:focus {
      border-color: #0056b3;
      box-shadow: 0 0 0 0.25rem rgba(38, 143, 255, 0.25);
    }
    .mb-3 {
      margin-bottom: 20px;
    }
    .img-fluid {
      max-height: 300px;
      object-fit: contain;
      border-radius: 10px;
    }
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
    }
    .btn-primary:hover {
      background-color: #0056b3;
      border-color: #0056b3;
    }
    .alert {
      margin-bottom: 30px;
    }
    .modal-footer {
      border-top: none;
    }
    /* 居中封面部分 */
    .cover-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      margin-bottom: 30px;
    }
  </style>
</head>
<body>
<div class="container">
  <c:if test="${not empty sessionScope.message}">
    <div class="alert alert-success">
        ${sessionScope.message}
      <c:set var="message" value="" scope="session" />
    </div>
  </c:if>
  <!-- 居中标题 -->
  <h1 class="text-center mb-4">编辑音乐</h1>

  <!-- 居中封面部分 -->
  <div class="cover-container">
    <div class="col-md-4 mb-4 text-center">
      <img src="<c:url value='/imagines/${sessionScope.music.coverImagine}' />" alt="${sessionScope.music.title}" class="img-fluid shadow rounded">
      <form action="<c:url value='/admin/changeMusicCover' />" method="post" enctype="multipart/form-data">
        <input type="hidden" class="form-control" id="id" name="id" value="${sessionScope.music.iid}">
        <div class="modal-body">
          <label for="coverImagine" class="form-label">选择新封面：</label>
          <input type="file" class="form-control" id="coverImagine" name="coverImagine" required>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
          <button type="submit" class="btn btn-primary">上传</button>
        </div>
      </form>
    </div>
  </div>

  <!-- 音乐编辑表单 -->
  <form action="<c:url value='/admin/updateMusic'/>" method="post" class="w-100">
    <input type="hidden" class="form-control" id="iid" name="iid" value="${sessionScope.music.iid}">

    <div class="mb-3">
      <label for="title" class="form-label">标题</label>
      <input type="text" class="form-control" id="title" name="title" value="${sessionScope.music.title}" required>
    </div>

    <div class="mb-3">
      <label for="tags" class="form-label">分类</label>
      <input type="text" class="form-control" id="tags" name="tags" value="${sessionScope.music.categories}">
    </div>

    <div class="mb-3">
      <label for="artists" class="form-label">创作者</label>
      <input type="text" class="form-control" id="artists" name="artists" value="${sessionScope.music.artists}">
    </div>

    <div class="mb-3">
      <label for="album" class="form-label">专辑</label>
      <input type="text" class="form-control" id="album" name="album" value="${sessionScope.music.album}" required>
    </div>

    <div class="mb-3">
      <label for="lyrics" class="form-label">歌词</label>
      <textarea class="form-control" id="lyrics" name="lyrics" rows="4">${sessionScope.music.lyrics}</textarea>
    </div>

    <div class="mb-3">
      <label for="releaseDate" class="form-label">发行日期</label>
      <input type="date" class="form-control" id="releaseDate" name="releaseDate" value="${sessionScope.music.releaseDate}">
    </div>

    <!-- 提交按钮 -->
    <button type="submit" class="btn btn-primary w-100">更新音乐信息</button>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../footer.jsp"/>
</body>
</html>
