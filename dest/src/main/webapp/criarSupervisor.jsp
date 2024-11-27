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

	<%
	Integer numeroPedido = (Integer) request.getAttribute("numeroPedido");
	%>

	<div>
		<form method="get" action="criar">
			<input type="number" placeholder="NUMERO PEDIDO" name="numero"
				value=<%= numeroPedido %> />
			<button type="submit">Buscar Pedido</button>
		</form>
	</div>
	<div>
		<%
		String aluno = (String) request.getAttribute("nomeAluno");
		String empresa = (String) request.getAttribute("nomeEmpresa");
		if (aluno != null) {
		%>
		<form method="get" action="criar">
			<diV>
				<label for="aluno">Aluno</label>
				<div>
					<input id="aluno" type="text" placeholder="NUMERO PEDIDO" name="aluno"
						value="<%=aluno%>" readonly />
				</div>
			</diV>
			<div>
				<label for="empresa">Empresa</label>
				<div>
					<input id="empresa" type="text" placeholder="NUMERO PEDIDO" name="empresa"
						value="<%=empresa%>" readonly />
				</div>
			</div>
			<button type="submit">Cadastrar</button>
		</form>
		<%
		}
		%>

	</div>
</body>
</html>