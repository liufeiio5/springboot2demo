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
				inittype();
				initsurface($('#type').val())
				initline($('#type').val(),$('#surface').val())
                initpoint($('#type').val(),$('#surface').val(),$('#line').val())
				
			})
			function inittype()
			{
                $('#type').html('');
                $.ajax({
                    type:"get",
                    url:"getType",
                    dataType:'json',
                    data:{islive:1},
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
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i = 0 ; i<json.length ;i++)
                            $('#surface').append($('<option>').val(json[i].typeId).html(json[i].typeName))
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
                            $('#line').append($('<option>').val(json[i].typeId).html(json[i].typeName))
                    }
                });
            }
            function initpoint(typeid,surfaceid,pointid)
            {
                $('#point').html('');
                $.ajax({
                    type:"get",
                    url:"getPoint",
                    dataType:'json',
                    data:{
                        typeId:typeid,
                        surfaceId:surfaceid,
                        pointId:pointid,
                        islive:1
                    },
                    success :function (data)
                    {
                        var json = data.data;
                        for(var i = 0 ; i<json.length ;i++)
                            $('#point').append($('<option>').val(json[i].typeId).html(json[i].typeName))
                    }
                });
            }
		</script>
	</head>
	<body>
		
		<button style="margin: 30px;" class="btn btn-danger" data-toggle="modal" data-target="#addModal" ><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
		<span style="float: right;margin:20px 40px 0px 0px" id="usename"></span>
		<table class="table table-bordered">
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
        <tbody>
            
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
						<select class="form-control" id="type"></select><br />
						<select class="form-control" id="surface"></select><br />
						<select class="form-control" id="line"></select><br />
						<select class="form-control" id="point"></select><br />
						<textarea class="form-control" id="event"></textarea><br />
						<textarea class="form-control" id="process"></textarea><br />
						<textarea class="form-control" id="result"></textarea><br />
						<textarea class="form-control" id="method"></textarea><br />
						<textarea class="form-control" id="remarks"></textarea><br />
						<button type="add" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i></button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
    
	</body>
</html>
