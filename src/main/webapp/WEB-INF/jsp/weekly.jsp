<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/table.css"/>
    <link rel="stylesheet" href="/css/chosen.css"/>
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/laydate/laydate.js"></script>
    <script type="text/javascript" src="/js/Date.js"></script>
    <script type="text/javascript" src="/js/chosen.js"></script>
    <style>
        .asd {
            width: 150px;
        }

        #table-bordered thead tr th {
            text-align: center;
        }

        #table-bordered thead tr th:nth-of-type(1) {
            width: 50px;
        }

        #table-bordered thead tr th:nth-of-type(2) {
            width: 70px;
        }

        #table-bordered thead tr th:nth-of-type(3) {
            width: 80px;
        }

        #table-bordered thead tr th:nth-of-type(4) {
            width: 80px;
        }

        #table-bordered thead tr th:nth-of-type(5) {
            width: 60px;
        }

        #table-bordered thead tr th:nth-of-type(6) {
            max-width: 450px;
            min-width: 300px;
        }

        #table-bordered thead tr th:nth-of-type(7) {
            width: 100px;
        }

        #table-bordered thead tr th:nth-of-type(8) {
            width: 205px;
        }

        #table-bordered thead tr th:nth-of-type(9) {
            width: 205px;
        }

        #table-bordered thead tr th:nth-of-type(10) {
            width: 205px;
        }

        #table-bordered thead tr th:nth-of-type(11) {
            width: 205px;
        }

        #table-bordered thead tr th:nth-of-type(12) {
            width: 120px;
        }
    </style>
    <script>
        $(function () {

                var date1 = new Date('2018-12-31');
                alert(date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate())

                var date2 = new Date(date1);
                date2.setDate(date1.getDate()+7);
                var times = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
                alert('111111:'+times);



            function fun_date(aa){
                var date1 = new Date(),
                time1=date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate();//time1表示当前时间
                var date2 = new Date(date1);
                date2.setDate(date1.getDate()+aa);
                var time2 = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
                return time2;
            }

            alert('sssssssssssssssssssss'+fun_date(7))



            //周小结  协助人下拉

            $.ajax({
                url: '/getUser',
                dataType: 'json',
                success: function (data) {
                    var str;
                    var sel;
                    var json = data.data
                    /*<select id="secGroupOwner" data-placeholder="请选择协助人" class="chzn-select" multiple+
                            style="width: 300px; height: 13px; line-height: 16px; border-style: none;"></select>*/
                    sel = "<select id='secGroupOwner' data-placeholder='请选择协助人' class='chzn-select' multiple style='width: 300px; height: 13px; line-height: 16px; border-style: none;'>" + '</select>';
                    $('#tbs').append(sel);
                    for (var i in json) {
                        str = '<option value="' + json[i].fullName + '">' + json[i].fullName + '</option>';
                        $("#secGroupOwner").append(str)
                    }
                    $("#secGroupOwner").trigger("liszt:updated");

                    $("#secGroupOwner").chosen();
                }
            })


            var mapss
            laydate.render({
                elem: '#startDate'
                , type: 'month'

            });
            laydate.render({
                elem: '#endDate'
                , type: 'month'
            });
            laydate.render({
                elem: '#sDate',
                done: (function (value) {

                    var start = $("#sDate").val();
                    var year = start.slice(0, 4);
                    var month = start.slice(5, 7);
                    var day = start.slice(8, 10);
                    var year = parseInt(year);
                    var month = parseInt(month);
                    var day = parseInt(day);

                    var dd = new Date();
                    var week = dd.getDay(); //获取时间的星期数
                    var minus = week ? week - 1 : 6;
                    dd.setDate(dd.getDate() - minus); //获取minus天前的日期
                    var y = dd.getFullYear();
                    var m = dd.getMonth() + 1 -1 - 1; //获取月份
                    var d = dd.getDate();
                    var first = y + "-" + m + "-" + d;
                    alert('first' + first)


                    var ff = new Date();
                    ff.setDate(dd.getDate() + 7); //获取minus天前的日期
                    var y = ff.getFullYear();
                    var m = ff.getMonth() + 1 -1 -1; //获取月份
                    var d = ff.getDate();
                    var two = y + "-" + m + "-" + d;
                    alert('two' + two)


                    var ee = new Date();
                    ee.setDate(ff.getDate() + 7); //获取minus天前的日期
                    var y = ee.getFullYear();
                    var m = ee.getMonth() + 1 -1 -1; //获取月份
                    var d = ee.getDate();
                    var three = y + "-" + m + "-" + d;
                    alert('three' + three)


                    var gg = new Date();
                    gg.setDate(ee.getDate() + 7); //获取minus天前的日期
                    var y = gg.getFullYear();
                    var m = gg.getMonth() + 1 -1 -1; //获取月份
                    var d = gg.getDate();
                    var four = y + "-" + m + "-" + d;
                    alert('four' + four)


                    var qq = new Date();
                    qq.setDate(gg.getDate() + 7); //获取minus天前的日期
                    var y = qq.getFullYear();
                    var m = qq.getMonth() + 1 - 1 -1; //获取月份
                    var d = qq.getDate();
                    var five = y + "-" + m + "-" + d;
                    alert('five' + five)

                    //第几周
                    var start = $("#sDate").val();
                    var start = $("#sDate").val();
                    var day = start.slice(8, 10);
                    var day = parseInt(day);
                    var month = start.slice(5, 7);
                    var weekly;

                    var weekly;
                    if (day < 7 + 3) {
                        weekly = 1
                    } else if (day < 10 + 7) {
                        weekly = 2
                    } else if (day < 10 + 14) {
                        weekly = 3
                    } else if (day < 10 + 21) {
                        weekly = 4
                    } else {
                        weekly = day / 7;
                        if (weekly != 0) {
                            weekly = weekly + 1;
                        }
                    }

                    alert('weekly:' + parseInt(weekly))

                    if (parseInt(weekly) == 1) {
                        $('#sDate').val(first)
                    }

                    if (parseInt(weekly) == 2) {
                        $('#sDate').val(two)
                    }

                    if (parseInt(weekly) == 3) {
                        $('#sDate').val(three)
                    }

                    if (parseInt(weekly) == 4) {
                        $('#sDate').val(four)
                    }

                    var start = $("#sDate").val();
                    var year = start.slice(0, 4);
                    var month = start.slice(5, 7);
                    var day = start.slice(8, 10);
                    var year = parseInt(year);
                    var month = parseInt(month);
                    var day = parseInt(day);
                    if (day < 26) {
                        day = day + 4;
                    } else {
                        switch (month) {
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                                day = day + 4 - 31;
                                month = month + 1;
                                break;
                            case 4:
                            case 6:
                            case 9:
                            case 11:
                                day = day + 4 - 30;
                                month = month + 1;
                                break;
                            case 2:
                                day = day + 4 - 29;
                                month = month + 1;
                                break;
                            case 12:
                                day = day + 4 - 31;
                                year = year + 1;
                                month = 1;
                        }
                    }
                    var autonumber = year + "-" + month + "-" + day;
                    $("#eDate").val(autonumber);

                    //第几周
                    var start = $("#sDate").val();
                    var start = $("#sDate").val();
                    var day = start.slice(8, 10);
                    var day = parseInt(day);
                    var month = start.slice(5, 7);
                    var weekly;

                    if (day < 7) {
                        weekly = 1
                    } else {
                        weekly = day / 7;
                        if (weekly != 0) {
                            weekly = weekly + 1;
                        }
                    }

                    $("#week").val("第" + parseInt(weekly) + "周");
                })
            })

            laydate.render({
                elem: '#eDate'
            });
            laydate.render({
                elem: '#sTime',
            });
            laydate.render({
                elem: '#eTime',
            });
            //初始化
            inittable();

            //查询
            $("#query").click(function () {
                inittable()
            })

            $("#sDate").click(function () {
                $("#eDate").val("");
                $("#week").val("");
            })

            $('#add').click(function () {
                var difficulty = $('#difficulty').val();
                var programme = $('#programme').val();
                var suggest = $('#suggest').val();
                var remark = $('#remark').val();
                var sdate = $('#sDate').val().replace('-', '').replace('-', '');
                var edate = $('#eDate').val().replace('-', '').replace('-', '');
                var week = $('#week').val().replace('第', '').replace('周', '');
                if (difficulty == "") {
                    layer.msg('优秀是好事儿,但咱们不骄傲')
                }
                if (difficulty != "" && programme == "") {
                    layer.msg('问题都解决了，却不知如何解决的，不可原谅。')
                    return false;
                }
                if (suggest == "") {
                    layer.msg('我们期待您下次的建议。')
                }
                $.ajax({
                    url: "/addWeekly",
                    dataType: 'json',
                    data: {
                        difficulty: difficulty,
                        programme: programme,
                        suggest: suggest,
                        remark: remark,
                        week: week,
                        sdate: sdate,
                        edate: edate,
                    },
                    success: function (data) {
                        if (data.message == "添加成功") {
                            layer.msg('添加成功!', {
                                icon: 1,
                                time: 1000
                            });
                            setTimeout(function wlh() {
                                window.location.href = "/weekly"
                            }, 500)
                        } else if (data.message = "不能提前创建日报") {
                            layer.msg("不能提前创建日报，您这样，欺天当劈");
                        } else {
                            layer.msg("添加失败");
                        }
                    }
                });
            })
        })

        function inittable() {
            $("#tbody").html("");
            var startDate = $("#startDate").val().replace('-', '').replace('-', '');
            var endDate = $("#endDate").val().replace('-', '').replace('-', '');
            var userId = $("#userid").val().trim()
            $.ajax({
                type: 'get',
                url: 'getWeekly',
                dataType: 'json',
                data: {
                    'sdate': startDate,
                    'edate': endDate,
                    'userId': userId
                },
                success: function (data) {
                    var json = data.data
                    $('#username').html('欢迎 ' + '<font color="red">' + data.fullName + '</font>' + ' 登录米仓周报');
                    console.info(data)
                    for (i in json) {
                        var tr = $('<tr>');
                        tr.append($('<td>').html(json[i].id))
                        tr.append($('<td>').html(json[i].fullName))
                        tr.append($('<td>').html(json[i].sdate))
                        tr.append($('<td>').html(json[i].edate))
                        /**/
                        tr.append($('<td >').html(json[i].week).css('margin-right', '10px').css('color', 'blue').css('cursor', 'pointer').attr('data-toggle', 'modal').attr('data-target', '#setModa4').addClass('weekbtn').attr("sdate", json[i].sdate).attr("edate", json[i].edate).attr("userId", data.userId))
                        var summaryId = json[i].summary_id
                        var id = json[i].id
                        tr.append($('<td>').append($('<button>').attr('summaryId', json[i].summary_id).addClass('addSummary btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setModal2').html('+')).append($('<table>').css('width', '100%').addClass('addtel' + '_' + id)))
                        summary(id, summaryId)
                        tr.append($('<td>').addClass('progress' + '_' + id))
                        tr.append($('<td>').html(json[i].difficulty))
                        tr.append($('<td>').html(json[i].programme))
                        tr.append($('<td>').html(json[i].suggest))
                        tr.append($('<td>').html(json[i].remark))
                        var set = $('<button>').addClass('btn btn-warning updbtn').css('margin-right', '10px').attr('data-toggle', 'modal').attr('data-target', '#setModal').html('<i class="glyphicon glyphicon-edit"></i>');
                        var del = $('<button>').addClass('btn btn-danger delbtn').html('<i class="glyphicon glyphicon-trash"></i>');
                        var td = $('<td>');
                        td.append(set);
                        td.append(del);
                        tr.append(td);
                        $("#tbody").append(tr);
                    }

                    //第几周 跳转 日报
                    $('.weekbtn').click(function () {
                        var startDate = $(this).attr('sdate')
                        var endDate = $(this).attr('edate')
                        var userId = $(this).attr('userId')
                        $("#tbodys").empty();
                        $.ajax({
                            type: 'get',
                            url: '/getDaily',
                            dataType: 'json',
                            data: {
                                'startDate': startDate,
                                'endDate': endDate,
                                'userId': userId
                            },
                            success: function (data) {
                                for (var i in data.data) {
                                    var tr = $('<tr>');
                                    if (typeof (data.data[i].id) != 'undefined') {
                                        tr.append($('<td>').html(data.data[i].id))
                                        tr.append($('<td>').html(data.data[i].fullName))
                                        tr.append($('<td>').html(data.data[i].date))
                                        tr.append($('<td>').html(data.data[i].time))
                                        tr.append($('<td>').html(data.data[i].typeName))
                                        tr.append($('<td>').html(data.data[i].surfaceName))
                                        tr.append($('<td>').html(data.data[i].lineName))
                                        tr.append($('<td>').html(data.data[i].pointName))
                                        tr.append($('<td>').html(data.data[i].event))
                                        tr.append($('<td>').html(data.data[i].process))
                                        tr.append($('<td>').html(data.data[i].result))
                                        tr.append($('<td>').html(data.data[i].method))
                                        tr.append($('<td>').html(data.data[i].remark))
                                        var td = $('<td>');
                                        tr.append(td);
                                        $("#tbodys").append(tr);
                                    }
                                }
                            }
                        })
                    })

                    //删除
                    $('.delbtn').click(function () {
                        var id = $(this).parent().parent().children().eq(0).text()
                        alert(id)
                        layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                            title: '信息',
                            btn: ['朕意已决', '泥奏凯，朕再想一想']
                        }, function (index) {
                            $.ajax({
                                dataType: 'json',
                                url: "/deleteWeekly",
                                data: {
                                    id: id
                                },
                                success: function (data) {
                                    if (data.code == "200") {
                                        layer.msg('已删除!', {
                                            icon: 1,
                                            time: 1000
                                        });
                                        setTimeout(function wlh() {
                                            window.location.href = "/weekly"
                                        }, 500)
                                    } else {
                                        layer.msg(data.result, {
                                            icon: 1,
                                            time: 1000
                                        });
                                    }
                                }
                            });
                        });
                    })

                    //修改
                    $('.updbtn').click(function () {
                        var id = $(this).parent().parent().children().eq(0).text()
                        $("#uprogress").val($(this).parent().parent().children().eq(6).text())
                        $("#udifficulty").val($(this).parent().parent().children().eq(7).text())
                        $("#uprogramme").val($(this).parent().parent().children().eq(8).text())
                        $("#usuggest").val($(this).parent().parent().children().eq(9).text())
                        $("#uremark").val($(this).parent().parent().children().eq(10).text())
                        $('#setUpd').click(function () {
                            var uprogress = $("#uprogress").val()
                            var udifficulty = $("#udifficulty").val()
                            var uprogramme = $("#uprogramme").val()
                            var usuggest = $("#usuggest").val()
                            var uremark = $("#uremark").val()
                            layer.confirm('确认要修改吗？', function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    type: "post",
                                    url: "/updateWeekly",
                                    data: {
                                        id: id,
                                        difficulty: udifficulty,
                                        programme: uprogramme,
                                        suggest: usuggest,
                                        remark: uremark,
                                        progress: uprogress
                                    },
                                    success: function (data) {
                                        if (data.code == "200") {
                                            setTimeout(function wlh() {
                                                window.location.href = "/weekly"
                                            }, 500)
                                            layer.msg('已修改!', {
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
                                });
                            });
                        })
                    })

                    //给提交传值
                    $(".addSummary").click(function () {
                        $("#midleValueId").val($(this).attr('summaryId'))
                    })
                    //提交  添加周小结
                    $("#addSummary").click(function () {
                        var summaryId = $("#midleValueId").val()
                        $.ajax({
                            url: "/addSummary",
                            dataType: 'json',
                            data: {
                                summaryId: summaryId,
                                content: $('#addcontent').val(),
                                singleProgress: $('#addsingleProgress').val(),
                                workHours: $('#addworkHours').val(),
                                assisMan: $('#addassisMan').val()
                            },
                            success: function (data) {
                                if (data.message == "添加成功") {
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function wlh() {
                                        window.location.href = "/weekly"
                                    }, 500)
                                } else {
                                    layer.msg("添加失败");
                                }
                            }
                        });
                    })

                },
            })


            //获取周小结
            function summary(id, summaryId) {
                $.ajax({
                    url: '../getSummary',
                    dataType: 'json',
                    data: {
                        summaryId: summaryId
                    },
                    success: function (data) {
                        var json = data.data
                        var progress = 1;
                        for (i in json) {
                            //百分号转小数进行乘积
                            var progress1 = json[i].singleProgress.replace("%", "") / 100;
                            progress = progress * progress1;
                            //小数转百分数
                            if (i == json.length - 1) {
                                var number = Number(progress * 100).toFixed(1);
                                var flag = '';
                                if (number > 90) {
                                    flag = '(赞)'
                                } else if (number > 60) {
                                    flag = '(一般)'
                                } else {
                                    flag = '(叹)'
                                }
                                $('.progress' + '_' + id).html(number + "%" + flag)
                            }
                            var str = $('<tr>');
                            str.append($('<td>').css('width', '250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].content))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("id", json[i].id).attr("summaryId", json[i].summaryId).attr("content", json[i].content).attr("singleProgress", json[i].singleProgress).attr("workHours", json[i].workHours).attr("assisMan", json[i].assisMan)
                                .addClass('btn btn-xs looksummary').attr('data-toggle', 'modal').attr('data-target', '#setModal3').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("summaryId", json[i].summaryId).attr("content", json[i].content).attr("singleProgress", json[i].singleProgress).attr("workHours", json[i].workHours).attr("assisMan", json[i].assisMan)
                                .addClass('btn btn-xs updsummary').attr('data-toggle', 'modal').attr('data-target', '#setModal4').html('改').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-xs delsummary').html('删');
                            str.append(look).append(upd).append(del)
                            str.append(td);
                            $(".addtel" + '_' + id).append(str);
                        }

                        //查看详情 周小结
                        $('.looksummary').click(function () {
                            $("#content").val($(this).attr('content'))
                            $("#singleProgress").val($(this).attr('singleProgress'))
                            $("#workHours").val($(this).attr('workHours'))
                            $("#assisMan").val($(this).attr('assisMan'))
                        })


                        //修改 周小结
                        $('.updsummary').click(function () {
                            var id = $(this).attr('id')
                            var summaryId = $(this).attr('summaryId')
                            $("#updcontent").val($(this).attr('content'))
                            $("#updsingleProgress").val($(this).attr('singleProgress'))
                            $("#updworkHours").val($(this).attr('workHours'))
                            $("#updassisMan").val($(this).attr('assisMan'))

                            $("#updSummary").click(function () {
                                var content = $("#updcontent").val()
                                var singleProgress = $("#updsingleProgress").val()
                                var workHours = $("#updworkHours").val()
                                var assisMan = $("#updassisMan").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateSummary',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            summaryId: summaryId,
                                            content: content,
                                            singleProgress: singleProgress,
                                            workHours: workHours,
                                            assisMan: assisMan
                                        },
                                        success: function (data) {
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/weekly"
                                                }, 500)
                                                layer.msg('已修改!', {
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

                        //删除  一周小结
                        $('.delsummary').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deleteSummary",
                                    data: {
                                        id: id
                                    },
                                    success: function (data) {
                                        if (data.code == "200") {
                                            layer.msg('已删除!', {
                                                icon: 1,
                                                time: 1000
                                            });
                                            setTimeout(function wlh() {
                                                window.location.href = "/weekly"
                                            }, 500)
                                        } else {
                                            layer.msg(data.result, {
                                                icon: 1,
                                                time: 1000
                                            });
                                        }
                                    }
                                });
                            });
                        })
                    }
                })

            }
        }

        //检查事件 过程 结果 的输入是否为空
        function checkAddInput() {
            var event = $("#event").val();
            var process = $("#process").val();
            var result = $("#result").val();
            if (event == null || event == '') {
                alert("事件不能为空！");
                ajax().abort;
            }
            if (process == null || process == '') {
                alert("过程不能为空！");
                ajax.abort;
            }
            if (result == null || result == '') {
                alert("结果不能为空!");
                ajax.abort;
            }
        }

        function onkeydownfun() {
            if (event.keyCode == 13) {
                inittable()
            }
        }

        function prompt(obj) {
            var prompt = obj.value
            if (prompt == '100%') {
                $("#span1").html("老板,加薪")
                $("#span2").html("老板,加薪")
            } else if (prompt == '90%') {
                $("#span1").html("为山九仞")
                $("#span2").html("为山九仞")
            } else {
                $("#span1").html("够长才够强")
                $("#span2").html("够长才够强")
            }
            setTimeout(function () {
                $("#span1").html("")
                $("#span2").html("")
            }, 4000)
        }
    </script>
