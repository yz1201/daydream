<%@ page import="cn.dbdj1201.entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/23
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>1</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-3.4.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>

</head>
<body class="bg-danger">

</body>
<%
    List<String> users = new ArrayList<>();
    users.add("1");
    users.add("1");
    users.add("1");
    users.add("1");
    users.add("1");
    request.setAttribute("users", users);

%>

<c:forEach items="${users}" varStatus="s" var="user">
    ${user}
</c:forEach>
</html>
