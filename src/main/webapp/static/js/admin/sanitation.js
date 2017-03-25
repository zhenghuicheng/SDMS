/**
 * Created by cheng on 2017/2/2.
 */
$(function(){
    initModel();
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});
    $(".modal .form-control[name='startTime']").datetimepicker().on('hide', function(event){
        event.preventDefault();
        event.stopPropagation();
    });
    $(".form-control[name='startTime']").datetimepicker().on('changeDate', function(event){
        $("#addForm").data('bootstrapValidator').revalidateField('startTime');
        $("#editForm").data('bootstrapValidator').revalidateField('startTime', true);
        console.log("12345678");
    });

    $(".modal .form-control[name='endTime']").datetimepicker().on('hide', function(event){
        event.preventDefault();
        event.stopPropagation();
    });
    $(".form-control[name='endTime']").datetimepicker().on('changeDate', function(event){
        $("#addForm").data('bootstrapValidator').revalidateField('endTime');
        $("#editForm").data('bootstrapValidator').revalidateField('endTime', true);
        console.log("12345678");
    });
    initChangeEvent();
    initAddSubmitEvent();
    initTable();
    initDeleteButtonEvent();
    initEditSubmitEvent();
    initSelectpicker();
    initSearchButton();
    Validator();
    $("#addReset").on("click",function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    });
})

function initModel(){
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/sanitation/search"});
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

    /*
     初始等级
     */
    var degreeSelect = $(".selectpicker[name='degree']");
    degreeSelect.empty();
    degreeSelect.append("<option value=''>无</option>");
    var degree =  new Array();
    degree[1] = '优';
    degree[2] = '良';
    degree[3] = '中';
    degree[4] = '差';
    for(var i = 1; i < 5; i++){
        degreeSelect.append("<option value = " + i + ">" + degree[i] + "</option>");
    }
    degreeSelect.selectpicker("refresh");
    degreeSelect.selectpicker('render');
}

function initChangeEvent() {
    $(".panel-body select[name='buildingId']").on("change",function(){
        getDormotioryList(".panel-body select[name='dormitoryId']", $(".panel-body select[name='buildingId']").val());
    })
    $(".modal.fade select[name='buildingId']").on("change",function(){
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
                url : "/sanitation/add",
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
        url : "/sanitation/search",
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
                degree : $(".panel-body select[name='degree']").val()
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
                title : "卫生情况",
                field : "degree",
                align : "center",
                formatter : function(value, row, index){
                    if(value == 1){
                        return "优";
                    }
                    else if(value == 2){
                        return "良";
                    }
                    else if(value == 3){
                        return "中";
                    }
                    else{
                        return "差";
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
        $("#editModal input[name='dormitoryNumber']").val(row.dormitoryNumber);
        $("#editModal input[name='startTime']").val(row.startTime);
        $("#editModal input[name='endTime']").val(row.endTime);
        $("#editModal .selectpicker[name='degree']").val(row.degree);
        $("#editModal .selectpicker[name='degree']").selectpicker("refresh");
        $("#editModal .selectpicker[name='degree']").selectpicker("render");

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
        url : "/sanitation/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/sanitation/search"});
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
                url : "/sanitation/update",
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
                        $("#table").bootstrapTable("refresh",{url : "/sanitation/search"});
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
        $("#table").bootstrapTable("refresh","/sanitation/search");
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
            startTime : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            },
            endTime : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            },
            buildingId : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            },
            dormitoryId : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            },
            degree : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
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
                        message : '必须选择'
                    }
                }
            },
            endTime : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            },
            degree : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            }
        }
    })


}