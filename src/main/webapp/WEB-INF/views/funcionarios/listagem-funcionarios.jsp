<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto Spring MVC</title>
        
        <!-- referencia das folhas de estilo css -->
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        
        <!-- referencia no CSS do jquery datatables -->
        <link rel="stylesheet" href="resources/css/jquery.dataTables.min.css"/>
        
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
			
			<h2>Consulta de Funcionários</h2>
			<a href="/springMvc01" class="btn btn-light"> Página inicial </a>

			<hr />
						
			<table id="tabelaFuncionarios" class="table table-bordered table-striped table-hover table-light">
				<thead>
					<tr>
						<th>Nome do Funcionário</th>
						<th>CPF</th>
						<th>Matrícula</th>
						<th>Salário</th>
						<th>Situação</th>
						<th width="160">Operações</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="f" items="${listafuncionarios}">
					
						<tr>
							<td>${f.nome}</td>
							<td>${f.cpf}</td>
							<td>${f.matricula}</td>
							<td>${f.salario}</td>
							<td>${f.situacao}</td>
							<td>
								<a href="edicaoFuncionario?id=${f.idFuncionario}" class="btn btn-primary btn-sm">
								Alterar
								</a>
								<a href="excluirFuncionario?id=${f.idFuncionario}" class="btn btn-danger btn-sm"
							   	   onclick="return window.confirm('Deseja realmente excluir este funcionário?');">
									Excluir
								</a>
							</td>
						</tr>
					
					</c:forEach>				
					
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">Quantidade de registros: ${listafuncionarios.size()}</td>
					</tr>
				</tfoot>
			</table>
						
		</div>

		<!-- referencia dos arquivos javascript -->
		<script src="resources/js/bootstrap.min.js"></script>
		
		<!-- referencia do jquery -->
		<script src="resources/js/jquery-3.5.1.min.js"></script>
		
		<!-- referencia do jquery datatables -->
		<script src="resources/js/jquery.dataTables.min.js"></script>
		
		<script>
		
			$(document).ready(function(){
				
				//aplicando o plugin dataTable na tabela
				$("#tabelaFuncionarios").DataTable({
					
					language: {
			            url: 'resources/js/Portuguese-Brasil.json'
			        }
					
				});
				
			})
		
		</script>

    </body>
</html>







