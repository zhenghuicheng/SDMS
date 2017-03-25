/**
 * Created by cheng on 2017/1/22.
 */
$(function(){
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $("#endTime").datetimepicker({format:'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    initModel();
    initAddSubmitEvent();
    initTable();
    initDeleteButtonEvent();
    Validator();
    initSearchButton();
    initEditSubmitEvent();
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/publicNote/search",
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
                title : $(".input-group input[name='title']").val(),
                startTime : $("#startTime").val(),
                endTime : $("#endTime").val()
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
                title : "标题",
                field : "title",
                align : 'center'
            },
            {
                title : "发布者",
                field : "adminName",
                align : 'center'
            },
            {
                title : "发布时间",
                field : "publicTime",
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

function operateFormatter(value, row, index) {
    return [
        '<a  href="javascript:void(0)" id="detail" title="查看">',
        '详情',
        '</a>  ',
        '<a  href="javascript:void(0)" id="edit" title="编辑">',
        '修改',
        '</a>  ',
        '<a  href="javascript:void(0)" id="remove" title="删除">',
        '删除',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click #detail' :function (e, value, row, index) {
        layer.msg(row.context, {
            time: 20000, //20s后自动关闭
            btn: ['知道了']
        });
    },
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
    'click #edit' : function(e, value, row, index){
        $("#editModal").modal('show');
        $("#editForm input[name='id']").val(row.id);
        $("#editForm input[name='title']").val(row.title);
        $("#editForm textarea[name='context']").val(row.context);
    }
};

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/publicNote/add",
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
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/publicNote/search"});
    })
}

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/publicNote/delete",
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
                $("#table").bootstrapTable('refresh',{ url :"/publicNote/search"})
            }
            else{
                layer.alert("删除失败！", {
                    icon : 0
                })
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

function Validator() {
    $("#addForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            title : {
                validators  : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength : {
                        min : 6,
                        max : 15,
                        message : '公告标题长度必须在6到15之间'
                    }
                }
            },
            context : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength : {
                        min : 40,
                        max : 150,
                        message : '公告内容长度必须在40到150之间'
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
            title : {
                validators  : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength : {
                        min : 6,
                        max : 15,
                        message : '公告标题长度必须在6到15之间'
                    }
                }
            },
            context : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    stringLength : {
                        min : 40,
                        max : 150,
                        message : '公告内容长度必须在40到150之间'
                    }
                }
            }

        }
    });
}

function initSearchButton() {
    $("#search").on("click", function () {
        $("#table").bootstrapTable("refresh", "/publicNote/search");
    })
}

function initEditSubmitEvent() {

    $("#editSubmit").click(function(){
        var bootstrapValidator = $("#editForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()) {
            var options = {
                url : "/publicNote/edit",
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
                        $("#editModal").modal("hide");
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
    })
}



