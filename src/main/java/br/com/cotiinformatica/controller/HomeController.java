package br.com.cotiinformatica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//método para mapear o endereço de uma página
	//não é regra, mas o nomes do método pode ser igual ao nome da página
	@RequestMapping("/")
	public String home() {
		
		//o retorno do método deverá ser o nome da página
		//que ele irá acessar, sem a extensão .jsp
		return "home";
	}
}
