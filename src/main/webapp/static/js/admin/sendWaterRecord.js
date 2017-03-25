/**
 * Created by cheng on 2017/2/6.
 */
$(function(){
    initModel();
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    initChangeEvent();
    initTable();
    initDeleteButtonEvent();
    initSelectpicker();
    initSearchButton();
})

function initModel(){
    $(".modal.fade").on("hide.bs.modal", function(e){
        $("#table").bootstrapTable("refresh",{url : "/sendWaterRecord/search"});
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

function initChangeEvent() {
    $(".panel-body select[name='buildingId']").on("change",function(){
        getDormotioryList(".panel-body select[name='dormitoryId']", $(".panel-body select[name='buildingId']").val());
    })
    $(".modal.fade select[name='buildingId']").on("change",function(){
        console.log("我变了");
        getDormotioryList(".modal.fade select[name='dormitoryId']", $(".modal.fade select[name='buildingId']").val());
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

function initTable(){
    $("#table").bootstrapTable({
        url : "/sendWaterRecord/search",
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
                name : $(".panel-body input[name='name']").val(),
                isSend : $(".panel-body select[name='isSend']").val()
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
                title : "申请时间",
                field : "time",
                align : "center"
            },
            {
                title : "申请者",
                field : "name",
                align : 'center'
            },
            {
                title : "桶数",
                field : "number",
                align : "center"
            },
            {
                title : "送达情况",
                field : "isSend",
                align : 'center',
                formatter : function(values, row, index){
                    if(values == 0){
                        return "未送";
                    }
                    else{
                        return "已送";
                    }
                }
            }
        ]
    })
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
        url : "/sendWaterRecord/delete",
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
            $("#table").bootstrapTable('refresh',{ url :"/sendWaterRecord/search"});
        }


    });


}

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/electricRecord/search");
    });
}