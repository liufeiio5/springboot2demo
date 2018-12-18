<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
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
    <script >
        $(function() {
            laydate.render({elem: '#leaveRecoredDate',format: 'yyyyMMdd'});
            laydate.render({elem: '#addDate',format: 'yyyyMMdd'});

            //请假人员 下拉
            $("#addLeaveName").html("")
            $("#updLeaveName").html("")
            var strs='<option class="assisManItem" value="">'+"请选择"+'</option>'
            $("#addLeaveName").append(strs)
            $("#updLeaveName").append(strs)
            $.ajax({
                url: '/getUser',
                dataType: 'json',
                success: function (data) {
                    var str;
                    var json = data.data
                    for (var i in json) {
                        str = '<option class="assisManItem" value="' + json[i].id + '">' + json[i].fullName + '</option>';
                        $("#addLeaveName").append(str)
                        $("#updLeaveName").append(str)
                    }

                    $("#addLeaveName").trigger("liszt:updated");
                    $("#updLeaveName").trigger("liszt:updated");
                    $("#addLeaveName").chosen({
                        no_results_text:'未找到',
                    });
                    $("#updLeaveName").chosen({
                        no_results_text:'未找到',
                    });
                }
            })

            //初始化
            inittable();

            //查询
            $("#query").click(function () {
                inittable()
            })

            //添加请假记录
            $('#addLeaveRecord').unbind('click').click(function () {
                checkAddInput()
                var id=$("#addDate").val().replace(/\-/g, '')
                $.ajax({
                    url: "/addLeaveRecord",
                    dataType: 'json',
                    data: {
                        id:id,
                        leaveName:$("#addLeaveName").val().toString(),
                        leaveReason:$("#addLeaveReason").val(),
                        leaveDuration:$("#addLeaveDuration").val()+$("#addLeaveDurationUnit").val()
                    },
                    success: function (data) {
                        if (data.message == "添加成功") {
                            layer.msg('添加成功!', {
                                icon: 1,
                                time: 1000
                            });
                            setTimeout(function () {
                                window.location.href = "/leaveRecord"
                            }, 500)
                        } else if (data.message == "禁止提前创建请假记录") {
                            layer.msg("禁止提前创建请假记录");
                        } else if (data.message == "重复添加") {
                            layer.msg("禁止请假记录重复添加");
                        } else {
                            layer.msg("添加失败");
                        }
                    }
                });
            })
        })

        function inittable() {
            var leaveId=$("#leaveRecoredDate").val().replace(/\-/g, '')
            var leaveRecoredNameOrId=$("#leaveRecoredNameOrId").val().trim()
            if(isNaN(leaveRecoredNameOrId)){
                var fullName=leaveRecoredNameOrId
            }else{
                var leaveName=leaveRecoredNameOrId
            }
            $.ajax({
                type: 'get',
                url: 'getLeaveRecord',
                dataType: 'json',
                data: {
                    id:leaveId,
                    fullName:fullName,
                    leaveName:leaveName
                },
                success: function (data) {
                    $("#tbody").html("");
                    console.log(data)
                    var json = data.data
                    for (var i in json) {
                        var tr = $('<tr>');
                        tr.append($('<td>').html(json[i].id))
                        tr.append($('<td>').html(json[i].fullName))
                        tr.append($('<td>').html(json[i].leaveReason))
                        tr.append($('<td>').html(json[i].leaveDuration))
                        var look = $('<button>').attr("id", json[i].id).attr("fullName", json[i].fullName).attr("leaveReason", json[i].leaveReason).attr("leaveDuration", json[i].leaveDuration)
                            .addClass('btn btn-info lookLeaveRecord').attr('data-toggle', 'modal').attr('data-target', '#lookModal').css('margin-right', '10px').html('<i class="glyphicon glyphicon-asterisk"></i>');
                        var upd = $('<button>').attr("id", json[i].id).attr("leaveName", json[i].leaveName).attr("leaveReason", json[i].leaveReason).attr("leaveDuration", json[i].leaveDuration)
                            .addClass('btn btn-warning updbtn').css('margin-right', '10px').attr('data-toggle', 'modal').attr('data-target', '#updModal').html('<i class="glyphicon glyphicon-edit"></i>');
                        var td = $('<td>');
                        td.append(look).append(upd);
                        tr.append(td);
                        $("#tbody").append(tr);
                    }
                    if (data.code != "200") {
                        layer.msg('当前数据为空!');
                    }
                    //详情
                    $(".lookLeaveRecord").unbind('click').click(function () {
                        $("#lookLeaveId").val($(this).attr("id"));
                        $("#lookLeaveName").val($(this).attr("fullName"));
                        $("#lookLeaveReason").val($(this).attr("leaveReason"));
                        $("#lookLeaveDuration").val($(this).attr("leaveDuration"));
                    })
                    //修改
                    $(".updbtn").unbind('click').click(function () {
                        var id=$(this).attr("id");
                        $("#updLeaveId").val(id);
                        var leaveName=$(this).attr("leaveName");
                        $("#updLeaveReason").val($(this).attr("leaveReason"));
                        var leaveDuration=$(this).attr("leaveDuration")
                        $("#updleaveDuration").val(leaveDuration.substr(0, leaveDuration.length - 1));

                        $('.assisManItem').prop('selected', false).trigger("chosen:updated");
                        if (!$.isEmptyObject(leaveName)) {
                            if(leaveName.indexOf(" ") == -1){
                                $("#updLeaveName" + " option[value='" + leaveName + "']").prop('selected', true);
                            }else {
                                var arr = leaveName.split(" ");
                                var length = arr.length;
                                var value = "";
                                for (i = 0; i < length; i++) {
                                    value = arr[i];
                                    $("#updLeaveName" + " option[value='" + leaveName + "']").prop('selected', true);
                                }
                            }
                            $("#updLeaveName").chosen();
                            $("#updLeaveName").trigger("chosen:updated");
                        }

                        //提交
                        $("#updLeaveRecord").unbind('click').click(function () {
                            checkUpdInput()
                            layer.confirm('确认要修改吗？', function (index) {
                                $.ajax({
                                    url: '/updateLeaveRecord',
                                    dataType: 'json',
                                    data: {
                                        id: id,
                                        leaveName:leaveName,
                                        leaveFlag: $("#updLeaveName").val().toString(),
                                        leaveReason:$("#updLeaveReason").val(),
                                        leaveDuration:$("#updleaveDuration").val()+$("#updLeaveDurationUnit").val()
                                    },
                                    success: function (data) {
                                        if (data.message == "修改成功") {
                                            setTimeout(function wlh() {
                                                window.location.href = "/leaveRecord"
                                            }, 500)
                                            layer.msg('已修改!', {
                                                icon: 1,
                                                time: 1000
                                            });
                                        } else if (data.message == "当前时间不在此月内,禁止修改") {
                                            layer.msg("当前时间不在此月内，禁止修改", {
                                                icon: 1,
                                                time: 1000
                                            });
                                        } else {
                                            layer.msg(data.result, {
                                                icon: 1,
                                                time: 1000
                                            });
                                        }
                                    }
                                })
                            })
                        })
                    })
                }
            })
        }
        //校验
        function checkUpdInput() {
            if ($("#updLeaveName").val()== null || $("#updLeaveName").val()== '') {
                layer.msg("请假人员不能为空！");
                ajax().abort;
            }
            if ($("#updLeaveReason").val().trim()== null || $("#updLeaveReason").val().trim() == '') {
                layer.msg("请假原因不能为空!");
                ajax.abort;
            }
            if ($("#updleaveDuration").val().trim()== null || $("#updleaveDuration").val().trim() == '') {
                layer.msg("请假时长不能为空!");
                ajax.abort;
            }
        }
        //校验
        function checkAddInput() {
            if ($("#addDate").val().trim() == null || $("#addDate").val().trim() == '') {
                layer.msg("日期不能为空！");
                ajax().abort;
            }
            if($("#addDate").val().trim().length!=8){
                layer.msg("格式不规范,日期或请假Id只能为8位数字")
                ajax().abort;
            }
            if ($("#addLeaveName").val()== null ||$("#addLeaveName").val()=="") {
                layer.msg("请假人员不能为空！");
                ajax.abort;
            }
            if ($("#addLeaveReason").val().trim()== null || $("#addLeaveReason").val().trim() == '') {
                layer.msg("请假原因不能为空!");
                ajax.abort;
            }
            if ($("#addLeaveDuration").val().trim()== null || $("#addLeaveDuration").val().trim()== '') {
                layer.msg("请假时长不能为空!");
                ajax.abort;
            }
            var r=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
            if (r.test($("#addLeaveDuration").val().trim())||isNaN($("#addLeaveDuration").val().trim())) {
                layer.msg("请假时长输入不规范,请按提示来!");
                ajax.abort;
            }
        }

        function loginOut(){
            if(confirm("确定要退出登录吗？")){
                window.location.href="/logout";
            }
        }
        function addClose() {
            $("#addDate").val("")
            $('.assisManItem').prop('selected', false).trigger("chosen:updated");
            $("#addLeaveReason").val("")
            $("#addLeaveDuration").val("")
        }

        document.onkeyup = function (e){
            e = e || window.event;
            var code = e.which || e.keyCode;
            if (code == 27){
                addClose()
            }
        }

        //爱心气泡，听我号令
        onload = function() {
            var click_cnt = 0;
            var $html = document.getElementsByTagName("html")[0];
            var $body = document.getElementsByTagName("body")[0];
            $html.onclick = function(e) {
                var $elem = document.createElement("b");
                if(click_cnt%7==0){
                    $elem.style.color="#00beff";
                }else if(click_cnt%7==1){
                    $elem.style.color="#dff0d8";
                }else if(click_cnt%7==2){
                    $elem.style.color="#ebcccc"
                }else if(click_cnt%7==3){
                    $elem.style.color="#7fff00";
                }else if(click_cnt%7==5){
                    $elem.style.color="#ffA500";
                }else{
                    $elem.style.color="#E94F06";
                }
                if(click_cnt==0){
                    $elem.style.color="#E94F06";
                }
                $elem.style.zIndex = 9999;
                $elem.style.position = "absolute";
                $elem.style.select = "none";
                var x = e.pageX;
                var y = e.pageY;
                $elem.style.left = (x - 10) + "px";
                $elem.style.top = (y - 20) + "px";
                clearInterval(anim);
                switch (++click_cnt) {
                    case 10:
                        $elem.innerText = "OωO";
                        break;
                    case 20:
                        $elem.innerText = "(๑•́ ∀ •̀๑)";
                        break;
                    case 30:
                        $elem.innerText = "(๑•́ ₃ •̀๑)";
                        break;
                    case 40:
                        $elem.innerText = "(๑•̀_•́๑)";
                        break;
                    case 50:
                        $elem.innerText = "（￣へ￣）";
                        break;
                    case 60:
                        $elem.innerText = "(╯°口°)╯(┴—┴";
                        break;
                    case 70:
                        $elem.innerText = "૮( ᵒ̌皿ᵒ̌ )ა";
                        break;
                    case 80:
                        $elem.innerText = "╮(｡>口<｡)╭";
                        break;
                    case 90:
                        $elem.innerText = "( ง ᵒ̌皿ᵒ̌)ง⁼³₌₃";
                        break;
                    case 100:
                    case 101:
                    case 102:
                    case 103:
                    case 104:
                    case 105:
                        $elem.innerText = "(ꐦ°᷄д°᷅)";
                        break;
                    default:
                        $elem.innerText = "❤";
                        break;
                }
                $elem.style.fontSize = Math.random() * 10 + 8 + "px";
                var increase = 0;
                var anim;
                setTimeout(function() {
                    anim = setInterval(function() {
                        if (++increase == 150) {
                            clearInterval(anim);
                            $body.removeChild($elem);
                        }
                        $elem.style.top = y - 20 - increase + "px";
                        $elem.style.opacity = (150 - increase) / 120;
                    }, 8);
                }, 70);
                $body.appendChild($elem);
            };
        };

    </script>
