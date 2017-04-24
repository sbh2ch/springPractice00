<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-04-24
  Time: 오후 5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${searchVO.totPage > 1}">
    <div class="paging">
        <c:if test="${searchVO.page > 1}">
            <a href="javascript:fnSubmitForm(1);">[first]</a>
            <a href="javascript:fnSubmitForm(${searchVO.page-1});">[before]</a>
        </c:if>
        <c:forEach var="i" begin="${searchVO.pageStart}" end="${searchVO.pageEnd}" step="1">
            <c:url var="pageLink" value="/board00/home.kosc">
                <c:param name="page" value="${i}"/>
            </c:url>
            <c:choose>
                <c:when test="${i eq searchVO.page}">
                    <c:out value="${i}"/>
                </c:when>
                <c:otherwise>
                    <a href="javascript:fnSubmitForm(${i})"><c:out value="${i}"/></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${searchVO.totPage > searchVO.page}">
            <a href="javascript:fnSubmitForm(${searchVO.page+1})">[next]</a>
            <a href="javascript:fnSubmitForm(${searchVO.totPage})">[last]</a>
        </c:if>
    </div>
    <br/>
    <input type="hidden" name="page" id="page" value=""/>

    <script>
        function fnSubmitForm(page){
            document.form1.page.value = page;
            document.form1.submit();
        }
    </script>
</c:if>