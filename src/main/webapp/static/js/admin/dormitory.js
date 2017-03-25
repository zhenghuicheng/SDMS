/**
 * Created by cheng on 2017/1/31.
 */
$(function () {
    initTable();
    initEditSubmitEvent();
    initDeleteButtonEvent();
    initModel();
    getBuildingData();
    initChange();
    initAddSubmitEvent();
    Validator();
    initSearchButton();
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/dormitory/search",
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
                floor : $(".panel-body select[name='floor']").val(),
                dormitoryId : $(".panel-body select[name='number']").val(),
                type : $(".panel-body select[name='type']").val(),
                studentNumber : $(".panel-body input[name='studentNumber']").val()
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
                field : "number",
                align : "center",
                formatter : function(value, row, index){
                    return row.buildingNumber + "-" + value;
                }
            },
            {
                title : "层数",
                field : "floor",
                align : "center"
            },
            {
                title : "床位",
                field : "bedNumber",
                align : 'center'
            },
            {
                title : "入住人数",
                field : "studentNumber",
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
        '<a  href="javascript:void(0)" id="edit" title="修改宿舍信息">',
        '修改',
        '</a>  ',
        '<a href="javascript:void(0)" id= "look" title="查看入住学生">',
        '查看',
        '</a>'
    ].join('');
}

window.operateEvents = {
    'click #edit': function (e, value, row, index) {
        $('#editModal').modal('show');
        $("#editModal input[name='id']").val(row.id);
        $("#editModal input[name='buildingNumber']").val(row.buildingNumber);
        $("#editModal input[name='floor']").val(row.floor);
        $("#editModal input[name='number']").val(row.number);
        $("#editModal input[name='bedNumber']").val(row.bedNumber);
        $("#editModal input[name='studentNumber']").val(row.studentNumber);
    },
    'click #look' : function(e, value, row, index){
        if(row.studentNumber == 0){
            layer.alert("该宿舍还没人入住！", {
                icon : 5
            });
        }
        else{
            $("#lookModal").modal("show");
            $.ajax({
                url : "/dormitory/student",
                method : "get",
                data : {id : row.id},
                dataType : "json",
                error : function(){
                    layer.alert("获取学生信息失败！", {
                        icon : 1
                    });
                },
                success : function(data){
                    console.log(data);
                    $("#studentTable").empty();
                    $("#studentTable").append("<tr><th>年级</th><th>学号</th><th>姓名</th></tr>");
                    if(data.length != 0){
                        for(var i = 0; i < data.length; i++){
                            $("#studentTable").append("<tr><td>" + data[i].grade + "</td><td>" +
                                data[i].number + "</td><td>" + data[i].name + "</td>");
                        }
                    }

                }
            })
        }

    }
};

function initEditSubmitEvent(){
    $("#editSubmit").click(function() {

        var bootstrapValidator = $("#editForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            if($("#editModal input[name='bedNumber']").val() < $("#editModal td[name='studentNumber']").html()){
                layer.alert("床位不应小于实际入往人数！", {
                    icon : 5
                });
            }
            else{
                var options = {
                    url : "/dormitory/update",
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
                            $("#table").bootstrapTable("refresh",{url : "/dormitory/search"});
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
        }




    });
}

function initDeleteButtonEvent() {

    var selection;
    var id;
    var status = true;

    $("#delete").click(function(){
        selection  = $.map($("#table").bootstrapTable('getSelections'), function (row) {
            return row;
        });

        if (selection.length == 0){
            layer.alert("请先勾选！", {
                icon : 5
            });
        }
        else{

            for(var i = 0; i < selection.length; i++){
                if(selection[i].studentNumber != 0){
                    status = false;
                }
            }
            if(status == true){
                id  = $.map($("#table").bootstrapTable('getSelections'), function (row) {
                    return row.id;
                });
                deleteAjax(id);
            }
            else{
                layer.alert("宿舍有学生入住，不能删除", {
                    icon : 5
                });
            }


        }



    });
}

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/dormitory/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/dormitory/search"});
        }


    });

}