</head>

<body onkeydown="onkeydownfun()">
<input type="text" id="startDate" name="user_date" style="width:130px;margin-left: 10px;" class="layui-input"
       placeholder="请选择开始日期"/> —
<input type="text" id="endDate" name="user_date" style="width:130px" class="layui-input" placeholder="请选择结束日期"/>
<input id="userid" placeholder="请输入用户ID"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询
</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增
</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username"></span>
<a href="/table">日报</a>
<div>
    <table class="table table-bordered" id="table-bordered">
        <thead>
        <tr>
            <th>序号</th>
            <th>发布人</th>
            <th>起始日期</th>
            <th>结束日期</th>
            <th>第几周</th>
            <th>一周小结</th>
            <th>总体进度</th>
            <th>遇上的困难</th>
            <th>解决方案</th>
            <th>建议</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>

<!--新增 -->
<div class="modal fade" id="addModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">日期:</td>
                        <td>
                            <input type="text" id="sDate" name="user_date" style="width:130px" class="form-control"
                                   placeholder="请选择开始时间"/>
                            <div>—</div>
                            <input type="text" id="eDate" name="user_date" style="width:130px" class="form-control"
                                   placeholder="请选择结束时间"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">周:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="week"></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">遇到的问题:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="difficulty"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">解决方案:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="programme"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">建议:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="suggest"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">备注:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="remark"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="add" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--修改 -->
