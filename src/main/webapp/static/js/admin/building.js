/**
 * Created by cheng on 2017/1/30.
 */
$(function(){
    initTable();
    initSelectEven(".panel-body select[name='departmentId']", ".panel-body select[name='adminId']", 0, 0);
    initModel();
    initAddSubmitEvent();
    initEditSubmitEvent();
    initDeleteButtonEvent();
    initSearchButton();
    Validator();
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/building/search",
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
                departmentId : $(".panel-body .selectpicker[name='departmentId']").val(),
                adminId : $(".panel-body .selectpicker[name='adminId']").val()
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
                title : "栋数",
                field : "number",
                align : 'center'
            },
            {
                title : "男/女宿舍楼群 ",
                field : "sex",
                align : 'center',
                formatter : function(value, row, index){
                    if( value == 0){
                        return "女";
                    }
                    else{
                        return "男";
                    }

                }
            },
            {
                title : "层数",
                field : "floor",
                align : "center"
            },
            {
                title : "每层房间数",
                field : "floorNumber",
                align : 'center'
            },
            {
                title : "管理员",
                field : "adminName",
                align : 'center'
            },
            {
                title : "所属学院",
                field : "departmentName",
                align : "center"
            },
            {
                title : "每间床位",
                field : "bedNumber",
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

function initSelectEven(department, admin, departmentId, adminId) {
    $.ajax({
        url : "/department/list",
        type : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取学院信息失败！", {
                icon : 5
            });
            var select = $(department);
            select.empty();
            select.append("<option value=''>无</option>");
        },
        success : function(data){
            var select = $(department);
            select.empty();
            select.append("<option value=''>无</option>");
            $.each(data, function(i, item){
                select.append("<option value = " + item.id + ">" + item.name + "</option>");
            });
            if(departmentId != 0){
                select.selectpicker("val", departmentId);
            }
            select.selectpicker("refresh");
            select.selectpicker('render');
        }
    });

    $.ajax({
        url : "/admin/list",
        type : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取学院信息失败！", {
                icon : 5
            });
            var select = $(admin);
            select.empty();
            select.selectpicker("refresh");
            select.selectpicker("render");
        },
        success : function(data){
            var select = $(admin);
            select.empty();
            select.append("<option value=''>无</option>");
            $.each(data, function(i, item){
                select.append("<option value = " + item.id + ">" + item.name + "</option>");
            });
            if(0 != adminId){
                select.selectpicker("val", adminId);
            }
            select.selectpicker("refresh");
            select.selectpicker('render');
        }
    });
}

function initModel() {

    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#addReset").trigger("click");
        $("#table").bootstrapTable("refresh",{url : "/building/search"});
    });

    $("#addModal").on("show.bs.modal", function (e) {
        initSelectEven("#departmentData", "#adminData", 0, 0);
    })
}

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            //表单提交的方法、比如ajax提交
            var options = {
                url : "/building/add",
                type : "post",
                data : null,
                beforeSubmit : function(){
                    layer.load(0, {shade: false});
                },
                error: function () {
                    layer.closeAll("loading");
                    layer.alert("添加失败！", {
                        icon : 0
                    });
                },
                success : function (data) {
                    layer.closeAll("loading");
                    if (data.message == true){
                        layer.alert("添加数据并生成宿舍信息成功！", {
                            icon : 1
                        });

                        $("#addModal").modal("hide");
                        //$("#table").bootstrapTable("refresh",{url : "/building/search"});
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

function operateFormatter(value, row, index) {
    return [
        '<a  href="javascript:void(0)" id="edit" title="修改">',
        '修改',
        '</a>  ',
    ].join('');
}

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        $('#editModal').modal('show');
        $("#editModal input[name='id']").val(row.id);
        $("#editModal input[name='number']").val(row.number);
        $("#editModal input[name='floor']").val(row.floor);
        $("#editModal input[name='floorNumber']").val(row.floorNumber);
        $("#editModal input[name='bedNumber']").val(row.bedNumber);
        $("#editModal input[name='name']").val(row.name);
        if(row.sex == 1){
            $("#editModal input[name='sex']").eq(0).click();
        }
        else{
            $("#editModal input[name='sex']").eq(1).click();
        }

        initSelectEven("#editModal .selectpicker[name='departmentId']", "#editModal .selectpicker[name='adminId']", row.departmentId, row.adminId);

    },
};

function initEditSubmitEvent(){
    $("#editSubmit").click(function() {
        var options = {
            url : "/building/update",
            type : "post",
            data : null,
            error: function () {
                layer.alert("操作失败！", {
                    icon : 0
                });
            },
            success : function (data) {
                console.log(data.message);
                if (data.message == true){
                    layer.alert("操作成功！", {
                        icon : 1
                    });
                    $("#editModal").modal("hide");
                }
                else{
                    layer.alert("操作失败！", {
                        icon : 0
                    });
                }


            }
        }
        var form = $("#editForm");
        form.ajaxSubmit(options);
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

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/building/delete",
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
                $("#table").bootstrapTable('refresh',{ url :"/building/search"})
            }
            else{
                if(data.number == null){
                    layer.alert("删除失败！",{
                        icon : 0
                    });
                }
                else{
                    var message = "";
                    for(var i = 0; i < data.number.length; i++){
                        if(i == (data.number.length - 1)){
                            message = message + data.number[i] + "栋";
                        }
                        else{
                            message = message + data.number[i] + "栋、" ;
                        }
                    }
                    layer.alert(message + "下有宿舍信息，先删除宿舍信息",{
                        icon : 0
                    });
                }
            }
        }


    });


}

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/building/search");
    })
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
            number : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '栋数不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是'
                    },
                    remote : {
                        url : '/building/validator',
                        message : "该栋已经存在",
                        delay  : 1000,
                        type : "post",
                    }
                },

            },
            floor : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '层数不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    },
                }
            },
            floorNumber : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '每层房间数不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    },

                }
            },
            bedNumber: {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '房间床位不能为空',

                    },regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    }

                }
            },
            departmentId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择所属学院'
                    },

                }
            },
            adminId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择所属管理员'
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
            departmentId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择所属学院'
                    },

                }
            },
            adminId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择所属管理员'
                    },

                }
            }
        }
    })
}