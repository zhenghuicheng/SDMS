/**
 * Created by cheng on 2017/3/6.
 */
$(function () {
    initTable();
    getBuildingData();
    initChange();
    initSearchButton();
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/dormitory/statistics/search",
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
                floor : $(".panel-body select[name='floor']").val(),
                dormitoryId : $(".panel-body select[name='number']").val(),
                type : $(".panel-body select[name='type']").val(),
                studentNumber : $(".panel-body input[name='studentNumber']").val()
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
                title : "层数",
                field : "floor",
                align : "center"
            },
            {
                title : "床位",
                field : "bedNumber",
                align : 'center'
            },
            {
                title : "入住人数",
                field : "studentNumber",
                align : 'center'
            }
        ]
    })
}

function getBuildingData() {
    $.ajax({
        url : "/building/list",
        method : "get",
        dataType : "json",
        error : function(){
            layer.alert("获取栋数信息失败！", {
                icon : 0
            });
        },
        success : function (data) {
            buildingData = data;
            var select = $(".selectpicker[name='buildingId']");
            select.empty();
            select.append("<option value=''>无</option>");
            for(var i = 0; i < data.length; i++){
                select.append("<option value = '" + data[i].id + "'>" + data[i].number + "</option>");
            }
            select.selectpicker("refresh");
            select.selectpicker('render');
        }
    })
}

function initChange() {
    $("#addModal select[name='buildingId']").on("change",function(){

        console.log($("select[name='buildingId']").val());

        $("#addForm").data("bootstrapValidator").resetField("floor", true);
        $("#addForm").data("bootstrapValidator").resetField("number", true);

        for(var i = 0; i < buildingData.length; i++){
            if($("#addForm select[name='buildingId']").val() == buildingData[i].id){
                console.log(buildingData[i].floor);
                getFloor(buildingData[i].floor);
            }
        }
    });


}

function getFloor(data) {

    var select = $(".selectpicker[name='floor']");
    select.empty();
    select.append("<option value=''>无</option>");
    for(var i = 1; i <= data; i++ ){
        select.append("<option value='" + i + "'>" + i + "</option>");
    }
    select.selectpicker("refresh");
    select.selectpicker('render');
}

function initSearchButton() {
    $("#search").on("click", function(){

       if($(".panel-body select[name='model']").val() == "table"){
            $("#table").bootstrapTable("refresh", "/dormitory/statistics/search");
        }
        else{

            var bedData = new Array();
            var studentData = new Array();
            var xAxisData = new Array();
            var title;
           $.ajax({
               url : '/dormitory/statistics/search',
               type : 'get',
               data : {
                   currentPage : 1,
                   pageSize :1000,
                   buildingId :  $(".panel-body select[name='buildingId']").val(),
                   type : $(".panel-body select[name='type']").val(),
                   studentNumber :$(".panel-body input[name='studentNumber']").val()
               },
               dataType : 'json',
               error : function(){
                   layer.alert("获取数据失败！", {
                       icon: 0
                   });
               },
               success :function(data){


                   if(data == null || data.rows.length == 0){
                       layer.alert("没有数据！", {
                           icon: 0
                       });
                   }
                   else{
                       if(data.rows[0].hasOwnProperty('floor')){
                           title = data.rows[0].buildingNumber + "栋宿舍楼层数统计";
                           for(var i = 0; i < data.rows.length; i++) {
                               xAxisData[i] = data.rows[i].floor + "层";
                               bedData[i] = data.rows[i].bedNumber;
                               studentData[i] = data.rows[i].studentNumber;
                           }
                       }
                       else{
                           title = "宿舍栋数统计";
                           for(var i = 0; i < data.rows.length; i++) {
                               xAxisData[i] = data.rows[i].buildingNumber + "栋";
                               bedData[i] = data.rows[i].bedNumber;
                               studentData[i] = data.rows[i].studentNumber;
                           }
                       }


                       layer.open({
                           type: 1,
                           skin: 'layui-layer-rim', //加上边框
                           area: ['650px', '500px'], //宽高
                           content: '<div id="bar" style="width: 600px;height: 430px" align="center"></div>'
                       });
                       var option = {
                           title : {
                               text: title,
                           },
                           tooltip : {
                               trigger: 'axis'
                           },
                           legend: {
                               data:['床位','入住人数']
                           },
                           toolbox: {
                               show : true,
                               feature : {
                                   mark : {show: true},
                                   dataView : {show: true, readOnly: false},
                                   magicType : {show: true, type: ['line', 'bar']},
                                   restore : {show: true},
                                   saveAsImage : {show: true}
                               }
                           },
                           calculable : true,
                           xAxis : [
                               {
                                   type : 'category',
                                   data : xAxisData
                               }
                           ],
                           yAxis : [
                               {
                                   type : 'value'
                               }
                           ],
                           series : [
                               {
                                   name:'床位',
                                   type:'bar',
                                   data: bedData,
                               },
                               {
                                   name:'入住人数',
                                   type:'bar',
                                   data:studentData,

                               }
                           ]
                       };
                       var chart = echarts.init(document.getElementById('bar'), 'macarons');
                       chart.setOption(option);
                   }

               }
           })

           /*layer.open({
               type: 1,
               skin: 'layui-layer-rim', //加上边框
               area: ['650px', '500px'], //宽高
               content: '<div id="bar" style="width: 600px;height: 430px" align="center"></div>'
           });
            var option = {
                title : {
                    text: '某地区蒸发量和降水量',
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['床位','入住人数']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : xAxisData
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'床位',
                        type:'bar',
                        data: bedData,
                    },
                    {
                        name:'入住人数',
                        type:'bar',
                        data:studentData,

                    }
                ]
            };
            var chart = echarts.init(document.getElementById('bar'), 'macarons');
            chart.setOption(option);*/
        }


    })
}

