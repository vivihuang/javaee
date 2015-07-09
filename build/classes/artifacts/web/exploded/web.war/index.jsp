<%--
  Created by IntelliJ IDEA.
  User: Vivi
  Date: 7/7/15
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title></title>
</head>

<body>

<%--<%--%>
  <%--//List<User> userList = new ArrayList<User>();--%>
  <%--//request.setAttribute("userList",userList);--%>
<%--%>--%>

<a href="user.jsp">添加</a>
<%--<c:out value="hi"/><br>--%>
<table border="4">
  <tr align="center">
    <%--<td><c:out value="编号" /></td>--%>
    <td><c:out value="姓名" /></td>
    <td><c:out value="性别" /></td>
    <td><c:out value="邮箱" /></td>
    <td><c:out value="年龄" /></td>

  </tr>

  <c:forEach items="${userList}"  var="item" varStatus="status" >
    <tr align="center">
      <%--<td><c:out value="${item.id} " /></td>--%>
      <td><c:out value="${item.name}" /></td>
      <td><c:out value="${item.sex}" /></td>
      <td><c:out value="${item.email}" /></td>
      <td><c:out value="${item.age}" /></td>
      <td><a href="UserServlet?action=updateUser&userId=<c:out value="${item.id}"/>">修改</a></td>
      <td><a href="UserServlet?action=deleteUser&userId=<c:out value="${item.id}"/>">删除</a></td>
    </tr>
  </c:forEach>
</table>
  </body>
</html>
