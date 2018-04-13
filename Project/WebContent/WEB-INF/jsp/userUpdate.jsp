<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/update.css">
</head>
<body>
<div class = "contain">

<div class = "top">

<span>ユーザ名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><a href = "UserLogoutServlet">ログアウト</a></small></span>

</div>

<c:if test= "${eM != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${eM}
		</div>
</c:if>

<div class = "update-area">

<h3>ユーザ情報更新</h3>

<div class = "login-id">

<h6>ログインID<span><c:out value = "${user.loginId}"/></span></h6>

</div>

<div class = "update-area-contain">
<form class = "form-area" action = "UserUpdateServlet" method = "post">

  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label"><nobr>Password</nobr></label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name = "password">
    </div>
  </div>

  <div class="form-group row">
    <label for="inputPasswordConfirm" class="col-sm-3 col-form-label"><nobr>パスワード(確認)</nobr></label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="inputPasswordConfirm" placeholder="パスワード(確認)" name = "password2">
    </div>
  </div>

  <div class="form-group row">
    <label for="inputUser" class="col-sm-3 col-form-label"><nobr>ユーザ名</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputUser" placeholder="ユーザ名" name = "userName" value = <c:out value = "${user.name}"/>>
    </div>
  </div>

  <div class="form-group row">
    <label for="inputBirthday" class="col-sm-3 col-form-label"><nobr>生年月日</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputBirthday" placeholder="生年月日" name = "birthDate" value = <c:out value = "${user.birthDate}"/>>
    </div>
  </div>

  <input type ="hidden" name = "loginId" value = <c:out value = "${user.loginId}"/>>

  <div class ="login-button">
    <input type="submit" class="btn btn-secondary" value = "更新">
  </div>

</form>

<a href = "javascript:history.back()">戻る</a>
</div>

</div>

</div>
</body>
</html>