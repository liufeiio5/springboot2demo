<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page isELIgnored="false" %>
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
            laydate.render({elem: '#dutyRecoredDate',format: 'yyyyMMdd'});
            laydate.render({elem: '#addDate',format: 'yyyyMMdd'});

            //值班人员 下拉
            $("#addempName").html("")
            $("#updempName").html("")
            $.ajax({
                url: '/getUser',
                dataType: 'json',
                success: function (data) {
                    var str;
                    var json = data.data
                    for (var i in json) {
                        str = '<option class="assisManItem" value="' + json[i].fullName + '">' + json[i].fullName + '</option>';
                        $("#addempName").append(str)
                        $("#updempName").append(str)
                    }

                    $("#addempName").trigger("liszt:updated");
                    $("#updempName").trigger("liszt:updated");
                    $("#addempName").chosen({
                        no_results_text:'未找到',
                    });
                    $("#updempName").chosen({
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

            //添加值班记录
            $('#addDutyRecord').unbind('click').click(function () {
                checkAddInput()
                var id=$("#addDate").val().replace(/\-/g, '')
                var empName;
                if($("#addempName").val()!=null){
                    empName=$("#addempName").val().toString().replace(/\,/g," ")
                }
                $.ajax({
                    url: "/addDutyRecord",
                    dataType: 'json',
                    data: {
                        id:id,
                        empName:empName,
                        customerServiceName:$("#addcustomerServiceName").val(),
                        jiraId:$("#addjiraId").val()
                    },
                    success: function (data) {
                        if (data.message == "添加成功") {
                            layer.msg('添加成功!', {
                                icon: 1,
                                time: 1000
                            });
                            setTimeout(function wlh() {
                                window.location.href = "/dutyRecord"
                            }, 500)
                        } else if (data.message == "禁止提前创建值班记录") {
                            layer.msg("禁止提前创建值班记录");
                        } else if (data.message == "重复添加") {
                            layer.msg("禁止值班记录重复添加");
                        } else {
                            layer.msg("添加失败");
                        }
                    }
                });
            })
        })

        function inittable() {
            var recordId=$("#dutyRecoredDate").val().replace(/\-/g, '')
            var dutyRecoredNameOrId=$("#dutyRecoredNameOrId").val()
            if(isNaN(dutyRecoredNameOrId)){
                var empName=dutyRecoredNameOrId
            }else{
                var userId=dutyRecoredNameOrId
            }
            $.ajax({
                type: 'get',
                url: 'getDutyRecord',
                dataType: 'json',
                data: {
                    id:recordId,
                    userId: userId,
                    empName:empName
                },
                success: function (data) {
                    $("#tbody").html("");
                    var json = data.data

                    for (var i in json) {
                        var tr = $('<tr>');
                        tr.append($('<td>').html(json[i].id))
                        tr.append($('<td>').html(json[i].empName))
                        tr.append($('<td>').html(json[i].customerServiceName))
                        tr.append($('<td>').html(json[i].jiraId))
                        var look = $('<button>').attr("id", json[i].id).attr("empName", json[i].empName).attr("customerServiceName", json[i].customerServiceName).attr("jiraId", json[i].jiraId)
                            .addClass('btn btn-info lookDutyRecord').attr('data-toggle', 'modal').attr('data-target', '#lookModal').css('margin-right', '10px').html('<i class="glyphicon glyphicon-asterisk"></i>');
                        var upd = $('<button>').attr("id", json[i].id).attr("empName", json[i].empName).attr("customerServiceName", json[i].customerServiceName).attr("jiraId", json[i].jiraId)
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
                    $(".lookDutyRecord").unbind('click').click(function () {
                        $("#lookrecordId").val($(this).attr("id"));
                        $("#lookempName").val($(this).attr("empName"));
                        $("#lookcustomerServiceName").val($(this).attr("customerServiceName"));
                        $("#lookjiraId").val($(this).attr("jiraId"));
                    })
                    //修改
                    $(".updbtn").unbind('click').click(function () {
                        var id=$(this).attr("id");
                        $("#updrecordId").val(id);
                        var empName=$(this).attr("empName");
                        $("#updcustomerServiceName").val($(this).attr("customerServiceName"));
                        $("#updjiraId").val($(this).attr("jiraId"));

                        $('.assisManItem').prop('selected', false).trigger("chosen:updated");
                        if (!$.isEmptyObject(empName)) {
                            if(empName.indexOf(" ") == -1){
                                $("#updempName" + " option[value='" + empName + "']").prop('selected', true);
                            }else {
                                var arr = empName.split(" ");
                                var length = arr.length;
                                var value = "";
                                for (i = 0; i < length; i++) {
                                    value = arr[i];
                                    $("#updempName" + " option[value='" + value + "']").prop('selected', true);
                                }
                            }
                            $("#updempName").chosen();
                            $("#updempName").trigger("chosen:updated");
                        }

                        //提交
                        $("#updDutyRecord").unbind('click').click(function () {
                            checkUpdInput()
                            layer.confirm('确认要修改吗？', function (index) {
                                $.ajax({
                                    url: '/updateDutyRecord',
                                    dataType: 'json',
                                    data: {
                                        id: id,
                                        empName: $("#updempName").val().toString().replace(/\,/g," "),
                                        customerServiceName:$("#updcustomerServiceName").val(),
                                        jiraId:$("#updjiraId").val()
                                    },
                                    success: function (data) {
                                        if (data.message == "修改成功") {
                                            setTimeout(function wlh() {
                                                window.location.href = "/dutyRecord"
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
            if ($("#updempName").val()== null || $("#updempName").val()== '') {
                layer.msg("值班人员不能为空！");
                ajax().abort;
            }
        }
        //校验
        function checkAddInput() {
            if ($("#addDate").val().trim() == null || $("#addDate").val().trim() == '') {
                layer.msg("日期不能为空！");
                ajax().abort;
            }
            if($("#addDate").val().trim().length!=8){
                layer.msg("格式不规范,日期或值班Id只能为8位数字")
                ajax().abort;
            }
            if ($("#addempName").val()== null ||$("#addempName").val()=="") {
                layer.msg("值班人员不能为空！");
                ajax.abort;
            }
        }
        //爱心气泡，听我号令
        onload = function() {
            var click_cnt = 0;
            var $html = document.getElementsByTagName("html")[0];
            var $body = document.getElementsByTagName("body")[0];
            $html.onclick = function(e) {
                var $elem = document.createElement("b");
                $elem.style.color = "#E94F06";
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
        function loginOut(){
            if(confirm("确定要退出登录吗？")){
                window.location.href="/logout";
            }
        }
    </script>
</head>

<body>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">值班记录</span></div>
<input type="text" id="dutyRecoredDate" name="user_date" style="width:155px;margin-left: 10px;" class="layui-input" placeholder="请选择日期或输入Id"/>
<input  id="dutyRecoredNameOrId" style="width:155px;margin-left: 10px;" class="layui-input" placeholder="请输入用户Id或用户姓名"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增
</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓 值班记录</span>
<a id="home" href="/home" class="glyphicon glyphicon-home"></a>
<a onclick="loginOut()" class="glyphicon glyphicon-off"></a>
<div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>值班 Id</th>
            <th>值班人员</th>
            <th>客服人员</th>
            <th>jira编号</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>
<!--值班记录新增 -->
<div class="modal fade" id="addModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">值班记录 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">值班日期:</td>
                        <td>
                            <input type="text" id="addDate"  class="form-control date form_datetime"  name="user_date" style="width:130px" placeholder="请选择日期"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">值班人员:</td>
                        <td style="width:60%;">
                            <select id="addempName" data-placeholder="请选择值班人员" multiple class="chzn-select"></select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">客服人员:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addcustomerServiceName"></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">jira编号:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addjiraId"></input>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addDutyRecord" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--值班记录 详情-->
<div class="modal fade" id="lookModal" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">值班记录 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">值班Id:</td>
                        <td>
                            <input  id="lookrecordId"  class="form-control" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">值班人员:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookempName"  readonly></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">客服人员:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookcustomerServiceName" readonly></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">jira编号:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookjiraId" readonly></input>
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
<!--值班记录修改 -->
<div class="modal fade" id="updModal" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">值班记录 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">值班Id:</td>
                        <td>
                            <input  id="updrecordId"  class="form-control date form_datetime" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">值班人员:</td>
                        <td style="width:60%;">
                            <select id="updempName" data-placeholder="请选择值班人员" multiple class="chzn-select"></select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">客服人员:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updcustomerServiceName"></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">jira编号:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updjiraId"></input>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updDutyRecord" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
