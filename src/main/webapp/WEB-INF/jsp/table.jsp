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

		<script>
			$(function  () {
                fic();
				inittype();
				initsurface($('#type').val())
				initline($('#type').val(),$('#surface').val())
                initpoint($('#type').val(),$('#surface').val(),$('#line').val())

                $('#add').click(function ()
                {
                    var type = $('#type').val();
                    var surface = $('#surface').val();
                    var line = $('#line').val();
                    var point = $('#point').val();
                    var eventName=$("#event").val();
                    var process=$("#process").val();
                    var result=$("#result").val();
                    var method=$("#method").val();
                    var remarks=$("#remarks").val();
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
                            remarks:remarks
                        },
                        success :function (data)
                        {
                            if(data.code==200){
                                $("#event").val('');
                                $("#process").val('');
                                $("#result").val('');
                                $("#method").val('');
                                $("#remarks").val('');
                                alert("添加成功");
                            }else{
                                alert("添加失败");
                            }
                        }
                    });
                })

                $('#type').next().click(function()
                {
                    $('#addtype').val('')
                    $('#addtype').show();
                    $('#type').hide()
                })
                $('#addtype').keyup(function(event){
                    if(event.keyCode ==13)
                    {
                        $('#type').show();
                        $('#addtype').hide()
                        var addtype = $('#addtype').val();
                        if(addtype == null || addtype == '')
                        	layer.msg('添加的类型名字不能为空');
                        $.ajax({
                            url:"addType",
                            type:"post",
                            dataType:'json',
                            data:{typeName:addtype,},
                            success :function (data)
                            {
                                if(data.code==200)
								{
                                    inittype();
                                    layer.msg("添加成功");
								}
                                else
                                    layer.msg("添加失败");
                            }
                        });
                    }
                });

                $('#surface').next().click(function()
                {
                    $('#addsurface').val('')
                    $('#addsurface').show();
                    $('#surface').hide()
                })
                $('#addsurface').keyup(function(event){
                    if(event.keyCode ==13)
                    {
                        $('#surface').show();
                        $('#addsurface').hide()
                        var addsurface = $('#addsurface').val();
                        if(addsurface == null || addsurface == '')
                            layer.msg('添加的面名字不能为空');
                        $.ajax({
                            url:"addSurface",
                            type:"post",
                            dataType:'json',
                            data:
							{
                                typeId:$('#type').val(),
                                surfaceName:addsurface,
							},
                            success :function (data)
                            {
                                if(data.code==200)
								{
                                    initsurface($('#type').val())
                                    layer.msg("添加成功");
								}
                                else
                                    layer.msg("添加失败");
                            }
                        });
                    }
                });

                $('#line').next().click(function()
                {
                    $('#addline').val('')
                    $('#addline').show();
                    $('#line').hide()
                })
                $('#addline').keyup(function(event){
                    if(event.keyCode ==13)
                    {
                        $('#line').show();
                        $('#addline').hide()
                        var addline = $('#addline').val();
                        if(addline == null || addline == '')
                            layer.msg('添加的线名字不能为空');
                        $.ajax({
                            url:"addLine",
                            type:"post",
                            dataType:'json',
                            data:
							{
                                typeId:$('#type').val(),
                                surfaceId:$('#surface').val(),
                                lineName:addline,
							},
                            success :function (data)
                            {
                                if(data.code==200)
								{
                                    initline($('#type').val(),$('#surface').val())
                                    layer.msg("添加成功");
								}
                                else
                                    layer.msg("添加失败");
                            }
                        });
                    }
                });

                $('#point').next().click(function()
                {
                    $('#addpoint').val('')
                    $('#addpoint').show();
                    $('#point').hide()
                })
                $('#addpoint').keyup(function(event){
                    if(event.keyCode ==13)
                    {
                        $('#point').show();
                        $('#addpoint').hide()
                        var addpoint = $('#addpoint').val();
                        if(addpoint == null || addpoint == '')
                            layer.msg('添加的点名字不能为空');
                        $.ajax({
                            url:"addPoint",
                            type:"post",
                            dataType:'json',
                            data:
							{
                                typeId:$('#type').val(),
                                surfaceId:$('#surface').val(),
                                lineId:$('#line').val(),
                                pointName:addpoint,
							},
                            success :function (data)
                            {
                                if(data.code==200)
								{
                                    initpoint($('#type').val(),$('#surface').val(),$('#line').val())
                                    layer.msg("添加成功");
								}
                                else
                                    layer.msg("添加失败");
                            }
                        });
                    }
                });

			})

            function fic(){
			    console.log(2222222222222222222222222);
                $("#tbody").empty();
                var eventId = $("#eventId").val();
                $.ajax({
                    type: 'get',
                    url: '/getDailyInfo',
                    dataType: 'json',
                    data: {
                        eventId:eventId,
                        isLive: 1
                    },
                    success: function (data) {
                        var str;
                        for (i in  data.data) {
                            str = '<td>' + data.data[i].id + '</td>' +
                                '<td>' + data.data[i].date + '</td>' +
                                '<td>' + data.data[i].time + '</td>' +
                                '<td>' + data.data[i].type_name + '</td>' +
                                '<td>' + data.data[i].surface_name + '</td>' +
                                '<td>' + data.data[i].line_name + '</td>' +
                                '<td>' + data.data[i].point_name + '</td>'+
                                '<td>' + data.data[i].event_name + '</td>'+
                                '<td>' + data.data[i].process + '</td>'+
                                '<td>' + data.data[i].result + '</td>' +
                                '<td>' + data.data[i].method + '</td>'+
                                '<td>' + data.data[i].remarks + '</td>';
                            $("#tbody").append('<tr>' + str + '</tr>');
                        }
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
					    for(var i = 0 ; i<json.length ;i++)
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
                    data:{typeId:typeid,islive:1},
                    async:false,
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i = 0 ; i<json.length ;i++)
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
                        for(var i = 0 ; i<json.length ;i++)
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
                        typeId:typeid,``
                        surfaceId:surfaceid,
                        lineId:lineid,
                        islive:1
                    },
                    async:false,
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i = 0 ; i<json.length ;i++) {
                            $('#point').append($('<option>').val(json[i].pointId).html(json[i].pointName))
							return json[i].pointId;
                        }
                    }
                });
            }

		</script>
	</head>
	<body>
		
		<button style="margin: 30px;" class="btn btn-danger" data-toggle="modal" data-target="#addModal" ><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
		<span style="float: right;margin:20px 40px 0px 0px" id="usename"></span>
		<div>
			<table class="table table-bordered" id="table-bordered">
				<thead>
				<tr>
					<th>编号</th>
					<th>日期</th>
					<th>时间点</th>
					<th>类型</th>
					<th>面</th>
					<th>线</th>
					<th>点</th>
					<th>事件</th>
					<th>过程</th>
					<th>结果</th>
					<th>解决方案</th>
					<th>备注</th>
				</tr>
				</thead>
				<tbody id="tbody">

				</tbody>
			</table>
		</div>


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
									<i class="glyphicon glyphicon-plus"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">面:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addsurface" style="display:none;">
									<select class="form-control" id="surface"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-plus"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">线:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addline" style="display:none;">
									<select class="form-control" id="line"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-plus"></i></button>
								</td>
							</tr>
							<tr>
								<td style="width:12%;">点:</td>
								<td style="width:60%;">
									<input type="text" class="form-control" id="addpoint" style="display:none;">
									<select class="form-control" id="point"></select>&nbsp;<button class="btn btn-danger">
									<i class="glyphicon glyphicon-plus"></i></button>
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
							<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
							<button id="add" class="btn btn-primary">提交</button>
						</div>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
    
	</body>
</html>
