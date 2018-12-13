<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript" src="js/Date.js"></script>
    <script type="text/javascript" src="js/chosen.js"></script>
    <script src="js/bootstrap-datetimepicker.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap-datetimepicker.fr.js" type="text/javascript" charset="utf-8"></script>
    <style>
        .form-control {
            display: inline-block;
        }
        .dsa{
            vertical-align: top;
            padding-top: 6px;
        }
        .asd {
            width: 150px;
        }

        #table-bordered thead tr th {
            text-align: center;
        }

        #table-bordered thead tr th:nth-of-type(1) {
            max-width: 50px;
            min-width: 50px;
        }

        #table-bordered thead tr th:nth-of-type(2) {
            max-width: 75px;
            min-width: 75px;
        }

        #table-bordered thead tr th:nth-of-type(3) {
            max-width: 50px;
            min-width: 50px;
        }

        #table-bordered thead tr th:nth-of-type(4) {
            max-width: 40px;
            min-width: 40px;
        }

        #table-bordered thead tr th:nth-of-type(5) {
            max-width: 350px;
            min-width: 350px;
        }

        #table-bordered thead tr th:nth-of-type(6) {
            max-width: 75px;
            min-width: 75px;
        }

        #table-bordered thead tr th:nth-of-type(7) {
            max-width: 300px;
            min-width: 300px;
        }

        #table-bordered thead tr th:nth-of-type(8) {
            max-width: 300px;
            min-width: 300px;
        }

        #table-bordered thead tr th:nth-of-type(9) {
            max-width: 300px;
            min-width: 300px;
        }

        #table-bordered thead tr th:nth-of-type(10) {
            max-width: 300px;
            min-width: 300px;
        }

    </style>
    <script >
        $(function() {
            //选择年月
            $('#addMonthlyDate').datetimepicker({
                language: 'zh-CN',
                startView: 3,
                minView: 3,
                format: 'yyyy-mm',
                todayBtn: 1,
                autoclose: 1,
            });

            //选择年份
            $('#monthlyYear').datetimepicker({
                //设置为中文，这里需添加中文的js
                language: 'zh-CN',
                //日期时间选择器打开之后首先看到的视图 选择后后面还有天、小时、分钟
                // 0 "hour" 1 "day" 2 "month" 3 "year" 4 "dacade"
                startView: 4,
                // 选到哪一步日历就结束。
                minView: 4,
                //默认为"mm/dd/yyyy"  一般设置为"yyyy-mm-dd hh:ii"
                format: 'yyyy',
                //false则没有今天两个字，"linked"当天日期会被选中
                todayBtn: false,
                //就是选择年月日之后会自动关闭，一定要设置
                autoclose: true,
                //开始时间
                startDate: new Date("2016"),
                //结束时间
                endDate: new Date(new Date().setFullYear(new Date().getFullYear() + 1))
            });
            //选择月份
            $('#monthlyMouth').datetimepicker({
                language: 'zh-CN',
                startView: 3,
                minView: 3,
                format: 'mm',
                todayBtn: 1,
                autoclose: 1,
            })

            $("#addassisman").html("")
            $("#updassisman").html("")
            //月小结  协助人下拉
            $.ajax({
                url: '/getUser',
                dataType: 'json',
                success: function (data) {
                    var str;
                    var json = data.data
                    for (var i in json) {
                        str = '<option class="assisManItem" value="' + json[i].fullName + '">' + json[i].fullName + '</option>';
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
                var year = $('#addMonthlyDate').val().substring(0,4)
                var month = $('#addMonthlyDate').val().substring(5,7)
                $.ajax({
                    url: "/addmonthly",
                    dataType: 'json',
                    data: {
                        year: year,
                        month: month,
                        userId:${sessionUser.id}
                    },
                    success: function (data) {
                        if (data.message == "添加成功") {
                            layer.msg('添加成功!', {
                                icon: 1,
                                time: 1000
                            });
                            setTimeout(function wlh() {
                                window.location.href = "/monthly"
                            }, 500)
                        } else if (data.message == "禁止提前创建月报") {
                            layer.msg("禁止提前创建月报");
                        } else if (data.message == "重复添加") {
                            layer.msg("禁止月报重复添加");
                        } else {
                            layer.msg("添加失败");
                        }
                    }
                });
            })
        })

        function inittable() {
            $("#tbody").html("");
            var monthlyYear = $("#monthlyYear").val()
            var monthlyMouth = $("#monthlyMouth").val()
            if (monthlyYear == '' && monthlyMouth != '') {
                layer.msg('月份需与年份配对')
                return false;
            }
            var userId = $("#userid").val().trim()
            $.ajax({
                type: 'get',
                url: 'getMonthly',
                dataType: 'json',
                data: {
                    userId: userId,
                    year:monthlyYear,
                    month:monthlyMouth
                },
                success: function (data) {
                    var json = data.data
                    for (var i in json) {
                        var summaryId = json[i].summary_id
                        var difficultyId = json[i].difficulty_id
                        var programmeId = json[i].programme_id
                        var suggestId = json[i].suggest_id
                        var remarkId = json[i].remark_id
                        var id = json[i].id
                        var tr = $('<tr>');
                        tr.append($('<td>').css('vertical-align','middle').css('text-align','center').html(eval(i+"")+1))
                        tr.append($('<td>').css('vertical-align','middle').css('text-align','center').html(json[i].fullName))
                        tr.append($('<td>').css('vertical-align','middle').css('text-align','center').html(json[i].year))
                        tr.append($('<td>').css('vertical-align','middle').css('text-align','center').html(json[i].month).attr("year", json[i].year).attr("month", json[i].month).css("color", "blue").css("cursor", "pointer").css('margin-right', '10px').attr('data-toggle', 'modal').attr('data-target', '#getModal').addClass('monthbtn').attr("sdate", json[i].sdate).attr("edate", json[i].edate).attr("userId", data.userId))
                        tr.append($('<td>').append($('<button>').attr('summaryId', json[i].summary_id).addClass('addSummary btn btn-info btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setModal2').html('+')).append($('<table>').css('width', '100%').addClass('addSmmarytel' + '_' + id)))
                        tr.append($('<td>').css('vertical-align','middle').css('text-align','center').addClass('progress' + '_' + id))
                        tr.append($('<td>').append($('<button>').attr('difficultyId', json[i].difficulty_id).addClass('addDifficulty btn btn-info btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty1').html('+')).append($('<table>').css('width', '100%').addClass('monthlydifficulty' + '_' + id)))
                        tr.append($('<td>').append($('<button>').attr('programmeId', json[i].programme_id).addClass('addProgramme btn btn-info btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setProgramme1').html('+')).append($('<table>').css('width', '100%').addClass('monthlyProgramme' + '_' + id)))
                        tr.append($('<td>').append($('<button>').attr('suggestId', json[i].suggest_id).addClass('addSuggest btn btn-info btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setSuggest1').html('+')).append($('<table>').css('width', '100%').addClass('monthlySuggest' + '_' + id)))
                        tr.append($('<td>').append($('<button>').attr('remarkId', json[i].remark_id).addClass('addRemark btn btn-info btn-xs').attr('data-toggle', 'modal').attr('data-target', '#setRemark1').html('+')).append($('<table>').css('width', '100%').addClass('monthlyRemark' + '_' + id)))
                        var del = $('<button>').attr("mid",json[i].id).attr("summaryId", json[i].summary_id).attr("difficultyId", json[i].difficulty_id).attr("programmeId", json[i].programme_id).attr("suggestId", json[i].suggest_id).attr("remarkId", json[i].remark_id).addClass('btn btn-danger delbtn').html('<i class="glyphicon glyphicon-trash"></i>');
                        summary(id, summaryId)
                        difficulty(id, difficultyId)
                        programme(id, programmeId)
                        suggest(id, suggestId)
                        remark(id, remarkId);
                        /*  var td = $('<td>');
                          tr.append(td);*/
                        $("#tbody").append(tr);
                    }
                    if (data.code != "200") {
                        layer.msg('当前数据为空!');
                    }
                    //第几月 跳转 月报
                    $('.monthbtn').click(function () {
                        var year = $(this).attr('year')
                        var month = $(this).attr('month')<10?'0'+$(this).attr('month'):$(this).attr('month')
                        var userId = $(this).attr('userId')
                        $("#monthspan").html(year + '年' + month + '月 ');
                        $("#tbodys").html("");
                        $.ajax({
                            url: '/getWeekly',
                            type: 'get',
                            dataType: 'json',
                            data: {
                                'year': year ,
                                'mouth': month,
                                'userId': userId
                            },
                            success: function (data) {
                                var json=data.data;
                                for (var i in json) {
                                    var tr = $('<tr>');
                                    tr.append($('<td>').html(json[i].id))
                                    tr.append($('<td>').html(json[i].fullName))
                                    tr.append($('<td>').html(json[i].sdate))
                                    tr.append($('<td>').html(json[i].edate))
                                    tr.append($('<td>').html(json[i].week))
                                    tr.append($('<td>').addClass("summary" + "_" + json[i].summary_id))
                                    tr.append($('<td>').addClass("progress" + "_" + json[i].summary_id))
                                    $.ajax({
                                        url: '/getSummary',
                                        dataType: 'json',
                                        data: {
                                            summaryId: json[i].summary_id
                                        },
                                        type:"get",
                                        success: function (data) {
                                            var json = data.data
                                            var progress = 1;
                                            for (i in json) {
                                                var str = $('<tr>');
                                                str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].content)).append($('</td>')).append($('</tr>'))
                                                $(".summary"+'_'+json[i].summaryId).append(str)
                                                //百分号转小数进行乘积
                                                var progress1 = json[i].singleProgress.replace("%", "") / 100;
                                                progress = progress + progress1;
                                                //小数转百分数
                                                if (i == json.length - 1) {
                                                    var number = (Number(progress * 100).toFixed(1) - 100) / json.length;
                                                    $('.progress' + '_' + json[i].summaryId).html(number.toFixed(1) + "%")
                                                }
                                            }
                                        }
                                    })
                                    tr.append($('<td>').addClass("difficulty" + "_" + json[i].difficulty_id))
                                    $.ajax({
                                        url: '/getWeeklyDifficulty',
                                        dataType: 'json',
                                        data: {
                                            difficultyId: json[i].difficulty_id
                                        },
                                        type:"get",
                                        success: function (data) {
                                            var json = data.data;
                                            for (i in json) {
                                                var str = $('<tr>');
                                                str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].difficultyContent)).append($('</td>')).append($('</tr>'))
                                                $('.difficulty' + "_" + json[i].difficultyId).append(str)
                                            }
                                        }
                                    })
                                    tr.append($('<td>').addClass("programme" + "_" + json[i].programme_id))
                                    $.ajax({
                                        url: '../getWeeklyProgramme',
                                        dataType: 'json',
                                        data: {
                                            programmeId: json[i].programme_id
                                        },
                                        success: function (data) {
                                            var json = data.data
                                            for (i in json) {
                                                var str = $('<tr>');
                                                str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].programmeContent)).append($('</td>')).append($('</tr>'))
                                                $('.programme' + "_" + json[i].programmeId).append(str)
                                            }
                                        }
                                    })
                                    tr.append($('<td>').addClass("suggest" + "_" + json[i].suggest_id))
                                    $.ajax({
                                        url: '/getWeeklySuggest',
                                        dataType: 'json',
                                        data: {
                                            suggestId: json[i].suggest_id
                                        },
                                        success: function (data) {
                                            var json = data.data
                                            for (i in json) {
                                                var str = $('<tr>');
                                                str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].suggestContent)).append($('</td>')).append($('</tr>'))
                                                $('.suggest' + "_" + json[i].suggestId).append(str)
                                            }
                                        }
                                    })
                                    tr.append($('<td>').addClass("remark" + "_" + json[i].remark_id))
                                    $.ajax({
                                        url: '/getWeeklyRemark',
                                        dataType: 'json',
                                        data: {
                                            remarkId: json[i].remark_id
                                        },
                                        success: function (data) {
                                            var json = data.data
                                            for (i in json) {
                                                var str = $('<tr>');
                                                str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].remarkContent)).append($('</td>')).append($('</tr>'))
                                                $('.remark' + "_" + json[i].remarkId).append(str)
                                            }
                                        }
                                    })
                                    $("#tbodys").append(tr);
                                }
                            }
                        })
                    })

                    //删除
                    /* $('.delbtn').click(function () {
                         var id = $(this).attr("mid")
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
                                 url: "/deleteMonthly",
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
                                             window.location.href = "/monthly"
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
                     })*/

                    //给提交传值
                    $(".addSummary").click(function () {
                        $("#addcontent").val("")
                        $("#addsingleProgress").val("")
                        $("#addworkHours").val("")
                        var year= $(this).parent().parent().children().eq(2).text()
                        var month= $(this).parent().parent().children().eq(3).text()
                        $("#addSummary").attr("summaryId",$(this).attr("summaryId")).attr("year",year).attr("month",month)
                    })
                    //提交  添加月小结
                    $("#addSummary").unbind('click').click(function () {
                        checkAddInput()
                        var workHours = $('#addworkHours').val() + $('#addworkHoursUnit').val()
                        var singleProgress=$('#addsingleProgress').val()
                        if(parseInt(singleProgress)>100||parseFloat('100%') < parseFloat(singleProgress)){
                            layer.msg("您输入的进度指数不规范")
                            ajax().abort()
                        }
                        var assisMan='';
                        if($("#addassisman").val()!=null){
                            var assisMan = $("#addassisman").val().toString();
                        }
                        var year=$(this).attr("year")
                        var month=$(this).attr("month")
                        var summaryId=$(this).attr("summaryId")
                        $.ajax({
                            url: "/addMonthlySummary",
                            dataType: 'json',
                            data: {
                                summaryId:  summaryId,
                                summaryContent: $('#addcontent').val(),
                                singleProgress: singleProgress,
                                workHours: workHours,
                                assismans: assisMan,
                                year:year,
                                month:month
                            },
                            success: function (data) {
                                if (data.message == "添加成功") {
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function wlh() {
                                        window.location.href = "/monthly"
                                    }, 500)
                                } else if(data.message=="此月月末三天后,禁止添加") {
                                    layer.msg("此月月末三天后,禁止添加");
                                }else {
                                    layer.msg("添加失败");
                                }

                            }
                        });
                    })

                    //给提交传值 困难
                    $(".addDifficulty").click(function () {
                        $("#addDifficutyContent").val("")
                        var year= $(this).parent().parent().children().eq(2).text()
                        var month= $(this).parent().parent().children().eq(3).text()
                        $("#addDifficulty").attr("difficultyId",$(this).attr("difficultyId")).attr("year",year).attr("month",month)
                    })
                    //提交  添加月 困难
                    $("#addDifficulty").unbind('click').click(function () {
                        var addDifficutyContent=$('#addDifficutyContent').val().trim()
                        if(addDifficutyContent==null|| addDifficutyContent==''){
                            layer.msg("困难内容不能为空!");
                            ajax().abort()
                        }
                        var year = $(this).attr("year")
                        var month = $(this).attr("month")
                        var difficultyId = $(this).attr("difficultyId")
                        $.ajax({
                            url: "/addMonthlyDifficulty",
                            dataType: 'json',
                            data: {
                                difficultyId: difficultyId,
                                difficultyContent: addDifficutyContent,
                                year:year,
                                month:month
                            },
                            success: function (data) {
                                if (data.message == "添加成功") {
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function wlh() {
                                        window.location.href = "/monthly"
                                    }, 500)
                                } else if(data.message=="此月月末三天后,禁止添加") {
                                    layer.msg("此月月末三天后,禁止添加");
                                }else {
                                    layer.msg("添加失败");
                                }
                            }
                        });
                    })
                    //给提交传值 方案
                    $(".addProgramme").click(function () {
                        $("#addProgrammeContent").val("")
                        var year= $(this).parent().parent().children().eq(2).text()
                        var month= $(this).parent().parent().children().eq(3).text()
                        $("#addProgramme").attr("programmeId",$(this).attr("programmeId")).attr("year",year).attr("month",month)
                    })
                    //提交 添加月 方案
                    $("#addProgramme").unbind('click').click(function () {
                        var addProgrammeContent=$('#addProgrammeContent').val().trim()
                        if(addProgrammeContent==null||addProgrammeContent==''){
                            layer.msg("方案内容不能为空!");
                            ajax().abort()
                        }
                        var year = $(this).attr("year")
                        var month = $(this).attr("month")
                        var programmeId = $(this).attr("programmeId")
                        $.ajax({
                            url: "/addmonthlyProgramme",
                            dataType: 'json',
                            data: {
                                programmeId: programmeId,
                                programmeContent: addProgrammeContent,
                                year:year,
                                month:month
                            },
                            success: function (data) {
                                if (data.message == "添加成功") {
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function wlh() {
                                        window.location.href = "/monthly"
                                    }, 500)
                                }else if(data.message=="此月月末三天后,禁止添加") {
                                    layer.msg("此月月末三天后,禁止添加");
                                } else {
                                    layer.msg("添加失败");
                                }
                            }
                        });
                    })
                    //给提交传值 建议
                    $(".addSuggest").click(function () {
                        $("#addSuggestContent").val("")
                        var year= $(this).parent().parent().children().eq(2).text()
                        var month= $(this).parent().parent().children().eq(3).text()
                        $("#addSuggest").attr("suggestId",$(this).attr('suggestId')).attr("year",year).attr("month",month)
                    })
                    //提交 添加月 建议
                    $("#addSuggest").unbind('click').click(function () {
                        var addSuggestContent=$('#addSuggestContent').val().trim()
                        if(addSuggestContent==null||addSuggestContent==''){
                            layer.msg("建议内容不能为空!");
                            ajax().abort()
                        }
                        var year=$(this).attr('year')
                        var month=$(this).attr('month')
                        var suggestId =$(this).attr('suggestId')
                        $.ajax({
                            url: "/addmonthlySuggest",
                            dataType: 'json',
                            data: {
                                suggestId: suggestId,
                                suggestContent: addSuggestContent,
                                year:year,
                                month:month
                            },
                            success: function (data) {
                                if (data.message == "添加成功") {
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function wlh() {
                                        window.location.href = "/monthly"
                                    }, 500)
                                }else if(data.message=="此月月末三天后,禁止添加") {
                                    layer.msg("此月月末三天后,禁止添加");
                                }else {
                                    layer.msg("添加失败");
                                }
                            }
                        });
                    })
                    //给提交传值 备注
                    $(".addRemark").click(function () {
                        $("#addRemarkContent").val("")
                        var year= $(this).parent().parent().children().eq(2).text()
                        var month= $(this).parent().parent().children().eq(3).text()
                        $("#addRemark").attr("remarkId",$(this).attr('remarkId')).attr("year",year).attr("month",month)
                    })
                    //提交 添加月 备注
                    $("#addRemark").unbind('click').click(function () {
                        var addRemarkContent=$('#addRemarkContent').val().trim()
                        if(addRemarkContent==null||addRemarkContent==''){
                            layer.msg("备注内容不能为空!");
                            ajax().abort()
                        }
                        var remarkId = $(this).attr("remarkId")
                        var year = $(this).attr("year")
                        var month = $(this).attr("month")
                        $.ajax({
                            url: "/addMonthlyRemark",
                            dataType: 'json',
                            data: {
                                remarkId: remarkId,
                                remarkContent: addRemarkContent,
                                year:year,
                                month:month
                            },
                            success: function (data) {
                                if (data.message == "添加成功") {
                                    layer.msg('添加成功!', {
                                        icon: 1,
                                        time: 1000
                                    });
                                    setTimeout(function wlh() {
                                        window.location.href = "/monthly"
                                    }, 500)
                                } else if(data.message=="此月月末三天后,禁止添加") {
                                    layer.msg("此月月末三天后,禁止添加");
                                }else{
                                    layer.msg("添加失败");
                                }
                            }
                        });
                    })
                },
            })

            //获取月 困难集
            function difficulty(id, difficultyId) {
                $.ajax({
                    url: '../getMonthlyDifficulty',
                    dataType: 'json',
                    data: {
                        difficultyId: difficultyId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>').css('border-top','1px dashed #ccc');
                            str.append($('<td>').css('width', '190px').addClass('asd').html(json[i].difficultyContent))
                            var td = $('<td>').addClass('dsa');
                            var look = $('<button>').attr("id", json[i].id).attr("difficultyId", difficultyId).attr("difficultyContent", json[i].difficultyContent)
                                .addClass('btn btn-success btn-xs lookDifficulty').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty2').html('<i class="glyphicon glyphicon-search"></i>').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("difficultyId", difficultyId).attr("content", json[i].content).attr("difficultyContent", json[i].difficultyContent)
                                .addClass('btn btn-warning btn-xs updDifficulty').attr('data-toggle', 'modal').attr('data-target', '#setDifficulty3').html('<i class="glyphicon glyphicon-edit"></i>').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-danger btn-xs delDifficulty').html('<i class="glyphicon glyphicon-trash"></i>');
                            td.append(look).append(upd).append(del)
                            str.append(td);
                            $(".monthlydifficulty" + '_' + id).append(str);
                        }
                        //查看详情 月困难
                        $('.lookDifficulty').click(function () {
                            $("#lookdifficultyContent").val($(this).attr('difficultyContent'))
                        })
                        //修改 月 困难
                        $('.updDifficulty').click(function () {
                            var year= $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            var month= $(this).parent().parent().parent().parent().parent().children().eq(3).text()
                            var id = $(this).attr('id')
                            var difficultyId = $(this).attr('difficultyId')
                            $("#updDifficultyContent").val($(this).attr('difficultyContent'))
                            //提交
                            $("#updDifficulty").click(function () {
                                var difficultyContent = $("#updDifficultyContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateMonthlyDifficulty',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            difficultyId: difficultyId,
                                            difficultyContent: difficultyContent,
                                            year:year,
                                            month:month
                                        },
                                        success: function (data) {
                                            console.info(data)
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/monthly"
                                                }, 500)
                                                layer.msg('已修改!', {
                                                    icon: 1,
                                                    time: 1000
                                                });
                                            } else if (data.message == "当前时间不在此月内,禁止修改") {
                                                layer.msg('当前时间不在此月内，禁止修改!', {
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
                        //删除  一月 困难
                        $('.delDifficulty').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deletMonthlyDifficulty",
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
                                                window.location.href = "/monthly"
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

            //获取月 方案集
            function programme(id, programmeId) {
                $.ajax({
                    url: '../getMonthlyProgramme',
                    dataType: 'json',
                    data: {
                        programmeId: programmeId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>').css('border-top','1px dashed #ccc');
                            str.append($('<td>').css('width', '190px').addClass('asd').html(json[i].programmeContent))
                            var td = $('<td>').addClass('dsa');
                            var look = $('<button>').attr("programmeId", programmeId).attr("programmeContent", json[i].programmeContent)
                                .addClass('btn btn-success btn-xs lookProgramme').attr('data-toggle', 'modal').attr('data-target', '#setProgramme2').html('<i class="glyphicon glyphicon-search"></i>').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("programmeId", programmeId).attr("programmeContent", json[i].programmeContent)
                                .addClass('btn btn-warning btn-xs updProgramme').attr('data-toggle', 'modal').attr('data-target', '#setProgramme3').html('<i class="glyphicon glyphicon-edit"></i>').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-danger btn-xs delProgramme').html('<i class="glyphicon glyphicon-trash"></i>');
                            td.append(look).append(upd).append(del)
                            str.append(td);
                            $(".monthlyProgramme" + '_' + id).append(str);
                        }
                        //查看详情 月 方案
                        $('.lookProgramme').click(function () {
                            $("#lookProgrammeContent").val($(this).attr('programmeContent'))
                        })
                        //修改 月 方案
                        $('.updProgramme').click(function () {
                            var id = $(this).attr('id')
                            var programmeId = $(this).attr('programmeId')
                            $("#updProgrammeContent").val($(this).attr('programmeContent'))
                            var year= $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            var month= $(this).parent().parent().parent().parent().parent().children().eq(3).text()
                            //提交
                            $("#updProgramme").click(function () {
                                var programmeContent = $("#updProgrammeContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updatemonthlyProgramme',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            programmeId: programmeId,
                                            programmeContent: programmeContent,
                                            year:year,
                                            month:month
                                        },
                                        success: function (data) {
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/monthly"
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
                        //删除  一月 方案
                        $('.delProgramme').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/delmonthlyProgramme",
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
                                                window.location.href = "/monthly"
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

            //获取月 建议
            function suggest(id, suggestId) {
                $.ajax({
                    url: '../getmonthlySuggest',
                    dataType: 'json',
                    data: {
                        suggestId: suggestId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>').css('border-top','1px dashed #ccc');
                            str.append($('<td>').css('width', '190px').addClass('asd').html(json[i].suggestContent))
                            var td = $('<td>').addClass('dsa');
                            var look = $('<button>').attr("suggestId", suggestId).attr("suggestContent", json[i].suggestContent)
                                .addClass('btn btn-success btn-xs lookSuggest').attr('data-toggle', 'modal').attr('data-target', '#setSuggest2').html('<i class="glyphicon glyphicon-search"></i>').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("suggestId", suggestId).attr("suggestContent", json[i].suggestContent)
                                .addClass('btn btn-warning btn-xs updSuggest').attr('data-toggle', 'modal').attr('data-target', '#setSuggest3').html('<i class="glyphicon glyphicon-edit"></i>').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-danger btn-xs delSuggest').html('<i class="glyphicon glyphicon-trash"></i>');
                            td.append(look).append(upd).append(del)
                            str.append(td);
                            $(".monthlySuggest" + '_' + id).append(str);
                        }
                        //查看详情 月 建议
                        $('.lookSuggest').click(function () {
                            $("#lookSuggestContent").val($(this).attr('suggestContent'))
                        })
                        //修改 月 建议
                        $('.updSuggest').click(function () {
                            var id = $(this).attr('id')
                            var suggestId = $(this).attr('suggestId')
                            $("#updSuggestContent").val($(this).attr('suggestContent'))
                            var year= $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            var month= $(this).parent().parent().parent().parent().parent().children().eq(3).text()
                            //提交
                            $("#updSuggest").click(function () {
                                var suggestContent = $("#updSuggestContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updatemonthlySuggest',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            suggestId: suggestId,
                                            suggestContent: suggestContent,
                                            year:year,
                                            month:month
                                        },
                                        success: function (data) {
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/monthly"
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
                        //删除  一月 建议
                        $('.delSuggest').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/delmonthlySuggest",
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
                                                window.location.href = "/monthly"
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

            //获取月 备注
            function remark(id, remarkId) {
                $.ajax({
                    url: '../getMonthlyRemark',
                    dataType: 'json',
                    data: {
                        remarkId: remarkId
                    },
                    success: function (data) {
                        var json = data.data
                        for (i in json) {
                            var str = $('<tr>').css('border-top','1px dashed #ccc');
                            str.append($('<td>').css('width', '190px').addClass('asd').html(json[i].remarkContent))
                            var td = $('<td>').addClass('dsa');
                            var look = $('<button>').attr("remarkId", remarkId).attr("remarkContent", json[i].remarkContent)
                                .addClass('btn btn-success btn-xs lookRemark').attr('data-toggle', 'modal').attr('data-target', '#setRemark2').html('<i class="glyphicon glyphicon-search"></i>').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("remarkId", remarkId).attr("remarkContent", json[i].remarkContent)
                                .addClass('btn btn-warning btn-xs updRemark').attr('data-toggle', 'modal').attr('data-target', '#setRemark3').html('<i class="glyphicon glyphicon-edit"></i>').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-danger btn-xs delRemark').html('<i class="glyphicon glyphicon-trash"></i>');
                            td.append(look).append(upd).append(del)
                            str.append(td);
                            $(".monthlyRemark" + '_' + id).append(str);

                        }
                        //查看详情 月 备注
                        $('.lookRemark').click(function () {
                            $("#lookRemarkContent").val($(this).attr('remarkContent'))
                        })
                        //修改 月 备注
                        $('.updRemark').click(function () {
                            var id = $(this).attr('id')
                            var remarkId = $(this).attr('remarkId')
                            $("#updRemarkContent").val($(this).attr('remarkContent'))
                            var year= $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            var month= $(this).parent().parent().parent().parent().parent().children().eq(3).text()
                            //提交
                            $("#updRemark").click(function () {
                                var remarkContent = $("#updRemarkContent").val()
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateMonthlyRemark',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            remarkId: remarkId,
                                            remarkContent: remarkContent,
                                            year:year,
                                            month:month
                                        },
                                        success: function (data) {
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/monthly"
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
                        //删除  一月 备注
                        $('.delRemark').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deleteMonthlyRemark",
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
                                                window.location.href = "/monthly"
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

            //获取月小结
            function summary(id, summaryId) {
                $.ajax({
                    url: '../getMonthlySummary',
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
                            var str = $('<tr>').css('border-top','1px dashed #ccc');
                            str.append($('<td>').css('width', '290px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].summaryContent))
                            var td = $('<td>').addClass('dsa');
                            var look = $('<button>').attr("id", json[i].id).attr("summaryId", json[i].summaryId).attr("content", json[i].summaryContent).attr("singleProgress", json[i].singleProgress).attr("workHours", json[i].workHours).attr("assisMan", json[i].assisMan)
                                .addClass('btn btn-success btn-xs looksummary').attr('data-toggle', 'modal').attr('data-target', '#setModal3').html('<i class="glyphicon glyphicon-search"></i>').css('margin-right', '5px');
                            var upd = $('<button>').attr("id", json[i].id).attr("summaryId", json[i].summaryId).attr("content", json[i].summaryContent).attr("singleProgress", json[i].singleProgress).attr("workHours", json[i].workHours).attr("assisMan", json[i].assisMan)
                                .addClass('btn btn-warning btn-xs updsummary').attr('data-toggle', 'modal').attr('data-target', '#setModal4').html('<i class="glyphicon glyphicon-edit"></i>').css('margin-right', '5px');
                            var del = $('<button>').attr("id", json[i].id).addClass('btn btn-danger btn-xs delsummary').html('<i class="glyphicon glyphicon-trash"></i>');
                            td.append(look).append(upd).append(del)
                            str.append(td);
                            $(".addSmmarytel" + '_' + id).append(str);
                        }

                        //查看详情 月小结
                        $('.looksummary').click(function () {
                            $("#assisMan").val('')
                            $("#content").val($(this).attr('content'))
                            $("#singleProgress").val($(this).attr('singleProgress'))
                            $("#workHours").val($(this).attr('workHours'))
                            var lookassisman = $(this).attr('assisMan');
                            $("#assisMan").val(lookassisman.replace(/\,/g, ' '))
                        })

                        //修改 月小结
                        $('.updsummary').unbind('click').click(function () {
                            var id = $(this).attr('id')
                            var summaryId = $(this).attr('summaryId')
                            $("#updcontent").val($(this).attr('content'))
                            $("#updsingleProgress").val($(this).attr('singleProgress'))
                            var workHours=$(this).attr('workHours')
                            $("#updworkHours").val(workHours.substr(0, workHours.length - 1))
                            //工时单位
                            var unit=workHours.substr( workHours.length - 1,workHours.length);
                            $("#updworkHoursUnit"+ " option[value='" + unit + "']").prop('selected', true);

                            var assisman =''
                            if($(this).attr('assisMan').toString()!=null){
                                assisman=$(this).attr('assisMan').toString()
                            }

                            $('.assisManItem').prop('selected', false).trigger("chosen:updated");
                            if (!$.isEmptyObject(assisman)) {
                                var arr = assisman.split(',');
                                var length = arr.length;
                                var value = '';
                                for (i = 0; i < length; i++) {
                                    value = arr[i];
                                    $("#updassisman" + " option[value='" + value + "']").prop('selected', true);
                                }
                                $("#updassisman").chosen();
                                $("#updassisman").trigger("chosen:updated");
                            }

                            var year= $(this).parent().parent().parent().parent().parent().children().eq(2).text()
                            var month= $(this).parent().parent().parent().parent().parent().children().eq(3).text()
                            //提交
                            $("#updSummary").click(function () {
                                var content = $("#updcontent").val().trim()
                                var singleProgress = $("#updsingleProgress").val().trim()
                                var workHours = $('#updworkHours').val()+$("#updworkHoursUnit").val()
                                //校验
                                if(content==null || content==''){
                                    layer.msg("周结内容不能为空")
                                    ajax().abort()
                                }
                                if(singleProgress==null || singleProgress==''){
                                    layer.msg("进度不能为空")
                                    ajax().abort()
                                }
                                var r=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
                                if (r.test(singleProgress)||isNaN(singleProgress)) {
                                    layer.msg("您输入的进度指数不规范,请重新输入!");
                                    ajax.abort;
                                }
                                if($('#updworkHours').val().trim()==null || $('#updworkHours').val().trim()==''){
                                    layer.msg("工时不能为空")
                                    ajax().abort()
                                }
                                if (r.test($('#updworkHours').val().trim())||isNaN($('#updworkHours').val().trim())) {
                                    layer.msg("您输入的工时不规范,请重新输入!");
                                    ajax.abort;
                                }
                                if(parseInt(singleProgress)>100||parseFloat('100%') < parseFloat(singleProgress)){
                                    layer.msg("您输入的进度指数不规范")
                                    ajax().abort()
                                }

                                var assisMan=''
                                if($("#updassisman").val()!=null){
                                    assisMan = $("#updassisman").val().toString();
                                }
                                layer.confirm('确认要修改吗？', function (index) {
                                    $.ajax({
                                        url: '/updateMonthlySummary',
                                        dataType: 'json',
                                        data: {
                                            id: id,
                                            summaryId: summaryId,
                                            summaryContent: content,
                                            singleProgress: singleProgress,
                                            workHours: workHours,
                                            assismans: assisMan,
                                            year: year,
                                            month: month
                                        },
                                        success: function (data) {
                                            if (data.code == "200") {
                                                setTimeout(function wlh() {
                                                    window.location.href = "/monthly"
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


                        //删除  一月小结
                        $('.delsummary').click(function () {
                            var id = $(this).attr("id")
                            layer.confirm('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除吗？', {
                                title: '信息',
                                btn: ['朕意已决', '爱卿退下，朕且三思']
                            }, function (index) {
                                $.ajax({
                                    dataType: 'json',
                                    url: "/deletMonthlySummary",
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
                                                window.location.href = "/monthly"
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

        //获取周报内容
        function getWeekSummary(mid,summaryId) {
            $.ajax({
                url: '/getSummary',
                dataType: 'json',
                data: {
                    summaryId: summaryId
                },
                type:"get",
                success: function (data) {
                    var json = data.data
                    var progress = 1;
                    for (i in json) {
                        var str = $('<tr>');
                        str.append($('<td>').css('width', '250px').addClass('asd').html(eval(parseInt(i) + 1) + '、' + json[i].content)).append($('</td>').append($('</tr>')))
                        $(".weeklySummary" + '_' + mid).append(str);
                        //百分号转小数进行乘积
                        var progress1 = json[i].singleProgress.replace("%", "") / 100;
                        progress = progress + progress1;
                        //小数转百分数
                        if (i == json.length - 1) {
                            var number = (Number(progress * 100).toFixed(1) - 100) / json.length;
                            $('.weeklyProgress' + '_' + mid).html(number.toFixed(1) + "%")
                        }
                    }
                }
            })
        }
        function  getWeekDifficulty(mid,difficultyId) {
            $.ajax({
                url: '/getWeeklyDifficulty',
                dataType: 'json',
                data: {
                    difficultyId: difficultyId
                },
                type:"get",
                success: function (data) {
                    var json = data.data;
                    for (i in json) {
                        var str = $('<tr>');
                        str.append($('<td>').css('width', '250px').addClass('asd').html(json[i].content))
                        str.append($('</td>'));
                        str.append($('</tr>'));
                        $(".weeklyDifficulty" + '_' + mid).append(str);
                    }
                }
            })
        }

        //检查月小结 内容 进度 工时 的输入是否为空 是否规范
        function checkAddInput() {
            var addcontent = $("#addcontent").val().trim();
            var addsingleProgress = $("#addsingleProgress").val().trim();
            var addworkHours = $("#addworkHours").val().trim();
            //校验
            if(content==null || content==''){
                layer.msg("周结内容不能为空")
                ajax().abort()
            }
            if(singleProgress==null || singleProgress==''){
                layer.msg("进度不能为空")
                ajax().abort()
            }
            var r=/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i;
            if (r.test(singleProgress)) {
                layer.msg("您输入的进度指数不规范,请重新输入!");
                ajax.abort;
            }
            if($('#addworkHours').val().trim()==null || $('#addworkHours').val().trim()==''){
                layer.msg("工时不能为空")
                ajax().abort()
            }
            if (r.test($('#addworkHours').val().trim())) {
                layer.msg("您输入的工时不规范,请重新输入!");
                ajax.abort;
            }
            if(parseInt(singleProgress)>100||parseFloat('100%') < parseFloat(singleProgress)){
                layer.msg("您输入的进度指数不规范")
                ajax().abort()
            }
        }
    </script>
</head>

<body>
<div style="height: 10px;margin-left: 20px;"><b>当前操作:</b><span style="color: red">月报</span></div>
<input type="text" id="monthlyYear"  name="user_date" style="width:130px;margin-left: 10px;"  placeholder="请选择年份" />
<input type="text" id="monthlyMouth"  name="user_date" style="width:130px;margin-left: 10px;" placeholder="请选择月份" />
<input id="userid" placeholder="请输入用户ID"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询
</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增
</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username">欢迎 <font color="red">${sessionUser.fullName}</font> 登录米仓 月报</span>
<a id="home" href="/home" class="glyphicon glyphicon-home"></a>
<div>
    <table class="table table-bordered" id="table-bordered">
        <thead style="background-color: #f4f4f4;">
        <tr>
            <th>序号</th>
            <th>发布人</th>
            <th>年</th>
            <th>月</th>
            <th>月小结</th>
            <th>总体进度</th>
            <th>遇上的困难</th>
            <th>解决方案</th>
            <th>建议</th>
            <th>备注</th>
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
                <h4 class="modal-title">添加月报</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;">日期:</td>
                        <td>
                            <input type="text" id="addMonthlyDate" class="form-control date" name="user_date" style="width:130px" placeholder="请选择时间" />
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
<!--一月小结 -->
<div class="modal fade" id="setModal2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月小结新增</h4>
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
                            <input class="form-control" id="addsingleProgress" placeholder=" 请输入数字,例如:55(不能超过100)" />
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="addworkHours" placeholder="请输入数字,默认为m(分)"/>
                        </td>
                        <td style="width:15%;">
                            <select id="addworkHoursUnit" class="form-control">
                                <option value="分">m(分)</option>
                                <option value="时">h(时)</option>
                                <option value="天">d(天)</option>
                                <option value="月">w(周)</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">协助人:</td>
                        <td style="width:60%">
                            <select id="addassisman" data-placeholder="请选择协助人" multiple class="chzn-select"></select>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addSummary" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--一月小结  详情 -->
<div class="modal fade" id="setModal3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月小结详情</h4>
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
<!--一月小结  修改 -->
<div class="modal fade" id="setModal4" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月小结修改</h4>
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
                            <input class="form-control" id="updsingleProgress" placeholder=" 请输入数字,例如:55(不能超过100)"/>
                        </td>
                        <td style="width:15%;text-align: center"><span id="span2" style="color:red"></span></td>
                    </tr>
                    <tr>
                        <td style="width:12%;">工时:</td>
                        <td style="width:60%;">
                            <input class="form-control" id="updworkHours" placeholder="请输入数字,默认为m(分)"/>
                        </td>
                        <td>
                            <select class="form-control" id="updworkHoursUnit">
                                <option value="分">m(分)</option>
                                <option value="时">h(时)</option>
                                <option value="天">d(天)</option>
                                <option value="月">w(周)</option>
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

<!--一月 困难 新增-->
<div class="modal fade" id="setDifficulty1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月困难 新增</h4>
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
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addDifficulty" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一月困难 详情 -->
<div class="modal fade" id="setDifficulty2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月困难详情</h4>
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
<!--一月困难 修改 -->
<div class="modal fade" id="setDifficulty3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月困难 修改</h4>
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
<!--一月 方案 新增-->
<div class="modal fade" id="setProgramme1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月方案 新增</h4>
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
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addProgramme" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一月 方案 详情-->
<div class="modal fade" id="setProgramme2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月方案 详情</h4>
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
<!--一月 方案  修改 -->
<div class="modal fade" id="setProgramme3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月 方案 修改</h4>
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
<!--一月 建议 新增-->
<div class="modal fade" id="setSuggest1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月建议 新增</h4>
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
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addSuggest" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一月 建议 详情-->
<div class="modal fade" id="setSuggest2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月建议 详情</h4>
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
<!--一月 建议  修改 -->
<div class="modal fade" id="setSuggest3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月 建议 修改</h4>
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
<!--一月 备注 新增-->
<div class="modal fade" id="setRemark1" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月备注 新增</h4>
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
                    <button data-dismiss="modal" class="btn btn-default">关闭</button>
                    <button id="addRemark" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--一月 备注 详情-->
<div class="modal fade" id="setRemark2" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">一月备注 详情</h4>
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
<!--一月 备注  修改 -->
<div class="modal fade" id="setRemark3" data-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">月 备注 修改</h4>
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
                <div align="center"><b><font size="16" color="red"><span id="monthspan"></span>所有周报</font></b></div>
                <table class="table table-bordered" id="table-bordereds">
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
                    </tr>
                    </thead>
                    <tbody id="tbodys">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</body>
</html>
