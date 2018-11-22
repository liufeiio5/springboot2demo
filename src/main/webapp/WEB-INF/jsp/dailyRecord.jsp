<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

    <script>
        $(function  () {
            fic();

        })
        function fic(){
            $("#tbody").empty();
            var userId = $("#userId").val();
            $.ajax({
                type: 'get',
                url: '/getDailyRecord',
                dataType: 'json',
                data: {
                    userId:userId
                },
                success: function (data) {
                    var str;
                    for (i in  data.data) {
                        str = '<td>' + data.data[i].id + '</td>' +
                            '<td>' + data.data[i].userId + '</td>' +
                            '<td>' + data.data[i].type + '</td>' +
                            '<td>' + data.data[i].surface + '</td>' +
                            '<td>' + data.data[i].line + '</td>' +
                            '<td>' + data.data[i].point + '' + '</td>' +
                            '<td>' + data.data[i].event + '</td>'+
                            '<td>' + data.data[i].process + '</td>'+
                            '<td>' + data.data[i].result + '</td>'+
                            '<td>' + data.data[i].method + '</td>' +
                            '<td>' + data.data[i].remark + '</td>'+
                            '<td>' + data.data[i].dateTime + '</td>'+
                            '<td>' + data.data[i].createTime + '</td>';
                        $("#tbody").append('<tr>' + str + '</tr>');
                    }
                },
            })
        }


        //添加日记
        function addDailyRecord(){
            var userId = $("#userId").val();
            var type = $("#type").val();
            var surface = $("#surface").val();
            var line = $("#line").val();
            var point = $("#point").val();
            var event = $("#event").val();
            var process = $("#process").val();
            var result = $("#result").val();
            var method = $("#method").val();
            var remark = $("#remark").val();
            var dateTime = $("#dateTime").val();
            $.ajax({
                url:"/addDailyRecord",
                data:{
                    userId:userId,
                    type:type,
                    surface:surface,
                    line:line,
                    point:point,
                    event:event,
                    process:process,
                    result:result,
                    method:method,
                    remark:remark,
                    dateTime:dateTime
                },
                dataType:"json",
                type:"post",
                success:function (data) {
                    if(data){
                        alert("添加成功");
                        window.location.href="http://localhost:9000/dailyRecord";
                    }else{
                        alert("添加失败");
                        window.location.href="http://localhost:9000/dailyRecord";
                    }
                }
            })

        }
    </script>
</head>
<body>
    <div>
        <input name="name" id="goodsId" style="margin-left: 20px;"/>
        <button id="query"  class="btn btn-info" onclick="fic()">查询</button>
    </div>
<button style="margin: 30px;" class="btn btn-danger" data-toggle="modal" data-target="#addModal" ><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
<span style="float: right;margin:20px 40px 0px 0px" id="usename"></span>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>用户ID</th>
        <th>类型</th>
        <th>面</th>
        <th>线</th>
        <th>点</th>
        <th>事件</th>
        <th>过程</th>
        <th>结果</th>
        <th>解决方案</th>
        <th>备注</th>
        <th>日期</th>
        <th>创建时间</th>
    </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>

<div class="modal fade" id="addModal"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">新增</h4>
            </div>
            <div class="modal-body">
                <p>用户ID:</p>
                <textarea class="form-control" id="userId"></textarea><br />
                <p>类型:</p>
                <textarea class="form-control" id="type"></textarea><br />
                <p>面:</p>
                <textarea class="form-control" id="surface"></textarea><br />
                <p>线:</p>
                <textarea class="form-control" id="line"></textarea><br />
                <p>点:</p>
                <textarea class="form-control" id="point"></textarea><br />
                <p>事件:</p>
                <textarea class="form-control" id="event"></textarea><br />
                <p>过程:</p>
                <textarea class="form-control" id="process"></textarea><br />
                <p>结果:</p>
                <textarea class="form-control" id="result"></textarea><br />
                <p>解决方法:</p>
                <textarea class="form-control" id="method"></textarea><br />
                <p>备注:</p>
                <textarea class="form-control" id="remarks"></textarea><br />
                <p>创建日期:</p>
                <textarea class="form-control" id="dateTime"></textarea><br />
                <button type="add" class="btn btn-primary" onclick="addDailyRecord()"><i class="glyphicon glyphicon-plus"></i></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

</body>
</html>
