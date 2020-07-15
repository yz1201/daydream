<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        //切换验证码
        function refreshCode() {
            //1.获取验证码图片对象
            const vcode = document.getElementById("vcode");

            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/captcha?time=" + new Date().getTime();
        }
    </script>
    <style>
        #div_refresh {
            align-content: center;
        }

        input, h3 {
            text-align: center;
        }

        div{
            text-align: center;
            align-content: center;
        }

        h3 {
            margin-top: 20px;
        }
    </style>
</head>
<body class="bg-success">
<div class="container" style="width: 400px; align-content: center">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline" id="div_refresh">
            <label for="verifyCode">VerifyCode:&nbsp;</label>
            <input id="verifyCode" name="verifyCode" class="form-control"
                   type="text"><br>
            <%--            <img src="captcha"  width="130px" height="48px"/>--%>
            <a href="javascript:refreshCode();" style="margin-top: 20px; margin-left: 120px">
                <img src="${pageContext.request.contextPath}/captcha" title="看不清点击刷新" id="vcode" alt=""/>
            </a>

        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert">
            <span>&times;</span>
        </button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>