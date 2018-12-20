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
    <title>下午茶点入库</title>
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
            inittable()
            function inittable() {
                $.ajax({
                    url:"getTeaRepository",
                    type:'get',
                    dataType:"json",
                    data:{

                    },
                    success:function (data) {
                        var json=data.data
                        console.log(json)
                        for (var i in json) {
                            var tr = $('<tr>');
                            tr.append($('<td>').html(json[i].id))
                            tr.append($('<td>').html(json[i].catName))
                            tr.append($('<td>').html(json[i].tName))
                            tr.append($('<td>').append($('<img>').attr('src',json[i].tImg)))
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

                        //茶点入库 上传图片
                        $('#addfiles').unbind('click').click(function () {
                            addcheck();
                            $.ajax({
                                url: "addTeaRepository",
                                type: 'post',
                                cache: false, // 不缓存
                                data: new FormData($('#fileform')[0]),
                                processData: false,//  告诉jquery不要处理发送的数据
                                contentType:false,    // 告诉jquery不要设置content-Type请求头
                                dataType:"json",
                                async:false,
                                success : function(data) {
                                    /*$('#x8').val('['+data.urls+']');*/
                                    if(data.code==200){
                                        layer.msg('上传成功,已入库!', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        setTimeout(function wlh() {
                                            window.location.href = "/teaRepository"
                                        }, 500)
                                    }else if(data.message=="重复添加"){
                                        layer.msg("该茶点已存在!")
                                    }else{
                                        layer.msg("修改失败!")
                                    }
                                }
                            });
                        })

                        //查看
                        $('.lookTeaRepository').click(function () {
                            $("#lookCatName").val($(this).attr("catName"))
                            $("#looktName").val($(this).attr("tName"))
                            $("#looktImg").append($('<img>').attr('src',$(this).attr("tImg")))
                            $("#lookStandard").val($(this).attr("standard"))
                            $("#lookPrice").val($(this).attr("price"))
                            $("#lookNote").val($(this).attr("note"))
                        })

                        //修改
                        $('.updTeaRepository').unbind('click').click(function () {
                            $("#updId").val($(this).attr("id"))
                            $("#updCatName").val($(this).attr("catName"))
                            $("#updtName").val($(this).attr("tName"))
                            $("#updtImgShow").append($('<img>').attr('src',$(this).attr("tImg")))
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
                                data: new FormData($('#updfileform')[0],$('#updfileform')[1],$('#updfileform')[2],$('#updfileform')[3],$('#updfileform')[4],$('#updfileform')[5],$('#updfileform')[6]),
                                processData: false,
                                contentType: false,
                                dataType:"json",
                                success : function(data) {
                                    /*$('#s9').val('['+data.urls+']');*/
                                    if(data.code==200){
                                        layer.msg('修改成功!', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        setTimeout(function wlh() {
                                            window.location.href = "/teaRepository"
                                        }, 500)
                                    }else if(data.message=="重复添加"){
                                        layer.msg("该茶点已存在")
                                    }else{
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

        //校验
        function addcheck() {
            if ($("#addCatName").val().trim() == null || $("#addCatName").val().trim() == '') {
                layer.msg("类别不能为空!");
                ajax().abort;
            }
            if($("#addtName").val().trim()== null|| $("#addtName").val().trim() == ''){
                layer.msg("名称不能为空!");
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
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">下午茶茶点仓库</span></div>
<select id="queryCatName" style="width:155px;margin-left: 10px;" class="layui-input" placeholder="请输入茶点类别"/></select>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增
</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓 下午茶茶点仓库</span>
<a id="home" href="/home" class="glyphicon glyphicon-home"></a>
<a onclick="loginOut()" class="glyphicon glyphicon-off"></a>
<div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
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
        <tbody id="tbody">

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
                            <form id="updfileform" action="upload" method="post" enctype="multipart/form-data">
                            <tr>
                                <td style="width:12%;">编号:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="updId" name="id" readonly></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">类别:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="updCatName" name="catName"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">名称:</td>
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
                            <button id="updfiles" >上传提交</button>
                            <input id="updDutyRecord" class="btn btn-primary" type="reset" value="重置">
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
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">添加 茶点</h4>
            </div>
            <div class="modal-body">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <table>
                        <tbody>
                        <form id="fileform" action="upload" method="post" enctype="multipart/form-data">
                            <tr>
                                <td style="width:12%;">类别:</td>
                                <td style="width:60%;">
                                    <input class="form-control" id="addCatName" name="catName"></input>
                                </td>
                            </tr>
                            <tr>
                                <td style="width:12%;">名称:</td>
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
