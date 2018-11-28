<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录界面</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/Login.css"/>
    <link rel="stylesheet" href="/iconfont/style.css"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/layer/layer.js"></script>
    <script type="text/javascript" src="/js/md5.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <%--<style>--%>
    <%--body{margin:0;padding:0;background:url(images/bg.jpg) no-repeat;}--%>
    <%--.main_content{background:url(images/main_bg.png) repeat;}--%>
    <%--</style>--%>
</head>

<body  onkeydown="keyLogin();">
<div class="container wrap1" style="height:450px;">
    <h2 class="mg-b20 text-center box">米仓金服日报系统</h2>
    <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
        <p class="text-center font16 box">用户登录</p>
        <form>
            <div class="form-group mg-t20">
                <i class="icon-user icon_font"></i>
                <input type="tel" class="login_input" id="userName" placeholder="请输入用户名"/>
            </div>
            <div class="form-group mg-t20">
                <i class="icon-lock icon_font"></i>
                <input type="password" class="login_input" id="password" placeholder="请输入密码"/>
            </div>
            <div class="checkbox mg-b25" style="text-align: right;">
                <label>
                    <a style="color: white;" data-toggle="modal" data-target="#Modal">立即注册</a>
                </label>
            </div>
            <input type="button" class="login_btn" value="登录" onclick="Login()"/>
        </form>
    </div><!--row end-->
</div><!--container end-->

<div class="modal fade" id="Modal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="color: #333333;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">用户注册</h4>
            </div>
            <div class="modal-body">
                <table>
                    <tbody>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>真实姓名:</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="fullName" placeholder="请输入真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>用户名：</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="userNames" placeholder="请输入英文或数字组合">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>密码:</td>
                        <td style="width:60%;">
                            <input type="password" class="form-control" id="passwords" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>邮箱:</td>
                        <td style="width:60%;">
                            <input type="tel" class="form-control" id="email" placeholder="请输入邮箱">

                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>手机号:</td>
                        <td style="width:60%;">
                            <input type="tel" class="form-control" id="phone" placeholder="请输入手机号">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>籍贯:</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="birthplace" placeholder="请输入籍贯">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;">生日:</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="birthday" placeholder="请输入生日">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>职位:</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="position" placeholder="请输入职位">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>兴趣:</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="hobby" placeholder="请输入兴趣">
                        </td>
                    </tr>
                    <tr>
                        <td style="width:12%;"><span style="color: red;">*</span>座右铭:</td>
                        <td style="width:60%;">
                            <input type="text" class="form-control" id="motto" placeholder="请输入座右铭">
                        </td>
                    </tr>
                    </tbody>
                </table>
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
<script>
    function keyLogin(){
        if (event.keyCode==13)
            $(".login_btn").click();
    }

    function Login() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        if (userName == "") {
            layer.alert("<p style='color:#333333'>" + "请输入用户名" + "</p>", {
                icon: 0
            });
            return false;
        }
        if (password == "") {
            layer.alert("<p style='color:#333333'>" + "请输入密码" + "</p>", {
                icon: 0
            });
            return false;
        }
        $.ajax({
            url: "/userLogin",
            data: {
                userName: userName,
                password: hex_md5(hex_md5(password))
            },
            dataType: "json",
            type: "GET",
            success: function (data) {
                if (data.code == 200) {
                    window.location.href = "/table"
                } else {
                    layer.msg("登录失败")
                    window.location.href = "/login"
                }
            }
        })
    }

    $('#register').click(function () {
        $('#Modal input').val('');
    })

    $('#add ').click(function () {
        var fullName = $('#fullName').val();
        var userName = $('#userNames').val();
        var passwords = $('#passwords').val();
        var email = $('#email').val();
        var phone = $('#phone').val();
        var birthplace = $('#birthplace').val();
        var position = $('#position').val();
        var birthday = $("#birthday").val();
        var hobby = $('#hobby').val();
        var motto = $('#motto').val();

        if (fullName == "") {
            layer.alert("<p style='color:#333333'>" + "请输入真实姓名！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (userName == "") {
            layer.alert("<p style='color:#333333'>" + "请输入用户名！" + "</p>", {
                icon: 0
            });
            return false;
        }
        if (!(/^[\da-z]+$/i.test(userName))) {
            layer.alert("<p style='color:#333333'>" + "用户名只能输入数字和英文组合！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (passwords == "") {
            layer.alert("<p style='color:#333333'>" + "请输入密码！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (email == "") {
            layer.alert("<p style='color:#333333'>" + "请输入邮箱！" + "</p>", {
                icon: 0
            });
            return false;
        }
        if (!(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email))) {
            layer.alert("<p style='color:#333333'>" + "请输入正确的邮箱！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (phone == "") {
            layer.alert("<p style='color:#333333'>" + "请输入手机号码！" + "</p>", {
                icon: 0
            });
            return false;
        }
        if (!(/^1[34578]\d{9}$/.test(phone))) {
            layer.alert("<p style='color:#333333'>" + "请输入正确的手机号码！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (birthplace == "") {
            layer.alert("<p style='color:#333333'>" + "请输入籍贯！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (position == "") {
            layer.alert("<p style='color:#333333'>" + "请输入职位！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (hobby == "") {
            layer.alert("<p style='color:#333333'>" + "请输入兴趣！" + "</p>", {
                icon: 0
            });
            return false;
        }

        if (motto == "") {
            layer.alert("<p style='color:#333333'>" + "请输入座右铭！" + "</p>", {
                icon: 0
            });
            return false;
        }

        $.ajax({
            url: "/register",
            data: {
                'fullName': fullName,
                'userName': userName,
                'password': hex_md5(hex_md5(passwords)),
                'email': email,
                'phone': phone,
                'birthplace': birthplace,
                'birthday': birthday,
                'position': position,
                'hobby': hobby,
                'motto': motto
            },
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    layer.msg("添加成功！");
                    $('#Modal .modal-footer button').eq(0).trigger('click')
                }else{
                    layer.msg("添加失败！");
                }
            }
        })

    })
</script>
