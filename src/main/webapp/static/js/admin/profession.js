/**
 * Created by cheng on 2017/1/17.
 */
$(function(){
    initTable();
    initSelectpicker(".panel-body .selectpicker");
    initAddSubmitEvent();
    initModel();
    initDeleteButtonEvent();
    initEditSubmitEvent();
    initAddButton();
    Validator();
    $(".selectpicker").on("change",function(){
        console.log($(".selectpicker").val());
    });
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh", "/profession/search");
    });
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    });
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/profession/search",
        method : "get",
        striped : "true",
        cache : false,
        pagination : true,
        sidePagination : "server",
        pageNumber : 1,
        pageSize : 10,
        pageList : [10,25,50],
        queryParams:function(params){
            var temp = {
                pageSize : params.limit,
                currentPage : function(){
                    if(params.limit != 0){
                        return params.offset / params.limit + 1;
                    }
                    return params.offset
                },
                departmentId : $(".panel-body .selectpicker").val(),
                name : $(".panel-body input[name='name']").val()
            }
            return temp;
        },
        columns:[
            {
                checkbox: true,
            },
            {
                title : 'id',
                field : "id",
                visible : false
            },
            {
                title : '序号',
                align : "center",
                formatter : function(value, row, index){
                    return index + 1;
                }
            },
            {
                title : "编号",
                field : "number",
                align : 'center'
            },
            {
                title : "专业名称",
                field : "name",
                align : 'center'
            },
            {
                title : "所属学院",
                field : "departmentName",
                align : 'center'
            },
            {
                title : "共有班级",
                field : "classNumber",
                align : "center"
            },
            {
                title : "备注",
                field : "remark",
                align : "center",
            },
            {
                title : "操作",
                field : 'operate',
                align : "center",
                formatter : operateFormatter,
                events : operateEvents
            }
        ]
    })
}

function operateFormatter(value, row, index) {
    return [
        '<a  href="javascript:void(0)" id="edit" title="编辑">',
        '修改',
        '</a>  ',
        '<a  href="javascript:void(0)" id="remove" title="删除">',
        '删除',
        '</a>'
    ].join('');
}

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/profession/add",
                type : "post",
                data : null,
                error: function () {
                    layer.alert("提交数据失败！", {
                        icon : 0
                    });
                },
                success : function (data) {
                    console.log(data.message);
                    if (data.message == true){
                        layer.alert("提交数据成功！", {
                            icon : 1
                        });
                        $("#addModal").modal("hide");
                    }
                    else{
                        layer.alert("提交数据失败！", {
                            icon : 0
                        });
                    }


                }
            }
            var form = $("#addForm");
            form.ajaxSubmit(options);
        }

    });
}

function initModel() {

    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/profession/search"});
    })
}

