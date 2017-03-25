<%--
  Created by IntelliJ IDEA.
  User: cheng
  Date: 2017/1/3
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/base.jsp"%>
<html>
<head>
    <title>Title</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
</head>
<body>
<select class="js-example-basic-single" id="12">
    <option value="AL">数学与信息学院</option>
    <option value="1">数学与信息学院</option>
    <option value="3223">数学与信息学院</option>
    <option value="3r32r23">数学与信息学院</option>
    <option value="WY">数学与信息学院</option>
</select>
<script>

    $("#12").select2({
        tags: "true",
        placeholder: "Select an option",
        maximumSelectionLength : 1,
        allowClear: true
    });
    //BindSelect("12","/department/list");

    function BindSelect(ctrlName, url) {
        var control = $('#' + ctrlName);
        //设置Select2的处理
        control.select2({
            allowClear: true,
            //formatResult: formatResult,
            //formatSelection: formatSelection,
            escapeMarkup: function (m) {
                return m;
            }
        });

        //绑定Ajax的内容
        $.getJSON(url, function (data) {
            control.empty();//清空下拉框
            $.each(data, function (i, item) {
                control.append("<option value='" + item.id + "'>&nbsp;" + item.name + "</option>");
            });
        });
    }
</script>
</body>
</html>
