/**
 * Created by cheng on 2017/1/13.
 */
$(function(){
    initTable();
    $("#startTime").datetimepicker({format: 'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});
    $("#endTime").datetimepicker({format:'yyyy-mm-dd hh:ii:ss',language:'zh-CN'});

    $("#search").click(function(){

        if ($("#startTime").val() != "" && $("#endTime").val() != ""){
            if($("#startTime").val() > $("#endTime").val()){
                layer.alert("结束时间不能小于开始时间！", {
                    icon : 0
                })
            }
            else{
                $("#table").bootstrapTable("refresh","/log/search");
            }
        }
        else{

            $("#table").bootstrapTable("refresh","/log/search");
        }

    })
});

function initTable(){
    $("#table").bootstrapTable({
        url : "/log/search",
        method : "get",
        striped : "true",
        cache : false,
        pagination : true,
        sidePagination : "server",
        clickToSelect: true,
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
                adminId : $("#table").data("id"),
                startTime : $("#startTime").val(),
                endTime : $("#endTime").val()
            }
            return temp;
        },
        columns:[
            {
                title : '序号',
                align : "center",
                formatter : function(value, row, index){
                    return index + 1;
                }
            },
            {
                title : "IP",
                field : "ip",
                align : "center"
            },
            {
                title : "登录时间",
                field : "loginTime",
                align : "center",
                formatter : function (value, row, index) {
                    return value.substring(0, 19);
                }
            }
        ]
    })
}

