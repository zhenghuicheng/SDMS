<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/2/6
  Time: 18:06
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
    <script src="${jsPath}/base/jquery.form.js"></script>
    <script src="${uilibPath}/layer/layer.js"></script>
    <script src="${jsPath}/base/bootstrap-select.min.js"></script>
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
        来访信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 500px">
            <span class="input-group-addon">报修时间</span>
            <input type="text" class="form-control" placeholder="开始时间" name="startTime">
            <label class="input-group-addon">至</label>
            <input type="text" class="form-control" placeholder="结束时间" name="endTime">
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">进度：</span>
            <select class="selectpicker" data-size="5" name = "isRepairs">
                <option value="-1">无</option>
                <option value="0">未处理</option>
                <option value="1">处理中</option>
                <option value="2">已处理</option>
            </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 230px">
            <span class="input-group-addon">编号</span>
            <input type="text" class="form-control" placeholder="数字" name="number">
        </div>
        <div class="btn-group" style="margin-right: 0px">
            <button type="button" class="btn btn-primary" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-success" style="margin-right: 10px;margin-left:10px;width: 100px" data-toggle="modal" id="addButton"><span class="glyphicon glyphicon-plus"></span> 报修</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-danger" style="margin-right: 10px;margin-left:10px;width: 100px" id="delete"><span class="glyphicon glyphicon-trash"></span> 批量删除</button>
        </div>
        <table id="table" data-height="480" data-width="50" data-dormitory="${user.dormitoryId}"></table>
    </div>
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="addModalLabel">
                        报修登记
                    </h4>
                </div>
                <form  method="post" id="addForm" class="form-horizontal">
                    <div class="modal-body">
                       <%-- <table align="center">
                            <tr>
                                <td align="right">报修内容：</td>
                                <td align="left">
                                    <textarea   cols="25"   rows="4"  style="resize:none;" placeholder="不超过200字" name="context"></textarea>
                                </td>
                            </tr>

                        </table>--%>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">报修内容</label>
                            <div class="col-sm-6">
                                <textarea   cols="25"   rows="4"  style="resize:none;" placeholder="不超过200字" name="context"></textarea>
                            </div>
                        </div>
                         <div class="form-group">
                               <label class="col-sm-4 control-label">备注</label>
                               <div class="col-sm-6">
                                   <textarea   cols="25"   rows="2"  style="resize:none;" placeholder="不超过200字" name="remark"></textarea>
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
                        <input  name="id" type="hidden" >
                        <%--<table align="center">
                            <tr>
                                <td align="right">栋数：</td>
                                <td name="buildingNumber">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">宿舍号：</td>
                                <td name="dormitoryNumber">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">报修内容：</td>
                                <td align="left">
                                    <textarea   cols="25"   rows="4"  style="resize:none;" placeholder="不超过200字" name="context"></textarea>
                                </td>
                            </tr>

                        </table>--%>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">栋数</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                        <input type="text" class="form-control" placeholder="不超过15字" name="buildingNumber" readonly>
                                    </div>
                            </div>
                            </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">宿舍号</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="不超过15字" name="dormitoryNumber" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">报修内容</label>
                            <div class="col-sm-6">
                                <textarea   cols="25"   rows="4"  style="resize:none;" placeholder="不超过200字" name="context"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">备注</label>
                            <div class="col-sm-6">
                                <textarea   cols="25"   rows="4"  style="resize:none;" placeholder="不超过200字" name="remark"></textarea>
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

    <div class="modal fade" id="stateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="stateModalLabel">
                        修改处理状态
                    </h4>
                </div>
                <form  method="post" id="stateForm" class="form-horizontal">
                    <div class="modal-body">
                        <input  name="id" type="hidden" >
                        <%--<table align="center">
                            <tr>
                                <td align="right">栋数：</td>
                                <td name="buildingNumber">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">宿舍号：</td>
                                <td name="dormitoryNumber">
                                </td>
                            </tr>
                            <tr>
                                <td align="right">报修内容：</td>
                                <td align="left">
                                    <textarea   cols="25"   rows="4"  style="resize:none;" placeholder="不超过200字" name="context"></textarea>
                                </td>
                            </tr>

                        </table>--%>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">修理状态</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <select class="selectpicker" data-size="5" name = "isRepairs">
                                        <option value="1">处理中</option>
                                        <option value="2">已处理</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                        <button type="button" class="btn btn-primary" id="stateSubmit">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<script src="${jsPath}/user/repairsRecord.js"></script>
</body>
</html>

