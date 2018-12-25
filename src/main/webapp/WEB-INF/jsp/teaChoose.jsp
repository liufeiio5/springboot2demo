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
    <title>茶点选餐</title>
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
            laydate.render({elem: '#chooseDate'});

            $.ajax({
                url: "/getTeaRepository",
                type: 'get',
                dataType: "json",
                data: {
                },
                success: function (data) {
                    var json = data.catNameList
                    $("#addCatName").append('<option value="">请选择</option>')
                    for (var i in json) {
                        var str = '<option  class="assisManItem" value="' + json[i].catName + '"  >' + json[i].catName + '</option>';
                        $("#addCatName").append(str)
                    }
                    $("#addCatName").trigger("liszt:updated");
                    $("#addCatName").chosen({
                        no_results_text:'未找到',
                    });
                }
            })

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
                        userId:${sessionUser.id},
                        date:$("#chooseDate").val().replace('-', '').replace('-', '').trim()
                    },
                    success:function (data) {
                        if (data.code == 200) {
                            var json = data.data
                            //余额
                            var TeaBalance = 10;
                            for (var i in json) {
                                var tr = $('<tr>');
                                var id = json[i].id
                                tr.append($('<td>').html(id))
                                tr.append($('<td>').html(json[i].date))
                                tr.append($('<td>').html(json[i].fullName))
                                tr.append($('<td>').html(json[i].catName))
                                tr.append($('<td>').html(json[i].tName))
                                tr.append($('<td>').append($('<img>').attr('width','154px').attr('height','136px').attr('src', json[i].tImg)))
                                tr.append($('<td>').html(json[i].standard))
                                tr.append($('<td>').html(json[i].price))
                                tr.append($('<td>').html(json[i].money).addClass('teaMoney' + '_' + id))
                                TeaBalance = TeaBalance - json[i].money
                                var minus = $('<td>').append($('<button>').attr('id', id).html("-").addClass('minus').attr("teaPrice", json[i].price).css('background', 'white').css('border', 'white'))
                                var number = $('<td>').append(json[i].number).addClass('teaNumber' + '_' + id)
                                var addTeaNumber = $('<td>').append($('<button>').attr('id', id).html("+").addClass('addTeaNumber').attr("teaPrice", json[i].price).css('background', 'white').css('border', 'white'))
                                tr.append($('<td>').append($('<table>')).append($('<tr>')).append(minus).append(number).append(addTeaNumber))
                                $("#tbody").append(tr);
                            }
                            $("#TeaBalance").html(TeaBalance + "茶币")
                            //减
                            $(".minus").click(function () {
                                var id = $(this).attr('id')
                                if ($(".teaNumber" + '_' + id).html() == 1) {
                                    if (confirm("是否删除该茶点？")) {
                                        del(id)
                                        return false;
                                    } else {
                                        return false;
                                    }
                                }
                                var teaNumber = eval($(".teaNumber" + '_' + id).html() - 1)
                                var teaPrice = $(this).attr("teaPrice")
                                var teaMoney = teaNumber * teaPrice
                                $(".teaNumber" + '_' + id).html(teaNumber)
                                $(".teaMoney" + '_' + id).html(teaMoney)
                                $.ajax({
                                    url: "/updateTeaChoose",
                                    dataType: "json",
                                    data: {
                                        id: id,
                                        number: teaNumber,
                                        money: teaMoney
                                    },
                                    success: function (data) {
                                        $("#TeaBalance").html( parseFloat($("#TeaBalance").html())+parseFloat(teaPrice) + "茶币")
                                    }
                                })
                            })

                            //删
                            function del(id) {
                                $.ajax({
                                    url: '/deleteTeaChoose',
                                    dataType: "json",
                                    data: {
                                        id: id,
                                    },
                                    success: function (data) {
                                        if (data.code == 200) {
                                            setTimeout(function () {
                                                window.location.href = "/teaChoose"
                                            }, 500)
                                        }
                                    }
                                })
                            }
                            //加
                            $(".addTeaNumber").click(function () {
                                var id = $(this).attr('id')
                                var teaNumber = eval(parseInt($(".teaNumber" + '_' + id).html()) + 1)
                                var teaPrice = $(this).attr("teaPrice")
                                var teaMoney = teaNumber * teaPrice
                                isBeOutTenMoney(teaPrice);
                                $(".teaNumber" + '_' + id).html(teaNumber)
                                $(".teaMoney" + '_' + id).html(teaMoney)
                                $.ajax({
                                    url: "/updateTeaChoose",
                                    dataType: "json",
                                    data: {
                                        id: id,
                                        number: teaNumber,
                                        money: teaMoney
                                    },
                                    success: function (data) {
                                        $("#TeaBalance").html( parseFloat($("#TeaBalance").html())-parseFloat(teaPrice) + "茶币")
                                    }
                                })
                            })
                        }else {
                            $("#TeaBalance").html("10茶币")
                            layer.msg("当前数据为空")
                        }
                        //茶点选餐
                        $('#addfiles').click(function () {
                            var addNumber=$("#addNumber").val();
                            var teaId=$("#addTeaId").val();
                            $.ajax({
                                url: "addTeaChoose",
                                data: {
                                    userId:${sessionUser.id},
                                    teaId:teaId,
                                    number:addNumber,
                                },
                                dataType: "json",
                                success: function (data) {
                                    if (data.code == 200) {
                                        layer.msg('点餐成功!', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        setTimeout(function wlh() {
                                            window.location.href = "/teaChoose"
                                        }, 500)
                                    }else if(data.message=="out"){
                                        layer.msg('您的茶币不足!', {
                                            icon: 1,
                                            time: 1000
                                        });
                                    } else if (data.message == "repeat") {
                                        layer.msg("该茶点已点!")
                                    } else {
                                        layer.msg("修改失败!")
                                    }
                                }
                            });
                        })
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
                    }
                }
            })
            if(flag=="false"){
                ajax().abort;
            }
        }

        //校验
        function addcheck() {
            /*if ($("#addCatName").val().trim() == null || $("#addCatName").val().trim() == '') {
                layer.msg("类别不能为空!");
                ajax().abort;
            }*/
        }

        function loginOut(){
            if(confirm("确定要退出登录吗？")){
                window.location.href="/logout";
            }
        }



    </script>
