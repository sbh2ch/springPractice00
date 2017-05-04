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
    <title>form</title>
</head>
<body>
<form name="form1" action="/board00/boardSave.kosc" method="post" enctype="multipart/form-data">
    <table border="1" style="width: 600px">
        <caption>board</caption>
        <colgroup>
            <col width="15%"/>
            <col width="*%"/>
        </colgroup>
        <tbody>
        <tr>
            <td>writer</td>
            <td><input type="text" name="brdwriter" size="20" maxlength="20"
                       value="<c:out value="${boardinfo.brdwriter}"/>" required/></td>
        </tr>
        <tr>
            <td>title</td>
            <td><input type="text" name="brdtitle" size="20" maxlength="20"
                       value="<c:out value="${boardinfo.brdtitle}"/>" required/></td>
        </tr>
        <tr>
            <td>content</td>
            <td><input type="text" name="brdmemo" size="20" maxlength="20"
                       value="<c:out value="${boardinfo.brdmemo}"/>" required/></td>
        </tr>
        <tr>
            <td>attach</td>
            <td>
                <c:forEach var="listview" items="${listview}" varStatus="status">
                    <input type="checkbox" name="fileNo" value="<c:out value="${listview.fileNo}"/>"/>
                    <a href="fileDownload?fileName=<c:out value="${listview.fileName}"/>&downName=<c:out value="${listview.realName}"/>"><c:out value="${listview.fileName}"/></a>
                    <c:out value="${listview.size2String()}"/><br/>
                </c:forEach>
                <input type="file" name="uploadfile" multiple/>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="hidden" name="brdno" value="<c:out value="${boardinfo.brdno}"/>">
    <a href="#" onclick="form1.submit()">save</a>
</form>
</body>
</html>