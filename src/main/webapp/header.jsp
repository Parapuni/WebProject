<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #183b83; font-family: 'Arial', sans-serif;">
  <div class="container-fluid">
    <a class="navbar-brand" href="<c:url value='/' />">
      <img src="sre/main/resources/images/logo.png" alt="Logo" style="height: 40px;">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <!-- 管理员管理入口 -->
        <c:if test="${not empty sessionScope.admin}">
          <li class="nav-item"><a class="nav-link" href="<c:url value='/adminmanage' />">管理员管理</a></li>
        </c:if>
        <!-- 普通用户分类入口 -->
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Movie' />">电影</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Book' />">书籍</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Music' />">音乐</a></li>
      </ul>
      <!-- 搜索框 -->
      <form class="d-flex" action="<c:url value='/search' />" method="get">
        <input class="form-control me-2 search-bar" type="search" placeholder="搜索" name="query">
        <button class="btn btn-outline-light search-btn" type="submit">搜索</button>
      </form>
      <ul class="navbar-nav">
        <!-- 用户和管理员登录逻辑 -->
        <c:choose>
          <c:when test="${empty sessionScope.user && empty sessionScope.admin}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/login' />">登录</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/register' />">注册</a></li>
          </c:when>
          <c:when test="${not empty sessionScope.admin}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/admindashboard' />">管理员中心</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/logout' />">退出</a></li>
          </c:when>
          <c:otherwise>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/profile' />">个人信息</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/logout' />">退出</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
<style>
  .fa-star {
    color: #ddd; /* 灰色表示未选中 */
  }
  .fa-star.checked, .fa-star-half-alt.checked {
    color: #ffc107; /* 金色表示选中 */
  }

  .search-bar {
    height: 36px;
    font-size: 16px;
    font-family: 'Verdana', sans-serif;
    margin-left: -10px;
    margin-top: 5px;
  }

  .search-btn {
    height: 36px;
    width: 120px;
    font-size: 16px;
    line-height: 1;
    font-family: 'Verdana', sans-serif;
    margin-left: -10px;
    margin-top: 5px;
  }

  .navbar-nav .nav-link {
    font-size: 16px;
    font-family: 'Verdana', sans-serif;
  }

  .navbar-brand img {
    border-radius: 4px;
  }
</style>
