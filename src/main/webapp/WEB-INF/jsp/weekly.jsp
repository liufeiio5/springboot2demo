<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/table.css"/>
    <link rel="stylesheet" href="/css/chosen.css"/>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-datetimepicker.min.css" media="screen">
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/laydate/laydate.js"></script>
    <script type="text/javascript" src="/js/Date.js"></script>
    <script type="text/javascript" src="/js/chosen.js"></script>
    <script src="/js/bootstrap-datetimepicker.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.fr.js" type="text/javascript" charset="utf-8"></script>
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
            width: 40px;
        }
    </style>
    <script >
        $(function () {
            $('.form_datetime').datetimepicker({
                bootcssVer: 3,
                language:  'zh-CN',
                minView: "month", //选择日期后，不会再跳转去选择时分秒
                format: 'yyyy-mm-dd',
                todayBtn: 1,
                autoclose: 1,
            });
            $('#addstartDate').datetimepicker('setDaysOfWeekDisabled', [0, 2, 3, 4, 5, 6]);
            $('#addstartDate').datetimepicker().on('changeDate',function(){
                var start = $("#addstartDate").val();
//			    alert(start)
                var year = start.slice(0, 4);
                var month = start.slice(5, 7);
                var day = start.slice(8, 10);
                var year = parseInt(year);
                var month = parseInt(month);
                var day = parseInt(day);
//               console.log("年" + year + "月" + month + "日" + day);
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
                //				console.log("年" + year + "月" + month + "日" + day);
                mouth=month<10?'0'+month:month
                day=day<10?'0'+day:day
                var autonumber = year + "-" + mouth + "-" + day;
                $("#addendDate").val(autonumber);

                //第几周
                var start = $("#addstartDate").val();
                var start = $("#addstartDate").val();
                var day = start.slice(8, 10);
//              console.log(day)
                var day = parseInt(day);
                var weekly;
                if (day < 7) {
                    weekly = 1
                } else {
                    weekly = day / 7;
                    if (weekly != 0) {
                        weekly = weekly + 1;
                    }
                }
                console.log(parseInt(weekly))
                $("#addweek").val("第"+parseInt(weekly)+"周");
            })
            $("#addstartDate").click(function() {
                $("#addendDate").val("");
                $("#addweek").val("");
            })

            $("#addassisman").html("")
            $("#updassisman").html("")
            //周小结  协助人下拉
            $.ajax({
                url: '/getUser',
                dataType: 'json',
                success: function (data) {
                    var str;
                    var json = data.data
                    for (var i in json) {
                        str = '<option value="' + json[i].fullName + '">' + json[i].fullName + '</option>';
                        $("#addassisman").append(str)
                        $("#updassisman").append(str)
                    }
                    $("#addassisman").trigger("liszt:updated");
                    $("#updassisman").trigger("liszt:updated");
                    $("#addassisman").chosen({
                        no_results_text:'未找到',
                    });
                    $("#updassisman").chosen({
                        no_results_text:'未找到',
                    });
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
                var sdate = $('#addstartDate').val().replace('-', '').replace('-', '');
                var edate = $('#addendDate').val().replace('-', '').replace('-', '');
                var week = $('#addweek').val().replace('第', '').replace('周', '');
                $.ajax({
                    url: "/addWeekly",
                    dataType: 'json',
                    data: {
                        week: week,
                        sdate: sdate,
                        edate: edate,
                    },
                    success: function (data) {
                        console.info(data.message)
                        if (data.message == "添加成功") {
                            layer.msg('添加成功!', {
                                icon: 1,
                                time: 1000
                            });
                            setTimeout(function wlh() {
                                window.location.href = "/weekly"
                            }, 500)
                        } else if (data.message == "不能提前创建周报") {
                            layer.msg("禁止提前创建周报");
                        } else if (data.message == "重复添加") {
                            layer.msg("禁止周报重复添加");
                        } else {
                            layer.msg("添加失败");
                        }
                    }
                });
            })
        })

        function inittable() {
            $("#tbody").html("");
            var weeklyYear = $("#weeklyYear").val().replace('-', '').replace('-', '');
            var weeklyMouth = $("#weeklyMouth").val().replace('-', '').replace('-', '');
            if (weeklyYear == '' && weeklyMouth != '') {
                layer.msg('月份需与年份配对')
                return false;
            }
            var userId = $("#userid").val().trim()
            $.ajax({
                type: 'get',
                url: 'getWeekly',
                dataType: 'json',
                data: {
                    year: weeklyYear,
                    mouth: weeklyMouth,
                    userId: userId
                },
                success: function (data) {
                    var json = data.data
                    $('#username').html('欢迎 ' + '<font color="red">' + data.fullName + '</font>' + ' 登录米仓周报');
                    for (i in json) {
                        var summaryId = json[i].summary_id
                        var difficultyId = json[i].difficulty_id
                        var programmeId = json[i].programme_id
                        var suggestId = json[i].suggest_id
                        var remarkId = json[i].remark_id
                        var id = json[i].id
                        var tr = $('<tr>');
                        tr.append($('<td>').html(json[i].id))
                        tr.append($('<td>').html(json[i].fullName))
                        tr.append($('<td>').html(json[i].sdate))
                        tr.append($('<td>').html(json[i].edate))
                        tr.append($('<td>').html(json[i].week).attr("week", json[i].week).css("color", "blue").css("cursor", "pointer").css('margin-right', '10px').attr('data-toggle', 'modal').attr('data-target', '#getModal').addClass('weekbtn').attr("sdate", json[i].sdate).attr("edate", json[i].edate).attr("userId", data.userId))
                        tr.append($('<td>').append($('<button>').attr('summaryId', json[i].summary_id).addClass('addSummary btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setModal2').html('+')).append($('<table>').css('width', '100%').addClass('addSmmarytel' + '_' + id)))
                        summary(id, summaryId)
                        tr.append($('<td>').addClass('progress' + '_' + id))
                        tr.append($('<td>').append($('<button>').attr('difficultyId', json[i].difficulty_id).addClass('addDifficulty btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty1').html('+')).append($('<table>').css('width', '100%').addClass('weeklydifficulty' + '_' + id)))
                        difficulty(id, difficultyId)
                        tr.append($('<td>').append($('<button>').attr('programmeId', json[i].programme_id).addClass('addProgramme btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setProgramme1').html('+')).append($('<table>').css('width', '100%').addClass('weeklyProgramme' + '_' + id)))
                        programme(id, programmeId)
                        tr.append($('<td>').append($('<button>').attr('suggestId', json[i].suggest_id).addClass('addSuggest btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setSuggest1').html('+')).append($('<table>').css('width', '100%').addClass('weeklySuggest' + '_' + id)))
                        suggest(id, suggestId)
                        tr.append($('<td>').append($('<button>').attr('remarkId', json[i].remark_id).addClass('addRemark btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setRemark1').html('+')).append($('<table>').css('width', '100%').addClass('weeklyRemark' + '_' + id)))
                        remark(id, remarkId);
                        var del = $('<button>').attr("summaryId", json[i].summary_id).attr("difficultyId", json[i].difficulty_id).attr("programmeId", json[i].programme_id).attr("suggestId", json[i].suggest_id).attr("remarkId", json[i].remark_id).addClass('btn btn-danger delbtn').html('<i class="glyphicon glyphicon-trash"></i>');
                        var td = $('<td>');
                        td.append(del);
                        tr.append(td);
                        $("#tbody").append(tr);
                    }
                    if (data.code != "200") {
                        layer.msg('当前数据为空!');
                    }

                    //第几周 跳转 日报
                    $('.weekbtn').click(function () {
                        var startDate = $(this).attr('sdate')
                        var endDate = $(this).attr('edate')
                        var userId = $(this).attr('userId')
                        var year = startDate.slice(0, 4)
                        var mouth = startDate.slice(4, 6)
                        var week = $(this).attr('week')
                        $("#weekspan").html(year + '年' + mouth + '月  ' + '第' + week + '周');
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
                                        $("#tbodys").append(tr);
                                    }
                                }
                            }
                        })
                    })

                    //删除
                    $('.delbtn').click(function () {
                        var id = $(this).parent().parent().children().eq(0).text()
                        summaryId = $(this).attr("summaryId")
                        difficultyId = $(this).attr("difficultyId")
                        programmeId = $(this).attr("programmeId")
                        suggestId = $(this).attr("suggestId")
                        remarkId = $(this).attr("remarkId")
                        layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                            title: '信息',
                            btn: ['朕意已决', '泥奏凯，朕再想一想']
                        }, function (index) {
                            $.ajax({
                                dataType: 'json',
                                url: "/deleteWeekly",
                                data: {
                                    id: id,
                                    summaryId: summaryId,
                                    difficultyId: difficultyId,
                                    programmeId: programmeId,
                                    suggestId: suggestId,
                                    remarkId: remarkId
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

                    //给提交传值
                    $(".addSummary").click(function () {
                        $("#midleValueId").val($(this).attr('summaryId'))
                    })
                    //提交  添加周小结
                    $("#addSummary").click(function () {
                        checkAddInput()
                        var summaryId = $("#midleValueId").val()
                        var workHours = $('#addworkHours').val() + $('#addworkHoursUnit').val()
                        var singleProgress=$('#addsingleProgress').val()
                        if(parseInt(singleProgress)>100||parseFloat('100%') <= parseFloat(singleProgress)){
                            layer.msg("您输入的进度指数不规范")
                            ajax().abort()
                        }
                        var assisMan
                        if($("#addassisman").val()!=null){
                           assisMan = $("#addassisman").val().toString();
                        }
                        $.ajax({
                            url: "/addSummary",
                            dataType: 'json',
                            data: {
                                summaryId: summaryId,
                                content: $('#addcontent').val(),
                                singleProgress: singleProgress,
                                workHours: workHours,
                                assismans: assisMan
                            },
                            async:false,
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

                    //给提交传值 困难
                    $(".addDifficulty").click(function () {
                        $("#dmidleValueId").val($(this).attr('difficultyId'))
                    })
                    //提交  添加周 困难
                    $("#addDifficulty").click(function () {
                        var addDifficutyContent=$('#addDifficutyContent').val().trim()
                        if(addDifficutyContent==null||addDifficutyContent==''){
                            layer.msg("困难内容不能为空!");
                            ajax().abort()
                        }
                        var difficultyId = $("#dmidleValueId").val()
                        $.ajax({
                            url: "/addWeeklyDifficulty",
                            dataType: 'json',
                            data: {
                                difficultyId: difficultyId,
                                difficultyContent: addDifficutyContent
                            },
                            async:false,
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
                    //给提交传值 方案
                    $(".addProgramme").click(function () {
                        $("#pmidleValueId").val($(this).attr('programmeId'))
                    })
                    //提交 添加周 方案
                    $("#addProgramme").click(function () {
                        var addProgrammeContent=$('#addProgrammeContent').val().trim()
                        if(addProgrammeContent==null||addProgrammeContent==''){
                            layer.msg("方案内容不能为空!");
                            ajax().abort()
                        }
                        var programmeId = $("#pmidleValueId").val()
                        $.ajax({
                            url: "/addWeeklyProgramme",
                            dataType: 'json',
                            data: {
                                programmeId: programmeId,
                                programmeContent:addProgrammeContent
                            },
                            async:false,
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
                    //给提交传值 建议
                    $(".addSuggest").click(function () {
                        $("#smidleValueId").val($(this).attr('suggestId'))
                    })
                    //提交 添加周 建议
                    $("#addSuggest").click(function () {
                        var addSuggestContent=$('#addSuggestContent').val().trim()
                        if(addSuggestContent==null||addSuggestContent==''){
                            layer.msg("建议内容不能为空!");
                            ajax().abort()
                        }
                        var suggestId = $("#smidleValueId").val()
                        $.ajax({
                            url: "/addWeeklySuggest",
                            dataType: 'json',
                            data: {
                                suggestId: suggestId,
                                suggestContent: addSuggestContent
                            },
                            async:false,
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
                    //给提交传值 备注
                    $(".addRemark").click(function () {
                        $("#rmidleValueId").val($(this).attr('remarkId'))
                    })
                    //提交 添加周 备注
                    $("#addRemark").click(function () {
                        var addRemarkContent=$('#addRemarkContent').val().trim()
                        if(addRemarkContent==null||addRemarkContent==''){
                            layer.msg("备注内容不能为空!");
                            ajax().abort()
                        }
                        var remarkId = $("#rmidleValueId").val()
                        $.ajax({
                            url: "/addWeeklyRemark",
                            dataType: 'json',
                            data: {
                                remarkId: remarkId,
                                remarkContent: addRemarkContent
                            },
                            async:false,
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

            //获取周 困难集
            function difficulty(id, difficultyId) {
                $.ajax({
                    url: '../getWeeklyDifficulty',
                    dataType: 'json',
                    data: {
                        difficultyId: difficultyId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>');
                            str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].difficultyContent))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("id", json[i].id).attr("difficultyId", difficultyId).attr("difficultyContent", json[i].difficultyContent)
                                .addClass('btn btn-xs lookDifficulty').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("difficultyId", difficultyId).attr("content", json[i].content).attr("difficultyContent", json[i].difficultyContent)
                                .addClass('btn btn-xs updDifficulty').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty3').html('改').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-xs delDifficulty').html('删');
                            str.append(look)
                            str.append(upd)
                            str.append(del)
                            str.append(td);
                            $(".weeklydifficulty" + '_' + id).append(str);
                        }
                        //查看详情 周困难
                        $('.lookDifficulty').click(function () {
                            $("#lookdifficultyContent").val($(this).attr('difficultyContent'))
                        })
                        //修改 周 困难
                        $('.updDifficulty').click(function () {
                            var sdate = $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            var id = $(this).attr('id')
                            var difficultyId = $(this).attr('difficultyId')
                            $("#updDifficultyContent").val($(this).attr('difficultyContent'))
                            //提交
                            $("#updDifficulty").click(function () {
                                var difficultyContent = $("#updDifficultyContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklyDifficulty',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            difficultyId: difficultyId,
                                            difficultyContent: difficultyContent,
                                            sdate: sdate
                                        },
                                        success: function (data) {
                                            console.info(data)
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/weekly"
                                                }, 500)
                                                layer.msg('已修改!', {
                                                    icon: 1,
                                                    time: 1000
                                                });
                                            } else if (data.message == "当前时间不在此周内，禁止修改") {
                                                layer.msg('当前时间不在此周内，禁止修改!', {
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
                        //删除  一周 困难
                        $('.delDifficulty').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deleteWeeklyDifficulty",
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

            //获取周 方案集
            function programme(id, programmeId) {
                $.ajax({
                    url: '../getWeeklyProgramme',
                    dataType: 'json',
                    data: {
                        programmeId: programmeId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>');
                            str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].programmeContent))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("programmeId", programmeId).attr("programmeContent", json[i].programmeContent)
                                .addClass('btn btn-xs lookProgramme').attr('data-toggle', 'modal').attr('data-target', '#setProgramme2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("programmeId", programmeId).attr("programmeContent", json[i].programmeContent)
                                .addClass('btn btn-xs updProgramme').attr('data-toggle', 'modal').attr('data-target', '#setProgramme3').html('改').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-xs delProgramme').html('删');
                            str.append(look)
                            str.append(upd)
                            str.append(del)
                            str.append(td);
                            $(".weeklyProgramme" + '_' + id).append(str);
                        }
                        //查看详情 周 方案
                        $('.lookProgramme').click(function () {
                            $("#lookProgrammeContent").val($(this).attr('programmeContent'))
                        })
                        //修改 周 方案
                        $('.updProgramme').click(function () {
                            var id = $(this).attr('id')
                            var programmeId = $(this).attr('programmeId')
                            $("#updProgrammeContent").val($(this).attr('programmeContent'))
                            var sdate = $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            //提交
                            $("#updProgramme").click(function () {
                                var programmeContent = $("#updProgrammeContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklyProgramme',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            programmeId: programmeId,
                                            programmeContent: programmeContent,
                                            sdate: sdate
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
                                            } else if (data.message == "当前时间不在此周内，禁止修改") {
                                                layer.msg("当前时间不在此周内，禁止修改", {
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
                        //删除  一周 方案
                        $('.delProgramme').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deleteWeeklyProgramme",
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

            //获取周 建议
            function suggest(id, suggestId) {
                $.ajax({
                    url: '../getWeeklySuggest',
                    dataType: 'json',
                    data: {
                        suggestId: suggestId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>');
                            str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].suggestContent))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("suggestId", suggestId).attr("suggestContent", json[i].suggestContent)
                                .addClass('btn btn-xs lookSuggest').attr('data-toggle', 'modal').attr('data-target', '#setSuggest2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("suggestId", suggestId).attr("suggestContent", json[i].suggestContent)
                                .addClass('btn btn-xs updSuggest').attr('data-toggle', 'modal').attr('data-target', '#setSuggest3').html('改').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-xs delSuggest').html('删');
                            str.append(look)
                            str.append(upd)
                            str.append(del)
                            str.append(td);
                            $(".weeklySuggest" + '_' + id).append(str);
                        }
                        //查看详情 周 建议
                        $('.lookSuggest').click(function () {
                            $("#lookSuggestContent").val($(this).attr('suggestContent'))
                        })
                        //修改 周 建议
                        $('.updSuggest').click(function () {
                            var id = $(this).attr('id')
                            var suggestId = $(this).attr('suggestId')
                            $("#updSuggestContent").val($(this).attr('suggestContent'))
                            var sdate = $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            //提交
                            $("#updSuggest").click(function () {
                                var suggestContent = $("#updSuggestContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklySuggest',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            suggestId: suggestId,
                                            suggestContent: suggestContent,
                                            sdate: sdate
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
                                            } else if (data.message == "当前时间不在此周内，禁止修改") {
                                                layer.msg("当前时间不在此周内，禁止修改", {
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
                        //删除  一周 建议
                        $('.delSuggest').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deleteWeeklySuggest",
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

            //获取周 备注
            function remark(id, remarkId) {
                $.ajax({
                    url: '../getWeeklyRemark',
                    dataType: 'json',
                    data: {
                        remarkId: remarkId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>');
                            str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].remarkContent))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("remarkId", remarkId).attr("remarkContent", json[i].remarkContent)
                                .addClass('btn btn-xs lookRemark').attr('data-toggle', 'modal').attr('data-target', '#setRemark2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("remarkId", remarkId).attr("remarkContent", json[i].remarkContent)
                                .addClass('btn btn-xs updRemark').attr('data-toggle', 'modal').attr('data-target', '#setRemark3').html('改').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-xs delRemark').html('删');
                            str.append(look)
                            str.append(upd)
                            str.append(del)
                            str.append(td);
                            $(".weeklyRemark" + '_' + id).append(str);

                        }
                        //查看详情 周 备注
                        $('.lookRemark').click(function () {
                            $("#lookRemarkContent").val($(this).attr('remarkContent'))
                        })
                        //修改 周 备注
                        $('.updRemark').click(function () {
                            var id = $(this).attr('id')
                            var remarkId = $(this).attr('remarkId')
                            $("#updRemarkContent").val($(this).attr('remarkContent'))
                            var sdate = $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            //提交
                            $("#updRemark").click(function () {
                                var remarkContent = $("#updRemarkContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklyRemark',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            remarkId: remarkId,
                                            remarkContent: remarkContent,
                                            sdate: sdate
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
                                            } else if (data.message == "当前时间不在此周内，禁止修改") {
                                                layer.msg("当前时间不在此周内，禁止修改", {
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
                        //删除  一周 备注
                        $('.delRemark').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deleteWeeklyRemark",
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
                            progress = progress + progress1;
                            //小数转百分数
                            if (i == json.length - 1) {
                                var number = (Number(progress * 100).toFixed(1) - 100) / json.length;
                                $('.progress' + '_' + id).html(number.toFixed(1) + "%")
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
                            $(".addSmmarytel" + '_' + id).append(str);
                        }

                        //查看详情 周小结
                        $('.looksummary').click(function () {
                            $("#assisMan").val('')
                            $("#content").val($(this).attr('content'))
                            $("#singleProgress").val($(this).attr('singleProgress'))
                            $("#workHours").val($(this).attr('workHours'))
                            var assisman = $(this).attr('assisMan');
                            $("#assisMan").val(assisman.replace(',', ' '))
                        })

                        //修改 周小结
                        $('.updsummary').click(function () {
                            var id = $(this).attr('id')
                            var summaryId = $(this).attr('summaryId')
                            $("#updcontent").val($(this).attr('content'))
                            $("#updsingleProgress").val($(this).attr('singleProgress'))
                            $("#updworkHours").val($(this).attr('workHours'))
                            var assisman = $(this).attr('assisMan')

                            console.log(assisman)
                            if (!$.isEmptyObject(assisman)) {
                                //assisMan = $(this).attr('assisMan');
                                chose_mult_set_ini('#updassisman',assisman);
                                //初始化
                                $("#updassisman").chosen();
                                $("#updassisman").trigger("chosen:updated");
                                //$(".chzn-select").chosen();
                            }
                            // 多选 select 数据初始化
                            function chose_mult_set_ini(select, values) {
                                //$(select).empty();
                                //$(select).trigger("chosen:updated");
                                var arr = values.split(',');
                                var length = arr.length;
                                var value = '';
                                for (i = 0; i < length; i++) {
                                    value = arr[i];
                                    $(select + " option[value='" + value + "']").attr('selected', true);
                                }
                                $(select).trigger("chosen:updated");
                            }

                            var sdate = $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            //提交
                            $("#updSummary").click(function () {
                                var content = $("#updcontent").val().trim()
                                var singleProgress = $("#updsingleProgress").val().trim()
                                var workHours = $('#updworkHours').val().substr(0, $('#updworkHours').val().length - 1) + $("#updworkHoursUnit").val().slice($('#addworkHoursUnit').val().length - 1)
                                //校验
                                if(content==null || content==''){
                                    layer.msg("周结内容不能为空")
                                    ajax().abort()
                                }
                                if(singleProgress==null || singleProgress==''){
                                    layer.msg("进度不能为空")
                                    ajax().abort()
                                }
                                if($('#updworkHours').val().trim()==null || $('#updworkHours').val().trim()==''){
                                    layer.msg("工时不能为空")
                                    ajax().abort()
                                }
                                if(parseInt(singleProgress)>100||parseFloat('100%') < parseFloat(singleProgress)){
                                    layer.msg("您输入的进度指数不规范")
                                    ajax().abort()
                                }
                                var assisman =''
                                if($("#updassisman").val()!=null){
                                    assisman = $("#updassisman").val().toString()
                                }
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
                                            assismans: assisman,
                                            sdate: sdate
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
                                            } else if (data.message == "当前时间不在此周内，禁止修改") {
                                                layer.msg("当前时间不在此周内，禁止修改", {
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

        //检查周小结 内容 进度 工时 的输入是否为空
        function checkAddInput() {
            var addcontent = $("#addcontent").val().trim();
            var addsingleProgress = $("#addsingleProgress").val().trim();
            var addworkHours = $("#addworkHours").val().trim();
            if (addcontent == null || addcontent == '') {
                layer.msg("内容不能为空！");
                ajax().abort;
            }
            if (addsingleProgress == null || addsingleProgress == '') {
                layer.msg("进度不能为空！");
                ajax.abort;
            }
            if (addworkHours == null || addworkHours == '') {
                layer.msg("工时不能为空!");
                ajax.abort;
            }
        }

        //年
        $(function year() {
            $("#weeklyYear").html("")
            //获取系统年份
            var myDate = new Date().getFullYear();
            var str = '<option value="">-- 请选择年份--</option>';
            $("#weeklyYear").append(str)
            for (var i = 0; i <= 3; i++) {
                var str1 = '<option value="' + (myDate - i) + '" >' + (myDate - i) + '</option>';
                $("#weeklyYear").append(str1);
            }
        })

        //月
        $(function mouth() {
            $("#weeklyMouth").html("")
            var str = '<option value="">-- 请选择月份--</option>';
            $("#weeklyMouth").append(str)
            for (var i = 1; i <= 12; i++) {
                if (i < 10) {
                    var str1 = '<option value="0' + i + '" >' + i + '月</option>';
                } else {
                    var str1 = '<option value="' + i + '" >' + i + '月</option>';
                }
                $("#weeklyMouth").append(str1);
            }
        })
    </script>
</head>

<body>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">周报</span></div>
<select id="weeklyYear" style="margin-left: 10px;">
    <option value="">-- 请选择年份 --</option>
</select>
<select id="weeklyMouth" style="margin-left: 10px;">
    <option value="">-- 请选择月份 --</option>
</select>
<input id="userid" placeholder="请输入用户ID"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询
</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增
</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username"></span>
<a id="home" href="/home" class="glyphicon glyphicon-home"></a>
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
                            <input type="text" id="addstartDate"  class="form-control date form_datetime"  name="user_date" style="width:130px"
                                   placeholder="请选择开始时间"/>
                            <div>—</div>
                            <input type="text" id="addendDate" class="form-control date form_datetime" name="user_date" style="width:130px" class="form-control"
                                   placeholder="请选择结束时间"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">周:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addweek"></input>
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
                            <input type="text" id="addsingleProgress" placeholder="  例如 : 30f或8h或3d或1w">
                        </td>
                        <td style="width:15%;text-align: center"><span id="span1" style="color:red"></span></td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addworkHours" placeholder="例如 : 55% 或 55"/>
                        </td>
                        <td>
                            <select id="addworkHoursUnit">
                                <option value="分">m(分)</option>
                                <option value="时">h(时)</option>
                                <option value="天">d(天)</option>
                                <option value="周">w(周)</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%">
                            <select id="addassisman" data-placeholder="请选择协助人" multiple class="chzn-select">
                            </select>
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
                            <input type="text" id="updsingleProgress">
                        </td>
                        <td style="width:15%;text-align: center"><span id="span2" style="color:red"></span></td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updworkHours"></input>
                        </td>
                        <td>
                            <select id="updworkHoursUnit">
                                <option value="分">m(分)</option>
                                <option value="时">h(时)</option>
                                <option value="天">d(天)</option>
                                <option value="周">w(周)</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%;">
                            <select id="updassisman" data-placeholder="请选择协助人" multiple class="chzn-select">
                            </select>
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

<!--一周 困难 新增-->
<div class="modal fade" id="setDifficulty1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周困难 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体困难:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addDifficutyContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <input type="text" id="dmidleValueId" style="display: none">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addDifficulty" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周困难 详情 -->
<div class="modal fade" id="setDifficulty2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周困难详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体困难:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="lookdifficultyContent" readonly></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!--一周困难 修改 -->
<div class="modal fade" id="setDifficulty3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周困难 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体困难:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updDifficultyContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updDifficulty" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周 方案 新增-->
<div class="modal fade" id="setProgramme1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周方案 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体方案:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addProgrammeContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <input type="text" id="pmidleValueId" style="display: none">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addProgramme" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周 方案 详情-->
<div class="modal fade" id="setProgramme2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周方案 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体方案:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="lookProgrammeContent"></textarea>
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
<!--一周 方案  修改 -->
<div class="modal fade" id="setProgramme3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周 方案 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体方案:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updProgrammeContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updProgramme" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周 建议 新增-->
<div class="modal fade" id="setSuggest1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周建议 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体建议:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addSuggestContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <input type="text" id="smidleValueId" style="display: none">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addSuggest" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周 建议 详情-->
<div class="modal fade" id="setSuggest2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周建议 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体建议:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="lookSuggestContent"></textarea>
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
<!--一周 建议  修改 -->
<div class="modal fade" id="setSuggest3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周 建议 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体建议:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updSuggestContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updSuggest" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周 备注 新增-->
<div class="modal fade" id="setRemark1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周备注 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体备注:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addRemarkContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <input type="text" id="rmidleValueId" style="display: none">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addRemark" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一周 备注 详情-->
<div class="modal fade" id="setRemark2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周备注 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体备注:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="lookRemarkContent"></textarea>
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
<!--一周 备注  修改 -->
<div class="modal fade" id="setRemark3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一周 备注 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">具体备注:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updRemarkContent"></textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="updRemark" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="getModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
                <div>
                    <div align="center"><b><font size="16" color="red"><span id="weekspan"></span>所有日报</font></b></div>
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
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</body>
</html>
