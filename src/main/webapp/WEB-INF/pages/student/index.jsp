<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/19
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%--<link rel="stylesheet" href="${uilibPath}/layui/css/layui.css">--%>
    <script src="${jsPath}/base/jquery.form.js"></script>
    <script src="${uilibPath}/layer/layer.js"></script>
    <script src="${jsPath}/base/bootstrap-select.min.js"></script>
    <script src="${jsPath}/base/bootstrapValidator.min.js"></script>
    <script src="${jsPath}/base/bootstrap-table-export.js"></script>
    <script src="${jsPath}/base/tableExport.js"></script>
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
        学生信息
     </div>
     <div class="panel-body">
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
             <span class="input-group-addon">院系</span>
             <select class="selectpicker" data-size="5" name = "departmentId"></select>
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
             <span class="input-group-addon">年级</span>
             <select class="selectpicker" data-size="5" name = "grade"></select>
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 200px">
             <span class="input-group-addon">专业</span>
             <select class="selectpicker" data-size="5" name="professionId"></select>
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 150px">
             <span class="input-group-addon">班级</span>
             <select class="selectpicker" data-size="5" name="classNumber" >
             </select>
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 130px">
             <span class="input-group-addon">性别</span>
             <select class="selectpicker" data-size="5" name="sex" >
                 <option value="0">无</option>
                 <option value="1">男</option>
                 <option value="2">女</option>
             </select>
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 130px">
             <span class="input-group-addon">住宿</span>
             <select class="selectpicker" data-size="5" name="dormitoryId" >
                 <option value="-1">无</option>
                 <option value="1">已分配</option>
                 <option value="0">未配</option>
             </select>
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 180px">
             <span class="input-group-addon">姓名</span>
             <input type="text" class="form-control" placeholder="姓名" name="name">
         </div>
         <div class="input-group" style="margin-bottom: 10px;float: left;width: 180px">
             <span class="input-group-addon">学号</span>
             <input type="text" class="form-control" placeholder="数字" name="number">
         </div>
         <div class="btn-group">
             <button type="button" class="btn btn-primary" style="margin-left:10px;width: 100px" id="search"><span class="glyphicon glyphicon-search"></span> 搜索</button>
         </div>
         <div class="btn-group">
             <button type="button" class="btn btn-success" style="margin-right: 10px;margin-left:10px;width: 100px" data-toggle="modal" data-target="#addModal" id = "add"><span class="glyphicon glyphicon-plus"></span> 添加</button>
         </div>
         <div class="btn-group">
             <button type="button" class="btn btn-danger" style="margin-right: 10px;margin-left:10px;width: 100px" id="delete"><span class="glyphicon glyphicon-trash"></span> 批量删除</button>
         </div>
         <div class="btn-group">
             <button type="button" class="btn btn-info" style="margin-right: 10px;margin-left:10px;width: 100px" id="info" title="批量导入学生信息"><span class="glyphicon glyphicon-save"></span> 导入信息</button>
         </div>
         <div class="btn-group">
             <button type="button" class="btn btn-warning" style="margin-right: 10px;margin-left:10px;width: 100px" id="allot" title="智能分配 "><span class="glyphicon glyphicon-adjust"></span> 智能分配</button>
         </div>
         <table id="table" data-height="440"></table>
     </div>
    </div>
   <!--添加框-->
   <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                       &times;
                   </button>
                   <h4 class="modal-title" id="addModalLabel">
                       编辑学生
                   </h4>
               </div>
               <form  method="post" id="addForm"  class="form-horizontal">
                   <div class="modal-body">
                       <%--<table align="center">
                           <tr>
                               <td align="right">学号：</td>
                               <td align="left">
                                   <div class="input-group">
                                       <input type="text" class="form-control" placeholder="学号" name="number">
                                   </div>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">姓名：</td>
                               <td align="left">
                                   <div class="input-group">
                                       <input type="text" class="form-control" placeholder="学号" name="name">
                                   </div>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">性别：</td>
                               <td align="left">
                                   <input name="sex" type="radio" value="1" checked="checked"/>男&nbsp;&nbsp;
                                   <input name="sex" type="radio" value="0"/>女
                               </td>
                           </tr>
                           <tr>
                               <td align="right">所属年级：</td>
                               <td>
                                   <select class="selectpicker" data-size="5" name = "grade"></select>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">所属学院：</td>
                               <td align="left">
                                   <select class="selectpicker" data-size="5" name = "departmentId"></select>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">所属专业：</td>
                               <td align="left">
                                   <select class="selectpicker" data-size="5" name="professionId"></select>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">所属班级：</td>
                               <td align="">
                                   <select class="selectpicker" data-size="5" name="classNumber" >
                                   </select>
                               </td>
                           </tr>

                       </table>--%>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">学号</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="学号" name="number">
                               </div>
                           </div>
                       </div>
                       <div class="form-group">
                       <label class="col-sm-4 control-label">姓名</label>
                       <div class="col-sm-6">
                           <div class="input-group">
                               <input type="text" class="form-control" placeholder="学号" name="name">
                           </div>
                       </div>
                   </div>
                  <div class="form-group">
                           <label class="col-sm-4 control-label">性别</label>
                           <div class="col-sm-6">
                               <input name="sex" type="radio" value="1" checked="checked"/>男&nbsp;&nbsp;
                               <input name="sex" type="radio" value="0"/>女
                           </div>
                  </div>
                       <div class="form-group">
                               <label class="col-sm-4 control-label">所属年级</label>
                               <div class="col-sm-6">
                                   <select class="selectpicker" data-size="5" name="grade"></select>
                               </div>
                           </div>
                       <div class="form-group">
                               <label class="col-sm-4 control-label">所属学院</label>
                               <div class="col-sm-6">
                                   <select class="selectpicker" data-size="5" name = "departmentId"></select>
                               </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">所属专业</label>
                           <div class="col-sm-6">
                               <select class="selectpicker" data-size="5" name="professionId"></select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">所属班级</label>
                           <div class="col-sm-6">
                               <select class="selectpicker" data-size="5" name="classNumber" ></select>
                           </div>
                       </div>

                   </div>
                   <div class="modal-footer">
                       <button type="reset" class="btn btn-primary" id="addReset">重置</button>
                       <button type="button" class="btn btn-primary" id="addSubmit">
                           提交
                       </button>
                   </div>
               </form>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>
   <%--修改框--%>
   <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                       &times;
                   </button>
                   <h4 class="modal-title" id="myModalLabel">
                       修改学生信息
                   </h4>
               </div>
               <form  method="post" id="editForm" class="form-horizontal">
                   <div class="modal-body">
                       <input  name="id" type="hidden">
                       <%--<table align="center">
                           <tr>
                               <td align="right">年级：</td>
                               <td name="grade"></td>
                           </tr>
                           <tr>
                               <td align="right">专业：</td>
                               <td name="profession">
                               </td>
                           </tr>
                           <tr>
                               <td align="right">班级：</td>
                               <td name="classNumber">
                               </td>
                           </tr>
                           <tr>
                               <td align="right">学号：</td>
                               <td align="left">
                                   <div class="input-group">
                                       <input type="text" class="form-control" placeholder="姓名" name="number">
                                   </div>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">姓名：</td>
                               <td align="left">
                                   <div class="input-group">
                                       <input type="text" class="form-control" placeholder="数学" name="name">
                                   </div>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">性别：</td>
                               <td align="left">
                                   <input name="sex" type="radio" value="1"/>男&nbsp;&nbsp;
                                   <input name="sex" type="radio" value="0" checked="checked"/>女
                               </td>
                           </tr>
                           <tr>
                               <td align="right">电话：</td>
                               <td align="left">
                                   <div class="input-group">
                                       <input type="text" class="form-control" placeholder="不超过15字" name="phone">
                                   </div>
                               </td>
                           </tr>
                       </table>
                       </table>--%>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">年级</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="学号" name="grade" readonly>
                               </div>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">专业</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="学号" name="profession" readonly>
                               </div>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">班级</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="学号" name="classNumber" readonly>
                               </div>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">学号</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="学号" name="number">
                               </div>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">姓名</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="学号" name="name">
                               </div>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">性别</label>
                           <div class="col-sm-6">
                               <input name="sex" type="radio" value="1"/>男&nbsp;&nbsp;
                               <input name="sex" type="radio" value="0" checked="checked"/>女
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">电话</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="text" class="form-control" placeholder="不超过15字" name="phone">
                               </div>
                           </div>
                       </div>
                   </div>
                   <div class="modal-footer">
                       <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                       <button type="button" class="btn btn-primary" id="editSubmit">
                           提交
                       </button>
                   </div>
               </form>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>
   <%--分配宿舍--%>
   <div class="modal fade" id="assignModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                       &times;
                   </button>
                   <h4 class="modal-title" id="allotModalLabel">
                       分配宿舍
                   </h4>
               </div>
               <form  method="post" id="assignForm" class="form-horizontal">
                   <input  name="studentId" type="hidden">
                   <div class="modal-body">
                       <%--<table align="center">
                           <tr>
                               <td align="right">宿舍楼：</td>
                               <td align="left">
                                   <select class="selectpicker" data-size="5" name = "buildingId">
                                       <option value="">无</option>
                                   </select>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">宿舍号：</td>
                               <td align="left">
                                   <select class="selectpicker" data-size="10" name = "dormitoryId">
                                       <option value="">无</option>
                                   </select>
                               </td>
                           </tr>
                           <tr>
                               <td align="right">宿舍床位：</td>
                               <td align="left" name="bedNumber"></td>
                           </tr>
                           <tr>
                               <td align="right">宿舍人数：</td>
                               <td align="left" name="studentNumber"></td>
                           </tr>
                       </table>--%>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">宿舍楼</label>
                           <div class="col-sm-6">
                               <select class="selectpicker" data-size="5" name = "buildingId">
                                   <option value="">无</option>
                               </select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">宿舍号</label>
                           <div class="col-sm-6">
                               <select class="selectpicker" data-size="10" name = "dormitoryId">
                                   <option value="">无</option>
                               </select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">宿舍床位</label>
                           <div class="col-sm-6">
                               <input type="text" class="form-control"  name="bedNumber" readonly>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">宿舍人数</label>
                           <div class="col-sm-6">
                               <input type="text" class="form-control" name="studentNumber" readonly>
                           </div>
                       </div>

                   </div>
                   <div class="modal-footer">
                       <button type="button" class="btn btn-primary" id="assignSubmit">
                           提交
                       </button>
                   </div>
               </form>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>
   <%--上传excel文件--%>
   <div class="modal fade" id="uploadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                       &times;
                   </button>
                   <h4 class="modal-title" id="uploadingModalLabel">
                       选择文件
                   </h4>
               </div>
               <form  method="post" id="uploadingForm" class="form-horizontal" enctype="multipart/form-data">
                   <input  name="studentId" type="hidden">
                   <div class="modal-body">
                       <div class="form-group">
                           <label class="col-sm-4 control-label">所属学院</label>
                           <div class="col-sm-6">
                               <select class="selectpicker" data-size="5" name = "departmentId"></select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">所属专业</label>
                           <div class="col-sm-6">
                               <select class="selectpicker" data-size="5" name="professionId"></select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">文件</label>
                           <div class="col-sm-6">
                               <div class="input-group">
                                   <input type="file" class="form-control" placeholder="学号" name="studentFile" multiple="multiple">
                               </div>
                           </div>
                       </div>
                   </div>
                   <div class="modal-footer">
                       <button type="reset" class="btn btn-primary" id="uploadingReset" style="visibility: hidden">重置</button>
                       <button type="button" class="btn btn-primary" id="uploadingSubmit">
                           提交
                       </button>
                   </div>
               </form>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>
   <%--查看宿舍情况--%>
   <div class="modal fade" id="lookModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                       &times;
                   </button>
                   <h4 class="modal-title" id="lookModalLabel">
                       入住宿舍
                   </h4>
               </div>
               <form  method="post" id="lookForm" class="form-horizontal">
                   <input  name="studentId" type="hidden">
                   <div class="modal-body">
                       <div class="form-group">
                           <label class="col-sm-4 control-label">宿舍楼</label>
                           <div class="col-sm-6">
                               <input class="form-control" readonly name="buildingNumber">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">宿舍号</label>
                           <div class="col-sm-6">
                                   <input class="form-control" readonly name="dormitoryNumber">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">床位</label>
                           <div class="col-sm-6">
                               <input class="form-control" name="bedNumber" readonly>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-sm-4 control-label">入住人数</label>
                           <div class="col-sm-6">
                               <input class="form-control" name="studentNumber" readonly>
                           </div>
                       </div>
                   </div>
                   <div class="modal-footer">
                   </div>
               </form>
           </div><!-- /.modal-content -->
       </div><!-- /.modal -->
   </div>

<script src="${jsPath}/admin/student.js"></script>


</body>
</html>
