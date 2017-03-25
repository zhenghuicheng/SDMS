<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/17
  Time: 20:19
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
    <%--<link rel="stylesheet" href="${cssPath}/base/select2.min.css"/>--%>
    <script src="${jsPath}/base/jquery.form.js"></script>
    <script src="${uilibPath}/layer/layer.js"></script>
    <script src="${jsPath}/base/bootstrap-select.min.js"></script>
    <script src="${jsPath}/base/bootstrapValidator.min.js"></script>
    <%--<script src="${jsPath}/base/select2.full.min.js"></script>--%>
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
        专业信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 300px">
            <span class="input-group-addon">院系:</span>
            <select class="selectpicker" data-size="5" name = "departmentId"></select>
        </div>
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 300px">
            <span class="input-group-addon">专业:</span>
            <input type="text" class="form-control" placeholder="专业名称" name="name">
        </div>
        <div class="btn-group" style="margin-right: 0px">
            <button type="button" class="btn btn-primary" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-success" style="margin-right: 10px;margin-left:10px;width: 100px" data-toggle="modal" data-target="#addModal" id="add"><span class="glyphicon glyphicon-plus"></span> 添加</button>
        </div>
        <div class="btn-group">
            <button type="button" class="btn btn-danger" style="margin-right: 10px;margin-left:10px;width: 100px" id="delete"><span class="glyphicon glyphicon-trash"></span> 批量删除</button>
        </div>
        <table id="table" data-height="480"></table>
    </div>
   </div>

  <%--编辑模态框--%>
  <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                      &times;
                  </button>
                  <h4 class="modal-title" id="addModalLabel">
                      编辑专业
                  </h4>
              </div>
              <form  method="post" id="addForm" class="form-horizontal">
                  <div class="modal-body">
                      <%--<table align="center">
                          <tr>
                              <td align="right">专业名称：</td>
                              <td align="left">
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="名称" name="name">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">专业编号：</td>
                              <td align="left">
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="编号" name="number">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">所属学院：</td>
                              <td align="left">
                                  <select class="selectpicker" data-size="5" name = "departmentId"></select>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">共有班级：</td>
                              <td align="left">
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="数量" name="classNumber">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">备注：</td>
                              <td align="left">
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="不超过200个字" name="remark" width="300px">
                                  </div>
                              </td>
                          </tr>
                      </table>--%>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">专业名称</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="名称" name="name">
                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">专业编号</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="编号" name="number">
                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">所属学院</label>
                          <div class="col-sm-6">
                              <select class="selectpicker" data-size="5" name = "departmentId"></select>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">共有班级</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="数量" name="classNumber">
                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">备注</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="不超过200个字" name="remark" width="300px">
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
                  <h4 class="modal-title" id="editModalLabel">
                      修改专业信息
                  </h4>
              </div>
              <form  method="post" id="editForm" class="form-horizontal">
                  <div class="modal-body">
                      <input  name="id" type="hidden">
                      <%--<table align="center">
                          <tr>
                              <td>专业名称：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="名称" name="name">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td>专业编号：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="编号" name="number">
                                  </div>
                              </td>
                          <tr>
                              <td>所属学院：</td>
                              <td>
                                  <select class="selectpicker" data-size="5" name = "departmentId"></select>
                              </td>
                           </tr>
                          <tr>
                              <td>共有班级：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="数量" name="classNumber">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td>备注：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="不超过200个字" name="remark">
                                  </div>
                              </td>
                          </tr>
                      </table>--%>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">专业名称</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="名称" name="name" readonly>
                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">专业编号</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="名称" name="number" readonly>
                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">共有班级</label>
                          <div class="col-sm-6">
                              <div class="input-group">
                                  <input type="text" class="form-control" placeholder="数字" name="classNumber" readonly>
                              </div>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">共有班级</label>
                          <div class="col-sm-6">
                              <select class="selectpicker" data-size="5" name = "departmentId"></select>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">备注</label>
                          <div class="col-sm-6">
                              <input type="text" class="form-control" placeholder="不超过200个字" name="remark">
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
<script src="${jsPath}/admin/profession.js"></script>
</body>
</html>
