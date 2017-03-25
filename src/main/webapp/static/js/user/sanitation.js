/**
 * Created by cheng on 2017/2/2.
 */
$(function(){
    $(".form-control[name='startTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});
    $(".form-control[name='endTime']").datetimepicker({format: 'yyyy-mm-dd',language:'zh-CN',minView: "month"});
    initTable();
    initSearchButton();
    initDegree();
})


function initTable(){
    $("#table").bootstrapTable({
        url : "/user/sanitationSearch",
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
                degree : $(".panel-body select[name='degree']").val()
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
                title : "卫生情况",
                field : "degree",
                align : "center",
                formatter : function(value, row, index){
                    if(value == 1){
                        return "优";
                    }
                    else if(value == 2){
                        return "良";
                    }
                    else if(value == 3){
                        return "中";
                    }
                    else{
                        return "差";
                    }
                }
            }
        ]
    })
}

function initSearchButton() {
    $("#search").click(function(){
        $("#table").bootstrapTable("refresh","/user/sanitationSearch");
    });
}

function initDegree() {
    var degreeSelect = $(".selectpicker[name='degree']");
    degreeSelect.empty();
    degreeSelect.append("<option value=''>无</option>");
    var degree =  new Array();
    degree[1] = '优';
    degree[2] = '良';
    degree[3] = '中';
    degree[4] = '差';
    for(var i = 1; i < 5; i++){
        degreeSelect.append("<option value = " + i + ">" + degree[i] + "</option>");
    }
    degreeSelect.selectpicker("refresh");
    degreeSelect.selectpicker('render');
}
