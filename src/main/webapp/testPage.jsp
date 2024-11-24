<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/tld/ratingTag.tld" prefix="ct" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>动态星级评分</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
  <h1>评分示例</h1>
  <div>
    <h3>电影: 《盗梦空间》</h3>
    <ct:rating rating="3.5" />
  </div>
  <div>
    <h3>书籍: 《了不起的盖茨比》</h3>
    <ct:rating rating="4.5" />
  </div>
  <div>
    <h3>音乐: 《波西米亚狂想曲》</h3>
    <ct:rating rating="1.5" />
  </div>
</div>
</body>
</html>
