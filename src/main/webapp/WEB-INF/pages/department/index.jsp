<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/14
  Time: 9:36
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
    </style>
</head>
<body>

<div class="panel panel-info">
    <div class="panel-heading">
        学院信息
    </div>
    <div class="panel-body">
        <div class="input-group" style="margin-bottom: 10px;float: left;width: 300px">
            <select class="selectpicker" data-size="5" name = "departmentId">
                <option value="number">编&nbsp;&nbsp;号</option>
                <option value="name">系&nbsp;&nbsp;名</option>
            </select>
            <input type="text" class="form-control" placeholder="院系名字" id="name">
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

   <!--添加模态框-->
  <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                      &times;
                  </button>
                  <h4 class="modal-title" id="addModalLabel">
                      添加院系
                  </h4>
              </div>
              <div class="modal-body">
              <form  method="post" id="addForm" class="form-horizontal">

                  <div class="form-group">
                      <label class="col-sm-4 control-label">院系名称</label>
                      <div class="col-sm-6">
                          <input  class="form-control" name="name" placeholder="数字" >
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-4 control-label">院系编号</label>
                      <div class="col-sm-6">
                          <input  class="form-control" name="number" placeholder="数字">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-4 control-label">院数</label>
                      <div class="col-sm-6">
                          <input  class="form-control" name="building" placeholder="院楼">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="col-sm-4 control-label">院系电话</label>
                      <div class="col-sm-6">
                          <input  class="form-control" name="phone" placeholder="区位—电话号码" >
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
  <%--修改模态框--%>
  <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                      &times;
                  </button>
                  <h4 class="modal-title" id="myModalLabel">
                      修改院系
                  </h4>
              </div>
              <form  method="post" id="editForm" class="form-horizontal">
                  <div class="modal-body">
                      <input  name="id" type="hidden">
                      <%--<table align="center">
                          <tr>
                              <td align="right">院系名称：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="名称" name="name">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">院系编号：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="编号" name="number">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">院楼：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="院楼" name="building">
                                  </div>
                              </td>
                          </tr>
                          <tr>
                              <td align="right">院系电话：</td>
                              <td>
                                  <div class="input-group">
                                      <input type="text" class="form-control" placeholder="电话" name="phone">
                                  </div>
                              </td>
                          </tr>
                      </table>--%>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">院系名称</label>
                          <div class="col-sm-6">
                              <input  class="form-control" name="name" placeholder="数字" readonly>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">院系编号</label>
                          <div class="col-sm-6">
                              <input  class="form-control" name="number" placeholder="数字" readonly>
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">院楼</label>
                          <div class="col-sm-6">
                              <input  class="form-control" name="building" placeholder="数字" >
                          </div>
                      </div>
                      <div class="form-group">
                          <label class="col-sm-4 control-label">院系电话</label>
                          <div class="col-sm-6">
                              <input  class="form-control" name="phone" placeholder="数字" >
                          </div>
                      </div>

                  </div>
                  <div class="modal-footer">
                      <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                      <%--<button type="reset" class="btn btn-primary" id="editReset">重置</button>--%>
                      <button type="button" class="btn btn-primary" id="editSubmit">
                          提交
                      </button>
                  </div>
              </form>
          </div><!-- /.modal-content -->
      </div><!-- /.modal -->
  </div>


<script src="${jsPath}/admin/department.js"></script>
</body>
</html>
