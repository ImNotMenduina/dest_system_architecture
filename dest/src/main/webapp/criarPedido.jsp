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
		<h1>Criar Pedido</h1>
	</div>

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
		<%=emailUsuario%>
	</h4>
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
	String pedidost = (String) request.getAttribute("pedido");
	Integer ira = (Integer) request.getAttribute("ira");
	Integer chCumprida = (Integer) request.getAttribute("chCumprida");
	%>

	<%
	if (pedidost == "pedido") {
	%>
	<div>
		<form method="post" action="criar_pedido">
			<div>
				<label for="nome">Nome</label>
				<div>
					<input id="nome" type="text" placeholder="Nome" name="nome" />
				</div>
			</div>

			<div>
				<label for="matricula">Matrícula</label>
				<div>
					<input id="matricula" type="text" placeholder="Matrícula"
						name="matricula" />
				</div>
			</div>

			<div>
				<label for="ira">IRA</label>
				<div>
					<input id="ira" type="number" placeholder="IRA" name="ira" value="<%= ira %>" readonly />
				</div>
			</div>

			<div>
				<label for="cargaHora">Carga Horária</label>
				<div>
					<input id="cargaHora" type="number" placeholder="Carga Horária"
						name="cargaHora" value="<%= chCumprida %>" readonly/>
				</div>
			</div>

			<div>
				<label for="endereco">Endereço</label>
				<div>
					<input id="endereco" type="text" placeholder="Endereço"
						name="endereco" />
				</div>
			</div>

			<div>
				<label for="infoPrimeiro">Informações do Primeiro Semestre</label>
				<div>
					<input id="infoPrimeiro" type="text"
						placeholder="Informações do Primeiro Semestre" name="infoPrimeiro" />
				</div>
			</div>

			<div>
				<label for="nomeEmpresa">Nome da Empresa</label>
				<div>
					<input id="nomeEmpresa" type="text" placeholder="Nome da Empresa"
						name="nomeEmpresa" />
				</div>
			</div>

			<div>
				<label for="endEmpresa">Endereço da Empresa</label>
				<div>
					<input id="endEmpresa" type="text"
						placeholder="Endereço da Empresa" name="endEmpresa" />
				</div>
			</div>

			<div>
				<label for="modalidade">Modalidade</label>
				<div>
					<input id="modalidade" type="text" placeholder="Modalidade"
						name="modalidade" />
				</div>
			</div>

			<div>
				<label for="cargaHoraSem">Carga Horária Semanal</label>
				<div>
					<input id="cargaHoraSem" type="number"
						placeholder="Carga Horária Semanal" name="cargaHoraSem" />
				</div>
			</div>

			<div>
				<label for="valorBolsa">Valor da Bolsa</label>
				<div>
					<input id="valorBolsa" type="number" placeholder="Valor da Bolsa"
						name="valorBolsa" />
				</div>
			</div>

			<div>
				<label for="resumo">Resumo</label>
				<div>
					<input id="resumo" type="text" placeholder="Resumo" name="resumo" />
				</div>
			</div>
			<div>
				<button type="submit">Cadastrar Pedido</button>
			</div>
		</form>
	</div>
	<%
	}
	%>
</body>
</html>