<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="./index.jsp">Home</a>
	</div>
	<div>
		<h1>Login</h1>
	</div>

	<%
		// Verificando se o atributo "mensagem" existe
		String mensagem = (String) request.getAttribute("mensagem");
		if (mensagem != null) {
	%>
			<h4><%=mensagem%></h4>
	<%
		}
	%>
	<div>
		<form method=post action="login_user">
			<div>
				<input type="text" placeholder="EMAIL" name="email" />
			</div>
			<div>
				<input type="password" placeholder="SENHA" name="password" />
			</div>
			<button type="submit">Sign in</button>
		</form>
	</div>
</body>
</html>