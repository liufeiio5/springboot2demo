<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录界面</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/Login.css" />
<link rel="stylesheet" href="/iconfont/style.css" />
<script type="text/javascript" src="/js/jquery.min.js" ></script>
<script type="text/javascript" src="/layer/layer.js" ></script>
    <script type="text/javascript" src="/js/md5.js" ></script>
    <%--<style>--%>
        <%--body{margin:0;padding:0;background:url(images/bg.jpg) no-repeat;}--%>
        <%--.main_content{background:url(images/main_bg.png) repeat;}--%>
    <%--</style>--%>
</head>

<body>
    <div class="container wrap1" style="height:450px;">
            <h2 class="mg-b20 text-center box">米仓金服日报系统</h2>
            <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
                <p class="text-center font16 box">用户登录</p>
                <form>
                    <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        <input type="tel" class="login_input" id="userName" placeholder="请输入用户名" value="卜志锋" />
                    </div>
                    <div class="form-group mg-t20">
                        <i class="icon-lock icon_font"></i>
                        <input type="password" class="login_input" id="password" placeholder="请输入密码" value="bzf7330" />
                    </div>
                    <%--<div class="checkbox mg-b25">--%>
                        <%--<label>--%>
                            <%--<input type="checkbox" />记住账号--%>
                        <%--</label>--%>
                    <%--</div>--%>
                    <input type="button" class="login_btn" value="登录" onclick="Login()" />
               </form>
        </div><!--row end-->
    </div><!--container end-->
</body>
</html>
<script>
	function Login() {
	    var userName = $("#userName").val();
	    var password = $("#password").val();
		if(userName == ""){
			layer.alert("<p style='color:#333333'>" + "请输入用户名" + "</p>",{
				icon:0
			});
			return false;
		}
		if(password == ""){
			layer.alert("<p style='color:#333333'>" + "请输入密码" + "</p>",{
				icon:0
			});
			return false;
		}
        $.ajax({
            url:"/userLogin",
            data:{
                userName:userName,
                //password:hex_md5(hex_md5('password'))
                password:password
            },
            dataType:"json",
            type:"GET",
            success:function (data) {
                if(data.code==200){
                    window.location.href="http://localhost:9000/table"
                }else{
                    layer.msg("登录失败")
                    window.location.href="http://localhost:9000/login"
                }
            }
        })

	}
</script>
