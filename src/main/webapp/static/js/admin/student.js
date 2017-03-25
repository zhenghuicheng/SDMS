/**
 * Created by cheng on 2017/1/21.
 */
$(function(){

    initModel();
    initAddButton();
    initDeleteButtonEvent();
    changEvent();
    initAddSubmitEvent();
    initSelect();
    initTable();
    initInfoEvent();
    initSearchButton();
    initEditSubmitEvent();
    initAssignSubmitEvent();
    initUploadingSubmitEvent();
    Validator();
    initAllotEvent();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
    });
});

function initModel() {

    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#assignReset").trigger("click");
        $("#uploadingReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#editForm").data("bootstrapValidator").resetForm();
        $("#uploadingForm").data("bootstrapValidator").resetForm();

        $("#assignForm input[name='bulidingId']").val("");
        $("#assignForm input[name='dormitoryId']").val("");
        $("#assignForm").data("bootstrapValidator").resetForm();
        $("#table").bootstrapTable("refresh",{url : "/student/search"});
    })

    $("#assignModal").on("show.bs.modal", function(e){
        initSelectpicker();
    });



    var grade = $(".selectpicker[name='grade']");
    var year = (new Date()).getFullYear();
    year = year ;
    grade.empty();
    grade.append("<option value =''>无</option>");
    for(var i = 0; i < 6; i++){

        grade.append("<option value = " + (year - i) +">" + (year - i)+ "</option>");
    }
    grade.selectpicker('refresh');
    grade.selectpicker('render');

}

function initAddButton() {
    $("#add").click(function(){
        //获取学院信息

        /*$.ajax({
                url : "/department/list",
                type : "get",
                dataType : "json",
                error : function(){
                    layer.alert("获取学院信息失败！", {
                        icon : 5
                    });
                },
                success : function(data){
                    var select = $("select[name='departmentId']");
                    select.empty();
                    $.each(data, function(i, item){
                        if (i == 0){
                            getProfession(item.id);
                        }

                        select.append("<option value = " + item.id + ">" + item.name + "</option>");
                    });
                    select.selectpicker("refresh");
                    select.selectpicker('render');

                }
            });*/



        }
       );
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
        url : "/profession/delete",
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
                $("#table").bootstrapTable('refresh',{ url :"/profession/search"})
            }
            else{
                layer.alert("删除失败！", {
                    icon : 0
                })
            }
        }


    });


}

function changEvent() {

    $(".panel-body select[name='departmentId']").on("change",function(){

        console.log($("select[name='departmentId']").val());

        if($("select[name='departmentId']").val() != 0 ){
            getProfession($(".panel-body select[name='departmentId']").val());
        }


    });

    $(".panel-body select[name='professionId']").on("change",function(){
        var data = professionClassNumber;
        for(var i = 0; i < data.length; i++){
            if(data[i].id == $(".panel-body select[name='professionId']").val()){
                getClassNumber(data[i].classNumber);
            }
        }

    });

    $("#addModal select[name='departmentId']").on("change",function(){

        console.log($("select[name='departmentId']").val());

        getProfession($("#addModal select[name='departmentId']").val());
    });

    $("#addModal select[name='professionId']").on("change",function(){
        var data = professionClassNumber;
        for(var i = 0; i < data.length; i++){
            if(data[i].id == $("#addModal select[name='professionId']").val()){
                getClassNumber(data[i].classNumber);
            }
        }

    });
    
    $("#assignModal select[name='buildingId']").on("change", function () {
        getDormotioryList(".modal.fade select[name='dormitoryId']", $(".modal.fade select[name='buildingId']").val());
        $("#assignForm").data("bootstrapValidator").resetField("dormitoryId", true);
        $("#assignModal input[name='bedNumber']").val("");
        $("#assignModal input[name='studentNumber']").val("");
    });

    $("#assignModal select[name='dormitoryId']").on("change", function(){
        $.ajax({
            url: "/dormitory/get",
            method : "get",
            data : {id: $("#assignModal select[name='dormitoryId']").val()},
            dataType : "json",
            error : function(){
                $("#assignModal input[name='bedNumber']").val("请求数据失败");
                $("#assignModal input[name='studentNumber']").val("请求失败");
            },
            success : function(data){
                console.log(data);
                if(data == undefined){
                    $("#assignModal input[name='bedNumber']").val("请求数据失败");
                    $("#assignModal input[name='studentNumber']").val("请求数据失败");
                }
                else{
                    $("#assignModal input[name='bedNumber']").val(data.bedNumber);
                    $("#assignModal input[name='studentNumber']").val(data.studentNumber);
                }

            }
        });
    })

}

