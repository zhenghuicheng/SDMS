/**
 * Created by cheng on 2017/2/7.
 */
$(function(){
    initModel();
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    initAddSubmitEvent();
    initTable();
    initAddButton();
    initDeleteButtonEvent();
    initEditSubmitEvent();
    initSearchButton();
    Validator();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    })
})

function initModel(){
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#table").bootstrapTable("refresh",{url : "/sendWaterRecord/user/search"});
        $("#addReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#editForm").data("bootstrapValidator").resetForm();
    })

    $(".modal.fade").on("show.bs.modal", function(e){
        //$("#addReset").trigger("click");
    })
}

function initAddButton(){
    $("#addButton").click(function(){
        var dormitoryId = $("#table").data("dormitory");
        if(dormitoryId == 0){
            layer.alert("未分配宿舍！", {
                icon : 0
            });
        }
        else{
            $("#addModal").modal("show");
        }
    })
}

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {
        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/sendWaterRecord/user/add",
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
        url : "/sendWaterRecord/user/search",
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
                buildingId : $(".panel-body select[name='buildingId']").val(),
                dormitoryId : $(".panel-body select[name='dormitoryId']").val(),
                startTime : $(".panel-body input[name='startTime']").val(),
                endTime : $(".panel-body input[name='endTime']").val(),
                name : $(".panel-body input[name='name']").val(),
                isSend : $(".panel-body select[name='isSend']").val()
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
                title : "申请时间",
                field : "time",
                align : "center"
            },
            {
                title : "申请者",
                field : "name",
                align : 'center'
            },
            {
                title : "桶数",
                field : "number",
                align : "center"
            },
            {
                title : "送达情况",
                field : "isSend",
                align : 'center',
                formatter : function(values, row, index){
                    if(values == 0){
                        return "未送";
                    }
                    else{
                        return "已送";
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
        '<a  href="javascript:void(0)" id="isSend" title="确认已送">',
        '确认',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        //var department = JSON.stringify(row);
        if(row.isSend == 1){
            layer.alert("已确认，不能修改！", {
                icon : 0
            });
        }
        else{
            $('#editModal').modal('show');
            $("#editModal input[name='id']").val(row.id);
            $("#editModal input[name='buildingNumber']").val(row.buildingNumber);
            $("#editModal input[name='dormitoryNumber']").val(row.dormitoryNumber);
            $("#editModal input[name='number']").val(row.number);
        }

    },
    'click #isSend': function (e, value, row, index) {
        if(row.isSend == 1){
            layer.alert("已经确认！", {
                icon : 0
            });
        }
        else{
            isSendAjax(row.id);
        }

        $("#table").bootstrapTable("refresh",{url : "/sendWaterRecord/user/search"});

    }
};

function isSendAjax(data) {
    console.log(data);
    $.ajax({
        url : "/sendWaterRecord/user/isSend",
        type : "get",
        data : {id : data},
        traditional: true,
        dataType : "json",
        error : function(){
            layer.alert("操作失败！", {
                icon : 0
            });
        },
        success : function(data){

            if(data.message == true){
                layer.alert("操作成功！",{
                    icon : 1
                });

            }
            else{
                layer.alert("删除失败！", {
                    icon : 0
                })
            }
            $("#table").bootstrapTable('refresh',{ url :"/sendWaterRecord/user/search"});
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

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/sendWaterRecord/user/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/sendWaterRecord/user/search"});
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
                    url : "/sendWaterRecord/user/update",
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
                            $("#table").bootstrapTable("refresh",{url : "/sendWaterRecord/user/search"});
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
        $("#table").bootstrapTable("refresh","/electricRecord/search");
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
            number : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp : {
                        regexp : /^([1-9][0-9]*){1,3}$/,
                        message : '必须是非零的正整数'
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
            number : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp : {
                        regexp : /^([1-9][0-9]*){1,3}$/,
                        message : '必须是非零的正整数'
                    }
                }
            }
        }
    });
}