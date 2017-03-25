/**
 * Created by cheng on 2017/1/22.
 */
$(function(){
    initTable();
})

function initTable(){
    $("#table").bootstrapTable({
        url : "/publicNote/search",
        method : "get",
        striped : "true",
        cache : false,
        pagination : true,
        cardView: true,
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
                }
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
                title : "标题",
                field : "title",
                align : 'center'
            },
            {
                title : "发布者",
                field : "adminName",
                align : 'center'
            },
            {
                title : "发布时间",
                field : "publicTime",
                align : "center"
            },
            {
                title : "操作",
                field : 'operate',
                align : "center",
                formatter : operateFormatter,
                events : operateEvents
            }
        ]
    })
}

function operateFormatter(value, row, index) {
    return [
        '<a  href="javascript:void(0)" id="detail" title="查看">',
        '详情',
        '</a>  '
    ].join('');
}

window.operateEvents = {
    'click #detail' :function (e, value, row, index) {
        layer.msg(row.context, {
            time: 20000, //20s后自动关闭
            btn: ['知道了']
        });
    },
    'click #edit': function (e, value, row, index) {
        //var department = JSON.stringify(row);
        $('#editModal').modal('show');
        $("#editModal input[name='name']").val(row.name);
        $("#editModal input[name='number']").val(row.number);
        $("#editModal input[name='classNumber']").val(row.classNumber);
        $("#editModal input[name='remark']").val(row.remark);
        $("#editModal input[name='id']").val(row.id);
        console.log(row.departmentId);

        var select = $(".selectpicker");

        $.ajax({
            url : "/department/list",
            type : "get",
            dataType : "json",
            error : function(){
                layer.alert("获取学院信息失败！", {
                    icon : 5
                });
            },
            success : function(data){
                select.empty();
                $.each(data, function(i, item){
                    select.append("<option value = " + item.id + ">" + item.name + "</option>");
                });
                $(".selectpicker").val(row.departmentId);
                $(".selectpicker").selectpicker("refresh");
                $('.selectpicker').selectpicker('render');
            }
        })

        $('.selectpicker').find("option[value="+ 13 +"]").attr("selected",true);
        $('.selectpicker').selectpicker('refresh');
        $('.selectpicker').selectpicker('render');

    },
    'click #remove': function (e, value, row, index) {

        layer.confirm('您确认删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            var data = new Array();
            data[0] = row.id;
            deleteAjax(data);
        }, function(){

        });
    }
};