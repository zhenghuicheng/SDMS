/**
 * Created by cheng on 2017/2/5.
 */
$(function(){
    initModel();
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    initAddSubmitEvent();
    initAddButton();
    initTable();
    initDeleteButtonEvent();
    initEditSubmitEvent();
    initSearchButton();
    Validator();
    initStateSubmitEvent();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    })
})

function initModel(){
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/repairsRecord/user/search"});
    })

    $(".modal.fade").on("show.bs.modal", function(e){
        //$("#addReset").trigger("click");
    })
}

function initAddButton() {
    $("#addButton").click(function () {
        var dormitoryId = $("#table").data("dormitory");
        if(dormitoryId == 0){
            layer.alert("还未分配宿舍！", {
                icon : 0
            });
        }
        else{
            $("#addModal").modal('show');
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
                url : "/repairsRecord/user/add",
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
        url : "/repairsRecord/user/search",
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
                isRepairs : $(".panel-body select[name='isRepairs']").val(),
                number : $(".panel-body input[name='number']").val()
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
            },{
                title : '编号',
                align : "center",
                field : 'number'
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
                title : "报修时间",
                field : "repairsTime",
                align : "center"
            },
            {
                title : "报修者",
                field : "name",
                align : 'center'
            },
            {
                title : "报修内容",
                field : "context",
                align : "center"
            },
            {
                title : '备注',
                field : 'remark',
                align : 'center'
            },
            {
                title : "修理情况",
                field : "isRepairs",
                align : 'center',
                formatter : function(values, row, index){
                    if(values == 0){
                        return "未修理";
                    }
                    else if(values == 1){
                        return "处理中";
                    }
                    else{
                        return "已修理"
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
        '<a  href="javascript:void(0)" id="isRepairs" title="确认已修">',
        '确认',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        //var department = JSON.stringify(row);
        if(row.isRepairs == 2){
            layer.alert("已确认，不能修改！", {
                icon : 0
            });
        }
        else{
            $('#editModal').modal('show');
            $("#editModal input[name='id']").val(row.id);
            $("#editModal input[name='buildingNumber']").val(row.buildingNumber);
            $("#editModal input[name='dormitoryNumber']").val(row.dormitoryNumber);
            $("#editModal textarea[name='context']").val(row.context);
            $("#editModal textarea[name='remark']").val(row.remark);
        }


    },
    'click #isRepairs': function (e, value, row, index) {
        if(row.isRepairs == 2){
            layer.alert("已经确认，无法修改！", {
                icon : 0
            });
        }
        else{
            $("#stateModal").modal('show');
            $("#stateModal input[name='id']").val(row.id);
        }

        $("#table").bootstrapTable("refresh",{url : "/repairsRecord/user/search"});

    }
};


function  initStateSubmitEvent() {
    $("#stateSubmit").on("click",function () {
        console.log("123432423423");
      isRepairsAjax($("#stateModal input[name='id']").val(), $("#stateModal select[name='isRepairs']").val());
    })
}

function isRepairsAjax(id, state) {
    $.ajax({
        url : "/repairsRecord/user/is",
        type : "get",
        data : {id : id,state : state},
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
                layer.alert("操作失败！", {
                    icon : 0
                })
            }
            $("#stateModal").modal("hide");
            $("#table").bootstrapTable('refresh',{ url :"/repairsRecord/search"});
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
        url : "/repairsRecord/user/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/repairsRecord/user/search"});
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
                url : "/repairsRecord/user/update",
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
                        $("#table").bootstrapTable("refresh",{url : "/repairsRecord/user/search"});
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
        $("#table").bootstrapTable("refresh","/repairsRecord/user/search");
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
            context : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 100,
                        message: '内容长度必须在5到100之间'
                    },
                }
            },
            remark : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '不能超过30个字，没有信息的话就填无'
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
            context : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 150,
                        message: '内容长度必须在5到150之间'
                    },
                }
            },
            remark :{
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '不能超过30个字，没有信息的话就填无'
                    },
                }
            }
        }
    });
}
