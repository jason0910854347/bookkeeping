<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      <form class="form-signin" method="post" action="${pageContext.request.contextPath}/loginAction">
        <h2 class="form-signin-heading">登入系統</h2>
        <p>
          <label for="username" class="sr-only">帳號</label>
          <input type="text" name="username1" class="form-control" placeholder="Username" required autofocus>
        </p>
        <p>
          <label for="password" class="sr-only">密碼</label>
          <input type="password" name="password1" class="form-control" placeholder="Password" required>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
      </form>
</body>
</html>