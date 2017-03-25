<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2016/12/26
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>
    <link rel="stylesheet" href="${cssPath}/base/pintuer.css">
    <link rel="stylesheet" href="${cssPath}/base/admin.css">
    <script src="${jsPath}/base/base.js"></script>
    <script type="text/javascript">
        if(top.location!=self.location){
            top.location=self.location;
        }
        var message = "${message}";
        if(message != null &&  message.length > 0){
            alert(message);
        }
    </script>
</head>
<body>
<div class="bg"></div>

<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <h1 align="center">学生宿舍管理系统</h1>
            <div class="media media-y margin-big-bottom">
            </div>
            <form action="/login/login" method="post">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>用户登录</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="name" placeholder="登录账号" data-validate="required:请填写账号" />
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="password" placeholder="登录密码" data-validate="required:请填写密码" />
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input input-big" name="random" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                                <img src="/login/verify" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">
                            </div>
                        </div>
                        <div class="form-group" style="top:3px">
                            <div style="float: left;left: 2px;"><label><input type="radio" name="role" value="0" checked="checked">学生</label></div>
                            <div style="float: right;right: 2px;"><label style="align-content: center"><input type="radio" name="role" value="1">管理员</label></div>
                        </div>
                    </div>
                    <div style="padding:30px;"><input type="submit" class="button button-block bg-main text-big input-big" value="登录"></div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
