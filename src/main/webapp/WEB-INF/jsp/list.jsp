<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-04-21
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>list</title>
    <script>
        function fn_formSubmit(){
            document.form1.submit();
        }
    </script>
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
        <col width="10%"/>
    </colgroup>
    <thead>
    <tr>
        <th>no</th>
        <th>title</th>
        <th>writer</th>
        <th>date</th>
        <th>hit</th>
        <th>attach</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="listview" items="${listview}" varStatus="status">
        <c:url var="link" value="/board00/boardRead.kosc">
            <c:param name="brdno" value="${listview.brdno}"/>
        </c:url>
        <tr>
            <td><c:out value="${pageVO.totRow-((pageVO.page-1)*pageVO.displayRowCount+status.index)}"/></td>
            <td style="max-width: 100px; border: 1px solid black; overflow: hidden; white-space: nowrap; text-overflow: ellipsis"><a href="${link}"><c:out value="${listview.brdtitle}"/></a>
            <c:if test="${listview.replycnt>0}">
                (<c:out value="${listview.replycnt}"/>)
            </c:if>
            </td>
            <td><c:out value="${listview.brdwriter}"/></td>
            <td><c:out value="${listview.brddate}"/></td>
            <td><c:out value="${listview.brdhit}"/></td>
            <td><c:out value="${listview.filecnt}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form id="form1" name="form1" method="post">
    <jsp:include page="/WEB-INF/jsp/common/paging.jsp"/>
    <div>
        <input type="checkbox" name="searchType" value="brdtitle" <c:if test="${fn:indexOf(searchVO.searchType, 'brdtitle') != -1}">checked="checked"</c:if>/>
        <label class="chkselect">title</label>
        <input type="checkbox" name="searchType" value="brdmemo" <c:if test="${fn:indexOf(searchVO.searchType, 'brdmemo') != -1}">checked="checked"</c:if>/>
        <label class="chkselect">content</label>
        <input type="text" name="searchKeyword" style="width: 150px;" maxlength="50" value="<c:out value="${searchVO.searchKeyword}"/>" onkeydown="if(event.keyCode == 13) { fn_formSubmit(); }"/>
        <input type="button" name="btn_search" class="btn_sch" value="search" onclick="fn_formSubmit()"/>
    </div>
</form>
</body>
</html>
