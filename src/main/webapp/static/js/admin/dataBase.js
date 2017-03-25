/**
 * Created by cheng on 2017/3/7.
 */
$(function(){
    saveButtonEvent();
})

function saveButtonEvent(){
   $("#save").click(function () {
       $.ajax({
           url : "/dataBase/save",
           type : 'get',
           error : function () {

           },
           success : function(){

           }
       })
   })
}