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
    <title>茶点入库</title>
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

            $.fn.bootstrapDropdownHover();

            //查询
            $("#query").click(function () {
                inittable()
            })
            inittable()
            function inittable() {
                $("#tbody").html("");
                var lowPrice=$("#lowPrice").val();
                var highPrice=$("#highPrice").val();
                $.ajax({
                    url:"getTeaRepository",
                    type:'get',
                    dataType:"json",
                    data:{
                        catName:$("#queryCatName").val(),
                        tName:$("#querytName").val().trim(),
                        lowPrices:lowPrice,
                        highPrices:highPrice
                    },
                    success:function (data) {
                        console.log(data)
                        if(data.message=="数据获取成功") {
                            if ($("#queryCatName").val() == null) {
                                $("#queryCatName").html("");
                                $("#addCatNameSelect").html("");
                                var str = '<option value="" >请选择品类</option>';
                                $("#queryCatName").append(str);
                                $("#addCatNameSelect").append(str);
                                for (var i in data.catNameList) {
                                    var str1 = '<option value="' + data.catNameList[i].catName + '" >' + data.catNameList[i].catName + '</option>';
                                    $("#queryCatName").append(str1);
                                    $("#updCatName").append(str1);
                                    $("#addCatNameSelect").append(str1);
                                }
                            }
                            var json = data.data
                            for (var i in json) {
                                var tr = $('<tr>');
                                tr.append($('<td>').html(json[i].id))
                                tr.append($('<td>').html(json[i].catName))
                                tr.append($('<td>').html(json[i].tName))
                                tr.append($('<td>').append($('<div>').addClass('timgs').append($('<div>').addClass('timgs-item').append($('<img>').addClass('timg').attr('src', json[i].tImg).attr('bigUrl', json[i].tImg)))))
                                tr.append($('<td>').html(json[i].standard))
                                tr.append($('<td>').html(json[i].price))
                                tr.append($('<td>').html(json[i].note))
                                var look = $('<button>').attr("catName", json[i].catName).attr("tName", json[i].tName).attr("tImg", json[i].tImg).attr("standard", json[i].standard).attr("price", json[i].price).attr("note", json[i].note)
                                    .addClass('btn btn-info lookTeaRepository').attr('data-toggle', 'modal').attr('data-target', '#lookModal').css('margin-right', '10px').html('<i class="glyphicon glyphicon-asterisk"></i>');
                                var upd = $('<button>').attr("id", json[i].id).attr("catName", json[i].catName).attr("tName", json[i].tName).attr("tImg", json[i].tImg).attr("standard", json[i].standard).attr("price", json[i].price).attr("note", json[i].note)
                                    .addClass('btn btn-warning updTeaRepository').css('margin-right', '10px').attr('data-toggle', 'modal').attr('data-target', '#updModal').html('<i class="glyphicon glyphicon-edit"></i>');
                                var td = $('<td>');
                                td.append(look).append(upd);
                                tr.append(td);
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
                                        cssArr.top = (e.clientY + 10) + "px";
                                    }
                                    console.log($("#pic1").height(), wH)
                                    console.log(cssArr)
                                    $("#pic").css(cssArr).fadeIn("fast");
                                });
                            }, function () {
                                $("#pic").remove();
                            });

                            //茶点入库 上传图片
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
                                        } else if (data.message == "图片不能无") {
                                            layer.msg("请上传图片!")
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

                            //修改
                            $('.updTeaRepository').unbind('click').click(function () {
                                $("#updId").val($(this).attr("id"))
                                $("#updCatName").val($(this).attr("catName"))
                                $("#updtName").val($(this).attr("tName"))
                                $("#updtImgShow").append($('<img>').attr('width','154px').attr('height','136px').attr('src', $(this).attr("tImg")))
                                $("#updStandard").val($(this).attr("standard"))
                                $("#updPrice").val($(this).attr("price"))
                                $("#updNote").val($(this).attr("note"))
                            })

                            //提交 更新仓库 修改图片
                            $('#updfiles').click(function () {
                                $.ajax({
                                    url: "/updateTeaRepository",
                                    type: 'post',
                                    cache: false,
                                    data: new FormData($('#updfileform')[0]),
                                    processData: false,
                                    contentType: false,
                                    dataType: "json",
                                    success: function (data) {
                                        /*$('#s9').val('['+data.urls+']');*/
                                        if (data.code == 200) {
                                            layer.msg('修改成功!', {
                                                icon: 1,
                                                time: 1000
                                            });
                                            setTimeout(function wlh() {
                                                window.location.href = "/teaRepository"
                                            }, 500)
                                        } else if (data.message == "重复") {
                                            layer.msg("该茶点已存在")
                                        } else {
                                            layer.msg("修改失败!")
                                        }
                                    }
                                });
                            })
                        }else {
                            layer.msg("当前数据为空")
                        }
                    }
                })

                $('#addCatNameSelect').next().bind('click', function () {
                    $('#addCatName').val('')
                    $('#addCatName').toggle();
                    $('#addCatNameSelect').toggle();
                })
            }

        })

        function Close() {
            $("#updtImgShow").empty()
            $("#looktImg").empty()
            $("#addtName").val("")
            $("#addStandard").val("")
            $("#aadPrice").val("")
            $("#addNote").val("")
        }

        document.onkeyup = function (e){
            e = e || window.event;
            var code = e.which || e.keyCode;
            if (code == 27){
                Close()()
            }
        }

        //校验
        function addcheck() {
            if($("#addtName").val().trim()==null|| $("#addtName").val().trim()=="") {
                alert($("#addCatNameSelect").val())
                if ($("#addCatNameSelect").val().trim() == null || $("#addCatNameSelect").val().trim() == "") {
                    layer.msg("茶点名不能为空!");
                    ajax().abort;
                }
            }
            if ($("#addtName").val().trim() == null || $("#addtName").val().trim() == '') {
                layer.msg("品类不能为空!");
                ajax().abort;
            }
            if ($("#tImg")== null ) {
                layer.msg("图片不能为空!");
                ajax.abort;
            }
            if($("#addStandard").val().trim()== null|| $("#addStandard").val().trim() == ''){
                layer.msg("规格不能为空!");
                ajax().abort;
            }
            if($("#aadPrice").val().trim()== null|| $("#aadPrice").val().trim() == ''){
                layer.msg("价格不能为空!");
                ajax().abort;
            }
            var r=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
            if (r.test($("#aadPrice").val())||isNaN($("#aadPrice").val())) {
                layer.msg("价格请输入规范!");
                ajax.abort;
            }
        }

        function loginOut(){
            if(confirm("确定要退出登录吗？")){
                window.location.href="/logout";
            }
        }

    </script>
