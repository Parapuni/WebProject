<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../banner.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理员仪表盘</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .dashboard-container {
            margin-top: 40px;
        }
        .dashboard-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .dashboard-box {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 10px;
            text-align: center;
            padding: 20px;
            margin: 10px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .dashboard-box:hover {
            background-color: #007bff;
            color: #fff;
        }
        .dashboard-box a {
            text-decoration: none;
            color: inherit;
        }
        .dashboard-box a:hover {
            text-decoration: none;
        }
        .dashboard-box h4 {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container dashboard-container">
    <!-- 标题 -->
    <div class="dashboard-header">
        <h2>欢迎, ${sessionScope.admin.adminName}！</h2>
        <p>你好。</p>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="dashboard-box">
                <a href="<c:url value='/admin/manageUsers' />">
                    <h4>用户管理</h4>
                    <p>管理用户信息和权限</p>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="dashboard-box">
                <a href="<c:url value='/admin/manageBooks' />">
                    <h4>书籍管理</h4>
                    <p>管理书籍信息</p>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="dashboard-box">
                <a href="<c:url value='/admin/manageMusic' />">
                    <h4>音乐管理</h4>
                    <p>管理音乐信息</p>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="dashboard-box">
                <a href="<c:url value='/admin/manageMovies' />">
                    <h4>电影管理</h4>
                    <p>管理电影信息</p>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="dashboard-box">
                <a href="<c:url value='/admin/manageComments' />">
                    <h4>评论管理</h4>
                    <p>管理用户评论</p>
                </a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="dashboard-box">
                <a href="<c:url value='/' />">
                    <h4>返回主页</h4>
                    <p>返回到网站首页</p>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="../footer.jsp"/>
</body>
</html>
