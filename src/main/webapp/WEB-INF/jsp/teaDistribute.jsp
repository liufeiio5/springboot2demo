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
    <link rel="stylesheet" href="css/chosen_11.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css" media="screen">
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.bootstrap-dropdown-hover.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/laydate/laydate.js"></script>
    <script type="text/javascript" src="/js/Date.js"></script>
    <script type="text/javascript" src="/js/chosen_11.js"></script>
    <script src="/js/bootstrap-datetimepicker.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.fr.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://xiazai.jb51.net/201508/yuanma/imageselect.js"></script>
    <script type="text/javascript" src="/js/fq.js"></script>
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

        .tcen tr th{
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
                                    else if(json[i-1].fullName == json[i].fullName){
                                        length++;
                                        $('#DistributeTable').children('tr').eq(index).children('td').eq(0).attr('rowspan',length)
                                        /*console.log($('#DistributeTable').children('tbody').eq(0).children('tr').eq(index).children('td').eq(0).attr('rowspan',length))*/
                                    }
                                }
                                tr.append($('<td>').text(json[i].tName))
                                tr.append($('<td>').append($('<div>').addClass('timgs').append($('<div>').addClass('timgs-item').append($('<img>').addClass('timg').attr('src', json[i].tImg).attr('bigUrl', json[i].tImg)))))
                                tr.append($('<td>').text(json[i].number))
                                table.append(tr);
                            }
                            //图片放大
                            $(".timgs .timgs-item img").hover(function () {
                                var bigUrl = $(this).attr("bigUrl");
                                $(this).parents(".timgs-item").append("<div id='pic'><img src='" + bigUrl + "' id='pic1'></div>");
                                $(".timgs .timgs-item img").mousemove(function (e) {
                                    var wH = document.documentElement.clientHeight
                                    var wW = document.documentElement.clientWidth
                                    var imgW = $("#pic1").width()
                                    var imgH = $("#pic1").height()
                                    var cssArr = {
                                        "top": "",
                                        "left": "",
                                        "bottom": "",
                                        "right": ""
                                    }

                                    if (e.clientX + imgW > wW) {
                                        if (wW - e.clientX < imgW) {
                                            cssArr.left = (e.clientX - imgW - 10) + "px";
                                            ;
                                        } else {
                                            cssArr.right = 0;
                                        }
                                    } else {
                                        cssArr.left = (e.clientX + 10) + "px";
                                    }
                                    if (e.clientY + imgH > wH) {
                                        cssArr.bottom = 0;
                                    } else {
                                        cssArr.top = (e.clientY - 160) + "px";
                                    }
                                    console.log($("#pic1").height(), wH)
                                    console.log(cssArr)
                                    $("#pic").css(cssArr).fadeIn("fast");
                                });
                            }, function () {
                                $("#pic").remove();
                            });
                            getUser()
                     }else {
                            getUser()
                           layer.msg("当前数据为空, 还未点餐!")
                        }
                    }
                })
            }

            function getUser() {
                $.ajax({
                    url: '/getUser',
                    dataType: 'json',
                    success: function (data) {
                        var str;
                        var json = data.data
                        $("#queryNameOruserId").append('<option  value="">请选择用户</option>')
                        for (var i in json) {
                            str = '<option  value="' + json[i].id + '">' + json[i].fullName + '</option>';
                            $("#queryNameOruserId").append(str)
                        }
                        $("#queryNameOruserId").trigger("liszt:updated");
                        $("#queryNameOruserId").chosen({
                            no_results_text:'未找到',
                        });
                    }
                })
            }

        })

    </script>
</head>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点分配</span></div>
    &nbsp;&nbsp;&nbsp;
    <select style="width:160px;" id="queryNameOruserId"></select>
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
    <a href="/teaStatistics">统计</a>
    <div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;" class="tcen">
        <tr>
            <th>选餐人</th>
            <th>茶点</th>
            <th>图片</th>
            <th>个数</th>
        </tr>
        </thead>

        <tbody id="DistributeTable" cellspacing="0" border="1" style="border-collapse:collapse;width: 100%;height: 500px;" class="cen">

        </tbody>
    </table>
    </div>
</body>
</html>