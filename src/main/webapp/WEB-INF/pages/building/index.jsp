<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/30
  Time: 15:52
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
        宿舍楼信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">院系：</span>
            <select class="selectpicker" data-size="5"  name="departmentId"></select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">管理员：</span>
            <select class="selectpicker" data-size="5"  name="adminId"></select>
        </div>
        <div class="btn-group" style="margin-right: 0px">
            <button type="button" class="btn btn-primary" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-success" style="margin-right: 10px;margin-left:10px;width: 100px" data-toggle="modal" data-target="#addModal"><span class="glyphicon glyphicon-plus"></span> 添加</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-danger" style="margin-right: 10px;margin-left:10px;width: 100px" id="delete"><span class="glyphicon glyphicon-trash"></span> 批量删除</button>
        </div>
        <table id="table" data-height="480" data-width="50"></table>
    </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="addModalLabel">
                    添加宿舍楼
                </h4>
            </div>
            <form  method="post" id="addForm" class="form-horizontal">
                <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">栋数</label>
                            <div class="col-sm-6">
                                <input  class="form-control" name="number" placeholder="数字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">层数</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="数字" name="floor">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">每层房间数</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="数字" name="floorNumber">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">每间床位</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" placeholder="数字" name="bedNumber">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">男/女宿舍楼群 </label>
                            <div class="col-sm-6">
                                <input name="sex" type="radio" value="1" checked="checked"/>男&nbsp;&nbsp;
                                <input name="sex" type="radio" value="0"/>女
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">所属学院</label>
                            <div class="col-sm-6">
                                <select class="selectpicker" data-size="5" id = "departmentData" name="departmentId"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">管理员</label>
                            <div class="col-sm-6">
                                <select class="selectpicker" data-size="5" id = "adminData" name="adminId"></select>
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

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改报修信息
                </h4>
            </div>
            <form  method="post" id="editForm" class="form-horizontal">
                <div class="modal-body">
                    <input  name="id" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">栋数</label>
                        <div class="col-sm-6">
                            <input  class="form-control" name="number" placeholder="数字" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">层数</label>
                        <div class="col-sm-6">
                            <input  class="form-control" name="floor" placeholder="数字" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">每层房间数</label>
                        <div class="col-sm-6">
                            <input  class="form-control" name="floorNumber" placeholder="数字" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">每间床位</label>
                        <div class="col-sm-6">
                            <input  class="form-control" name="bedNumber" placeholder="数字" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">男/女宿舍楼</label>
                        <div class="col-sm-6">
                            <input name="sex" type="radio" value="1" checked="checked"/>男&nbsp;&nbsp;
                            <input name="sex" type="radio" value="0"/>女
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属学院</label>
                        <div class="col-sm-6">
                            <select class="selectpicker" data-size="5"  name="departmentId"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">管理员</label>
                        <div class="col-sm-6">
                            <select class="selectpicker" data-size="5"  name="adminId"></select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                    <button type="reset" class="btn btn-primary" id="editReset">重置</button>
                    <button type="button" class="btn btn-primary" id="editSubmit">
                        提交
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="${jsPath}/admin/building.js"></script>
</body>
</html>
