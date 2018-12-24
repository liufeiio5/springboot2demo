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
            laydate.render({elem: '#chooseDate'});

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
                                tr.append($('<td>').html(json[i].fullName))
                                tr.append($('<td>').html(json[i].catName))
                                tr.append($('<td>').html(json[i].tName))
                                tr.append($('<td>').append($('<img>').attr('src', json[i].tImg)))
                                tr.append($('<td>').html(json[i].standard))
                                tr.append($('<td>').html(json[i].price))
                                tr.append($('<td>').html(json[i].money).addClass('teaMoney' + '_' + id))
                                TeaBalance = TeaBalance - json[i].money
                                var minus = $('<td>').append($('<button>').attr('id', id).html("-").addClass('minus').attr("teaPrice", json[i].price).css('background', 'white').css('border', 'white'))
                                var number = $('<td>').append(json[i].number).addClass('teaNumber' + '_' + id)
                                var add = $('<td>').append($('<button>').attr('id', id).html("+").addClass('add').attr("teaPrice", json[i].price).css('background', 'white').css('border', 'white'))
                                tr.append($('<td>').append($('<table>')).append($('<tr>')).append(minus).append(number).append(add))
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
                                            $("#TeaBalance").html( parseInt($("#TeaBalance").html())+parseInt(teaPrice) + "茶币")
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
                            $(".add").click(function () {
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
                                    }
                                })
                            })

                            //茶点选餐
                            $('#addfiles').unbind('click').click(function () {
                                addcheck();
                                $.ajax({
                                    url: "addTeaRepository",
                                    type: 'post',
                                    cache: false, // 不缓存
                                    data: new FormData($('#fileform')[0]),
                                    processData: false,//  告诉jquery不要处理发送的数据
                                    contentType: false,    // 告诉jquery不要设置content-Type请求头
                                    dataType: "json",
                                    async: false,
                                    success: function (data) {
                                        /*$('#x8').val('['+data.urls+']');*/
                                        if (data.code == 200) {
                                            layer.msg('上传成功,已入库!', {
                                                icon: 1,
                                                time: 1000
                                            });
                                            setTimeout(function wlh() {
                                                window.location.href = "/teaRepository"
                                            }, 500)
                                        } else if (data.message == "重复添加") {
                                            layer.msg("该茶点已存在!")
                                        } else {
                                            layer.msg("修改失败!")
                                        }
                                    }
                                });
                            })

                            //查看
                            $('.lookTeaRepository').click(function () {
                                $("#lookCatName").val($(this).attr("catName"))
                                $("#looktName").val($(this).attr("tName"))
                                $("#looktImg").append($('<img>').attr('src', $(this).attr("tImg")))
                                $("#lookStandard").val($(this).attr("standard"))
                                $("#lookPrice").val($(this).attr("price"))
                                $("#lookNote").val($(this).attr("note"))
                            })
                    }else {
                            layer.msg("当前数据为空")
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
    </script>
</head>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点选餐</span></div>
    <input type="text" id="chooseDate" name="user_date" style="width:130px;margin-left: 10px;" class="layui-input" placeholder="请选择点餐日期"/>
    <button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search" ></i>&nbsp;查询</button>
    <button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
    <span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓日报系统</span>
    <a id="home" href="/home" class="glyphicon glyphicon-home"></a>
    <a onclick="loginOut()" class="glyphicon glyphicon-off"></a>
    <span><font size="4"><b>今日余额:<span style="color: red" id="TeaBalance"></span></b></font></span>
    <div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>茶点id</th>
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
        <tbody id="tbody">

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
                                <td style="width:12%;">茶点:</td>
                                <td style="width:60%;">
                                    <select tabindex="1" id="demo">
                                        <option value="">请选择</option>
                                      <%-- <option data-icon="/upload/tea_images/奥利奥.png">&nbsp;&nbsp;&nbsp;&nbsp;Thailand</option>--%>
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
                        <button id="addfiles" >上传提交</button>
                        <input id="addDutyRecord" class="btn btn-primary" type="reset" value="重置">
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
  /*  $(function () {
        $.ajax({
            url:"/getTeaRepository",
            type:'get',
            dataType:"json",
            data:{
            },
            success:function (data) {
                console.log(data)
                var json=data.data
                var str;
                for (var i in json){
                    str='<option value="1" data-icon="'+json[i].tImg+'">json[i].tName</option>'
                    $("#demo").append('<option data-icon="/upload/tea_images/奥利奥.png">'+'&nbsp;&nbsp;&nbsp;&nbsp;'+Thailand+'</option>')
                    /!*var str='<option value="'+json[i].id+'" data-icon="\'+json[i].tImg+\'">' + json[i].tName + '</option>';*!/
                }
            }
        })
    })*/
    $('select').wSelect();
</script>
</html>
