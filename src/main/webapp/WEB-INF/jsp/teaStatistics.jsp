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
    <script type="text/javascript" src="/js/jquery.bootstrap-dropdown-hover.js"></script>
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
        .timg{
            width: 90px;
            height: 90px;
        }

         th{
            text-align: center !important;
        }

        .cen tr td{
            text-align: center;
            vertical-align: middle !important;
        }

        img {
            cursor: pointer;
        }

        #pic {
            position: fixed;
            display: none;
        }

        #pic1 {
            width: 300px;
            height: auto;
            border-radius: 5px;
            -webkit-box-shadow: 5px 5px 5px 5px hsla(0, 0%, 5%, 1.00);
            box-shadow: 5px 5px 5px 0px hsla(0, 0%, 5%, 0.3);
        }
    </style>
    <script type="text/javascript">
        $(function () {

            $.fn.bootstrapDropdownHover();

            laydate.render({elem: '#chooseDate'});
            //查询
            $("#query").click(function () {
                inittable()
            })
            inittable()
            function inittable() {
                $("#tbody").html("");
                var chooseDate=$("#chooseDate").val().replace('-', '').replace('-', '').trim()
                $.ajax({
                    url:"getTeatatistics",
                    type:'get',
                    dataType:"json",
                    data:{
                        date:chooseDate,
                        tName:$("#tea_tName").val().trim()
                    },
                    success:function (data) {
                        if (data.code == 200) {
                            var json = data.data
                            var numberAll=0
                            var moneyAll=0
                            for (var i in json) {
                                var tr = $('<tr>');
                                tr.append($('<td>').html(json[i].id))
                                tr.append($('<td>').html(json[i].catName))
                                tr.append($('<td>').html(json[i].tName))
                                tr.append($('<td>').append($('<img>').attr('width','154px').attr('height','136px').attr('src', json[i].tImg)))
                                tr.append($('<td>').html(json[i].price))
                                tr.append($('<td>').html(json[i].number))
                                numberAll=numberAll+json[i].number
                                tr.append($('<td>').html(json[i].money))
                                moneyAll=moneyAll+json[i].money
                                $("#tbody").append(tr);
                            }
                            var zhongji = $("#zhongji").html("总量:"+numberAll).css('margin-right','100px')
                            var zhongji2 = $("#zhongji2").html("总RMB:"+'<font color="red">'+moneyAll+'元'+'</font>')
                            $('#zhongji1').append(zhongji).append(zhongji2).css('text-align','right').css('margin-right','100px').css('font-size','20px')
                    }else {
                            layer.msg("当前数据为空")
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
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点统计</span></div>
    <input type="text" id="chooseDate" name="user_date" style="width:130px;margin-left: 10px;" class="layui-input" placeholder="请选择统计日期"/>
    <input type="text" id="tea_tName" name="user_date" style="width:130px;margin-left: 10px;" class="layui-input" placeholder="请选择茶点"/>
    <button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search" ></i>&nbsp;查询</button>
    <div class="dropdown" style="float: right;margin-right:80px;margin-top: 20px;cursor:pointer;">
        <p class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            <img style="width: 40px;border-radius:50px;border: 1px solid #999999;margin-right: 10px;" src="/images/touxiang.jpg" />
            ${sessionUser.fullName}
            <span class="caret"></span>
        </p>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
            <li>
                <a href="/home">返回首页</a>
            </li>
            <li role="presentation">
                <a onclick="loginOut()">退出登录</a>
            </li>
        </ul>
    </div>
    <a href="/teaRepository">茶点仓库</a>
    <a href="/teaChoose">选餐</a>
    <a href="/teaDistribute">分发</a>
    <div>
    <table class="table table-bordered" id="table-bordered" class="tcen">
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
        <tbody id="tbody" class="cen">

        </tbody>
    </table>
    <div id="zhongji1">
        <span id="zhongji"></span>
        <span id="zhongji2"></span>
    </div>
    </div>
</body>
</html>
