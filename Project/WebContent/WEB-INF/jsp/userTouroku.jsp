<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/touroku.css">
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

<div class = "touroku-area">

<h3>ユーザ新規登録</h3>

<div class = "touroku-area-contain">
<form class = "form-area" action = "UserTourokuServlet" method = "post">

  <div class="form-group row">
    <label for="inputLogin" class="col-sm-3 col-form-label"><nobr>ログインID</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputLogin" placeholder="Login" name = "loginid">
    </div>
  </div>

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
      <input type="text" class="form-control" id="inputUser" placeholder="ユーザ名" name = "name">
    </div>
  </div>

  <div class="form-group row">
    <label for="inputBirthday" class="col-sm-3 col-form-label"><nobr>生年月日</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputBirthday" placeholder="生年月日" name = "birthDate">
    </div>
  </div>

  <div class ="login-button">
    <input type="submit" class="btn btn-secondary" value = "登録">
  </div>

</form>

<a href = "javascript:history.back()">戻る</a>
</div>

</div>

</div>
</body>
</html>