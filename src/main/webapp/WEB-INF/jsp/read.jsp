<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-04-21
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>read</title>
</head>
<body>
<table border="1" style="width: 600px;">
    <caption>board</caption>
    <colgroup>
        <col width="15%"/>
        <col width="*%"/>
    </colgroup>
    <tbody>
    <tr>
        <td>writer</td>
        <td><c:out value="${boardInfo.brdwriter}"/></td>
    </tr>
    <tr>
        <td>title</td>
        <td><c:out value="${boardInfo.brdtitle}"/></td>
    </tr>
    <tr>
        <td>content</td>
        <td><c:out value="${boardInfo.brdmemo}"/></td>
    </tr>
    </tbody>
</table>
<a href="#" onclick="history.back()">back</a>
<a href="/board00/boardDelete.kosc?brdno=<c:out value="${boardInfo.brdno}"/>">del</a>
<a href="/board00/boardForm.kosc?brdno=<c:out value="${boardInfo.brdno}"/>">update</a>
</body>
</html>
