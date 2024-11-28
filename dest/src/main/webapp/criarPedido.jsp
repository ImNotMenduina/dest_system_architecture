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
		<h1>Criar Pedido</h1>
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
	String email = (String) session.getAttribute("email");
	if (email != null) {
	%>
	<h4>
		Conta :
		<%=email%></h4>
	<%
	} else {
	%>
	<h1>Login</h1>
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
	<%
	}
	%>

	<%
	Integer numeroPedido = (Integer) request.getAttribute("numeroPedido");
	%>

	<div>
		<form method="get" action="criar_pedido">
			<div>
				<label for="ira">IRA</label>
				<div>
					<input id="ira" type="number" placeholder="IRA" name="ira" />
				</div>
			</div>

			<div>
				<label for="ch_cumprida">Carga Horária Cumprida</label>
				<div>
					<input id="ch_cumprida" type="number"
						placeholder="Carga Horária Cumprida" name="ch_cumprida" />
				</div>
			</div>

			<div>
				<label for="endereco">Endereço</label>
				<div>
					<input id="endereco" type="text" placeholder="Endereço"
						name="endereco" />
				</div>
			</div>
			<button type="submit">Enviar</button>
		</form>
	</div>






























	<div>
		<%
		String aluno = (String) request.getAttribute("nomeAluno");
		String empresa = (String) request.getAttribute("nomeEmpresa");
		if (aluno != null) {
		%>
		<div>
			<diV>
				<label for="aluno">Aluno</label>
				<div>
					<input id="aluno" type="text" placeholder="NUMERO PEDIDO"
						name="aluno" value="<%=aluno%>" readonly />
				</div>
			</diV>
			<div>
				<label for="empresa">Empresa</label>
				<div>
					<input id="empresa" type="text" placeholder="NUMERO PEDIDO"
						name="empresa" value="<%=empresa%>" readonly />
				</div>
			</div>
		</div>

		<form method="post" action="criar">
			<div>
				<label for="nome">Nome do Supervisor</label>
				<div>
					<input id="nome" type="text" placeholder="Nome" name="supervisor" />
				</div>
			</div>

			<div>
				<label for="email">Email</label>
				<div>
					<input id="email" type="email" placeholder="Email" name="email" />
				</div>
			</div>

			<div>
				<label for="senha">Senha</label>
				<div>
					<input id="senha" type="password" placeholder="Senha" name="senha" />
				</div>
			</div>

			<div>
				<label for="telefone">Telefone</label>
				<div>
					<input id="telefone" type="tel" placeholder="Telefone"
						name="telefone" />
				</div>
			</div>

			<div>
				<label for="nomeEmpresa">Nome da Empresa</label>
				<div>
					<input id="nomeEmpresa" type="text" placeholder="Nome da Empresa"
						name="nomeEmpresa" value="<%=empresa%>" />
				</div>
			</div>

			<div>
				<label for="cnpj">CNPJ</label>
				<div>
					<input id="cnpj" type="text" placeholder="CNPJ" name="cnpj" />
				</div>
			</div>

			<div>
				<label for="numeroPedidoEstagio">Número do Pedido de Estágio</label>
				<div>
					<input id="numeroPedidoEstagio" type="number"
						placeholder="Número do Pedido de Estágio"
						name="numeroPedidoEstagio" value="<%=numeroPedido%>" />
				</div>
			</div>

			<div>
				<label for="funcao">Função</label>
				<div>
					<input id="funcao" type="text" placeholder="Função" name="funcao" />
				</div>
			</div>
			<div>
				<button type="submit">Cadastrar</button>
			</div>
		</form>
		<%
		}
		%>

	</div>
</body>
</html>