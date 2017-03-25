/**
 * Created by cheng on 2017/1/22.
 */
$(function(){
    initAddSubmitEvent();
    initModel();
    initTable();
    $(".selectpicker").selectpicker("refresh");
    $(".selectpicker").selectpicker("render");
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/admin/search");
    });
    initDeleteButtonEvent();
    initEditSubmitEvent();
    Validator();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    });
});

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/admin/add",
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

function initTable(){
    $("#table").bootstrapTable({
        url : "/admin/search",
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
                jobNumber : function(){
                    if($(".selectpicker").val() == "jobNumber"){
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
                title : "工号",
                field : "jobNumber",
                align : 'center'
            },
            {
                title : "管理员名",
                field : "name",
                align : 'center'
            },
            {
                title : '角色',
                field : 'role',
                align : 'center',
                formatter : function(value, row, index){
                        if( value == 0){
                            return "系统管理员";
                        }
                        else{
                            return "普通管理员";
                        }
                    }
            },
            {
                title : "联系方式",
                field : "phone",
                align : "center"
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

function initModel() {

    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/admin/search"});
    })
}

function deleteAjax(data) {
    $.ajax({
        url : "/admin/delete",
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
                $("#table").bootstrapTable('refresh',{ url :"/admin/search"})
            }
            else{
                if(data.number == null){
                    layer.alert("删除失败！", {
                        icon : 0
                    })
                }
                else{
                    var message = "工号为";
                    for(var i = 0; i < data.number.length; i++){
                        if(i == (data.number.length - 1)){
                            message += data.number[i] + "的管理员管理着宿舍楼，不能删除！";
                        }
                        else{
                            message += data.number[i] + "、";
                        }
                    }
                    layer.alert(message, {
                        icon : 0
                    })
                }

            }
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
            deleteAjax(selection);
        }



    });
}

function operateFormatter(value, row, index) {

    if(row.role == 0){
        return [];
    }
    else{
        return [
            '<a  href="javascript:void(0)" id="initial" title="初始密码">',
            '密码',
            '</a>  ',
            '<a  href="javascript:void(0)" id="edit" title="编辑">',
            '修改',
            '</a>  ',
            '<a  href="javascript:void(0)" id="remove" title="删除">',
            '<span class="glyphicon glyphicon-trash"></span>',
            '</a>'
        ].join('');
    }


}

window.operateEvents = {
    'click #remove': function (e, value, row, index) {

        layer.confirm('您确认删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            var data = new Array();
            data[0] = row.id;
            deleteAjax(data);
        }, function(){
        });
    },
    'click #initial' : function (e, value, row, index) {
        $.ajax({
            url: "/admin/reset",
            type: "get",
            data: {id: row.id},
            traditional: true,
            dataType: "json",
            error: function () {
                layer.alert("初始密码失败！", {
                    icon: 0
                });
            },
            success: function (data) {

                if (data.message == true) {
                    layer.alert("初始密码成功，密码为该用户的工号！", {
                        icon: 1
                    });
                    $("#table").bootstrapTable('refresh', {url: "/admin/search"})
                }
                else {
                    layer.alert("删除失败！", {
                        icon: 0
                    })
                }
            }
        });
    },
    'click #edit' : function(e, value, row, index){
        $("#editModal").modal("show");
        $("#editModal input[name='id']").val(row.id);
        $("#editModal input[name='name']").val(row.name);
        $("#editModal input[name='jobNumber']").val(row.jobNumber);
        $("#editModal input[name='phone']").val(row.phone);
    }
};

function initEditSubmitEvent(){
    $("#editSubmit").click(function() {
        var options = {
            url : "/admin/update",
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
                    $("#editModal").modal("hide");
                    $("#table").bootstrapTable("refresh",{url : "/admin/search"});
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
            jobNumber : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是数字'
                    },
                    remote : {
                        url : '/admin/validateJobNumber',
                        message : "该工号已经存在",
                        delay  : 1000,
                        type : "post"
                    }
                }
            },
            name : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    remote : {
                        url : '/admin/validateName',
                        message : "该管理员名已经存在",
                        delay  : 1000,
                        type : "post"
                    }
                }
            },
            phone : {
                validators :{
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp :{
                        regexp : /^1[34578]\d{9}$/,
                        message : '格式错误'
                    }
                }
            }
        }
    })
}