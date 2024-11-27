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
		<a href="./index.html">Home</a>
	</div>
	<div>
		<h1>Criar Supervisor</h1>
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
		<form method="get" action="criar">
			<input type="text" placeholder="NUMERO PEDIDO" name="numero" />
			<button type="submit">Buscar</button>
		</form>
	</div>
</body>
</html>