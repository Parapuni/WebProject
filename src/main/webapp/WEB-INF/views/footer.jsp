<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* 页脚样式增强 */
    footer.footer {
      background-color: #343a40; /* 深灰色背景 */
      color: #d1d1d1; /* 柔和的白色文字 */
      border-top: 1px solid #444; /* 细分割线 */
      padding: 15px 0; /* 增加内边距 */

    }
    footer p {
      margin: 0;
    }
    /* 小屏幕调整 */
    @media (max-width: 576px) {
      footer {
        padding: 20px 10px;
        font-size: 14px;
      }
    }
  </style>
</head>
<body>
<footer class="footer my-auto py-3 text-white text-center">
  <p>&copy; 2024 Movie, Book, and Music Comment System</p>
</footer>
<script>
  // 动态调整页脚位置
  function adjustFooter() {
    const footer = document.getElementById('footer');
    const bodyHeight = document.body.scrollHeight; // 页面内容高度
    const viewportHeight = window.innerHeight; // 浏览器视口高度

    if (bodyHeight < viewportHeight) {
      footer.style.position = 'fixed';
      footer.style.bottom = '0';
      footer.style.width = '100%';
    } else {
      footer.style.position = 'relative';
    }
  }

  // 页面加载时调整页脚位置
  window.onload = adjustFooter;
  // 窗口调整时重新计算
  window.onresize = adjustFooter;
</script>
</body>
</html>
