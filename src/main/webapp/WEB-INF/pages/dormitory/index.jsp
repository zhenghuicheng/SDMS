<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/31
  Time: 10:28
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
        宿舍信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
            <span class="input-group-addon">栋楼</span>
             <select class="selectpicker" data-size="10" name = "buildingId">
                    <option value="">无</option>
             </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
            <span class="input-group-addon">层数</span>
            <select class="selectpicker" data-size="10" name = "floor">
                <option value="">无</option>
            </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
            <span class="input-group-addon">宿舍号</span>
            <select class="selectpicker" data-size="10" name = "number">
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
                    宿舍添加
                </h4>
            </div>
            <form  method="post" id="addForm" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">宿舍楼</label>
                        <div class="col-sm-6">
                            <select class="selectpicker" data-size="10" name = "buildingId">
                                <option value="">无</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">层数</label>
                        <div class="col-sm-6">
                            <select class="selectpicker" data-size="10" name = "floor">
                                <option value="">无</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">宿舍号</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="格式为101，1为层数" name="number">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">床位</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="床位" name="bedNumber">
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

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改宿舍信息
                </h4>
            </div>
            <form  method="post" id="editForm" class="form-horizontal">
                <div class="modal-body">
                    <input  name="id" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">栋数</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control"  name="buildingNumber" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">层数</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control"  name="floor" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">宿舍号</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control"  name="number" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">入住人数</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control"  name="studentNumber" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">床位</label>
                        <div class="col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control"  name="bedNumber">
                            </div>
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

<div class="modal fade" id="lookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <table align="center"  class="table table-bordered" id="studentTable">
                </table>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                <button type="reset" class="btn btn-primary" id="editReset">重置</button>
                <button type="button" class="btn btn-primary" id="editSubmit">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="${jsPath}/admin/dormitory.js"></script>
</body>
</html>