</head>

<body>
<div style="height: 10px;margin-left:20px; "><b>当前操作:</b><span style="color: red">请假记录</span></div>
<input type="text" id="leaveRecoredDate" name="user_date" style="width:155px;margin-left: 10px;" class="layui-input" placeholder="请选择日期或输入Id"/>
<input  id="leaveRecoredNameOrId" style="width:155px;margin-left: 10px;" class="layui-input" placeholder="请输入用户Id或用户姓名"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增
</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓 请假记录</span>
<a id="home" href="/home" class="glyphicon glyphicon-home"></a>
<a onclick="loginOut()" class="glyphicon glyphicon-off"></a>
<div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>请假 Id</th>
            <th>请假人员</th>
            <th>请假原因</th>
            <th>请假时长</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>
<!--请假记录新增 -->
<div class="modal fade" id="addModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true" onclick="addClose()">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">请假记录 添加</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">请假日期:</td>
                        <td>
                            <input type="text" id="addDate"  class="form-control date form_datetime"  name="user_date" style="width:130px" placeholder="请选择日期"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假人员:</td>
                        <td style="width:60%;">
                           <select id="addLeaveName" data-placeholder="请选择请假人员"  class="chosen-select" tabindex="-1"  style="display: none;"></select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假原因:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addLeaveReason"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假时长:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addLeaveDuration" placeholder="限定为数字"></input>
                        </td>
                        <td style="width: 20%">
                            <select  class="form-control" id="addLeaveDurationUnit">
                                <option value="天">天</option>
                                <option value="时">时</option>
                                <option value="分">分</option>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button onclick="addClose()" data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addLeaveRecord" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--请假记录 详情-->
<div class="modal fade" id="lookModal" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">请假记录 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">请假Id:</td>
                        <td>
                            <input  id="lookLeaveId"  class="form-control" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假人员:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookLeaveName"  readonly></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假原因:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="lookLeaveReason" readonly></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假时长:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookLeaveDuration" readonly></input>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--请假记录修改 -->
<div class="modal fade" id="updModal" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">请假记录 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">请假Id:</td>
                        <td>
                            <input  id="updLeaveId"  class="form-control date form_datetime" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%">请假人员:</td>
                        <td style="width:60%;">
                            <select id="updLeaveName" data-placeholder="请选择请假人员"  class="chosen-select" tabindex="-1"  style="display: none;"></select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假原因:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updLeaveReason"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">请假时长:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updleaveDuration" placeholder="限定为数字"></input>
                        </td>
                        <td style="width: 20%">
                            <select  class="form-control" id="updLeaveDurationUnit">
                                <option value="天">天</option>
                                <option value="时">时</option>
                                <option value="分">分</option>
                            </select>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updLeaveRecord" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
