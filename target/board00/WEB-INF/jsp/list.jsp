<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-04-21
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <col width="10%"/>
    </colgroup>
    <thead>
    <tr>
        <th>no</th>
        <th>title</th>
        <th>writer</th>
        <th>date</th>
        <th>hit</th>
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
            <td><c:out value="${listview.brdhit}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${pageVO.totPage > 1}">
    <div class="paging">
        <c:forEach var="i" begin="${pageVO.pageStart}" end="${pageVO.pageEnd}" step="1">
            <c:url var="pageLink" value="/board00/home.kosc">
                <c:param name="page" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i eq pageVO.page}">
                    <c:out value="${i}"/>
                </c:when>
                <c:otherwise>
                    <a href="${pageLink}"><c:out value="${i}"/></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
