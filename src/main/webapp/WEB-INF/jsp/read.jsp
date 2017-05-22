<%--
  Created by IntelliJ IDEA.
  User: kiost
  Date: 2017-04-21
  Time: 오후 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <td><c:out value="${boardInfo.brdmemo}" escapeXml="false"/></td>
    </tr>
    <tr>
        <td>attach</td>
        <td>
            <c:forEach var="listview" items="${listview}" varStatus="status">
                <a href="/fileDownload?fileName=<c:out value="${listview.fileName}"/>&downName=<c:out value="${listview.realName}"/>"><c:out
                        value="${listview.fileName}"/></a><c:out value="${listview.size2String()}"/><br/>
            </c:forEach>
        </td>
    </tr>
    </tbody>
</table>
<a href="#" onclick="history.back()">back</a>
<a href="/board00/boardDelete.kosc?brdno=<c:out value="${boardInfo.brdno}"/>">del</a>
<a href="/board00/boardForm.kosc?brdno=<c:out value="${boardInfo.brdno}"/>">update</a>
<p>&nbsp;</p>
<div style="border: 1px solid; width: 600px; padding: 5px;">
    <form name="form1" action="replySave" method="post">
        <input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>">
        작성자:<input type="text" name="rewriter" size="20" maxlength="20" required/><br/>
        <textarea name="rememo" rows="3" cols="60" maxlength="500" placeholder="insert reply" required></textarea>
        <input type="submit" value="save">
    </form>
</div>

<c:forEach var="replylist" items="${replylist}" varStatus="status">
    <div style="border: 1px solid gray; width: 600px; padding: 5px; margin-top: 5px; margin-left: <c:out
            value="${20*replylist.redepth}"/>px; display: inline-block">
        <c:out value="${replylist.rewriter}"/><c:out value="${replylist.redate}"/>
        <a href="#" onclick="fn_replyDelete('<c:out value="${replylist.reno}"/>')">del</a>
        <a href="#" onclick="fn_replyUpdate('<c:out value="${replylist.reno}"/>')">mod</a>
        <a href="#" onclick="fn_replyReply('<c:out value="${replylist.reno}"/>')">reply</a>
        <br/>
        <div id="reply<c:out value="${replylist.reno}"/>"><c:out value="${replylist.rememo}"/></div>
    </div>
    <br/>
</c:forEach>

<div id="replyDiv" style="width: 99%; display: none;">
    <form name="form2" action="replySave" method="post">
        <input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>">
        <input type="hidden" name="reno" >
        <textarea name="rememo" rows="3" cols="60" maxlength="500" required></textarea>
        <input type="submit" value="save">
        <a href="#" onclick="fn_replyUpdateCancel()">cancel</a>
    </form>
</div>

<div id="replyDialog" style="width: 99%; display:none;">
    <form name="form3" action="replySave" method="post">
        <input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>">
        <input type="hidden" name="reno">
        <input type="hidden" name="reparent">
        writer:<input type="text" name="rewriter" size="20" maxlength="20"><br/>
        <textarea name="rememo" rows="3" cols="60" maxlength="500"></textarea>
        <input type="submit" value="save">
        <a href="#" onclick="fn_replyReplyCancel()">cancel</a>
    </form>
</div>
<script>
    function fn_replyDelete(reno) {
        if (!confirm("do you want to delete this reply?")) {
            return;
        }
        var form = document.form2;
        form.action = "replyDelete";
        form.reno.value = reno;
        form.submit();
    }

    var updateReno = updateRememo = null;
    function fn_replyUpdate(reno) {
        hideDiv("replyDialog");

        var form = document.form2;
        var reply = document.getElementById("reply" + reno);
        var replyDiv = document.getElementById("replyDiv");
        replyDiv.style.display = "";

        if (updateReno) {
            document.body.appendChild(replyDiv);
            var oldReno = document.getElementById("reply" + updateReno);
            oldReno.innerText = updateRememo;
        }

        form.reno.value = reno;
        form.rememo.value = reply.innerText;
        reply.innerText = "";
        reply.appendChild(replyDiv);
        updateReno = reno;
        updateRememo = form.rememo.value;
        form.rememo.focus();
    }

    function fn_replyUpdateCancel() {
        hideDiv("replyDiv");

        var oldReno = document.getElementById("reply" + updateReno);
        oldReno.innerText = updateRememo;
        updateReno = updateRememo = null;
    }

    function hideDiv(id) {
        var div = document.getElementById(id);
        div.style.display = "none";
        document.body.appendChild(div);
    }

    function fn_replyReplyCancel() {
        hideDiv("replyDialog");
    }

    function fn_replyReply(reno) {
        var form = document.form3;
        var reply = document.getElementById("reply" + reno);
        var replyDia = document.getElementById("replyDialog");
        replyDia.style.display = "";

        if(updateReno){
            fn_replyUpdateCancel();
        }

        form.rememo.value="";
        form.reparent.value=reno;
        reply.appendChild(replyDia);
        form.rewriter.focus();
    }
</script>
</body>
</html>
