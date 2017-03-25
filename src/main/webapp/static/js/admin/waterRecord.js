/**
 * Created by cheng on 2017/1/31.
 */
$(function(){
    initModel();
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});

    //解决时间控件隐藏触发模态框隐藏事件
    $(".modal .form-control[name='startTime']").datetimepicker().on('hide', function(event){
        event.preventDefault();
        event.stopPropagation();
    });
    $(".modal .form-control[name='startTime']").datetimepicker().on('changeDate',function(){
        $("#addForm").data('bootstrapValidator').revalidateField('startTime');
        $("#editForm").data('bootstrapValidator').revalidateField('startTime');
    });

    //解决时间控件隐藏触发模态框隐藏
    $(".modal .form-control[name='endTime']").datetimepicker().on('hide', function(event){
        event.preventDefault();
        event.stopPropagation();
    });
    $(".modal .form-control[name='endTime']").datetimepicker().on('changeDate',function(){
        $("#addForm").data('bootstrapValidator').revalidateField('endTime');
        $("#editForm").data('bootstrapValidator').revalidateField('endTime');
    });

    initChangeEvent();
    initAddSubmitEvent();
    initTable();
    initDeleteButtonEvent();
    initEditSubmitEvent();
    initSelectpicker();
    initSearchButton();
    Validator();

    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    })
})

function initModel(){
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/waterRecord/search"});
    })

    $(".modal.fade").on("show.bs.modal", function(e){
        //$("#addReset").trigger("click");
        initSelectpicker();
    })
}

function initSelectpicker() {
    /*
    获取宿舍楼
     */
    $.ajax({
        url : "/building/list",
        type : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取宿舍楼失败！", {
                icon : 5
            });
            var select = $("select[name='buildingId']");
            select.empty();
            select.append("<option value=''>无</option>");
            select.selectpicker("refresh");
            select.selectpicker('render');
        },
        success : function(data){
            var select = $(".selectpicker[name='buildingId']");
            select.empty();
            select.append("<option value=''>无</option>");
            $.each(data, function(i, item){
                select.append("<option value = " + item.id + ">" + item.number + "</option>");
            });
            select.selectpicker("refresh");
            select.selectpicker('render');

        }
    });
}

function initChangeEvent() {
    $(".panel-body select[name='buildingId']").on("change",function(){
        getDormotioryList(".panel-body select[name='dormitoryId']", $(".panel-body select[name='buildingId']").val());
    })
    $(".modal.fade select[name='buildingId']").on("change",function(){
        console.log("我变了");
        getDormotioryList(".modal.fade select[name='dormitoryId']", $(".modal.fade select[name='buildingId']").val());
        $("#addForm").data("bootstrapValidator").resetField("dormitoryId", true);
    })
}

function getDormotioryList(element,buildingId) {

    var select = $(element);
    if(buildingId == undefined){

        select.empty();
        select.append("<option value=''>无</option>");
        select.selectpicker("refresh");
        select.selectpicker('render');
    }
    else{
        $.ajax({
            url : "/dormitory/list",
            type : "get",
            dataType : "json",
            data : { buildingId:buildingId },
            error : function(){
                layer.alert("获取宿舍楼失败！", {
                    icon : 5
                });
                select.empty();
                select.append("<option value=''>无</option>");
                select.selectpicker("refresh");
                select.selectpicker('render');
            },
            success : function(data){
                select.empty();
                select.append("<option value=''>无</option>");
                $.each(data, function(i, item){
                    select.append("<option value = " + item.id + ">" + item.number + "</option>");
                });
                select.selectpicker("refresh");
                select.selectpicker('render');

            }
        });
    }


}

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/waterRecord/add",
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
        url : "/waterRecord/search",
        method : "get",
        striped : "true",
        cache : false,
        pagination : true,
        sidePagination : "server",
        showExport: true,
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
                buildingId : $(".panel-body select[name='buildingId']").val(),
                dormitoryId : $(".panel-body select[name='dormitoryId']").val(),
                startTime : $(".panel-body input[name='startTime']").val(),
                endTime : $(".panel-body input[name='endTime']").val(),
                pay : $(".panel-body select[name='pay']").val()
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
                field : "buildingNumber",
                align : 'center'
            },
            {
                title : "宿舍号",
                field : "dormitoryNumber",
                align : "center",
                formatter : function(value, row, index){
                    return row.buildingNumber + "-" + value;
                }
            },
            {
                title : "开始时间",
                field : "startTime",
                align : "center"
            },
            {
                title : "结束时间",
                field : "endTime",
                align : 'center'
            },
            {
                title : "水费/元",
                field : "waterRate",
                align : 'center'
            },
            {
                title : "缴费情况",
                field : "pay",
                align : "center",
                formatter : function(value, row, index){
                    if(value == 0){
                        return "未缴费";
                    }
                    else{
                        return "已缴费";
                    }
                }
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
        '<a  href="javascript:void(0)" id="edit" title="修改">',
        '修改',
        '</a>  ',
        '<a  href="javascript:void(0)" id="pay" title="确认已缴费">',
        '确认',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        //var department = JSON.stringify(row);
        $('#editModal').modal('show');
        $("#editModal input[name='id']").val(row.id);
        $("#editModal input[name='buildingNumber']").val(row.buildingNumber);
        $("#editModal input[name='dormitoryNumber']").val(row.dormitoryNumber);
        $("#editModal input[name='startTime']").val(row.startTime);
        $("#editModal input[name='endTime']").val(row.endTime);
        $("#editModal input[name='waterRate']").val(row.waterRate);

    },
    'click #pay' : function (e, value, row, index) {
        payAjax(row.id);
    }
};

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
        url : "/waterRecord/delete",
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

            }
            else{
                layer.alert("删除失败！", {
                    icon : 0
                })
            }
            $("#table").bootstrapTable('refresh',{ url :"/waterRecord/search"});
        }


    });


}

function payAjax(data) {
    console.log(data);
    $.ajax({
        url : "/waterRecord/pay",
        type : "get",
        data : {id : data},
        traditional: true,
        dataType : "json",
        error : function(){
            layer.alert("确认失败！", {
                icon : 0
            });
        },
        success : function(data){

            if(data.message == true){
                layer.alert("确认成功！",{
                    icon : 1
                });

            }
            else{
                layer.alert("确认失败！", {
                    icon : 0
                })
            }
            $("#table").bootstrapTable('refresh',{ url :"/waterRecord/search"});
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
                url : "/waterRecord/update",
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
                        $("#table").bootstrapTable("refresh",{url : "/waterRecord/search"});
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

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/waterRecord/search");
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
            buildingId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            dormitoryId :{
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            },
            startTime : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    }
                }
            },
            endTime : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    }
                }
            },
            waterRate : {
                validators : {
                    notEmpty :{
                        message : '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp:/^[0-9]*[0-9]$/,
                        message: '必须是数字'
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
            startTime : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    }
                }
            },
            endTime : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    }
                }
            },
            waterRate : {
                validators : {
                    notEmpty :{
                        message : '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp:/^[0-9]*[0-9]$/,
                        message: '必须是数字'
                    }
                }
            }
        }
    });




}

