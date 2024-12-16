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
		
	<%
		// Verificando se o atributo "mensagem" existe
		String emailUsuario = (String) session.getAttribute("email");
		String mensagem = (String) request.getAttribute("mensagem");
		if (mensagem != null) {
	%>
			<h4><%=mensagem%></h4>
	<%
		}
	%>

	<%
		if (emailUsuario != null) {
	%>
			<h4>
				Conta :
				<%= emailUsuario %>
			</h4>
	<%
		} 
	%>
	</div>
	<div>
		<h1>Dest - Departamento de Estágio</h1>
	</div>
	<div>
		<ul style="list-style-type: circle">
			<li><a href="./criarSupervisor.jsp">Criar Supervisor</a></li>
			<li><a href="./criarPedido.jsp">Criar Pedido de Intenção</a></li>
			<li><a href="./login.jsp">Login (teste exception Usuario Logado)</a></li>
		</ul>
	</div>
</body>
</html>