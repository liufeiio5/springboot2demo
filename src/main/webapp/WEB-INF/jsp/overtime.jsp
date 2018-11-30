<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/table.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.1/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/laydate/laydate.js"></script>
    <script type="text/javascript" src="/js/Date.js"></script>

    <script>
        $(function() {
            laydate.render({
                elem: '#startDate'
            });
            laydate.render({
                elem: '#endDate'
            });
            laydate.render({
                elem: '#selectDate'
            });
        })

        function dailyRecord() {
            window.location.href="/table";
        }

    </script>

</head>
<body onkeydown="onkeydownfun()">
<input type="text" id="startDate" name="user_date" style="width:130px;margin-left: 10px;" class="layui-input" placeholder="请选择开始日期" /> —
<input type="text" id="endDate" name="user_date" style="width:130px" class="layui-input" placeholder="请选择结束日期" />
<input id="userid" placeholder="请输入用户ID" />
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal"><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
<button class="btn btn-danger" onclick="dailyRecord()"><i class="glyphicon glyphicon-plus"></i>&nbsp;日报</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username"></span>
<div>
    <table class="table table-bordered" id="table-bordered">
        <thead>
        <tr>
            <th width="75px">日期
            </th>
            <th width="75px">开始时间
            </th>
            <th width="70px">结束时间
            </th>
            <th width="60px">时长
            </th>
            <th width="50px">姓名
            </th>
            <th width="50px">事由
            </th>
            <th width="105px">事项
            </th>
            <th width="75px">处理进度
            </th>
            <th width="120px">处理结果
            </th>
            <th width="120px">备注</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>
    </table>
</div>

<!--新增加班记录 -->
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
                            <input type="text" id="selectDate" name="date" style="width:130px" class="layui-input form-control" placeholder="请选择开始时间" />
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">开始时间 :
                        </td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="startTime">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">结束时间 :
                        </td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="endTime">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">时长 :
                        </td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="duration">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">事由 :
                        </td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="cause"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">事项 :
                        </td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="matter"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">处理进度 :
                        </td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="schedule"></textarea>;
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">处理结果 :
                        </td>
                        <td style="width:60%;">
                            <textarea class="form-control" id="result"></textarea>
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
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

</body>
</html>
