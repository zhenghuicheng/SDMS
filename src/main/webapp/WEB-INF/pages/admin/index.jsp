<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2016/12/30
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理中心</title>
    <link rel="stylesheet" href="${cssPath}/base/bootstrap.css"/>
    <link rel="stylesheet" href="${uilibPath}/layui/css/layui.css">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">学生宿舍管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" id="myleft">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">学生管理<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#" data-url="/department/index">院系管理</a></li>
                        <li><a hre="#" data-url="/profession/index">专业管理</a></li>
                        <li><a href="#" data-url="/student/index">学生信息</a></li>
                    </ul>
                </li>
                <li><a href="#">宿舍管理</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">日常管理<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu" id="123">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
                <li><a href="#">平台管理</a></li>
                <li><a href="#" data-url="/log/index" id="23">登录日志</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#" id="log" data-id="${admin.id}"></a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${admin.name}<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/login/logout"><span class="glyphicon glyphicon-off"></span>  退出</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
</div>
<div class="container">
    <div>
        <ol class="breadcrumb" style="margin-bottom: 0px">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
            <li><a href="#">Library</a></li>
            <li class="active">Data</li>
        </ol>
    </div>
    <div>
        <iframe id="context" style="width: 100%;height: 100%"></iframe>
    </div>
</div>
<script src="${jsPath}/admin/index.js"></script>
</body>
</html>
