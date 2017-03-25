/**
 * Created by cheng on 2017/2/3.
 */
$(function(){

    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});

    initTable();
    initSearchButton();

})


function initTable(){
    $("#table").bootstrapTable({
        url : "/user/visitRecordSearch",
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
                visitor : $(".panel-body input[name='visitor']").val(),
                visitedName : $(".panel-body input[name='visitedName']").val()
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
                title : "到访时间",
                field : "startTime",
                align : "center"
            },
            {
                title : "离开时间",
                field : "endTime",
                align : 'center'
            },
            {
                title : "来访者",
                field : "visitor",
                align : 'center'
            },
            {
                title : "来访身份证号",
                field : "visitorNumber",
                align : 'center'
            },
            {
                title : "被访问者",
                field : "visitedName",
                align : 'center'
            }
        ]
    })
}


function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/user/visitRecordSearch");
    });
}


