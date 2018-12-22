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
                $("#tbody").html("");
                $.ajax({
                    url:"getTeaChoose",
                    type:'get',
                    dataType:"json",
                    data:{
                        userId:${sessionUser.id}
                    },
                    success:function (data) {
                        if (data.code == 200) {
                            var json = data.data
                            //余额
                            var TeaBalance = 10;
                            for (var i in json) {
                                var tr = $('<tr>');
                                var id = json[i].id

                                $("#tbody").append(tr);
                            }

                    }else {
                            alert(111)
                        }
                    }
                })
            }

        })

        function Close() {
            $("#updtImgShow").empty()
            $("#looktImg").empty()
        }

        document.onkeyup = function (e){
            e = e || window.event;
            var code = e.which || e.keyCode;
            if (code == 27){
                Close()()
            }
        }
        //校验是否超过10 茶币
        function isBeOutTenMoney(teaPrice){
            var flag="true"
            $.ajax({
                url:"/isBeOutTenMoney",
                dataType:"json",
                async:false,
                data:{
                    userId:${sessionUser.id},
                    teaPrice:teaPrice
                },
                success:function(data) {
                    if(data.message=="out"){
                        layer.msg('您的茶币不足!', {
                            icon: 1,
                            time: 1000
                        });
                        flag="false";
                    }else{
                        $("#TeaBalance").html((10-parseInt(data.message))+"茶币")
                    }
                }
            })
            if(flag=="false"){
                ajax().abort;
            }
        }
        //校验
        function addcheck() {
            if ($("#addCatName").val().trim() == null || $("#addCatName").val().trim() == '') {
                layer.msg("类别不能为空!");
                ajax().abort;
            }
        }

        function loginOut(){
            if(confirm("确定要退出登录吗？")){
                window.location.href="/logout";
            }
        }

     /*   $("#demo").click(function () {
            $.ajax({
                url:"/getTeaRepository",
                type:'get',
                dataType:"json",
                data:{
                },
                success:function (data) {
                    var json=data.data
                    for (var i in json){
                        var str='<option data-icon="'+json[i].tImag+'">json[i].tName</option>'
                        $("#demo").append(str)
                    }
                }
            })
        })*/
    </script>
</head>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点统计</span></div>
    <input type="text" class="" id="querytName" placeholder="请输入茶点名">
    <button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search" ></i>&nbsp;查询</button>
    <button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
    <span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓日报系统</span>
    <a id="home" href="/home" class="glyphicon glyphicon-home"></a>
    <a onclick="loginOut()" class="glyphicon glyphicon-off"></a>
    <div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>茶点id</th>
            <th>品类</th>
            <th>茶点名</th>
            <th>图片</th>
            <th>单价</th>
            <th>总数</th>
            <th>RMB</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
    </div>
</body>
</html>