</head>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">茶点仓库</span></div>
    <select id="queryCatName" style="width:174px;margin-left: 10px;height: 27px" class="layui-input" placeholder="请输入茶点类别"/></select>
    <input type="text" class="" id="querytName" placeholder="请输入茶点名">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格区间:&nbsp;<input type="text" id="lowPrice" placeholder="请输入最低期望价格">&nbsp;—&nbsp<input type="text" id="highPrice" placeholder="请输入最高期望价格">
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
    <a href="/teaChoose">点餐</a>
    <a href="/teaStatistics">统计</a>
    <a href="/teaDistribute">分发</a>
    <div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;" class="tcen">
        <tr >
            <th>茶点id</th>
            <th>类别</th>
            <th>名称</th>
            <th>图片</th>
            <th>规格</th>
            <th>价格</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody" class="cen">

        </tbody>
    </table>
    </div>
<!--更新茶点-->
<div class="modal fade" id="updModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true" onclick="Close()">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">更新茶点</h4>
            </div>
            <div class="modal-body">
                    <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                        <table>
                            <tbody>
                            <form id="updfileform" method="post" enctype="multipart/form-data">
                            <tr>
                                <td style="width:12%;">编号:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="updId" name="id" readonly></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">品类:</td>
                                <td style="width:60%;">
                                    <select class="form-control" id="updCatName" name="catName"></select>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">茶点:</td>
                                <td>
                                    <input type="text" id="updtName"  class="form-control" name="tName"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">图片:</td>
                                <td style="width:60%;">
                                    <div  multiple id="updtImgShow"></div>
                                    <input  name="file" type="file" multiple id="updtImg"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">规格:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="updStandard" name="standard"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">价格:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="updPrice" name="price"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">备注:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="updNote" name="note"></input>
                                </td>
                            </tr>
                            </form>
                            </tbody>
                        </table>
                        <tr><td class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" onclick="Close()">关闭</button>
                            <button id="updfiles" class="btn btn-primary">上传提交</button>
                            <input id="updDutyRecord" class="btn btn-danger" type="reset" value="重置">
                        </td></tr>
                        <tr><td class="main_tdbor"></td></tr>
                    </table>
                    <tr>
                    </tr>
            </div>
        </div>
    </div>
