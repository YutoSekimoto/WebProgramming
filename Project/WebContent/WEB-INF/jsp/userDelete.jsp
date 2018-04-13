<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/delete.css">
</head>
<body>
<div class  = "contain">

<div class = "top">

<span>ユーザ名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><a href = "UserLogoutServlet">ログアウト</a></small></span>

</div>

<div class = "delete-area">

<h3>ユーザ削除確認</h3>

<div class = "delete-area-contain">
<p>ログインID：<c:out value = "${loginId}"/><br>を本当に削除してよろしいでしょうか。</p>

<div class = "uDelete">
<form action = "UserDeleteServlet" method = "post">

<input type = "hidden" name = "loginid" value = <c:out value = "${loginId}"/>>
<input type = "submit" value = "削除する">

</form>
</div>

</div>

</div>

<a href = "javascript:history.back()">戻る</a>

</div>
</body>
</html>