function getProfession(departmentId){
    var profession = $("select[name='professionId']");
    profession.empty();
    $.ajax({
        url : "/profession/list",
        type : "get",
        data : {"departmentId" : departmentId},
        dataType : "json",
        error : function(){
            layer.alert("获取专业信息失败！", {
                icon : 5
            });
        },
        success : function(data){

            console.log(data);
            var result = data.result;
            if(result.length == 0){
                layer.alert("该学院下还没有专业！", {
                    icon : 5
                });
            }

            professionClassNumber = result;

            profession.append("<option value=''>无</option>");
            for (var i = 0; i < result.length; i++){
                profession.append("<option value=" + result[i].id   + ">" + result[i].name + "</option>");

            }
            profession.selectpicker("refresh");
            profession.selectpicker("render");

            getClassNumber(result[0].classNumber);

        }

    })
}

function getClassNumber(ClassNumber) {

    var select = $("select[name='classNumber']");
    select.empty();
    select.append("<option value=''>无</option>")

    for (var i = 1; i <= ClassNumber; i++){
        select.append("<option value=" + i + ">"  + i +"</option>");
    }

    select.selectpicker("refresh");
    select.selectpicker("render");
}

function initAddSubmitEvent() {

    //添加院系信息提交事件
    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/student/add",
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
        url : "/student/search",
        method : "get",
        striped : "true",
        cache : false,
        pagination : true,
        sidePagination : "server",
        pageNumber : 1,
        pageSize : 10,
        pageList : [10, 25, 50, 100],
        showExport: true,
        exportDataType: "selected",
        exportTypes : ['json', 'xml', 'csv', 'txt', 'sql', 'excel', 'doc'],
        queryParams:function(params){
            var temp = {
                pageSize : params.limit,
                currentPage : function(){
                    if(params.limit != 0){
                        return params.offset / params.limit + 1;
                    }
                    return params.offset
                },
                departmentId : $(".panel-body select[name='departmentId']").val(),
                grade : $(".panel-body select[name='grade']").val(),
                professionId : $(".panel-body select[name='professionId']").val(),
                classNumber : $(".panel-body select[name='classNumber']").val(),
                sex : $(".panel-body select[name='sex']").val(),
                dormitoryId : $(".panel-body select[name='dormitoryId']").val(),
                name : $(".panel-body input[name='name']").val(),
                number : $(".panel-body input[name='number']").val()
            }
            return temp;
        },
        columns:[
            {
                checkbox: true,
            }, {
                title : '序号',
                align : "center",
                formatter : function(value, row, index){
                    return index + 1;
                }
            }, {
                title : "学号",
                field : "number",
                align : 'center'
            }, {
                title : "姓名",
                field : "name",
                align : 'center'
            },
                {
                title : "性别",
                field : "sex",
                align : 'center',
                formatter : function(value, row, index){
                    if( value == 1){
                        return "男";
                    }
                    else{
                        return "女";
                    }
                }
            }, {
                title : "年级",
                field : "grade",
                align : 'center'
            },{
                title : "专业",
                field : "professionName",
                align : 'center'
            },{
                title : "班级",
                field : "classNumber",
                align : 'center'
            },{
                title : "电话",
                field : "phone",
                align : 'center',
                formatter : function (value, row, index) {
                    if( 0 == value){
                        return "无";
                    }
                    else{
                        return value;
                    }
                }
            },
            {
                title : "住宿情况",
                field : "dormitoryId",
                align : 'center',
                formatter : function(value, row, index){
                    if( 0 == value){
                        return "未分配";
                    }
                    else{
                        return "已分配";
                    }
                }
            },{
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

    var operate = [
        '<a  href="javascript:void(0)" id="edit" title="编辑">',
        '修改 ',
        '</a>  ',
        '<a  href="javascript:void(0)" id="updatePassword" title="初始密码">',
        '密码 ',
        '</a>'
    ];

    if(row.dormitoryId == 0){
        operate.push('<a  href="javascript:void(0)" id="assign" title="分配宿舍">', '分配 ', '</a>');
        operate.push('<a  href="javascript:void(0)" id="look" title="查看宿舍">', '查看', '</a>');
    }
    else{
        operate.push('<a  href="javascript:void(0)" id="quit" title="退宿">', '退宿 ', '</a>');
        operate.push('<a  href="javascript:void(0)" id="look" title="查看宿舍">', '查看', '</a>');
    }

    return operate.join('');
}

window.operateEvents = {
    'click #updatePassword' : function (e, value, row, index) {
        $.ajax({
            url: "/student/resetPassword",
            type: "get",
            data: {id: row.id},
            /*传递数组必须是true*/
            traditional: true,
            dataType: "json",
            error: function () {
                layer.alert("退宿失败！", {
                    icon: 0
                });
            },
            success: function (data) {

                if (data.message == true) {
                    layer.alert("初始密码成功，密码为该用户的学号！", {
                        icon: 1
                    });
                    $("#table").bootstrapTable('refresh', {url: "/student/search"})
                }
                else {
                    layer.alert("删除失败！", {
                        icon: 0
                    })
                }
            }
        });
    },
    'click #quit' : function(e, value, row, index){
        $.ajax({
            url : "/student/quit",
            method : "get",
            data: {id : row.id},
            dataType : "json",
            error : function(){
                layer.alert("初始密码失败！", {
                    icon: 0
                });
            },
            success : function(data){
                if (data.message == true) {
                    layer.alert("退宿成功", {
                        icon: 1
                    });
                    $("#table").bootstrapTable('refresh', {url: "/student/search"})
                }
                else {
                    layer.alert("退宿失败！", {
                        icon: 0
                    })
                }
            }

        })
    },
    'click #edit' : function(e, value, row, index) {
        $("#editModal").modal("show");
        $("#editModal input[name='id']").val(row.id);
        $("#editModal input[name='profession']").val(row.professionName);
        $("#editModal input[name='grade']").val(row.grade);
        $("#editModal input[name='classNumber']").val(row.classNumber);
        $("#editModal input[name='name']").val(row.name);
        $("#editModal input[name='number']").val(row.number);
        $("#editModal input[name='phone']").val(row.phone);
        $("#editModal input[name='sex']").eq(0).removeAttr("checked");
        $("#editModal input[name='sex']").eq(1).removeAttr("checked");
        if(row.sex == 1){
            $("#editModal input[name='sex']").eq(0).click();
        }
        else{
            $("#editModal input[name='sex']").eq(1).click();
        }

    },
    'click #assign' : function (e, value, row, index) {
        $("#assignModal").modal("show");
        $("#assignModal input[name='studentId']").val(row.id);
    },
    'click #look' : function(e, value, row, index){
        if(row.dormitoryId == 0){
            layer.alert("还未分配宿舍", {
                icon : 0
            });
        }
        else{
            $.ajax({
                url : "/student/getDormitory",
                method : "get",
                data: {id : row.dormitoryId},
                dataType : "json",
                error : function(){
                    layer.alert("初始宿舍信息失败！", {
                        icon: 0
                    });
                },
                success : function(data){
                   $("#lookModal").modal('show');
                    $("#lookForm input[name='buildingNumber']").val(data.buildingNumber);
                    $("#lookForm input[name='dormitoryNumber']").val(data.number);
                    $("#lookForm input[name='bedNumber']").val(data.bedNumber);
                    $("#lookForm input[name='studentNumber']").val(data.studentNumber);
                }
            })
        }

    }
}

function initSelect(){
    $.ajax({
        url : "/department/list",
        type : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取学院信息失败！", {
                icon : 5
            });
        },
        success : function(data){
            var select = $("select[name='departmentId']");
            select.empty();
            select.append("<option value=''>无</option>");
            $.each(data, function(i, item){
                if (i == 0){
                    getProfession(item.id);
                }
                select.append("<option value = " + item.id + ">" + item.name + "</option>");
            });
            select.selectpicker("refresh");
            select.selectpicker('render');

        }
    });

    $("select[name='departmentId']").selectpicker("refresh");
    $("select[name='departmentId']").selectpicker("render");

}

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/student/search");
    });
}

