<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/20
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>茶点统计</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/table.css" />
    <link rel="stylesheet" href="css/chosen.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css" media="screen">
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/laydate/laydate.js"></script>
    <script type="text/javascript" src="/js/Date.js"></script>
    <script type="text/javascript" src="/js/chosen.js"></script>
    <script src="/js/bootstrap-datetimepicker.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.fr.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://xiazai.jb51.net/201508/yuanma/imageselect.js"></script>
    <style type="text/css">
        #addtImgShow{
            height:200px;
            width:200px;
            border:1px solid #000;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            //查询
            $("#query").click(function () {
                inittable()
            })
            inittable()
            function inittable() {
                $('#DistributeTable').html("")
                $("#tbody").html("");
                var queryNameOruserId=$("#queryNameOruserId").val()
                if(isNaN(queryNameOruserId)){
                    var fullName=queryNameOruserId
                }else{
                    var userId=queryNameOruserId
                }
                $.ajax({
                    url:"getTeaDistribute",
                    type:'get',
                    dataType:"json",
                    data:{
                        userId:userId,
                        fullName:fullName
                    },
                    success:function (data) {
                        if (data.code == 200){
                            var json = data.data
                            console.log(data)
                            var table = $('#DistributeTable');
                            var index = 0;
                            var length = 1;
                            for (var i = 0 ; i< json.length ; i++) {
                                var tr = $('<tr>');
                                if(i-1<0)
                                    tr.append($('<td>').text(json[i].fullName))
                                if((i-1)>=0) {
                                    if(json[i-1].fullName != json[i].fullName){
                                        index = i;
                                        length = 1;
                                        tr.append($('<td>').text(json[i].fullName))
                                    }
                                    else if(json[i-1].fullName == json[i].fullName)
                                    {
                                        length++;
                                        $('#DistributeTable').children('tbody').eq(0).children('tr').eq(index).children('td').eq(0).attr('rowspan',length)
                                        /*console.log($('#DistributeTable').children('tbody').eq(0).children('tr').eq(index).children('td').eq(0).attr('rowspan',length))*/
                                    }
                                }
                                tr.append($('<td>').text(json[i].tName))
                                tr.append($('<td>').append($('<img>').attr('src',json[i].tImg)))
                                tr.append($('<td>').text(json[i].number))
                                table.append(tr);
                            }
                     }else {
                           layer.msg("当前数据为空!")
                        }
                    }
                })
            }

        })

        function loginOut(){
            if(confirm("确定要退出登录吗？")){
                window.location.href="/logout";
            }
        }
    </script>
</head>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点分配</span></div>
    <input type="text" class="" id="queryNameOruserId" placeholder="请输入用户Id或真实姓名">
    <button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search" ></i>&nbsp;查询</button>
    <span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓日报系统</span>
    <a id="home" href="/home" class="glyphicon glyphicon-home"></a>
    <a onclick="loginOut()" class="glyphicon glyphicon-off"></a>
    <a href="/teaRepository">茶点仓库</a>
    <a href="/teaChoose">点餐</a>
    <a href="/teaStatistics">统计</a>
    <div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>选餐人</th>
            <th>茶点</th>
            <th>图片</th>
            <th>个数</th>
        </tr>
        <table id="DistributeTable" cellspacing="0" border="1" style="border-collapse:collapse;width: 100%;height: 500px;">

        </table>
        </thead>
    </table>
    </div>
</body>
</html>
