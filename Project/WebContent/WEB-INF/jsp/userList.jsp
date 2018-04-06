<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/origin/list.css">
</head>
<body>
<div class = "contain">

<!-- トップ -->
<div class = "top">

<span>ユーザ名 <c:out value = "${userName.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><a href = "login.html">ログアウト</a></small></span>

</div>

<!-- センター -->
<div class = "list-area">

<h3>ユーザ一覧</h3>

<p><a href = "userTouroku.html">新規登録</a></p>


<form class = "form-area">

  <div class="form-group row">
    <label for="inputLogin" class="col-sm-3 col-form-label"><nobr>ログインID</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputLogin" placeholder="Login">
    </div>
  </div>

  <div class="form-group row">
    <label for="inputPassword" class="col-sm-3 col-form-label"><nobr>Password</nobr></label>
    <div class="col-sm-9">
      <input type="password" class="form-control" id="inputPassword" placeholder="Password">
    </div>
  </div>

  <div class="form-group row">
    <label for="inputBirthday" class="col-sm-3 col-form-label"><nobr>生年月日</nobr></label>
    <div class="col-sm-9">
      <input type="text" class="form-control" id="inputBirthday" placeholder="生年月日">
    </div>
  </div>

  <div class ="login-button">
    <button type="button" class="btn btn-secondary">検索</button>
  </div>

</form>

</div>

<!-- ボトム -->
<div class = "bottom">

<table class="table table-bordered">
  <thead>
    <tr>
      <th scope="col">ログインID</th>
      <th scope="col">ユーザ名</th>
      <th scope="col">生年月日</th>
      <th scope="col">ボタン</th>
    </tr>
  </thead>
  <tbody>
  <!--
  リクエスト(コレクション)
  ユーザー情報一覧
  -->

<!--
  <c:if test= "${manager != null}" >

  <c:forEach var = "user" items = "${userList}">
    <tr>
      <td><c:out value = "${user.loginId}"/></td>
      <td><c:out value = "${user.name}"/></td>
      <td><c:out value = "${user.birthDate}"/></td>
      <td>
        <a href = "UserDetailServlet?loginid=${user.loginId}"><button type="button" class="btn btn-primary">詳細</button></a>
        <a href = "UserUpdateServlet?loginid=${user.loginId}"><button type="button" class="btn btn-success">更新</button></a>
        <a href = "UserDeleteServlet?loginid=${user.loginId}"><button type="button" class="btn btn-danger">削除</button></a>
      </td>
    </tr>
    </c:forEach>

    </c:if>
-->

<c:choose>

<c:when test="${manager != null}">
    <c:forEach var = "user" items = "${userList}">
    <tr>
      <td><c:out value = "${user.loginId}"/></td>
      <td><c:out value = "${user.name}"/></td>
      <td><c:out value = "${user.birthDate}"/></td>
      <td>
        <a href = "UserDetailServlet?loginid=${user.loginId}"><button type="button" class="btn btn-primary">詳細</button></a>
        <a href = "UserUpdateServlet?loginid=${user.loginId}"><button type="button" class="btn btn-success">更新</button></a>
        <a href = "UserDeleteServlet?loginid=${user.loginId}"><button type="button" class="btn btn-danger">削除</button></a>
      </td>
    </tr>
    </c:forEach>
</c:when>

<c:when test="${notmanager != null}">
    <c:forEach var = "user" items = "${userList}">
    <tr>
      <td><c:out value = "${user.loginId}"/></td>
      <td><c:out value = "${user.name}"/></td>
      <td><c:out value = "${user.birthDate}"/></td>
      <td>
        <a href = "UserDetailServlet?loginid=${user.loginId}"><button type="button" class="btn btn-primary">詳細</button></a>
        <c:if test= "${userName.loginId == user.loginId}" >
        <a href = "UserUpdateServlet?loginid=${user.loginId}"><button type="button" class="btn btn-success">更新</button></a>
        </c:if>
      </td>
    </tr>
    </c:forEach>
</c:when>

</c:choose>

  </tbody>
</table>

</div>

</div>
</body>
</html>