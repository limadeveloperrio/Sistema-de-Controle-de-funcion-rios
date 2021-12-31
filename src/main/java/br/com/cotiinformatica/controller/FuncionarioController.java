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
	
	@Autowired //Inicializado pelo Spring (Injeção de dependência)
	private IFuncionarioRepository funcionarioRepository;

	// método para mapear a página de cadastro de funcionario
	@RequestMapping("/formularioFuncionario")
	public ModelAndView formularioFuncionario() {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/formulario-funcionario");
		modelAndView.addObject("funcionario", new Funcionario());
		
		return modelAndView;
	}
	
	//método para receber o SUBMIT POST do formulário de cadastro de funcionário..
	@RequestMapping(value = "cadastrarFuncionario", method = RequestMethod.POST)
	public ModelAndView cadastrarFuncionario(Funcionario funcionario, ModelMap map) {
		
		try {
			
			//verificando se o CPF informado já encontra-se cadastrado no banco de dados..
			if(funcionarioRepository.findByCpf(funcionario.getCpf()) != null)
				throw new Exception("O CPF informado já encontra-se cadastrado, tente outro."); //redireciona para o catch..
			
			//gravando o funcionário no banco de dados..
			funcionarioRepository.create(funcionario);
			
			map.addAttribute("mensagem_sucesso", "Funcionário cadastrado com sucesso.");
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/formulario-funcionario");
		modelAndView.addObject("funcionario", new Funcionario());
		
		return modelAndView;
	}

	// método para mapear a página de consulta de funcionarios
	@RequestMapping("/listagemFuncionarios")
	public ModelAndView listagemFuncionarios(ModelMap map) {
				
		ModelAndView modelAndView = new ModelAndView("funcionarios/listagem-funcionarios");
		
		try {
			//executando o método findAll (consultar todos)
			//e enviar a lista de funcionários para a página (view)
			modelAndView.addObject("listafuncionarios", funcionarioRepository.findAll());
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	//método para realizar a exclusão do funcionário
	@RequestMapping("/excluirFuncionario")
	public ModelAndView excluirFuncionario(Integer id, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/listagem-funcionarios");
		
		try {
			
			//criando um objeto funcionário..
			Funcionario funcionario = new Funcionario();
			funcionario.setIdFuncionario(id); //setando o id..
			
			//excluindo o funcionário
			funcionarioRepository.delete(funcionario);
			
			//executando a consulta de funcionários
			modelAndView.addObject("listafuncionarios", funcionarioRepository.findAll());
			
			map.addAttribute("mensagem_sucesso", "Funcionário excluído com sucesso.");
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}

	// método para mapear a página de edição de funcionario
	@RequestMapping("/edicaoFuncionario")
	public ModelAndView edicaoFuncionario(Integer id, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/edicao-funcionario");
		
		try {
			modelAndView.addObject("funcionario", funcionarioRepository.findById(id));
			
			//enviando as opções do ENUM para o formulário de edição
			modelAndView.addObject("situacoes", SituacaoFuncionario.values());
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	//método para realizar a atualização dos dados do funcionário..
	@RequestMapping(value = "atualizarFuncionario", method = RequestMethod.POST)
	public ModelAndView atualizarFuncionario(Funcionario funcionario, ModelMap map) {
		
		ModelAndView modelAndView = new ModelAndView("funcionarios/listagem-funcionarios");
		
		try {
			
			//atualizar o funcionário
			funcionarioRepository.update(funcionario);
			
			//executando a consulta de funcionários..
			modelAndView.addObject("listafuncionarios", funcionarioRepository.findAll());
			
			map.addAttribute("mensagem_sucesso", "Funcionário atualizado com sucesso.");
		}
		catch(Exception e) {
			map.addAttribute("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}
















