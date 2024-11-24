<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../header.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>添加作品</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script>
    // 动态字段显示函数
    function updateFormFields() {
      const type = document.getElementById('type').value.trim();
      console.log('Selected type:', type);

      // 隐藏所有动态字段
      const allFields = document.querySelectorAll('.dynamic-field');
      allFields.forEach(field => field.style.display = 'none');

      // 如果选择了有效的类别
      if (type && /^[a-zA-Z0-9_-]+$/.test(type)) {
        const selector = '.' + type;
        console.log('Generated selector:', selector);

        try {
          // 显示匹配的动态字段
          const specificFields = document.querySelectorAll(selector);
          console.log('Matched fields:', specificFields.length);
          specificFields.forEach(field => field.style.display = 'block');
        } catch (error) {
          console.error('Error in querySelectorAll:', error);
        }
      }
    }
  </script>
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center">添加作品</h2>
  <form action="<c:url value='/additem' />" method="post" enctype="multipart/form-data">
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
      <label for="type" class="form-label">类别</label>
      <select id="type" name="type" class="form-select" onchange="updateFormFields()" required>
        <option value="">选择类别</option>
        <option value="book">书籍</option>
        <option value="movie">电影</option>
        <option value="music">音乐</option>
      </select>
    </div>

    <!-- 动态字段 -->
    <div class="mb-3 dynamic-field movie" style="display:none;">
      <label for="director" class="form-label">导演</label>
      <input type="text" class="form-control" id="director" name="director">
    </div>
    <div class="mb-3 dynamic-field movie" style="display:none;">
      <label for="writers" class="form-label">编剧</label>
      <input type="text" class="form-control" id="writers" name="writers">
    </div>
    <div class="mb-3 dynamic-field movie" style="display:none;">
      <label for="cast" class="form-label">演员阵容</label>
      <input type="text" class="form-control" id="cast" name="cast">
    </div>
    <div class="mb-3 dynamic-field movie book" style="display:none;">
      <label for="introduction" class="form-label">简介</label>
      <textarea class="form-control" id="introduction" name="introduction"></textarea>
    </div>
    <div class="mb-3 dynamic-field book" style="display:none;">
      <label for="authors" class="form-label">作者</label>
      <input type="text" class="form-control" id="authors" name="authors">
    </div>
    <div class="mb-3 dynamic-field book" style="display:none;">
      <label for="publisher" class="form-label">出版社</label>
      <input type="text" class="form-control" id="publisher" name="publisher">
    </div>
    <div class="mb-3 dynamic-field music" style="display:none;">
      <label for="artists" class="form-label">创作者</label>
      <input type="text" class="form-control" id="artists" name="artists">
    </div>
    <div class="mb-3 dynamic-field music" style="display:none;">
      <label for="album" class="form-label">专辑</label>
      <input type="text" class="form-control" id="album" name="album">
    </div>
    <div class="mb-3 dynamic-field music" style="display:none;">
      <label for="lyrics" class="form-label">歌词</label>
      <textarea class="form-control" id="lyrics" name="lyrics"></textarea>
    </div>

    <!-- 封面图片 -->
    <div class="mb-3">
      <label for="coverImage" class="form-label">封面图片</label>
      <input type="file" class="form-control" id="coverImage" name="coverImage" accept="image/*" required>
    </div>

    <!-- 提交按钮 -->
    <button type="submit" class="btn btn-primary w-100">提交</button>
  </form>
</div>
<script>
  document.addEventListener('DOMContentLoaded', updateFormFields);
</script>
</body>
</html>
