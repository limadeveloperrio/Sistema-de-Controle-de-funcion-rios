package br.com.cotiinformatica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.enums.SituacaoFuncionario;
import br.com.cotiinformatica.interfaces.IFuncionarioRepository;

@Controller // define como controlador do Spring
public class FuncionarioController {
	
	@Autowired //Inicializado pelo Spring (Inje��o de depend�ncia)
	private IFuncionarioRepository funcionarioRepository;

	// m�todo para mapear a p�gina de cadastro de funcionario
	@RequestMapping("/formularioFuncionario")
	public ModelAndView formularioFuncionario() {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/formulario-funcionario");
		modelAndView.addObject("funcionario", new Funcionario());
		
		return modelAndView;
	}
	
	//m�todo para receber o SUBMIT POST do formul�rio de cadastro de funcion�rio..
	@RequestMapping(value = "cadastrarFuncionario", method = RequestMethod.POST)
	public ModelAndView cadastrarFuncionario(Funcionario funcionario, ModelMap map) {
		
		try {
			
			//verificando se o CPF informado j� encontra-se cadastrado no banco de dados..
			if(funcionarioRepository.findByCpf(funcionario.getCpf()) != null)
				throw new Exception("O CPF informado j� encontra-se cadastrado, tente outro."); //redireciona para o catch..
			
			//gravando o funcion�rio no banco de dados..
			funcionarioRepository.create(funcionario);
			
			map.addAttribute("mensagem_sucesso", "Funcion�rio cadastrado com sucesso.");
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/formulario-funcionario");
		modelAndView.addObject("funcionario", new Funcionario());
		
		return modelAndView;
	}

	// m�todo para mapear a p�gina de consulta de funcionarios
	@RequestMapping("/listagemFuncionarios")
	public ModelAndView listagemFuncionarios(ModelMap map) {
				
		ModelAndView modelAndView = new ModelAndView("funcionarios/listagem-funcionarios");
		
		try {
			//executando o m�todo findAll (consultar todos)
			//e enviar a lista de funcion�rios para a p�gina (view)
			modelAndView.addObject("listafuncionarios", funcionarioRepository.findAll());
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	//m�todo para realizar a exclus�o do funcion�rio
	@RequestMapping("/excluirFuncionario")
	public ModelAndView excluirFuncionario(Integer id, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/listagem-funcionarios");
		
		try {
			
			//criando um objeto funcion�rio..
			Funcionario funcionario = new Funcionario();
			funcionario.setIdFuncionario(id); //setando o id..
			
			//excluindo o funcion�rio
			funcionarioRepository.delete(funcionario);
			
			//executando a consulta de funcion�rios
			modelAndView.addObject("listafuncionarios", funcionarioRepository.findAll());
			
			map.addAttribute("mensagem_sucesso", "Funcion�rio exclu�do com sucesso.");
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}

	// m�todo para mapear a p�gina de edi��o de funcionario
	@RequestMapping("/edicaoFuncionario")
	public ModelAndView edicaoFuncionario(Integer id, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/edicao-funcionario");
		
		try {
			modelAndView.addObject("funcionario", funcionarioRepository.findById(id));
			
			//enviando as op��es do ENUM para o formul�rio de edi��o
			modelAndView.addObject("situacoes", SituacaoFuncionario.values());
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	//m�todo para realizar a atualiza��o dos dados do funcion�rio..
	@RequestMapping(value = "atualizarFuncionario", method = RequestMethod.POST)
	public ModelAndView atualizarFuncionario(Funcionario funcionario, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/listagem-funcionarios");
		
		try {
			
			//atualizar o funcion�rio
			funcionarioRepository.update(funcionario);
			
			//executando a consulta de funcion�rios..
			modelAndView.addObject("listafuncionarios", funcionarioRepository.findAll());
			
			map.addAttribute("mensagem_sucesso", "Funcion�rio atualizado com sucesso.");
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}
















