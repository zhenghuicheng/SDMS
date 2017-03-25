<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/3/6
  Time: 13:14
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
    <link rel="stylesheet" href="${cssPath}/base/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${cssPath}/base/bootstrapValidator.min.css"/>
    <script src="${jsPath}/base/jquery.form.js"></script>
    <script src="${uilibPath}/layer/layer.js"></script>
    <script src="${jsPath}/base/bootstrap-select.min.js"></script>
    <script src="${jsPath}/base/bootstrapValidator.min.js"></script>
    <script src="${jsPath}/base/echarts.js"></script>
    <script src="${jsPath}/base/macarons.js"></script>
    <style>
        .modal-body .input-group{
            margin-top : 5px;
            margin-top : 5px;
        }
        td{
            height:40px;
            line-height:20px;
            align : center
        }
    </style>
</head>
<body>
<div class="panel panel-info">
    <div class="panel-heading">
        宿舍统计信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
            <span class="input-group-addon">展示</span>
            <select class="selectpicker" data-size="10" name = "model">
                <option value="table">表格</option>
                <option value="graph">图表</option>
            </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
            <span class="input-group-addon">栋楼</span>
            <select class="selectpicker" data-size="10" name = "buildingId">
                <option value="">无</option>
            </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 180px">
            <select class="selectpicker" data-size="10" name = "type">
                <option value="0">无&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                <option value="gt">已住人数>=</option>
                <option value="lt">已住人数<=</option>
                <option value="=">已住人数=</option>
                <option value="!=">已住人数!=</option>
            </select>
            <input class="form-control" name="studentNumber" placeholder="数字" value="">
        </div>
        <div class="btn-group" style="margin-right: 0px">
            <button type="button" class="btn btn-warning" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 统计</button>
        </div>
        <table id="table" data-height="480" data-width="50"></table>
    </div>
</div>


<%--<div class="modal fade" id="lookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    入住学生信息
                </h4>
            </div>
            <div class="modal-body">
                <div id="bar" style="width: 200px;height: 200px"></div>
            </div>
            <div class="modal-footer">
                &lt;%&ndash;<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>&ndash;%&gt;
                <button type="reset" class="btn btn-primary" id="editReset">重置</button>
                <button type="button" class="btn btn-primary" id="editSubmit">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>--%>


<script src="${jsPath}/admin/statistics.js"></script>
</body>
</html>
