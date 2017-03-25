/**
 * Created by cheng on 2017/2/2.
 */
$(function(){
    initModel();
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $("input[name='leaveTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $("input[name='leaveTime']").datetimepicker().on('hide', function(event){
        event.preventDefault();
        event.stopPropagation();
    });
    $(".modal input[name='leaveTime']").datetimepicker().on('changeDate',function(){
        $("#addForm").data('bootstrapValidator').revalidateField('leaveTime');
        $("#editForm").data('bootstrapValidator').revalidateField('leaveTime');
    });
    initAddSubmitEvent();
    initTable();
    initDeleteButtonEvent();
    initEditSubmitEvent();
    initSelectpicker();
    initSearchButton();
    Validator();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    });

})

function initModel(){
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#addReset").trigger("click");
        $("#table").bootstrapTable("refresh",{url : "/goodsRecord/search"});
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

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/goodsRecord/add",
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
        url : "/goodsRecord/search",
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
                name : $(".panel-body input[name='name']").val(),
                startTime : $(".panel-body input[name='startTime']").val(),
                endTime : $(".panel-body input[name='endTime']").val(),

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
                title : "搬离时间",
                field : "leaveTime",
                align : "center",

            },
            {
                title : "搬离物品",
                field : "goods",
                align : "center"
            },
            {
                title : "搬离者",
                field : "name",
                align : 'center'
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
        '<a  href="javascript:void(0)" id="" title="确认已缴费">',
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
        $("#editModal input[name='leaveTime']").val(row.leaveTime);
        $("#editModal input[name='name']").val(row.name);
        $("#editModal input[name='goods']").val(row.goods);

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
        url : "/goodsRecord/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/goodsRecord/search"});
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
                url : "/goodsRecord/update",
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
                        $("#table").bootstrapTable("refresh",{url : "/goodsRecord/search"});
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
        $("#table").bootstrapTable("refresh","/goodsRecord/search");
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
            leaveTime : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                }
            },
            name : {
                validators :{
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '不能超过10个字'
                    },
                }
            },
            goods : {
                validators :{
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '不能超过10个字'
                    },
                }
            }
        }
    });

    $("#editForm").bootstrapValidator({
        message : 'This value is not valid',
        feedbackIcons :{
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            leaveTime : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    }
                }
            },
            name : {
                validators :{
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '不能超过10个字'
                    },
                }
            },
            goods : {
                validators :{
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '不能超过10个字'
                    },
                }
            }
        }
    })

}