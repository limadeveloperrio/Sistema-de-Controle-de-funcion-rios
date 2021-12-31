package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//m�todo para mapear o endere�o de uma p�gina
	//n�o � regra, mas o nomes do m�todo pode ser igual ao nome da p�gina
	@RequestMapping("/")
	public String home() {
		
		//o retorno do m�todo dever� ser o nome da p�gina
		//que ele ir� acessar, sem a extens�o .jsp
		return "home";
	}
}
