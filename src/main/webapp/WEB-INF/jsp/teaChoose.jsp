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
    <script type="text/javascript" src="http://enkj.jb51.net:81/201508/yuanma/imageselect.js"></script>
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
        th{
            text-align: center !important;
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
                        var str = '<option  class="teaChosen" value="' + json[i].catName + '">' + json[i].catName + '</option>';
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
                                tr.append($('<td>').append($('<div>').addClass('timgs').append($('<div>').addClass('timgs-item').append($('<img>').addClass('timg').attr('src', json[i].tImg).attr('bigUrl', json[i].tImg)))))
                                tr.append($('<td>').html(json[i].standard))
                                tr.append($('<td>').html(json[i].price))
                                tr.append($('<td>').html(json[i].money).addClass('teaMoney' + '_' + id))
                                TeaBalance = TeaBalance - json[i].money
                                var minus = $('<td>').append($('<button>').attr('id', id).html("-").addClass('minus').attr("teaPrice", json[i].price).css('background', 'white').css('border', 'white'))
                                var number = $('<td>').append(json[i].number).addClass('teaNumber' + '_' + id)
                                var addTeaNumber = $('<td>').append($('<button>').attr('id', id).html("+").addClass('addTeaNumber').attr("teaPrice", json[i].price).css('background', 'white').css('border', 'white'))
                                tr.append($('<td>').append($('<table>').css("")).append($('<tr>')).append(minus).append(number).append(addTeaNumber))
                                $("#tbody").append(tr);
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

                            $("#TeaBalance").html(TeaBalance + "茶币")
                            //减
                            $(".minus").click(function () {
                                var id = $(this).attr('id')
                                if ($(".teaNumber" + '_' + id).html() == 1) {
                                    layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要移除该茶点吗？', {
                                        title: '信息',
                                        btn: ['朕意已决', '爱卿退下，朕且三思']
                                    },function(index){
                                        del(id)
                                    })
                                    return false;
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
                                            layer.msg('已移除!', {
                                                icon: 1,
                                                time: 1000
                                            });
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
                            layer.msg("当前数据为空,请及时选餐")
                        }
                        //茶点选餐
                        $('#addfiles').click(function () {
                            addcheck()
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
                                        layer.msg('选餐成功!', {
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
        function check() {
            var reg = new RegExp(/^\d+$/);
            if(!reg.test($("#").val())){
                layer.msg("单价请输入数字")
            }
        }

        function Close() {
            $('.teaChosen').attr('selected',false).trigger("chosen:updated");
            $.ajax({
                url: "/getTeaRepository",
                type: 'get',
                dataType: "json",
                data: {},
                success: function (data) {
                    var json = data.data
                    var str
                    var td = $("#addTeaId").parent();
                    td.html('');
                    td.append($('<select>').attr('tabindex',1).attr('id','addTeaId'));
                    $("#addTeaId").append('<option value="">&nbsp;请选择</option>')
                    for (var i in json) {
                        str = '<option class="teaTwoChosen" value="' + json[i].id + '" data-icon="' + json[i].tImg + '">'+ '&nbsp;-&nbsp;' + json[i].tName + '&nbsp;-&nbsp;' + json[i].price + '元' + '</option>';
                        $("#addTeaId").append(str)
                    }
                    $("#addTeaId").wSelect();
                }
            })
            $("#addNumber").val("")
        }

        document.onkeyup = function (e){
            e = e || window.event;
            var code = e.which || e.keyCode;
            if (code == 27){
                Close()
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
            if ($("#addCatName").val().trim() == null || $("#addCatName").val().trim() == '') {
                layer.msg("类别不能为空!");
                ajax().abort;
            }
            if ($("#addTeaId").val().trim() == null || $("#addTeaId").val().trim() == '') {
                layer.msg("请选择茶点!");
                ajax().abort;
            }
            if ($("#addNumber").val().trim() == null || $("#addNumber").val().trim() == '') {
                layer.msg("数量不能为空!");
                ajax().abort;
            }
            var r=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
            if (r.test($("#addNumber").val())||isNaN($("#addNumber").val())) {
                layer.msg("数量请输入规范!");
                ajax.abort;
            }
            if($("#addNumber").val().indexOf(".")!=-1){
                layer.msg("数量输入不规范,请输入整数!");
                ajax.abort;
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
                        <tr>
                            <td style="width:22%;" align="center">品类:</td>
                            <td style="width:60%;">
                                <select class="form-control" id="addCatName" style="width: 100px;" onchange="addCatNameChoose()" />
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:22%;" align="center">茶点:</td>
                            <td style="width:60%;">
                                <select tabindex="1" id="addTeaId" style="width: 200px;">
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:22%;" align="center">数量:</td>
                            <td style="width:60%;">
                                <input class="form-control" id="addNumber" name="number" style="width: 250px;" placeholder="请输入您要点餐的个数"></input>
                            </td>
                        </tr>
                        <tr>
                            <td class="modal-footer"  style="width:22%;"></td>
                            <td class="modal-footer"  style="width:60%;">
                                <button data-dismiss="modal" class="btn btn-info" onclick="Close()">关闭</button>
                                <button id="addfiles" class="btn btn-danger">提交</button>
                            </td>
                            <td class="modal-footer" align="right" >
                            </td>
                        </tr>
                </table>
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
                var td = $("#addTeaId").parent();
                td.html('');
                td.append($('<select>').attr('tabindex',1).attr('id','addTeaId'));
                $("#addTeaId").append('<option value="">&nbsp;请选择</option>')
                for (var i in json) {
                    var str = '<option class="teaTwoChosen" value="' + json[i].id + '" data-icon="' + json[i].tImg + '">'+ '&nbsp;-&nbsp;' + json[i].tName + '&nbsp;-&nbsp;' + json[i].price + '元' + '</option>';
                    $("#addTeaId").append(str)
                }
               /* $("#addTeaId").trigger("liszt:updated");
                $("#addTeaId").chosen({
                    no_results_text:'未找到',
                });*/
                $("#addTeaId").addClass("wSelect-el");
                $("#addTeaId").wSelect();
            }
        })
    }

        $.ajax({
            url: "/getTeaRepository",
            type: 'get',
            dataType: "json",
            data: {},
            success: function (data) {
                var json = data.data
                var str
                $("#addTeaId").append('<option value="">&nbsp;请选择</option>')
                for (var i in json) {
                    str = '<option class="teaTwoChosen" value="' + json[i].id + '" data-icon="' + json[i].tImg + '">'+ '&nbsp;-&nbsp;' + json[i].tName + '&nbsp;-&nbsp;' + json[i].price + '元' + '</option>';
                    $("#addTeaId").append(str)
                }
                $("#addTeaId").wSelect();
            }
        })
</script>
</html>
