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
		<script type="text/javascript" src="/layer/layer.js" ></script>
		<script type="text/javascript" src="/laydate/laydate.js" ></script>

		<script>
			$(function  () {
                laydate.render({elem : '#startDate'});
                laydate.render({elem : '#endDate'});

                //初始化
                inittable();
				inittype();
				initsurface($('#type').val())
				initline($('#type').val(),$('#surface').val())
                initpoint($('#type').val(),$('#surface').val(),$('#line').val())

				$('#type').change(function ()
				{
                    initsurface($('#type').val())
                    initline($('#type').val(),$('#surface').val())
                    initpoint($('#type').val(),$('#surface').val(),$('#line').val())
                })

                $('#surface').change(function ()
                {
                    initline($('#type').val(),$('#surface').val())
                    initpoint($('#type').val(),$('#surface').val(),$('#line').val())
                })

                $('#line').change(function ()
                {
                    initpoint($('#type').val(),$('#surface').val(),$('#line').val())
                })



                $('#add').click(function ()
                {
                    checkAddInput();
                    var type = $('#type').val();
                    var surface = $('#surface').val();
                    var line = $('#line').val();
                    var point = $('#point').val();
                    var eventName=$("#event").val();
                    var process=$("#process").val();
                    var result=$("#result").val();
                    var method=$("#method").val();
                    var remarks=$("#remarks").val();
                    var typeName = $('#addtype').val();
                    var surfaceName = $('#addsurface').val();
                    var lineName = $('#addline').val();
                    var pointName = $('#addpoint').val();
                        $.ajax({
                            url:"addDaily",
                            dataType:'json',
                            data:{
                                type:type,
                                surface:surface,
                                line:line,
                                point:point,
                                eventName:eventName,
                                process:process,
                                result:result,
                                method:method,
                                remarks:remarks,
                                typeName:typeName,
                                surfaceName:surfaceName,
                                lineName:lineName,
                                pointName:pointName
                            },
                            success :function (data)
                            {
                                if(data.code==200){
                                    $("#event").val('');
                                    $("#process").val('');
                                    $("#result").val('');
                                    $("#method").val('');
                                    $("#remarks").val('');
                                    window.location.href="/table";
                                    layer.msg("添加成功");
                                }else{
                                    layer.msg("添加失败");
                                }
                            }
                        });
                })

                $('#type').next().bind('click',function()
                {
                    $('#addtype').val('')
                    $('#addtype').toggle();
                    $('#type').toggle();
                })


                $('#surface').next().bind('click',function()
                {
                    $('#addsurface').val('')
                    $('#addsurface').toggle();
                    $('#surface').toggle()
                })


                $('#line').next().bind('click',function()
                {
                    $('#addline').val('')
                    $('#addline').toggle();
                    $('#line').toggle()
                })


                $('#point').next().bind('click',function()
                {
                    $('#addpoint').val('')
                    $('#addpoint').toggle();
                    $('#point').toggle()
                })

                $('#query').click(function () { inittable() })


			})

            function inittable(){
                var startDate = $('#startDate').val().replace('-','').replace('-','');
                var endDate = $('#endDate').val().replace('-','').replace('-','');
                var userid = $('#userid').val();
                var data ={};
                if(startDate != '' && endDate != '' && userid != '')
                    data = {startDate : startDate,endDate : endDate,userId : userid};
                if(startDate != '' && endDate != '' && userid == '')
                    data = {startDate : startDate,endDate : endDate};
                if(startDate == '' && endDate == '' && userid != '')
                    data = {userId : userid};
                $("#tbody").empty();
                $.ajax({
                    type: 'get',
                    url: '/getDaily',
                    dataType: 'json',
                    data:data,
                    success: function (data) {
                        console.info(data)
                        $('#username').html('欢迎 '+'<font color="red">'+data.fullName+'</font>'+' 登录米仓日报');
                        for (i in  data.data) {
                            var tr = $('<tr>');
                            if (typeof (data.data[i].id) != 'undefined') {
                                tr.append($('<td>').html(data.data[i].id))
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
                                var set = $('<button>').addClass('btn btn-warning').css('margin-right', '10px').attr('data-toggle', 'modal').attr('data-target', '#setModal').html('<i class="glyphicon glyphicon-edit"></i>');
                                var del = $('<button>').addClass('btn btn-danger delbtn').html('<i class="glyphicon glyphicon-trash"></i>');
                                var td = $('<td>');
                                td.append(set);
                                td.append(del);
                                tr.append(td);
                                $("#tbody").append(tr);
                            }
                        }
                        $('.delbtn').click(function (){
                            var id =$(this).parent().parent().children().eq(0).text()
                            layer.confirm('确认要删除吗？', function(index) {
                                $.ajax({
                                    dataType: 'json',
                                    type: "post",
                                    url: "/deleteDailyRecord",
                                    data: { id:id},
                                    success: function(data) {
                                        if(data.code == "200") {
                                            layer.msg('已删除!', {
                                                icon: 1,
                                                time: 1000
                                            });
                                            window.location.href="/table"
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
                    },
                })
            }

			function inittype()
			{
                $('#type').html('');
                $.ajax({
                    type:"get",
                    url:"getType",
                    dataType:'json',
                    data:{islive:1},
					async:false,
                    success :function (data)
					{
					    var json = data.data;
					    for(var i in json)
                            $('#type').append($('<option>').val(json[i].typeId).html(json[i].typeName))
					}
                });
            }
            function initsurface(typeid)
            {
                $('#surface').html('');
                $.ajax({
                    type:"get",
                    url:"getSurface",
                    dataType:'json',
                    data:{
                        typeId:typeid,
						islive:1
					},
                    async:false,
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i in json)
                            $('#surface').append($('<option>').val(json[i].surfaceId).html(json[i].surfaceName))
                    }
                });
            }
            function initline(typeid,surfaceid)
            {
                $('#line').html('');
                $.ajax({
                    type:"get",
                    url:"getLine",
                    dataType:'json',
                    data:{
                        typeId:typeid,
                        surfaceId:surfaceid,
						islive:1
					},
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i in json)
                            $('#line').append($('<option>').val(json[i].lineId).html(json[i].lineName))
                    }
                });
            }
            function initpoint(typeid,surfaceid,lineid)
            {
                $('#point').html('');
                $.ajax({
                    type:"get",
                    url:"getPoint",
                    dataType:'json',
                    data:{
                        typeId:typeid,
                        surfaceId:surfaceid,
                        lineId:lineid,
                        islive:1
                    },
                    async:false,
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i in json) {
                            $('#point').append($('<option>').val(json[i].pointId).html(json[i].pointName))
							return json[i].pointId;
                        }
                    }
                });
            }

            //检查事件 过程 结果 的输入是否为空
            function checkAddInput() {
				var event = $("#event").val();
				var process = $("#process").val();
				var result = $("#result").val();
				if(event==null||event==''){
				    alert("事件不能为空！");
				    ajax().abort;
				}
				if(process==null||process==''){
				    alert("过程不能为空！");
				    ajax.abort;
				}
				if(result==null||result==''){
				    alert("结果不能为空!");
				    ajax.abort;
				}
            }


	</script>
</head>
<body>
<input type="text" id="startDate" name="user_date"style="width:130px" class="layui-input" placeholder="请选择开始时间" />
—
<input type="text" id="endDate" name="user_date"style="width:130px" class="layui-input" placeholder="请选择结束时间" />
<input  id="userid"  placeholder="请输入用户ID"/>
<button id="query" style="margin: 30px;" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i>&nbsp;查询</button>
<button class="btn btn-danger" data-toggle="modal" data-target="#addModal" ><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
<span style="float: right;margin:20px 40px 0px 0px;" id="username"></span>
<div>
	<table class="table table-bordered" id="table-bordered">
		<thead>
		<tr>
			<th width="50px">编&nbsp;号</th>
			<th width="30px">日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期</th>
			<th width="30px">时&nbsp;&nbsp;间&nbsp;&nbsp;点</th>
			<th width="50px">类型</th>
			<th width="50px">&nbsp;&nbsp;面</th>
			<th width="75px"><span>线</span></th>
			<th width="75px">&nbsp;&nbsp;&nbsp;&nbsp;点</th>
			<th width="150px"  style="text-align: center">事件</th>
			<th width="150px" style="text-align: center">过程</th>
			<th width="150px" style="text-align: center">结果</th>
			<th width="150px" style="text-align: center">解决方案</th>
			<th width="150px" style="text-align: center">备注</th>
			<th width="150px" style="text-align: center">操作</th>
		</tr>
		</thead>
		<tbody id="tbody">

				</tbody>
			</table>
		</div>

		<!--新增 -->
  		<div class="modal fade" id="addModal"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">新增</h4>
					</div>
					<div class="modal-body">
						<table>
							<tbody><tr>
								<td style="width:12%;">类型:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addtype" style="display:none;">
									<select class="form-control" id="type"></select>&nbsp;<button class="btn btn-danger" onclick="showSelect();">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">面:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addsurface" style="display:none;">
									<select class="form-control" id="surface"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">线:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addline" style="display:none;">
									<select class="form-control" id="line"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">点:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addpoint" style="display:none;">
									<select class="form-control" id="point"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">事件:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="event"></textarea>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">过程:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="process"></textarea>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">结果:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="result"></textarea>;
								</td>
							</tr>
							<tr>
								<td style="width:12%;">解决方案:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="method"></textarea>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">备注:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="remarks"></textarea>
								</td>
							</tr>
							</tbody></table>
						<div class="modal-footer">
							<button data-dismiss="modal" class="btn btn-default">关闭</button>
							<button id="add" class="btn btn-primary">提交</button>
						</div>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
		<!--修改 -->
		<div class="modal fade" id="setModal"  data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
						<h4 class="modal-title">修改</h4>
					</div>
					<div class="modal-body">
						<table>
							<tbody><tr>
								<td style="width:12%;">类型:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addSetType" style="display:none;">
									<select class="form-control" id="setType"></select>&nbsp;<button class="btn btn-danger" onclick="showSelect();">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">面:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addSetSurface" style="display:none;">
									<select class="form-control" id="setSurface"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">线:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addSetLine" style="display:none;">
									<select class="form-control" id="setLine"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">点:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addSetPoint" style="display:none;">
									<select class="form-control" id="setPoint"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-transfer"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">事件:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="setEvent"></textarea>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">过程:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="setProcess"></textarea>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">结果:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="setResult"></textarea>;
								</td>
							</tr>
							<tr>
								<td style="width:12%;">解决方案:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="setMethod"></textarea>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">备注:</td>
								<td style="width:60%;">
									<textarea class="form-control" id="setRemarks"></textarea>
								</td>
							</tr>
							</tbody></table>
						<div class="modal-footer">
							<button data-dismiss="modal" class="btn btn-default">关闭</button>
							<button id="set" class="btn btn-primary">提交</button>
						</div>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
	</body>
</html>
