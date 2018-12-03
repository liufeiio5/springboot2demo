<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/table.css" />
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/laydate/laydate.js"></script>
    <script type="text/javascript" src="/js/Date.js"></script>
    <style>
        .asd {
            width: 150px;
        }
        #table-bordered thead tr th{
            text-align: center;
        }
        #table-bordered thead tr th:nth-of-type(1){
            width: 50px;
        }
        #table-bordered thead tr th:nth-of-type(2){
            width: 70px;
        }
        #table-bordered thead tr th:nth-of-type(3){
            width: 80px;
        }
        #table-bordered thead tr th:nth-of-type(4){
            width: 80px;
        }
        #table-bordered thead tr th:nth-of-type(5){
            width: 60px;
        }
        #table-bordered thead tr th:nth-of-type(6){
            max-width: 450px;
            min-width: 300px;
        }
        #table-bordered thead tr th:nth-of-type(7){
            width: 100px;
        }
        #table-bordered thead tr th:nth-of-type(8){
            width: 205px;
        }
        #table-bordered thead tr th:nth-of-type(9){
            width: 205px;
        }
        #table-bordered thead tr th:nth-of-type(10){
            width: 205px;
        }
        #table-bordered thead tr th:nth-of-type(11){
            width: 205px;
        }
        #table-bordered thead tr th:nth-of-type(12){
            width: 40px;
        }
    </style>
    <script>
        $(function() {

            var mapss
            laydate.render({
                elem: '#startDate'
                ,type: 'year'

            });
            laydate.render({
                elem: '#endDate'
                ,type: 'month'
            });
            laydate.render({
                elem: '#sDate',
                done: (function(value) {
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
                    if(day<10&&day>0){
                        day='0'+day
                    }
                    var autonumber = year + "-" + month + "-" + day;
                    $("#eDate").val(autonumber);

                    //第几周
                    var start = $("#sDate").val();
                    var start = $("#sDate").val();
                    var day = start.slice(8, 10);
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
                    $("#week").val("第" + parseInt(weekly) + "周");
                })
            })

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

            $("#sDate").click(function() {
                $("#eDate").val("");
                $("#week").val("");
            })

            $('#add').click(function() {
                var sdate = $('#sDate').val().replace('-','').replace('-','');
                var edate = $('#eDate').val().replace('-','').replace('-','');
                var week = $('#week').val().replace('第','').replace('周','');
                $.ajax({
                    url: "/addWeekly",
                    dataType: 'json',
                    data: {
                        week:week ,
                        sdate:sdate,
                        edate:edate,
                    },
                    success: function(data) {
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
            var weeklyYear = $("#weeklyYear").val().replace('-', '').replace('-', '');
            var weeklyMouth = $("#weeklyMouth").val().replace('-', '').replace('-', '');
            if(weeklyYear==''&&weeklyMouth!='') {
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
                    console.info(json)
                    for (i in json) {
                        var tr = $('<tr>');
                        tr.append($('<td>').html(json[i].id))
                        tr.append($('<td>').html(json[i].fullName))
                        tr.append($('<td>').html(json[i].sdate))
                        tr.append($('<td>').html(json[i].edate))
                        tr.append($('<td>').html(json[i].week).addClass("weekbtn").attr("sdate",json[i].sdate).attr("edate",json[i].edate))
                        var summaryId = json[i].summary_id
                        var difficultyId = json[i].difficulty_id
                        var programmeId = json[i].programme_id
                        var suggestId = json[i].suggest_id
                        var remarkId=json[i].remark_id
                        var id = json[i].id
                        tr.append($('<td>').append($('<button>').attr('summaryId',json[i].summary_id).addClass('addSummary btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setModal2').html('+')).append($('<table>').css('width','100%').addClass('addSmmarytel' + '_' + id)))
                        summary(id, summaryId)
                        tr.append($('<td>').addClass('progress'+'_' + id))
                        tr.append($('<td>').append($('<button>').attr('difficultyId',json[i].difficulty_id).addClass('addDifficulty btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty1').html('+')).append($('<table>').css('width','100%').addClass('weeklydifficulty' + '_' + id)))
                        difficulty(id,difficultyId)
                        tr.append($('<td>').append($('<button>').attr('programmeId',json[i].programme_id).addClass('addProgramme btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setProgramme1').html('+')).append($('<table>').css('width','100%').addClass('weeklyProgramme' + '_' + id)))
                        programme(id,programmeId)
                        tr.append($('<td>').append($('<button>').attr('suggestId',json[i].suggest_id).addClass('addSuggest btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setSuggest1').html('+')).append($('<table>').css('width','100%').addClass('weeklySuggest' + '_' + id)))
                        suggest(id,suggestId)
                        tr.append($('<td>').append($('<button>').attr('remarkId',json[i].remark_id).addClass('addRemark btn btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setRemark1').html('+')).append($('<table>').css('width','100%').addClass('weeklyRemark' + '_' + id)))
                        remark(id,remarkId);
                        var del = $('<button>').attr("summaryId",json[i].summary_id).addClass('btn btn-danger delbtn').html('<i class="glyphicon glyphicon-trash"></i>');
                        var td = $('<td>');
                        td.append(del);
                        tr.append(td);
                        $("#tbody").append(tr);
                    }
                   /* //第几周 跳转 日报
                    $('.weekbtn').click(function () {
                        var startDate= $(this).attr('sdate')
                        var endDate= $(this).attr('edate')
                        window.location.href="/getDaily?startDate"+startDate+'endDate'+endDate
                    })*/

                    //删除
                    $('.delbtn').click(function () {
                        var id = $(this).parent().parent().children().eq(0).text()
                        summaryId=$(this).attr("summaryId")
                        layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                            title: '信息',
                            btn: ['朕意已决', '泥奏凯，朕再想一想']
                        }, function (index) {
                            $.ajax({
                                dataType: 'json',
                                url: "/deleteWeekly",
                                data: {
                                    id: id,
                                    summaryId:summaryId
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
                        var summaryId=$("#midleValueId").val()
                        $.ajax({
                            url: "/addSummary",
                            dataType: 'json',
                            data: {
                                summaryId:summaryId,
                                content:$('#addcontent').val(),
                                singleProgress:$('#addsingleProgress').val(),
                                workHours:$('#addworkHours').val(),
                                assisMan:$('#addassisMan').val()
                            },
                            success: function(data) {
                                if (data.message =="添加成功") {
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
                        var difficultyId=$("#dmidleValueId").val()
                        $.ajax({
                            url: "/addWeeklyDifficulty",
                            dataType: 'json',
                            data: {
                                difficultyId:difficultyId,
                                difficultyTitle:$('#addDifficutyTitle').val(),
                                difficultyContent:$('#addDifficutyContent').val()
                            },
                            success: function(data) {
                                if (data.message =="添加成功") {
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
                        var programmeId=$("#pmidleValueId").val()
                        $.ajax({
                            url: "/addWeeklyProgramme",
                            dataType: 'json',
                            data: {
                                programmeId:programmeId,
                                programmeTitle:$('#addProgrammeTitle').val(),
                                programmeContent:$('#addProgrammeContent').val()
                            },
                            success: function(data) {
                                if (data.message =="添加成功") {
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
                        var suggestId=$("#smidleValueId").val()
                        $.ajax({
                            url: "/addWeeklySuggest",
                            dataType: 'json',
                            data: {
                                suggestId:suggestId,
                                suggestTitle:$('#addSuggestTitle').val(),
                                suggestContent:$('#addSuggestContent').val()
                            },
                            success: function(data) {
                                if (data.message =="添加成功") {
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
                        var remarkId=$("#rmidleValueId").val()
                        $.ajax({
                            url: "/addWeeklyRemark",
                            dataType: 'json',
                            data: {
                                remarkId:remarkId,
                                remarkTitle:$('#addRemarkTitle').val(),
                                remarkContent:$('#addRemarkContent').val()
                            },
                            success: function(data) {
                                if (data.message =="添加成功") {
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
                            str.append($('<td>').css('width', '250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].difficultyTitle))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("id",json[i].id).attr("difficultyId",difficultyId).attr("difficultyTitle",json[i].difficultyTitle).attr("difficultyContent",json[i].difficultyContent)
                                .addClass('btn btn-xs lookDifficulty').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty2').html('查').css('margin-right','5px');
                            var upd = $('<button>').attr("id",json[i].id).attr("difficultyId",difficultyId).attr("content",json[i].content).attr("difficultyTitle",json[i].difficultyTitle).attr("difficultyContent",json[i].difficultyContent)
                                .addClass('btn btn-xs updDifficulty').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty3').html('改').css('margin-right','5px');
                            var del = $('<button>').attr("id",json[i].id).addClass('btn btn-xs delDifficulty').html('删');
                            str.append(look)
                            str.append(upd)
                            str.append(del)
                            str.append(td);
                            $(".weeklydifficulty" + '_' + id).append(str);
                        }
                        //查看详情 周困难
                        $('.lookDifficulty').click(function () {
                            $("#lookdifficultyTitle").val($(this).attr('difficultyTitle'))
                            $("#lookdifficultyContent").val($(this).attr('difficultyContent'))
                        })
                        //修改 周困难
                        $('.updDifficulty').click(function () {
                            var id=$(this).attr('id')
                            var difficultyId=$(this).attr('difficultyId')
                            $("#updDifficultyTitle").val($(this).attr('difficultyTitle'))
                            $("#updDifficultyContent").val($(this).attr('difficultyContent'))
                            //提交
                            $("#updDifficulty").click(function () {
                                var difficultyTitle=$("#updDifficultyTitle").val()
                                var difficultyContent=$("#updDifficultyContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklyDifficulty',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            difficultyId: difficultyId,
                                            difficultyTitle: difficultyTitle,
                                            difficultyContent: difficultyContent
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
                        //删除  一周 困难
                        $('.delDifficulty').click(function () {
                            var id=$(this).attr("id")
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
                            str.append($('<td>').css('width', '250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].programmeTitle))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("programmeId", programmeId).attr("programmeTitle", json[i].programmeTitle).attr("programmeContent", json[i].programmeContent)
                                .addClass('btn btn-xs lookProgramme').attr('data-toggle', 'modal').attr('data-target', '#setProgramme2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("programmeId", programmeId).attr("programmeTitle", json[i].programmeTitle).attr("programmeContent", json[i].programmeContent)
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
                            $("#lookProgrammeTitle").val($(this).attr('programmeTitle'))
                            $("#lookProgrammeContent").val($(this).attr('programmeContent'))
                        })
                        //修改 周 方案
                        $('.updProgramme').click(function () {
                            var id = $(this).attr('id')
                            var programmeId = $(this).attr('programmeId')
                            $("#updProgrammeTitle").val($(this).attr('programmeTitle'))
                            $("#updProgrammeContent").val($(this).attr('programmeContent'))
                            //提交
                            $("#updProgramme").click(function () {
                                var programmeTitle = $("#updProgrammeTitle").val()
                                var programmeContent = $("#updProgrammeContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklyProgramme',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            programmeId: programmeId,
                                            programmeTitle: programmeTitle,
                                            programmeContent: programmeContent
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
                        //删除  一周 方案
                        $('.delProgramme').click(function () {
                            var id=$(this).attr("id")
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
                            str.append($('<td>').css('width', '250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].suggestTitle))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("suggestId", suggestId).attr("suggestTitle", json[i].suggestTitle).attr("suggestContent", json[i].suggestContent)
                                .addClass('btn btn-xs lookSuggest').attr('data-toggle', 'modal').attr('data-target', '#setSuggest2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("suggestId", suggestId).attr("suggestTitle", json[i].suggestTitle).attr("suggestContent", json[i].suggestContent)
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
                            $("#lookSuggestTitle").val($(this).attr('suggestTitle'))
                            $("#lookSuggestContent").val($(this).attr('suggestContent'))
                        })
                        //修改 周 建议
                        $('.updSuggest').click(function () {
                            var id = $(this).attr('id')
                            var suggestId = $(this).attr('suggestId')
                            $("#updSuggestTitle").val($(this).attr('suggestTitle'))
                            $("#updSuggestContent").val($(this).attr('suggestContent'))
                            //提交
                            $("#updSuggest").click(function () {
                                var suggestTitle = $("#updSuggestTitle").val()
                                var suggestContent = $("#updSuggestContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklySuggest',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            suggestId: suggestId,
                                            suggestTitle: suggestTitle,
                                            suggestContent: suggestContent
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
                        //删除  一周 建议
                        $('.delSuggest').click(function () {
                            var id=$(this).attr("id")
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
                        console.info(json)
                        for (i in json) {
                            var str = $('<tr>');
                            str.append($('<td>').css('width', '250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].remarkTitle))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("remarkId", remarkId).attr("remarkTitle", json[i].remarkTitle).attr("remarkContent", json[i].remarkContent)
                                .addClass('btn btn-xs lookRemark').attr('data-toggle', 'modal').attr('data-target', '#setRemark2').html('查').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("remarkId", remarkId).attr("remarkTitle", json[i].remarkTitle).attr("remarkContent", json[i].remarkContent)
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
                            $("#lookRemarkTitle").val($(this).attr('remarkTitle'))
                            $("#lookRemarkContent").val($(this).attr('remarkContent'))
                        })
                        //修改 周 备注
                        $('.updRemark').click(function () {
                            var id = $(this).attr('id')
                            var remarkId = $(this).attr('remarkId')
                            $("#updRemarkTitle").val($(this).attr('remarkTitle'))
                            $("#updRemarkContent").val($(this).attr('remarkContent'))
                            //提交
                            $("#updRemark").click(function () {
                                var remarkTitle = $("#updRemarkTitle").val()
                                var remarkContent = $("#updRemarkContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateWeeklyRemark',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            remarkId: remarkId,
                                            remarkTitle: remarkTitle,
                                            remarkContent: remarkContent
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
                        //删除  一周 备注
                        $('.delRemark').click(function () {
                            var id=$(this).attr("id")
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
                        var progress=1;
                        for (i in json) {
                            //百分号转小数进行乘积
                            var progress1=json[i].singleProgress.replace("%","")/100;
                            progress=progress+progress1;
                            //小数转百分数
                            if(i==json.length-1) {
                                var number = (Number(progress * 100).toFixed(1)-100)/json.length;
                                $('.progress'+'_'+id).html(number+"%")
                            }
                            var str = $('<tr>');
                            str.append($('<td>').css('width','250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].content))
                            var td = $('</td>');
                            str.append($('<td>'))
                            var look = $('<button>').attr("id",json[i].id).attr("summaryId",json[i].summaryId).attr("content",json[i].content).attr("singleProgress",json[i].singleProgress).attr("workHours",json[i].workHours).attr("assisMan",json[i].assisMan)
                                .addClass('btn btn-xs looksummary').attr('data-toggle', 'modal').attr('data-target', '#setModal3').html('查').css('margin-right','5px');
                            var upd = $('<button>').attr("id",json[i].id).attr("summaryId",json[i].summaryId).attr("content",json[i].content).attr("singleProgress",json[i].singleProgress).attr("workHours",json[i].workHours).attr("assisMan",json[i].assisMan)
                                .addClass('btn btn-xs updsummary').attr('data-toggle', 'modal').attr('data-target', '#setModal4').html('改').css('margin-right','5px');
                            var del = $('<button>').attr("id",json[i].id).addClass('btn btn-xs delsummary').html('删');
                            str.append(look).append(upd).append(del)
                            str.append(td);
                            $(".addSmmarytel" + '_' + id).append(str);
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
                            var id=$(this).attr('id')
                            var summaryId=$(this).attr('summaryId')
                            $("#updcontent").val($(this).attr('content'))
                            $("#updsingleProgress").val($(this).attr('singleProgress'))
                            $("#updworkHours").val($(this).attr('workHours'))
                            $("#updassisMan").val($(this).attr('assisMan'))

                            $("#updSummary").click(function () {
                                var content=$("#updcontent").val()
                                var singleProgress=$("#updsingleProgress").val()
                                var workHours=$("#updworkHours").val()
                                var assisMan=$("#updassisMan").val()
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
                            var id=$(this).attr("id")
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

        //年
        $(function year(){
            $("#weeklyYear").html("")
            //获取系统年份
            var myDate = new Date().getFullYear();
            var str= '<option value="">-- 请选择年份--</option>';
            $("#weeklyYear").append(str)
            for(var i=0;i<=3;i++){
                var str1 = '<option value="'+ (myDate-i)+'" >'+(myDate-i)+ '</option>';
                $("#weeklyYear").append(str1);
            }
        })

        //月
        $(function mouth(){
            $("#weeklyMouth").html("")
            var str= '<option value="">-- 请选择月份--</option>';
            $("#weeklyMouth").append(str)
            for(var i=1;i<=12;i++){
                if(i<10) {
                    var str1 = '<option value="0' + i + '" >' + i + '月</option>';
                }else{
                    var str1 = '<option value="' + i + '" >' + i + '月</option>';
                }
                $("#weeklyMouth").append(str1);
            }
        })

    </script>
</head>

<body onkeydown="onkeydownfun()">
<select id="weeklyYear" style="margin-left: 10px;">
    <option value="">-- 请选择年份 --</option>
</select>
<select id="weeklyMouth" style="margin-left: 10px;">
    <option value="">-- 请选择月份 --</option>
</select>
<input id="userid" placeholder="请输入用户ID" />
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search" ></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
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
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">日期:</td>
                        <td>
                            <input type="text" id="sDate" name="user_date" style="width:130px" class="form-control" placeholder="请选择开始时间" />
                            <div>—</div>
                            <input type="text" id="eDate" name="user_date" style="width:130px" class="form-control" placeholder="请选择结束时间" />
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">周:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="week"></input>
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
<div class="modal fade" id="setModal2" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
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
                            <input type="text" id="addsingleProgress">
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
<div class="modal fade" id="setModal3" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
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
<div class="modal fade" id="setModal4" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周小结修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">内容:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updcontent" ></textarea>
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
                            <input class="form-control" id="updworkHours" ></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updassisMan" ></textarea>
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
<div class="modal fade" id="setDifficulty1" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周困难 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">困难标题:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addDifficutyTitle"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">具体困难:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addDifficutyContent"></input>
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
<div class="modal fade" id="setDifficulty2" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周小结详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">困难点:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookdifficultyTitle" readonly></input>
                        </td>
                    </tr>
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
<div class="modal fade" id="setDifficulty3" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周困难 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">困难点:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updDifficultyTitle" ></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">具体困难:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updDifficultyContent" ></textarea>
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
<div class="modal fade" id="setProgramme1" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周方案 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">方案标题:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="addProgrammeTitle"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">具体方案:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addProgrammeContent"></input>
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
<div class="modal fade" id="setProgramme2" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周方案 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">方案标题:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookProgrammeTitle"></input>
                        </td>
                    </tr>
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
<div class="modal fade" id="setProgramme3" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周 方案 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">方案点:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updProgrammeTitle" ></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">具体方案:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updProgrammeContent" ></textarea>
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
<div class="modal fade" id="setSuggest1" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周建议 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">建议标题:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addSuggestTitle"></input>
                        </td>
                    </tr>
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
<div class="modal fade" id="setSuggest2" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周建议 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">建议标题:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookSuggestTitle"></input>
                        </td>
                    </tr>
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
<div class="modal fade" id="setSuggest3" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周 建议 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">建议点:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updSuggestTitle" ></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">具体建议:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updSuggestContent" ></textarea>
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
<div class="modal fade" id="setRemark1" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周备注 新增</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">备注标题:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addRemarkTitle"></input>
                        </td>
                    </tr>
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
<div class="modal fade" id="setRemark2" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周备注 详情</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">备注标题:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="lookRemarkTitle"></input>
                        </td>
                    </tr>
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
<div class="modal fade" id="setRemark3" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">一周 备注 修改</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">备注点:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updRemarkTitle" ></input>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">具体备注:</td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="updRemarkContent" ></textarea>
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
</body>

</html>