/**
 * Created by cheng on 2017/1/14.
 */
$(function(){
    initAddSubmitEvent();
    initEditSubmitEvent();
    initTable();
    initDeleteButtonEvent();
    initSearchButton();
    $(".selectpicker").selectpicker("refresh");
    $(".selectpicker").selectpicker("render");
    Validator();
    initModal();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    })
})

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/department/add",
                type : "post",
                data : null,
                error: function () {
                    console.log("false");
                },
                success : function (data) {
                    console.log(data.message);
                    if (data.message == "true"){
                        layer.alert("提交数据成功！", {
                            icon : 1
                        });
                        $("#addReset").trigger("click");
                        $("#addModal").modal("hide");
                        $("#table").bootstrapTable("refresh",{url : "/department/search"});
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

function initTable(){
    $("#table").bootstrapTable({
        url : "/department/search",
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
                currentPage : function() {
                    if (params.limit != 0) {
                        return params.offset / params.limit + 1;
                    }
                    return params.offset
                },
                 number : function(){
                    if($(".selectpicker").val() == "number"){
                        return $("#name").val();
                    }
                    else{
                        return "";
                    }
                },
                name : function(){
                    if($(".selectpicker").val() == "name"){
                        return $("#name").val();
                    }
                    else{
                        return "";
                    }
                }
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
                title : "学院名称",
                field : "name",
                align : 'center'
            },
            {
                title : "学院编号",
                field : "number",
                align : 'center'
            },
            {
                title : "学院院楼",
                field : "building",
                align : 'center'
            },
            {
                title : "学院电话",
                field : "phone"
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

function  initModal() {
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#addReset").trigger("click");
        $("#table").bootstrapTable("refresh",{url : "/department/search"});
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

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        //var department = JSON.stringify(row);
        $('#editModal').modal('show');
        $("#editModal input[name='name']").val(row.name);
        $("#editModal input[name='building']").val(row.building);
        $("#editModal input[name='number']").val(row.number);
        $("#editModal input[name='phone']").val(row.phone);
        $("#editModal input[name='id']").val(row.id);

    },
    'click #remove': function (e, value, row, index) {

        layer.confirm('您确认删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            var id = new Array();
            id[0] = row.id;
            $.ajax({
                url : '/department/getProfession',
                type : 'get',
                data: {id: id},
                dataType : "json",
                traditional: true,
                error : function(){
                    layer.alert("获取学院信息失败！", {
                        icon : 5
                    });
                },
                success: function(data){
                    if(data.message == true){
                        deleteAjax(id);
                    }
                    else{
                        var list = data.list;
                        var msg = "";
                        for(var i = 0; i < list.length; i++){
                            if(i == (list.length - 1)){
                                msg += list[i] + "有相关专业，无法删除";
                            }
                            else{
                                msg += list[i] + " 、"
                            }

                        }
                        layer.alert(msg, {
                            icon : 5
                        });
                    }
                   console.log("1243");
                }
            })
        }, function(){

        });
    }
};

function initEditSubmitEvent(){
    $("#editSubmit").click(function() {


        var bootstrapValidator = $("#editForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/department/edit",
                type : "post",
                data : null,
                error: function () {
                    console.log("false");
                },
                success : function (data) {
                    console.log(data.message);
                    if (data.message == true){
                        layer.alert("提交数据成功！", {
                            icon : 1
                        });
                        $("#editReset").trigger("click");
                        $("#editModal").modal("hide");
                        $("#table").bootstrapTable("refresh",{url : "/department/search"});
                    }
                    else{
                        layer.alert("提交数据失败！", {
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
                url : '/department/getProfession',
                type : 'get',
                data: {id: selection},
                dataType : "json",
                traditional: true,
                error : function(){
                    layer.alert("获取学院信息失败！", {
                        icon : 5
                    });
                },
                success: function(data){
                    if(data.message == true){
                        deleteAjax(selection);
                    }
                    else{
                        var list = data.list;
                        var msg = "";
                        for(var i = 0; i < list.length; i++){
                            if(i == (list.length - 1)){
                                msg += list[i] + "有相关专业，无法删除";
                            }
                            else{
                                msg += list[i] + " 、"
                            }

                        }
                        layer.alert(msg, {
                            icon : 5
                        });
                    }
                }
            })

        }



    });
}

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/department/delete",
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
                $("#table").bootstrapTable('refresh',{ url :"/department/search"})
            }
            else{
                layer.alert("删除失败！", {
                    icon : 0
                })
            }
        }


    });


}

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/dormitory/search");
    })
}

function Validator() {
    console.log("怎么不执行，什么情况")
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
                    remote : {
                        url : '/department/validateName',
                        message : "该学院名称已经存在",
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
                        url : '/department/validateNumber',
                        message : "该编号已经存在",
                        delay  : 1000,
                        type : "post",
                    }
                }
            },
            building : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    }
                }
            },
            phone : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}$/,
                        message: '格式错误'
                    },
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
            building : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    }
                }
            },
            phone : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}$/,
                        message: '格式错误'
                    },
                }
            }
        }
    })
}









