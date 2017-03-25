/**
 * Created by cheng on 2017/1/31.
 */
$(function(){
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});

    initTable();
    initSearchButton();
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/user/waterRecordSearch",
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
                startTime : $(".panel-body input[name='startTime']").val(),
                endTime : $(".panel-body input[name='endTime']").val(),
                pay : $(".panel-body select[name='pay']").val()
            }
            return temp;
        },
        columns:[
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
                title : "水费/元",
                field : "waterRate",
                align : 'center'
            },
            {
                title : "缴费情况",
                field : "pay",
                align : "center",
                formatter : function(value, row, index){
                    if(value == 0){
                        return "未缴费";
                    }
                    else{
                        return "已缴费";
                    }
                }
            }
        ]
    })
}

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/user/waterRecordSearch");
    });
}



