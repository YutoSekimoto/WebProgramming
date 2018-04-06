<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/login.css">
</head>
<body>
<div class = "contain">

<c:if test= "${eM != null}" >
	    <div class="alert alert-danger" role="alert">
		  ${eM}
		</div>
</c:if>

<div class = "login-area">

<h3>ログイン画面</h3>

<form class = "form-area" action = "UserLoginServlet" method = "post">

  <div class="form-group row" >
    <label for="inputLogin" class="col-sm-3 col-form-label"><nobr>ログインID</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputLogin" placeholder="Login" name = "loginID">
    </div>
  </div>

  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label">Password</label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name = "password">
    </div>
  </div>

  <div class ="login-button">
  <a href = "userList.html">
    <input type="submit" class="btn btn-secondary" value = "ログイン">
   </a>
  </div>

</form>

</div>

</div>
</body>
</html>