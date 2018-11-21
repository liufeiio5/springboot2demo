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
				
				
			})
		</script>
	</head>
	<body>
		
		<button style="margin: 30px;" class="btn btn-danger" id="query" data-toggle="modal" data-target="#addModal" ><i class="glyphicon glyphicon-plus"></i>&nbsp;新增</button>
		<span style="float: right;margin:20px 40px 0px 0px" id="usename"></span>
		<table class="table table-bordered">
        <thead>
        <tr>
            <th>产品编号</th>
            <th>产品名称</th>
            <th>品牌编号</th>
            <th>上架</th>
            <th>商家编号</th>
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
						<select id="type"></select><br />
						<select id="surface"></select><br />
						<select id="line"></select><br />
						<select id="point"></select><br />
						<select id="type"></select><br />
						<textarea id="event"></textarea><br />
						<textarea id="process"></textarea><br />
						<textarea id="result"></textarea><br />
						<textarea id="method"></textarea><br />
						<textarea id="remarks"></textarea><br />
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div>
    
	</body>
</html>