function initEditSubmitEvent(){
    $("#editSubmit").click(function() {
        var options = {
            url : "/student/update",
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
                    $("#table").bootstrapTable("refresh",{url : "/student/search"});
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

function initUploadingSubmitEvent(){
    $("#uploadingSubmit").click(function() {
        console.log("1234");

        /*var options = {
            url : "/test",
            type : 'post',
            data : null,
            error : function(){

            },
            success : function(){

            }
        }*/

        var options = {
            url : "/student/uploading",
            type : "post",
            data : null,
            beforeSubmit : function(){
                layer.load(0, {shade: false});
            },
            error: function () {
                layer.closeAll("loading");
                layer.alert("发送数据失败", {
                    icon : 0
                });
            },
            success : function (data) {
                layer.closeAll("loading");
                console.log(data.message);
                if (data.message == true){
                    layer.alert("提交数据成功！", {
                        icon : 1
                    });
                }
                else{
                    if(data.reason != undefined){
                        layer.alert(data.reason, {
                            icon : 0
                        });
                    }
                    else if(data.firstList != undefined){
                        var message = "excel文件中学号";

                        for(var i = 0; i < data.firstList.length; i++){
                            if(i == (data.firstList.length - 1)){
                                message = message + data.firstList[i] + "重复";
                            }
                            else{
                                message = message + data.firstList[i] + "、";
                            }
                        }
                        layer.alert(message, {
                            icon : 0
                        });
                    }
                    else{
                        var message = "excel文件中学号";

                        for(var i = 0; i < data.twoList.length; i++){
                            if(i == (data.twoList.length - 1)){
                                message = message + data.twoList[i] + "在系统已经存在";
                            }
                            else{
                                message = message + data.twoList[i] + "、"
                            }
                        }
                        layer.alert(message, {
                            icon : 0
                        });
                    }

                }

                $("#uploadingModal").modal("hide");
                $("#table").bootstrapTable("refresh",{url : "/student/search"});

            }
        }
        var form = $("#uploadingForm");
        form.ajaxSubmit(options);


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
                if(selection[i].dormitoryId != 0){
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
                layer.alert("删除前先退宿！", {
                    icon : 5
                });
            }


        }



    });
}

function deleteAjax(data) {
    console.log(data);
    $.ajax({
        url : "/student/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/student/search"});
        }


    });


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

function initAssignSubmitEvent() {

    //添加院系信息提交事件
    $("#assignSubmit").click(function() {
        console.log($("#assignModal input[name='bedNumber']").val());
        console.log($("#assignModal input[name='studentNumber']").val());
        if($("#assignModal input[name='bedNumber']").val() <= $("#assignModal input[name='studentNumber']").val()){
            layer.alert("该宿舍已没有床位！",{
                icon : 5
            });
        }
        else{
            var options = {
                url : "/student/assign",
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

                    $("#assignModal").modal('hide');


                }
            }
            var form = $("#assignForm");
            form.ajaxSubmit(options);
        }


    });
}

