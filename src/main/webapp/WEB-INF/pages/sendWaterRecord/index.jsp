<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/2/5
  Time: 18:29
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
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">栋数：</span>
            <select class="selectpicker" data-size="5" name = "buildingId">
                <option value="">无</option>
            </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">宿舍号：</span>
            <select class="selectpicker" data-size="5" name = "dormitoryId">
                <option value="">无</option>
            </select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 500px">
            <span class="input-group-addon">申请时间：</span>
            <input type="text" class="form-control" placeholder="开始时间" name="startTime">
            <label class="input-group-addon">至</label>
            <input type="text" class="form-control" placeholder="结束时间" name="endTime">
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">申请者：</span>
            <input type="text" class="form-control" placeholder="姓名" name="name">
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
            <span class="input-group-addon">进度：</span>
            <select class="selectpicker" data-size="5" name = "isSend">
                <option value="-1">无</option>
                <option value="0">未送</option>
                <option value="1">已送</option>
            </select>
        </div>
        <div class="btn-group" style="margin-right: 0px">
            <button type="button" class="btn btn-primary" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-danger" style="margin-right: 10px;margin-left:10px;width: 100px" id="delete"><span class="glyphicon glyphicon-trash"></span> 批量删除</button>
        </div>
        <table id="table" data-height="480" data-width="50"></table>
    </div>

    <%--<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="addModalLabel">
                        来访者登记
                    </h4>
                </div>
                <form  method="post" id="addForm">
                    <div class="modal-body">
                        <table align="center">
                            <tr>
                                <td align="right">宿舍楼：</td>
                                <td align="left">
                                    <select class="selectpicker" data-size="5" name = "buildingId"></select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">宿舍号：</td>
                                <td align="left">
                                    <select class="selectpicker" data-size="10" name = "dormitoryId"></select>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">来访者：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="姓名" name="visitor">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">来访者身份证：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="数学" name="visitorNumber">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">被访问者：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="姓名" name="visitedName">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">到访时间：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="不超过15字" name="startTime">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">离开时间：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="不超过15字" name="endTime">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        &lt;%&ndash;<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>&ndash;%&gt;
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
                        修改来访纪录信息
                    </h4>
                </div>
                <form  method="post" id="editForm">
                    <div class="modal-body">
                        <input  name="id" type="hidden">
                        <table align="center">
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
                                <td align="right">来访者：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="姓名" name="visitor">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">来访者身份证：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="数学" name="visitorNumber">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">被访问者：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="姓名" name="visitedName">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">到访时间：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="不超过15字" name="startTime">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="right">离开时间：</td>
                                <td align="left">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="不超过15字" name="endTime">
                                    </div>
                                </td>
                            </tr>
                        </table>
                        </table>
                    </div>
                    <div class="modal-footer">
                        &lt;%&ndash;<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>&ndash;%&gt;
                        <button type="reset" class="btn btn-primary" id="editReset">重置</button>
                        <button type="button" class="btn btn-primary" id="editSubmit">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>--%>
</div>
<script src="${jsPath}/admin/sendWaterRecord.js"></script>
</body>
</html>


