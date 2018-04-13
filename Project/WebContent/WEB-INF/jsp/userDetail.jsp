<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/detail.css">
</head>
<body>
<div class = "contain">

<div class = "top">

<span>ユーザ名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><a href = "UserLogoutServlet">ログアウト</a></small></span>

</div>

<div class = "detail-area">

<h3>ユーザ情報詳細参照</h3>

<div class = "detail-area-contain">
<table>
    <tr>
      <th scope="row">ログインID</th>
      <td><c:out value = "${user.loginId}"/></td>
    </tr>
    <tr>
      <th scope="row">ユーザ名</th>
      <td><c:out value = "${user.name}"/></td>
    </tr>
    <tr>
      <th scope="row">生年月日</th>
      <td><c:out value = "${user.birthDate}"/></td>
    </tr>
    <tr>
      <th scope="row">登録日時</th>
      <td><c:out value = "${user.createDate}"/></td>
    </tr>
    <tr>
      <th scope="row">更新日時</th>
      <td><c:out value = "${user.updateDate}"/></td>
    </tr>
</table>

<a href = "javascript:history.back()">戻る</a>
</div>

</div>

</div>
</body>
</html>