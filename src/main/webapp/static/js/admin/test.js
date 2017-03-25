/**
 * Created by cheng on 2017/1/10.
 */
$(function(){

    initLayer();
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
    getLog();
    Validator();
    initGetData();
    initLookSubmitEvent();
    initBackup();
    initUploadingSubmitEvent();
    $("#addReset").on("click", function(){
        $("#addForm").data("bootstrapValidator").resetForm();

    })
});

function  initUpdatePassword() {
    $("#updatePassword").click(function(){
        console.log("aaabccc");
        $("#addModal").modal('show');
    })
}

function initModel() {

    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#addReset").trigger("click");
        $("#addForm").data("bootstrapValidator").resetForm();
        $("#lookForm").data("bootstrapValidator").resetForm();
    })

}

function initAddSubmitEvent() {

    $("#addSubmit").click(function() {
        var bootstrapValidator = $("#addForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/admin/updatePassword",
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

function initLayer() {
    var width = window.document.body.offsetWidth - 300;
    var height = window.screen.availHeight - 260;

    console.log(width + "   " + height);

    layer.open({
        type: 1
        ,time: 8000
        ,area : ['300','200']
        ,offset: [height, width]//具体配置参考：offset参数项
        ,content: '<div style="padding: 10px 60px;" id="context"></div>'
        ,shade: 0 //不显示遮罩
        ,yes: function(){
            layer.closeAll();
        }
    });

}

function getLog() {
    $.ajax({
            url: "/admin/log",
            type: "post",
            contentType: "application/x-www-form-urlencoded",
            data:{adminId:$("#log").data("id")},
            dataType:"json",
            success:function (data) {
                if (data.data != "error"){
                    var context = data.data;
                    $("#context").append("欢迎您，管理员！</br>上次登录时间：</br>" + context.loginTime.substring(0,19) + "</br>上次登录IP: </br>" + context.ip);
                }
            }
        }
    )
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

    $("#uploadingForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields:{
            backupFile : {
                validators : {
                    notEmpty : {
                        message : '不能为空'
                    },
                    regexp : {
                        regexp : /^.*?\.(sql)$/,
                        message : '文件格式必须为sql'
                    }
                }
            }
        }
    });

}

function  initGetData() {
    $("#lookData").on("click", function(){
        $("#lookModal").modal("show");
        $.ajax({
            url : '/admin/getData',
            type : 'get',
            dataType : 'json',
            error : function(){
                $("#lookForm input[name='id']").val("");
                $("#lookForm input[name='jobNumber']").val("");
                $("#lookForm input[name='name']").val("");
                $("#lookForm input[name='phone']").val("");
                $("#lookForm input[name='role']").val("");
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
                    $("#lookForm input[name='role']").val("");
                    layer.alert("获取信息失败！", {
                        icon : 0
                    });
                }
                else{
                    $("#lookForm input[name='id']").val(data.id);
                    $("#lookForm input[name='jobNumber']").val(data.jobNumber);
                    $("#lookForm input[name='name']").val(data.name);
                    $("#lookForm input[name='phone']").val(data.phone);
                    if(data.role == 0){
                        $("#lookForm input[name='role']").val("系统管理员");
                    }
                    else{
                        $("#lookForm input[name='role']").val("普通管理员");
                    }
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
                url : "/admin/updatePhone",
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

function initBackup() {
    $("#backup").on("click", function(){
        $("#uploadingModal").modal('show');
    })
}

function initUploadingSubmitEvent(){
    $("#uploadingSubmit").click(function() {

        var bootstrapValidator = $("#uploadingForm").data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            var options = {
                url : "/dataBase/backup",
                type : "post",
                data : null,
                beforeSubmit : function(){
                    layer.load(0, {shade: false});
                },
                error: function () {
                    layer.closeAll("loading");
                    layer.alert("还原数据失败！", {
                        icon : 0
                    });
                },
                success : function (data) {
                    layer.closeAll("loading");
                    if (data.message == true){
                        layer.alert("还原数据成功！", {
                            icon : 1
                        });
                        $("#uploadingModal").modal("hide");
                    }
                    else{
                        layer.alert("还原数据失败！", {
                            icon : 0
                        });
                    }


                }
            }
            var form = $("#uploadingForm");
            form.ajaxSubmit(options);
        }



    });
}

