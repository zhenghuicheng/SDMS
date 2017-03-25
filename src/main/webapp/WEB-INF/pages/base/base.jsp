<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2016/12/28
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%--导入jstl标签--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<%-- 设置页面全局变量 --%>
<c:set var="domain" value="http://127.0.0.1:8080"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="basePath" value="${domain}${ctx}"/>

<c:set var="cdnDomain" value="${domain}"/>
<c:set var="cdnPath" value="${basePath}/static"/>

<c:set var="jsPath" value="${cdnPath}/js"/>
<c:set var="cssPath" value="${cdnPath}/css"/>
<c:set var="imagesPath" value="${cdnPath}/images"/>
<c:set var="uilibPath" value="${cdnPath}/uilib"/>

<%--导入基本的js文件--%>
<script src="${jsPath}/base/jquery.js"></script><script src="${jsPath}/base/jquery.js"></script>

<script src="${jsPath}/base/bootstrap.js"></script>
<script src="${jsPath}/base/bootstrap-table.js"></script>
<script src="${jsPath}/base/bootstrap-table-zh-CN.js"></script>
<script src="${jsPath}/base/bootstrap-datetimepicker.js"></script>
<script src="${jsPath}/base/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${uilibPath}/layui/layui.js"></script>