function initInfoEvent(){
    $("#info").on("click", function(){

        var index = layer.confirm('用excel文件导入学生信息，导入学生信息是同一个专业的，excel文件的信息格式是年级(必填)、班级(必填)、学号(必填)、' +
            '姓名(必填)、性别(必填)、电话(没有的话就填0)', {
            btn: ['知道了'] //按钮
        }, function(){
            layer.close(index);
            $("#uploadingModal").modal('show');
        });

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
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是数字'
                    },
                    remote : {
                        url : '/student/validateNumber',
                        message : "该编号已经存在",
                        delay  : 1000,
                        type : "post",
                    }
                }
            },
            name : {
                validators : {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                }
            },
            grade : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            departmentId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            professionId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            classNumber : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是数字'
                    }
                }
            },

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
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                    regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                        regexp: /^[0-9]*[1-9][0-9]*$/,
                        message: '只能是数字'
                    },
                    remote : {
                        url : '/student/validateNumber',
                        message : "该编号已经存在",
                        delay  : 1000,
                        type : "post",
                    }
                }
            },
            name : {
                validators : {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    },
                }
            },
            phone : {
                validators : {
                    notEmpty: {/*非空提示*/
                        message: '不能为空'
                    }
                }
            }

        }
    });

    $("#uploadingForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields : {
            departmentId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            professionId : {
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '必须选择'
                        }
                    }
            },
            studentFile : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp : {
                        regexp : /^.*?\.(xls|xlsx)$/,
                        message : '文件格式必须为xls或者xlsx'
                    }
                }
            }
        }
    });

    $("#assignForm").bootstrapValidator({
        message : 'This value is not valid',
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyhicon-refresh'
        },
        fields : {
            buildingId : {
                validators: {
                    notEmpty: {/*非空提示*/
                        message: '必须选择'
                    }
                }
            },
            dormitoryId : {
                validators : {
                    notEmpty : {
                        message : '必须选择'
                    }
                }
            }
        }
    });
}

