<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>添加书籍</title>
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
    .btn-primary {
      background-color: #007bff;
      border-color: #007bff;
    }
    .btn-primary:hover {
      background-color: #0056b3;
      border-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center">添加音乐</h2>
  <form action="<c:url value='/admin/saveMusic'/>" method="post">
    <!-- 通用字段 -->
    <div class="mb-3">
      <label for="title" class="form-label">标题</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>
    <div class="mb-3">
      <label for="releaseDate" class="form-label">发布日期</label>
      <input type="date" class="form-control" id="releaseDate" name="releaseDate">
    </div>
    <div class="mb-3">
      <label for="releaseDate" class="form-label">分类标签（多个标签间使用逗号隔开）</label>
      <input type="text" class="form-control" id="tags" name="tags">
    </div>


    <div class="mb-3">
      <label for="artists" class="form-label">创作者</label>
      <input type="text" class="form-control" id="artists" name="artists">
    </div>
    <div class="mb-3">
      <label for="album" class="form-label">所属专辑</label>
      <input type="text" class="form-control" id="album" name="album">
    </div>
    <div class="mb-3">
      <label for="lyrics" class="form-label">歌词</label>
      <textarea class="form-control" id="lyrics" name="lyrics" rows="4"></textarea>
    </div>

    <!-- 提交按钮 -->
    <button type="submit" class="btn btn-primary w-100">提交</button>
  </form>
</div>

<jsp:include page="../footer.jsp"/>
</body>
</html>
