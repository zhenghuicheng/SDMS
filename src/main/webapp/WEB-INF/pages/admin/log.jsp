<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/4
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp"%>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${cssPath}/base/bootstrap.css"/>
    <link rel="stylesheet" href="${cssPath}/base/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="${cssPath}/base/bootstrap-table.css"/>
    <script src="${uilibPath}/layer/layer.js"></script>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">
        登录日志
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 700px">
            <span class="input-group-addon">登录时间：</span>
            <input type="text" class="form-control" placeholder="开始时间" id="startTime" >
            <label class="input-group-addon">至</label>
            <input type="text" class="form-control" placeholder="结束时间" id="endTime" >
        </div>
        <div class="btn-group" >
            <button type="button" class="btn btn-primary" style="margin-right: 0px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        </div>
        <table table data-id="${admin.id}" id="table" data-height="480">
        </table>
    </div>

</div>


<script src="${jsPath}/admin/log.js"></script>
</body>
</html>