function initModel() {
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/dormitory/search"});
    });
    $("#addModal").on("show.bs.modal", function(e){
       console.log("到这里");
    });

}

function getBuildingData() {
    $.ajax({
        url : "/building/list",
        method : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取栋数信息失败！", {
                icon : 0
            });
        },
        success : function (data) {
            buildingData = data;
            var select = $(".selectpicker[name='buildingId']");
            select.empty();
            select.append("<option value=''>无</option>");
            for(var i = 0; i < data.length; i++){
                select.append("<option value = '" + data[i].id + "'>" + data[i].number + "</option>");
            }
            select.selectpicker("refresh");
            select.selectpicker('render');
        }
    })
}

function initChange() {
    $("#addModal select[name='buildingId']").on("change",function(){

        console.log($("select[name='buildingId']").val());

        $("#addForm").data("bootstrapValidator").resetField("floor", true);
        $("#addForm").data("bootstrapValidator").resetField("number", true);

        for(var i = 0; i < buildingData.length; i++){
            if($("#addForm select[name='buildingId']").val() == buildingData[i].id){
                console.log(buildingData[i].floor);
                getFloor(buildingData[i].floor);
            }
        }
    });


    $(".panel-body select[name='buildingId']").on("change",function(){

        console.log($("select[name='buildingId']").val());

        for(var i = 0; i < buildingData.length; i++){
            if($(".panel-body select[name='buildingId']").val() == buildingData[i].id){
                console.log(buildingData[i].floor);
                getFloor(buildingData[i].floor);
            }
        }
    });


    $(".panel-body select[name='floor']").on("change", function(){
        console.log("执行了");
        var select = $(".panel-body select[name='number']");
        if($(".panel-body select[name='buildingId']").val != null){
            $.ajax({
                url : "/dormitory/listFloor",
                type : 'get',
                dataType : 'json',
                data : {buildingId : $(".panel-body select[name='buildingId']").val(),
                    floor : $(".panel-body select[name='floor']").val()},
                error : function(){
                    select.empty();
                    select.append("<option value =''>无</option>");
                    select.selectpicker("refresh");
                    select.selectpicker('render');
                },
                success : function(data){
                    select.empty();
                    select.append("<option value=''>无</option>");
                    for(var i = 0; i < data.length; i++){
                        select.append("<option value='" + data[i].id + "'>" + data[i].number + "</option>");
                    }
                    select.selectpicker("refresh");
                    select.selectpicker('render');
                }
            })
        }
    })
}

function getFloor(data) {

    var select = $(".selectpicker[name='floor']");
    select.empty();
    select.append("<option value=''>无</option>");
    for(var i = 1; i <= data; i++ ){
        select.append("<option value='" + i + "'>" + i + "</option>");
    }
    select.selectpicker("refresh");
    select.selectpicker('render');
}

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/dormitory/add",
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
                        message: '必须选择所属宿舍'
                    },

                }
            },
            floor : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择层数'
                    }
                }
            },
            number :{
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '宿舍号不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp:/^[0-9][0-9][0-9]$/,
                        message: '必须是数字'
                    },
                    remote : {
                        url : "/dormitory/validatorNumber",
                        type : "get",
                        dataType : 'json',
                        delay : 700,
                        data : {
                            buildingId : function() {
                                return $("#addForm select[name='buildingId']").val()
                            }
                        },
                        message : '该宿舍号已经存在'
                    }
                }
            },
            bedNumber : {
                validators :{
                    notEmpty : {
                        message : "床位不能为空"
                    },
                    regexp :{
                        regexp : /^[0-9]*[0-9]$/,
                        message : '必须是数字'
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
            bedNumber : {
                validators :{
                    notEmpty : {
                        message : "床位不能为空"
                    },
                    regexp :{
                        regexp : /^[0-9]*[0-9]$/,
                        message : '必须是数字'
                    }
                }
            }
        }
    });
}

function initSearchButton() {
    $("#search").on("click", function(){
        $("#table").bootstrapTable("refresh", "/dormitory/search");
    })
}
    