function initAllotEvent() {
    var selection;
    //是否分配

    $("#allot").click(function(){
        var isDormitory = true;
        var isGrade = true;
        var isProfession = true;
        var id;
        selection  = $.map($("#table").bootstrapTable('getSelections'), function (row) {
            return row;
        });
        if(selection.length == 0){
            layer.alert("请先勾选！", {
                icon : 5
            });
        }
        else{
            for(var i = 0; i < selection.length; i++){
                if(selection[i].dormitoryId != 0){
                    isDormitory = false;
                }
                if(i < (selection.length -1)){
                    if(selection[i].grade != selection[i+1].grade){
                        isGrade = false;
                    }
                    if(selection[i].professionId != selection[i+1].professionId){
                        isProfession = false;
                    }
                }
            }

            if(isDormitory == false){
                layer.alert("选中学生已有分配！", {
                    icon : 5
                });
            }else if(isGrade == false){
                layer.alert("选中学生不是同一年级！", {
                    icon : 5
                });
            }else if(isProfession == false){
                layer.alert("选中学生不是同一专业",{
                    icon : 5
                })
            }else{
                id = $.map($("#table").bootstrapTable('getSelections'), function (row) {
                    return row.id;
                });
                intelligentAllotAjax(id);
            }
        }
    })
}

function intelligentAllotAjax(data) {
    console.log(data);
    $.ajax({
        url : "/student/IntelligentAllot",
        type : "get",
        data : {id : data},
        traditional: true,
        dataType : "json",
        error : function(){
            layer.alert("智能分配失败！", {
                icon : 0
            });
        },
        success : function(data){

            if(data.message == true){
                layer.alert("匹配成功！",{
                    icon : 1
                });

            }
            else{
                if(data.result == 0){
                    layer.alert("匹配失败!", {
                        icon : 0
                    });
                }else if(data.result == 1){
                    layer.alert("匹配失败!匹配不到合适的男生宿舍", {
                        icon : 0
                    });
                }else if(data.result == 2){
                    layer.alert("匹配失败!匹配不到合适的女生宿舍", {
                        icon : 0
                    });
                }else if(data.result == 3){
                    layer.alert("匹配失败!男生宿舍床位不够", {
                        icon : 0
                    });
                }else{
                    layer.alert("匹配失败!女生宿舍床位不够", {
                        icon : 0
                    });
                }

            }
            $("#table").bootstrapTable('refresh',{ url :"/student/search"});
        }


    });


}