<div class="modal fade" id="setModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">日期:</td>
                        <td>
                            <input type="text" id="sTime" name="user_date" style="width:130px" class="layui-input"
                                   placeholder="请选择开始时间"/>
                            <div>—</div>
                            <input type="text" id="eTime" name="user_date" style="width:130px" class="layui-input"
                                   placeholder="请选择结束时间"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">一周小结:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="usummaryId"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">遇上的困难:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="udifficulty"></textarea>;
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">解决方案:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="uprogramme"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">建议:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="usuggest"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">备注:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="uremark"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="setUpd" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--一周小结 -->
<div class="modal fade" id="setModal2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周小结新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">内容:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addcontent"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">进度:</td>
                        <td style="width:60%;">
                            <select class="form-control" id="addsingleProgress" onchange="prompt(this)">
                                <option value="0%">--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择该小结的进度&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--</option>
                                <option value="10%">=&nbsp;&nbsp;10%&nbsp;&nbsp;</option>
                                <option value="20%">===&nbsp;&nbsp;20%&nbsp;&nbsp;</option>
                                <option value="30%">======&nbsp;&nbsp;30%&nbsp;&nbsp;</option>
                                <option value="40%">========&nbsp;&nbsp;40%&nbsp;&nbsp;</option>
                                <option value="50%">===========&nbsp;&nbsp;50%&nbsp;&nbsp;</option>
                                <option value="60%">===============&nbsp;&nbsp;60%&nbsp;&nbsp;</option>
                                <option value="70%">=================&nbsp;&nbsp;70%&nbsp;&nbsp;</option>
                                <option value="80%">===================&nbsp;&nbsp;80%&nbsp;&nbsp;</option>
                                <option value="90%">=====================&nbsp;&nbsp;90%&nbsp;&nbsp;</option>
                                <option value="100%">=======================&nbsp;&nbsp;100%&nbsp;&nbsp;</option>
                            </select>
                            </select>
                        </td>
                        <td style="width:15%;text-align: center"><span id="span1" style="color:red"></span></td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addworkHours"></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addassisMan"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <input type="text" id="midleValueId" style="display: none">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addSummary" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--一周小结  详情 -->