function initDeleteButtonEvent() {

    var selection;

    $("#delete").click(function(){
        selection  = $.map($("#table").bootstrapTable('getSelections'), function (row) {
            return row.id;
        });

        if (selection.length == 0){
            layer.alert("请先勾选！", {
                icon : 5
            });
        }
        else{
            $.ajax({
                url : "/profession/countStudent",
                type : 'get',
                data : {id : selection},
                dataType : 'json',
                traditional: true,
                error : function(){
                    layer.alert("获取专业信息失败！", {
                        icon : 5
                    });
                },
                success : function(data){
                    if(data.message == true){
                        deleteAjax(selection);
                    }
                    else{
                        var msg = ""
                        var list = data.list;
                        for(var i = 0; i < list.length; i++){
                            if(i == (list.length - 1 )){
                                msg = msg + list[i] + "有学生，不能删除";
                            }
                            else{
                                msg = msg + list[i] + "、";
                            }
                        }
                        layer.alert(msg , {
                            icon : 5
                        });
                    }
                }
            })
        }



    });
}

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        //var department = JSON.stringify(row);
         $('#editModal').modal('show');
         $("#editModal input[name='name']").val(row.name);
         $("#editModal input[name='number']").val(row.number);
         $("#editModal input[name='classNumber']").val(row.classNumber);
         $("#editModal input[name='remark']").val(row.remark);
         $("#editModal input[name='id']").val(row.id);
         console.log(row.departmentId);

         var select = $("#editModal .selectpicker");

        $.ajax({
            url : "/department/list",
            type : "get",
            dataType : "json",
            error : function(){
                layer.alert("获取学院信息失败！", {
                    icon : 5
                });
            },
            success : function(data){
                select.empty();
                select.append("<option value=''>无</option>");
                $.each(data, function(i, item){
                    select.append("<option value = " + item.id + ">" + item.name + "</option>");
                });
                select.val(row.departmentId);
                select.selectpicker("refresh");
                select.selectpicker('render');
            }
        })

         $('.selectpicker').find("option[value="+ 13 +"]").attr("selected",true);
         $('.selectpicker').selectpicker('refresh');
         $('.selectpicker').selectpicker('render');

    },
    'click #remove': function (e, value, row, index) {

        layer.confirm('您确认删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            var id = new Array();
            id[0] = row.id;
            $.ajax({
                url : "/profession/countStudent",
                type : 'get',
                data : {id : id},
                dataType : 'json',
                traditional: true,
                error : function(){
                    layer.alert("获取专业信息失败！", {
                        icon : 5
                    });
                },
                success : function(data){
                    if(data.message == true){
                        deleteAjax(id);
                    }
                    else{
                        var msg = ""
                        var list = data.list;
                        for(var i = 0; i < list.length; i++){
                            if(i == (list.length - 1 )){
                                msg = list[i] + "有学生，不能删除";
                            }
                            else{
                                msg = list[i] + "、";
                            }
                        }
                        layer.alert(msg , {
                            icon : 5
                        });
                }
                }

            })

        }, function(){

        });
    }
};

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/profession/delete",
        type : "get",
        data : {id : data},
        traditional: true,
        dataType : "json",
        error : function(){
            layer.alert("删除失败！", {
                icon : 0
            });
        },
        success : function(data){

            if(data.message == true){
                layer.alert("删除成功！",{
                    icon : 1
                });
                $("#table").bootstrapTable('refresh',{ url :"/profession/search"})
            }
            else{
                layer.alert("删除失败！", {
                    icon : 0
                })
            }
        }


    });


}

function initEditSubmitEvent(){
    $("#editSubmit").click(function() {

        var bootstrapValidator = $("#editForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/profession/edit",
                type : "post",
                data : null,
                error: function () {
                    console.log("false");
                },
                success : function (data) {
                    console.log(data.message);
                    if (data.message == true){
                        layer.alert("修改成功！", {
                            icon : 1
                        });
                        $("#editReset").trigger("click");
                        $("#editModal").modal("hide");
                        $("#table").bootstrapTable("refresh",{url : "/profession/search"});
                    }
                    else{
                        layer.alert("修改失败！", {
                            icon : 0
                        });
                    }


                }
            }
            var form = $("#editForm");
            form.ajaxSubmit(options);
        }


    });
}

function initAddButton() {
    $("#add").click(function(){
        initSelectpicker("#addForm .selectpicker");

    });
}

function initSelectpicker(data){
    var select = $(data);
    $.ajax({
        url : "/department/list",
        type : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取学院信息失败！", {
                icon : 5
            });
        },
        success : function(data){
            select.empty();
            select.append("<option value=''>无</option>");
            $.each(data, function(i, item){
                if( i == 0){
                    select.selectpicker("val",item.id);
                }
                select.append("<option value = " + item.id + ">" + item.name + "</option>");
            });
            select.selectpicker("refresh");
            select.selectpicker('render');
        }
    });
}

function Validator() {

    $("#addForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            name : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '不能超过10个字'
                    },
                    remote : {
                        url : '/profession/validateName',
                        message : "该编号已经存在",
                        delay  : 1000,
                        type : "post",
                    }
                }
            },
            number : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是数字'
                    },
                    remote : {
                        url : '/profession/validateNumber',
                        message : "该编号已经存在",
                        delay  : 1000,
                        type : "post",
                    }
                }
            },
            departmentId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            classNumber : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是数字'
                    }
                }
            },
            remark : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '不能超过30个字'
                    }
                }
            }
        }
    });

    $("#editForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            departmentId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            remark : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '不能超过30个字'
                    }
                }
            }
        }
    });


}





