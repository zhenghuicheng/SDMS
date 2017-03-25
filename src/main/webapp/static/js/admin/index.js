$(function(){
    getLog();
    initUrl();
});

function getLog() {
    $.ajax({
            url: "/admin/log",
            type: "post",
            contentType: "application/x-www-form-urlencoded",
            data:{adminId:$("#log").data("id")},
            dataType:"json",
            success:function (data) {
                if (data.data != "error"){
                    var context = data.data;
                    $("#log").append("上次时间：" + context.loginTime.substring(0,19) + " IP: " + context.ip);
                }
            }
        }
    )
}

function initUrl() {

    $("#myleft li:not(.dropdown)").on("click","a",function(){
        var a = $(this);
        var url = a.data("url");
        console.log(url);
        $("#context").attr("src",url);
    })

}
