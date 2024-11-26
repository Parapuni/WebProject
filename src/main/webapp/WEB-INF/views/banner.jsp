<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark shadow-lg" style="background-color: #1a2b4f; font-family: 'Arial', sans-serif;">
  <div class="container-fluid">
    <!-- Logo -->
    <a class="navbar-brand d-flex align-items-center" href="<c:url value='/' />">
      <img src="<c:url value='/imagines/IID1cover.jpg' />" alt="Logo" style="height: 50px;" class="rounded-circle me-2">
      <span style="font-size: 20px; font-weight: bold;">Comment System</span>
    </a>
    <!-- Mobile Menu Button -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Navbar Links -->
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto">
        <!-- 普通用户分类入口 -->
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Movie' />">电影</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Book' />">书籍</a></li>
        <li class="nav-item"><a class="nav-link" href="<c:url value='/items?category=Music' />">音乐</a></li>
      </ul>

      <form class="d-flex align-items-center w-100 py-2" action="<c:url value='/search' />" method="get">
        <!-- 搜索类型选择 -->
        <select class="form-select me-2 search-type" name="type">
          <option value="Movie">电影</option>
          <option value="Book">书籍</option>
          <option value="Music">音乐</option>
        </select>
        <!-- 搜索框 -->
        <input
                class="form-control me-2 search-bar"
                type="search"
                placeholder="请输入作品名称"
                name="query"
        >
        <!-- 搜索按钮 -->
        <button class="btn btn-outline-light search-btn" type="submit">
          <i class="fas fa-search"></i> 搜索
        </button>
      </form>


      <!-- 用户和管理员登录逻辑 使用session记录登录状态 -->
      <ul class="navbar-nav ms-3 align-items-center">
        <c:choose>
          <c:when test="${empty sessionScope.user && empty sessionScope.admin}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/login' />">登录</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/register' />">注册</a></li>
          </c:when>
          <c:when test="${not empty sessionScope.admin}">
            <h5  style="font-style: oblique;color: white">管理员${sessionScope.admin.adminName}</h5>
            <img src="<c:url value='/imagines/${sessionScope.admin.avatar}' />" alt="Profile Picture" style="height: 50px; width: 50px; border-radius: 50%;">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/admin/dashboard' />">管理员中心</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/adminLogout' />">退出</a></li>
          </c:when>
          <c:otherwise>
            <li class="nav-item d-flex align-items-center">
              <h5 style="font-style: oblique;color: white">${user.nickname}</h5>
              <a href="<c:url value='/profile' />" class="d-inline-block me-2">
                <img src="<c:url value='/imagines/${user.avatar}' />" alt="Profile Picture" style="height: 50px; width: 50px; border-radius: 50%;">
              </a>
              <a class="nav-link" href="<c:url value='/profile' />">个人信息</a>
              <a class="nav-link" href="<c:url value='/logout' />">退出</a>
            </li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<style>
  /* Navbar Styles */
  .navbar {
    padding: 15px 20px;
  }

  .navbar-brand img {
    height: 50px;
    width: 50px;
    border-radius: 50%;
  }

  .navbar-brand span {
    color: #fff;
  }

  .navbar-nav .nav-link {
    font-size: 16px;
    margin-right: 10px;
    font-family: 'Verdana', sans-serif;
    transition: color 0.3s ease;
  }

  .navbar-nav .nav-link:hover {
    color: #ffc107;
  }

  /* 搜索类型选择框 */
  .search-type {
    height: 40px;
    font-size: 16px;
    border-radius: 20px;
    flex-grow: 1; /* 较少的比例空间 */
    min-width: 120px; /* 限制选择框的最小宽度 */
    max-width: 150px; /* 限制选择框的最大宽度 */
  }

  /* 搜索框 */
  .search-bar {
    height: 40px;
    font-size: 16px;
    border-radius: 20px;
    flex-grow: 4; /* 占据主要比例空间 */
    min-width: 300px; /* 确保搜索框宽度主要部分 */
  }

  /* 搜索按钮 */
  .search-btn {
    height: 40px;
    font-size: 16px;
    border-radius: 20px;
    flex-grow: 1; /* 按钮较少比例 */
    min-width: 100px; /* 最小宽度适配内容 */
    display: flex;
    align-items: center;
    justify-content: center;
    white-space: nowrap; /* 防止文字换行 */
  }

  /* 搜索部分整体样式 */
  form.d-flex {
    max-width: 700px; /* 搜索部分的最大宽度 */
    margin-left: auto;
    margin-right: auto;
  }

  /* 搜索栏上下边距修正 */
  .navbar .d-flex {
    margin-top: auto;
    margin-bottom: auto;
    padding-top: 0;
    padding-bottom: 0;
  }

  /* 垂直对齐调整 */
  .navbar-nav.ms-3 .nav-item {
    display: flex;
    align-items: center;
  }

  /* 调整头像间距 */
  .navbar-nav .nav-item img {
    margin-right: 10px;
  }

  /* 移动端适配 */
  @media (max-width: 768px) {
    form.d-flex {
      max-width: 100%;
      flex-direction: column; /* 垂直排列 */
    }

    .search-bar,
    .search-type,
    .search-btn {
      width: 100%; /* 占满一行 */
      margin-bottom: 10px; /* 每个元素间距 */
    }
  }
</style>