</div>
<!--茶点详情-->
<div class="modal fade" id="lookModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true" onclick="Close()">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">茶点详情</h4>
            </div>
            <div class="modal-body">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <table>
                        <tbody>
                            <tr>
                                <td style="width:12%;">类别:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="lookCatName" name="catName"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">名称:</td>
                                <td>
                                    <input type="text" id="looktName"  class="form-control" name="tName"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">图片:</td>
                                <td style="width:60%;">
                                    <div  name="file" type="file" multiple id="looktImg"></div>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">规格:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="lookStandard" name="standard"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">价格:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="lookPrice" name="price"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">备注:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="lookNote" name="note"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;"></td>
                                <td class="modal-footer">
                                <button data-dismiss="modal" class="btn btn-default" onclick="Close()">关闭</button>
                            </td></tr>
                        </tbody>
                    </table>
                </table>
                <tr>
                </tr>
            </div>
        </div>
    </div>
</div>
<!--下午茶点新增-->
<div class="modal fade" id="addModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true" onclick="Close()">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">添加 茶点</h4>
            </div>
            <div class="modal-body">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <table>
                        <tbody>
                        <form id="fileform" method="post" enctype="multipart/form-data">
                            <tr>
                                <td style="width:12%;">品类:</td>
                                <td style="width:60%;">
                                    <input type="text" class="form-control" id="addCatName" style="display:none;" name="catName1">
                                    <select class="form-control" id="addCatNameSelect" name="catName2"></select>&nbsp;
                                    <input type="button" value="→" style="width: 30px;height: 30px;" class="btn btn-danger"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">茶点:</td>
                                <td>
                                    <input type="text" id="addtName"  class="form-control" name="tName"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">图片:</td>
                                <td style="width:60%;">
                                    <div id="addtImgShow"></div>
                                    <input type="file" accept="image/*" id="addImg"  name="file" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">规格:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="addStandard" name="standard"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">价格:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="aadPrice" name="price"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">备注:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="addNote" name="note"></input>
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                    <tr><td class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-default" onclick="Close()">关闭</button>
                        <button id="addfiles" class="btn btn-primary">上传提交</button>
                        <input id="addDutyRecord" class="btn btn-danger" type="reset" value="重置">
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
<script type="text/javascript">
    document.getElementById("addImg").addEventListener("change",function(e){
        var files = this.files;

        var img = new Image();
        var render  = new FileReader();
        render.readAsDataURL(files[0]);
        render.onloadstart = function(){
//				alert("start")
        };
        render.onload = function(){
            img.src = this.result;
            img.style.height = "100%";
            img.style.width = "100%";
            var addtImgShow = document.getElementById("addtImgShow");
            addtImgShow.innerHTML = "";

            addtImgShow.appendChild(img);
//				alert("success");
        };
        render.onloadend = function(){
//				alert("end");
        }
    });

    document.getElementById("updtImg").addEventListener("change",function(e){
        var files = this.files;

        var img = new Image();
        var render  = new FileReader();
        render.readAsDataURL(files[0]);
        render.onloadstart = function(){
//				alert("start")
        };
        render.onload = function(){
            img.src = this.result;
            img.style.height = "100%";
            img.style.width = "100%";
            var updtImgShow = document.getElementById("updtImgShow");
            updtImgShow.innerHTML = "";

            updtImgShow.appendChild(img);
//				alert("success");
        };
        render.onloadend = function(){
//				alert("end");
        }
    });
</script>
</html>
