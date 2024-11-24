<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>添加作品</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .container {
      max-width: 600px;
      margin-top: 50px;
      background-color: #f8f9fa;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<div class="container">
  <h2 class="text-center">添加作品</h2>
  <form action="<c:url value='/saveitem' />" method="post">
    <div class="mb-3">
      <label for="title" class="form-label">标题</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>
    <div class="mb-3">
      <label for="category" class="form-label">类别</label>
      <select id="category" name="category" class="form-select" required>
        <option value="Movie">电影</option>
        <option value="Book">书籍</option>
        <option value="Music">音乐</option>
      </select>
    </div>
    <div class="mb-3">
      <label for="releaseDate" class="form-label">发布日期</label>
      <input type="date" class="form-control" id="releaseDate" name="releaseDate" required>
    </div>
    <div class="mb-3">
      <label for="rating" class="form-label">评分</label>
      <input type="number" step="0.1" min="0" max="5" class="form-control" id="rating" name="rating" required>
    </div>
    <div class="mb-3">
      <label for="coverImagine" class="form-label">封面 URL</label>
      <input type="url" class="form-control" id="coverImagine" name="coverImagine">
    </div>
    <button type="submit" class="btn btn-primary w-100">添加作品</button>
  </form>
</div>
</body>
</html>
