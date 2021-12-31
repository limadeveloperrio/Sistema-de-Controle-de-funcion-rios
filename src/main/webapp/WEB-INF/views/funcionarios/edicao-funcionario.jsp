<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Projeto Spring MVC</title>

<!-- referencia das folhas de estilo css -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css" />

<!-- formata��o para o jquery validate -->
<style>

/* formata��o para as mensagens de erro */
label.error {
	color: #d9534f;
}

/*formata��o para os campos com erro*/
input.error {
	border: 1px solid #d9534f;
}

select.error {
	border: 1px solid #d9534f;
}
</style>

</head>
<body>
	
	<c:if test="${not empty mensagem_sucesso}">
	
		<div class="alert alert-success alert-dismissible fade show" role="alert">
  			<strong>Sucesso!</strong> ${mensagem_sucesso}
  			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	
	</c:if>
		
	<c:if test="${not empty mensagem_erro}">
	
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
  			<strong>Erro!</strong> ${mensagem_erro}
  			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	
	</c:if>

	<div class="container mt-4">

		<h2>Atualiza��o de Funcion�rio</h2>
		<a href="/springMvc01/listagemFuncionarios" class="btn btn-light"> Voltar para a consulta </a>

		<hr />

		<div class="row">
			<div class="col-md-4">

				<form id="formCadastro" method="post" action="atualizarFuncionario">

					<!-- campo oculto para armazenar o id do funcion�rio -->
					<form:input path="funcionario.idFuncionario" type="hidden"/>

					<div class="mb-3">
						<label>Nome do Funcion�rio:</label>
						<form:input path="funcionario.nome" type="text" id="nome"
							name="nome" class="form-control" placeholder="Ex: Jo�o da Silva"
							autocomplete="off" />
					</div>

					<div class="mb-3">
						<label>CPF:</label>
						<form:input path="funcionario.cpf" type="text" id="cpf" name="cpf"
							class="form-control" placeholder="Ex: 999.999.999-99"
							autocomplete="off" />
					</div>

					<div class="mb-3">
						<label>Matr�cula:</label>
						<form:input path="funcionario.matricula" type="text"
							id="matricula" name="matricula" class="form-control"
							placeholder="Ex: 9999-999" autocomplete="off" />
					</div>

					<div class="mb-3">
						<label>Sal�rio:</label>
						<form:input path="funcionario.salario" type="text" id="salario"
							name="salario" class="form-control" placeholder="Ex: 9999"
							autocomplete="off" />
					</div>

					<div class="mb-3">
						<label>Situa��o do Funcion�rio:</label> 
						<form:select path="funcionario.situacao" id="situacao"
							name="situacao" class="form-select">
							<form:options items="${situacoes}"/>
						</form:select>
					</div>

					<div>
						<button type="submit" class="btn btn-primary">Atualizar
							Funcion�rio</button>
					</div>

				</form>

			</div>
		</div>

	</div>

	<!-- referencia dos arquivos javascript -->
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- referencia ao jquery -->
	<script src="resources/js/jquery-3.5.1.min.js"></script>

	<!-- referencia do jquery validate -->
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/messages_pt_BR.min.js"></script>

	<!-- referencia do jquery maskedinput -->
	<script src="resources/js/jquery.maskedinput.min.js"></script>

	<!-- bloco de c�digo javascript -->
	<script>
		//criando um evento que ser� executado quando
		//a p�gina for carregada (abrir)..
		$(document).ready(function() {

			//aplicando m�scara aos campos do formul�rio..
			$("#cpf").mask("999.999.999-99");
			$("#matricula").mask("9999-999");

			//valida��o do formul�rio utilizando jquery validation
			$("#formCadastro").validate({

				//regras de valida��o..
				rules : {
					'nome' : {
						required : true, //campo obrigat�rio
						minlength : 6, //minimo de caracteres
						maxlength : 150
					//m�ximo de caracteres
					},
					'cpf' : {
						required : true, //campo obrigat�rio
						minlength : 14, //minimo de caracteres
						maxlength : 14
					//m�ximo de caracteres
					},
					'matricula' : {
						required : true, //campo obrigat�rio
						minlength : 8, //minimo de caracteres
						maxlength : 8
					//m�ximo de caracteres
					},
					'salario' : {
						required : true, //campo obrigat�rio
						number : true
					//tipo num�rico
					},
					'situacao' : {
						required : true
					//campo obrigat�rio
					}
				}

			});

		})
	</script>

</body>
</html>