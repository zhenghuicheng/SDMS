/**
 * Created by cheng on 2017/2/6.
 */
$(function(){
    $("#side-menu li").find('a').removeClass("active");
    $("#side-menu li").on("click","a",function(){
        $("#side-menu li").find('a').removeClass("active");
        var a = $(this);
        var url = a.data("url");
        a.addClass("active");
        if (undefined != url){
            $("#iframe").attr("src", url);
        }
        console.log(url);
    });
    initUpdatePassword();
    initModel();
    initAddSubmitEvent();
    Validator();
    initGetData();
    initLookSubmitEvent();
    initLookDormitoryEvent();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#lookForm").data("bootstrapValidator").resetForm();
    });
});

function  initUpdatePassword() {
    $("#updatePassword").click(function(){
        $("#addModal").modal('show');
    })
}

function initModel() {

    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
    })
}

function initAddSubmitEvent() {

    $("#addSubmit").click(function() {

        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/user/updatePassword",
                type : "post",
                data : null,
                error: function () {
                    layer.alert("修改密码失败！", {
                        icon : 0
                    });
                },
                success : function (data) {
                    console.log(data.message);
                    if (data.message == true){
                        layer.alert("修改密码成功！", {
                            icon : 1
                        });
                        $("#addModal").modal("hide");
                    }
                    else{
                        layer.alert("修改密码失败！", {
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
            oldPassword : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    }
                }
            },
            newPassword : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp : {
                        regexp : /^[A-Za-z0-9]+$/,
                        message : '必须是字母或数字'
                    },
                    stringLength : {
                        min : 6,
                        max : 10,
                        message : '密码长度必须在6到10之间'
                    }
                }
            },
            rePassword : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp : {
                        regexp : /^[0-9A-Za-z]*$/,
                        message : '必须是字母或数字'
                    },
                    stringLength : {
                        min : 6,
                        max : 10,
                        message : '密码长度必须在6到10之间'
                    },
                    identical: {//相同
                        field: 'newPassword',
                        message: '两次密码不一致'
                    },
                }
            }
        }
    });

    $("#lookForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            phone : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    }
                }
            }
        }
    });

}

function initGetData() {
    $("#lookData").on("click", function(){
        $("#lookModal").modal("show");
        $.ajax({
            url : '/user/getStudent',
            type : 'get',
            dataType : 'json',
            error : function(){
                $("#lookForm input[name='id']").val("");
                $("#lookForm input[name='jobNumber']").val("");
                $("#lookForm input[name='name']").val("");
                $("#lookForm input[name='phone']").val("");
                layer.alert("获取信息失败！", {
                    icon : 0
                });
            },
            success : function(data){
                if(data == null){
                    $("#lookForm input[name='id']").val("");
                    $("#lookForm input[name='jobNumber']").val("");
                    $("#lookForm input[name='name']").val("");
                    $("#lookForm input[name='phone']").val("");
                    layer.alert("获取信息失败！", {
                        icon : 0
                    });
                }
                else{
                    $("#lookForm input[name='id']").val(data.id);
                    $("#lookForm input[name='number']").val(data.number);
                    $("#lookForm input[name='grade']").val(data.grade);
                    $("#lookForm input[name='profession']").val(data.professionName);
                    $("#lookForm input[name='classNumber']").val(data.classNumber);
                    if(data.sex == 1){
                        $("#lookForm input[name='sex']").val('男');
                    }
                    else{
                        $("#lookForm input[name='sex']").val('女');
                    }
                    $("#lookForm input[name='name']").val(data.name);
                    $("#lookForm input[name='phone']").val(data.phone);
                }
            }
        })
    })
}

function initLookSubmitEvent() {

    $("#lookSubmit").click(function() {
        var bootstrapValidator = $("#lookForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/user/updateStudentPhone",
                type : "post",
                data : null,
                error: function () {
                    layer.alert("修改密码失败！", {
                        icon : 0
                    });
                },
                success : function (data) {
                    console.log(data.message);
                    if (data.message == true){
                        layer.alert("修改信息成功！", {
                            icon : 1
                        });
                        $("#lookModal").modal("hide");
                    }
                    else{
                        layer.alert("修改信息失败！", {
                            icon : 0
                        });
                    }


                }
            }
            var form = $("#lookForm");
            form.ajaxSubmit(options);
        }

    });
}

function initLookDormitoryEvent() {
    $("#lookDormitory").on('click', function(){

       $("#dormitoryForm input[name='dormitoryNumber']").val("");
        $("#dormitoryForm input[name='number']").val("");
        $("#dormitoryForm input[name='studentNumber']").val("");
        $("#dormitoryForm input[name='bedNumber']").val();
        $.ajax({
            url : '/user/getDormitory',
            type : 'get',
            dataType : 'json',
            error : function(){
                layer.alert("获取信息失败！", {
                    icon : 0
                });
            },
            success : function(data){
                if(data.message == true){

                    var dormitory = data.dormitory;
                    $("#dormitoryForm input[name='dormitoryNumber']").val(dormitory.buildingNumber);
                    $("#dormitoryForm input[name='number']").val(dormitory.number);
                    $("#dormitoryForm input[name='studentNumber']").val(dormitory.studentNumber);
                    $("#dormitoryForm input[name='bedNumber']").val(dormitory.bedNumber);
                    $("#dormitoryModal").modal('show');
                }
                else{
                    if(data.error == 0){
                        layer.alert("获取信息失败！", {
                            icon : 0
                        });
                    }
                    else{
                        layer.alert("该学生还未分配宿舍！", {
                            icon : 0
                        });
                    }
                }
            }
        })
    })
}
