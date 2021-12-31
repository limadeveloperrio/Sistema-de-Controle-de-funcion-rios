package br.com.cotiinformatica.interfaces;

import java.util.List;

import br.com.cotiinformatica.entities.Funcionario;

public interface IFuncionarioRepository {

	void create(Funcionario funcionario) throws Exception;
	void update(Funcionario funcionario) throws Exception;
	void delete(Funcionario funcionario) throws Exception;
	
	List<Funcionario> findAll() throws Exception;
	
	Funcionario findById(Integer id) throws Exception;
	Funcionario findByCpf(String cpf) throws Exception;
}
