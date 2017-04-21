<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-04-21
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>list</title>
</head>
<body>
    <a href="/board00/boardForm.kosc">write</a>
    <table border="1" style="width:600px">
        <caption>board</caption>
        <colgroup>
            <col width="8%"/>
            <col width="*%"/>
            <col width="15%"/>
            <col width="15%"/>
        </colgroup>
        <thead>
            <tr>
                <th>no</th>
                <th>title</th>
                <th>writer</th>
                <th>date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="listview" items="${listview}" varStatus="status">
                <c:url var="link" value="/board00/boardRead.kosc">
                    <c:param name="brdno" value="${listview.brdno}"/>
                </c:url>

                <tr>
                    <td><c:out value="${listview.brdno}"/></td>
                    <td><a href="${link}"><c:out value="${listview.brdtitle}"/></a></td>
                    <td><c:out value="${listview.brdwriter}"/></td>
                    <td><c:out value="${listview.brddate}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