</head>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点选餐</span></div>
<input type="text" id="chooseDate" name="user_date" style="width:130px;margin-left: 10px;" class="layui-input" placeholder="请选择点餐日期"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search" ></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
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
<span><font size="4"><b>今日余额:<span style="color: red" id="TeaBalance"></span></b></font></span>
    <a href="/teaRepository">茶点仓库</a>
<a href="/teaStatistics">统计</a>
<a href="/teaDistribute">分发</a>
<div>
    <table class="table table-bordered" id="table-bordered" class="tcen">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>茶点id</th>
            <th>日期</th>
            <th>选餐人</th>
            <th>品类</th>
            <th>品名</th>
            <th>图片</th>
            <th>规格</th>
            <th>价格</th>
            <th>茶币</th>
            <th>数量</th>
        </tr>
        </thead>
        <tbody id="tbody" class="cen">

        </tbody>
    </table>
</div>
<!--下午茶点新增-->
<div class="modal fade" id="addModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">添加 茶点</h4>
            </div>
            <div class="modal-body">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <table>
                        <tbody>
                        <tr>
                            <td style="width:12%;">品类:</td>
                            <td style="width:60%;">
                                <select class="form-control" id="addCatName" style="width: 100px;" onchange="addCatNameChoose()" />
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:12%;">茶点:</td>
                            <td style="width:60%;">
                                <select tabindex="1" id="addTeaId">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:12%;">数量:</td>
                            <td style="width:60%;">
                                <input class="form-control" id="addNumber" name="number"></input>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <tr><td class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default">关闭</button>
                        <button id="addfiles" >提交</button>
                    </td></tr>
                    <tr><td class="main_tdbor"></td></tr>
                </table>
                <tr>
                </tr>
            </div>
        </div>
    </div>
</div>
</body>
<link rel="Stylesheet" type="text/css" href="/css/Select.css"/>
<script type="text/javascript" src="/js/select.min.js"></script>
<script type="text/javascript">
    function addCatNameChoose() {
        $.ajax({
            url: "/getTeaRepository",
            type: 'get',
            dataType: "json",
            data: {
                catName:$("#addCatName").val()
            },
            success: function (data) {
                var json = data.data
                for (var i in json) {
                    var str = '<option value="' + json[i].id + '" data-icon="' + json[i].tImg + '">' + json[i].catName + '&nbsp;-&nbsp;' + json[i].tName + '&nbsp;-&nbsp;' + json[i].price + '元' + '</option>';
                    $("#addTeaId").append(str)
                }
                $("#addTeaId").wSelect();
            }
        })
    }

       /* $.ajax({
            url: "/getTeaRepository",
            type: 'get',
            dataType: "json",
            data: {},
            success: function (data) {
                var json = data.data
                console.log(json)
                for (var i in json) {
                    var str = '<option value="' + json[i].id + '" data-icon="' + json[i].tImg + '">' + json[i].catName + '&nbsp;-&nbsp;' + json[i].tName + '&nbsp;-&nbsp;' + json[i].price + '元' + '</option>';
                    $("#addTeaId").append(str)
                }
                $("#addTeaId").wSelect();
            }
        })*/
</script>
</html>
