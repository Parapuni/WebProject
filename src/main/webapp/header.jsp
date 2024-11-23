<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Movies' />">电影</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Books' />">书籍</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Music' />">音乐</a></li>
      </ul>
      <form class="d-flex" action="<c:url value='/search' />" method="get">
        <input class="form-control me-2 search-bar" type="search" placeholder="搜索" name="query">
        <button class="btn btn-outline-light search-btn" type="submit">搜索</button>
      </form>
      <ul class="navbar-nav">
        <c:choose>
          <c:when test="${empty user}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/login' />">登录</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/register' />">注册</a></li>
          </c:when>
          <c:otherwise>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/logout' />">退出</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>
<style>
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