<div class="modal fade" id="setModal3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周小结详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">内容:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="content" readonly></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">进度:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="singleProgress" readonly></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="workHours" readonly></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="assisMan" readonly></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!--一周小结  修改 -->
<div class="modal fade" id="setModal4" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周小结修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">内容:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updcontent"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">进度:</td>
                        <td style="width:60%;">
                            <select class="form-control" id="updsingleProgress" onchange="prompt(this)">
                                <option value="0%">--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择该小结的进度&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--</option>
                                <option value="10%">=&nbsp;&nbsp;10%&nbsp;&nbsp;</option>
                                <option value="20%">===&nbsp;&nbsp;20%&nbsp;&nbsp;</option>
                                <option value="30%">======&nbsp;&nbsp;30%&nbsp;&nbsp;</option>
                                <option value="40%">========&nbsp;&nbsp;40%&nbsp;&nbsp;</option>
                                <option value="50%">===========&nbsp;&nbsp;50%&nbsp;&nbsp;</option>
                                <option value="60%">===============&nbsp;&nbsp;60%&nbsp;&nbsp;</option>
                                <option value="70%">=================&nbsp;&nbsp;70%&nbsp;&nbsp;</option>
                                <option value="80%">===================&nbsp;&nbsp;80%&nbsp;&nbsp;</option>
                                <option value="90%">=====================&nbsp;&nbsp;90%&nbsp;&nbsp;</option>
                                <option value="100%">=======================&nbsp;&nbsp;100%&nbsp;&nbsp;</option>
                            </select>
                            </select>
                        </td>
                        <td style="width:15%;text-align: center"><span id="span2" style="color:red"></span></td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updworkHours"></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updassisMan"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updSummary" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="setModa4" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" style="width: 1050px">
        <div class="modal-content">
            <div class="modal-body">
                <div>
                    <table class="table table-bordered" id="table-bordereds">
                        <thead>
                        <tr>
                            <th width="75px">编号</th>
                            <th width="75px">发布人</th>
                            <th width="30px">日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期</th>
                            <th width="30px">时&nbsp;&nbsp;间&nbsp;&nbsp;点</th>
                            <th width="50px">类型</th>
                            <th width="50px">&nbsp;&nbsp;面</th>
                            <th width="75px"><span>线</span></th>
                            <th width="75px">&nbsp;&nbsp;&nbsp;&nbsp;点</th>
                            <th width="150px" style="text-align: center">事件</th>
                            <th width="150px" style="text-align: center">过程</th>
                            <th width="150px" style="text-align: center">结果</th>
                            <th width="150px" style="text-align: center">解决方案</th>
                            <th width="150px" style="text-align: center">备注</th>
                        </tr>
                        </thead>
                        <tbody id="tbodys">

                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div id="tbs">

</div>


</body>
</html>

