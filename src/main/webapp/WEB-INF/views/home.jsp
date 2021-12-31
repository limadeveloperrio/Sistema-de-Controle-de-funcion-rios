<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto Spring MVC</title>
        
        <!-- referencia das folhas de estilo css -->
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        
    </head>
    <body>
        
		<div class="container mt-4">
			
			<h2>Sistema de Controle de funcionários</h2>
			<p>Projeto criado com Spring MVC e JdbcTemplate</p>
			<hr/>
			
			<a href="/springMvc01/formularioFuncionario"
			   class="btn btn-light">
				Cadastrar Funcionário   
			</a>
			
			<a href="/springMvc01/listagemFuncionarios"
			   class="btn btn-light">
				Consultar Funcionários   
			</a>
			
		</div>

		<!-- referencia dos arquivos javascript -->
		<script src="resources/js/bootstrap.min.js"></script>

    </body>
</html>
