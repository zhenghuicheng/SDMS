<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/2/2
  Time: 14:02
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
    <link rel="stylesheet" href="${cssPath}/base/bootstrap-table.css"/>
    <link rel="stylesheet" href="${cssPath}/base/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="${cssPath}/base/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${cssPath}/base/bootstrapValidator.min.css"/>
    <script src="${jsPath}/base/jquery.form.js"></script>
    <script src="${uilibPath}/layer/layer.js"></script>
    <script src="${jsPath}/base/bootstrap-select.min.js"></script>
    <script src="${jsPath}/base/bootstrapValidator.min.js"></script>
    <style>
        .modal-body .input-group{
            margin-top : 5px;
            margin-top : 5px;
        }
    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">
        卫生评比信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 400px">
            <span class="input-group-addon">时间范围</span>
            <input type="text" class="form-control" placeholder="开始时间" name="startTime">
            <label class="input-group-addon">至</label>
            <input type="text" class="form-control" placeholder="结束时间" name="endTime">
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
            <span class="input-group-addon">卫生情况</span>
            <select class="selectpicker" data-size="10" name = "degree">

            </select>
        </div>
        <div class="btn-group" style="margin-right: 0px">
            <button type="button" class="btn btn-primary" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        </div>
        <table id="table" data-height="480" data-width="50"></table>
    </div>

</div>
<script src="${jsPath}/user/sanitation.js"></script>
</body>
</html>
