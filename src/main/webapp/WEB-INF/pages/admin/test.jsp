<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/20
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>学生宿舍管理中心</title>

    <!-- Bootstrap Core CSS -->
    <link href="${cssPath}/base/bootstrap.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${cssPath}/base/metisMenu.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${cssPath}/base/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${cssPath}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${cssPath}/base/bootstrapValidator.min.css"/>


    <script src="${jsPath}/base/metisMenu.min.js"></script>
    <script src="${jsPath}/base/jquery.form.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${jsPath}/base/sb-admin-2.min.js"></script>
    <script src="${uilibPath}/layer/layer.js"></script>
    <script src="${jsPath}/base/bootstrapValidator.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <![endif]-->
    <style>
        .modal-body .input-group{
            margin-top : 5px;
            margin-top : 5px;
        }
    </style>
</head>
<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;background-color: white">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">学生宿舍管理系统</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right" >
            <%--<li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages">
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>Read All Messages</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-messages -->
            </li>--%>
            <!-- /.dropdown -->
            <%--<li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-tasks">
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 1</strong>
                                    <span class="pull-right text-muted">40% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                        <span class="sr-only">40% Complete (success)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 2</strong>
                                    <span class="pull-right text-muted">20% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                        <span class="sr-only">20% Complete</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 3</strong>
                                    <span class="pull-right text-muted">60% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (warning)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 4</strong>
                                    <span class="pull-right text-muted">80% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                        <span class="sr-only">80% Complete (danger)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Tasks</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-tasks -->
            </li>--%>
            <!-- /.dropdown -->
            <%--<li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                <span class="pull-right text-muted small">12 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-envelope fa-fw"></i> Message Sent
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-tasks fa-fw"></i> New Task
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                <span class="pull-right text-muted small">4 minutes ago</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-alerts -->
            </li>--%>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i id="log" data-id="${admin.id}">${admin.name}</i>
                    <%--<i class="fa fa-user fa-fw"></i>--%> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#" id="lookData"> 个人信息</a>
                    </li>
                    <li><a href="#" id="updatePassword"> 重置密码</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="/login/logout"><i class="fa fa-sign-out fa-fw"></i> 退出登录</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation" style="background-color: white">
            <div class="sidebar-nav navbar-collapse" >
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="#" data-url="/publicNote/first">首页</a>
                    </li>
                    <li>
                        <a href="#">学生管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#" data-url="/department/index">院系信息</a>
                            </li>
                            <li>
                                <a href="#" data-url="/profession/index">专业信息</a>
                            </li>
                            <li>
                                <a href="#" data-url="/student/index">学生信息</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#">宿舍管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#" data-url="/building/index">宿舍楼信息</a>
                            </li>
                            <li>
                                <a href="#" data-url="/dormitory/index">宿舍信息</a>
                                <!-- /.nav-third-level -->
                            </li>
                            <li>
                                <a href="#" data-url="/dormitory/statistics">统计信息</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#">日常管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#" data-url="/repairsRecord/index">报修记录</a>
                            </li>
                            <li>
                                <a href="#" data-url="/sendWaterRecord/index">送水纪录</a>
                            </li>
                            <li>
                                <a href="#" data-url="/visitRecord/index">来访登记</a>
                            </li>
                            <li>
                                <a href="#" data-url="/goodsRecord/index">物品出入登记</a>
                            </li>
                            <li>
                                <a href="#" data-url="/sanitation/index">卫生评比</a>
                            </li>
                            <li>
                                <a href="#" data-url="/waterRecord/index">水费登记</a>
                            </li>
                            <li>
                                <a href="#" data-url="/electricRecord/index">电费登记</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#" data-url="/publicNote/index">公告管理</a>
                    </li>
                    <li>
                        <a href="#" data-url="/admin/admin">用户管理</a>
                    </li>
                    <li>
                        <a href="#" data-url="/log/index">日志管理</a>
                    </li>
                    <li>
                        <a href="#">数据库管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#" data-url="/dataBase/save">数据备份</a>
                            </li>
                            <li>
                                <a href="#" id="backup">数据恢复</a>
                            </li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div>
        <iframe src="/publicNote/first" style="width: 100%;height: 100%;overflow-y: hidden" frameborder='no' border='0' id="iframe"  >
        </iframe>
        </div>
    </div>
    <!-- /#page-wrapper -->

    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="addModalLabel">
                        修改密码
                    </h4>
                </div>
                <form  method="post" id="addForm" class="form-horizontal">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">原密码</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="password" class="form-control" placeholder="原密码" name="oldPassword">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">新密码</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="password" class="form-control" placeholder="数字或字母，长度为6到10" name="newPassword">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">确认密码</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="password" class="form-control" placeholder="再次输入新密码" name="rePassword">
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                        <button type="reset" class="btn btn-primary" id="addReset">重置</button>
                        <button type="button" class="btn btn-primary" id="addSubmit">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>


    <div class="modal fade" id="lookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="lookModalLabel">
                        修改个人信息
                    </h4>
                </div>
                <form  method="post" id="lookForm" class="form-horizontal">
                    <input name="id"  hidden>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">工号</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control"  name="jobNumber" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">姓名</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control"  name="name" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">角色</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="role" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">电话</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control"  name="phone">
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                        <button type="button" class="btn btn-primary" id="lookSubmit">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <%--上传sql文件--%>
    <div class="modal fade" id="uploadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="uploadingModalLabel">
                        修改个人信息
                    </h4>
                </div>
                <form  method="post" id="uploadingForm" class="form-horizontal" enctype="multipart/form-data">
                    <input name="id"  hidden>
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">sql文件</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="file" class="form-control"  name="backupFile" multiple="multiple">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                        <button type="button" class="btn btn-primary" id="uploadingSubmit">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</div>
<!-- /#wrapper -->

<!-- Metis Menu Plugin JavaScript -->
<!-- Custom Theme JavaScript -->
<script src="${jsPath}/admin/test.js"></script>
</body>
</html>

