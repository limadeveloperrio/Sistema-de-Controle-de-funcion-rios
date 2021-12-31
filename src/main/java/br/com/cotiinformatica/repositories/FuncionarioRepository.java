package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Funcionario;
import br.com.cotiinformatica.enums.SituacaoFuncionario;
import br.com.cotiinformatica.interfaces.IFuncionarioRepository;

public class FuncionarioRepository implements IFuncionarioRepository {

	// atributo
	private JdbcTemplate jdbcTemplate;

	// construtor
	public FuncionarioRepository(DataSource dataSource) {
		// inicializando o Spring JDBC!
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Funcionario funcionario) throws Exception {

		String query = "insert into funcionario(nome, cpf, matricula, salario, situacao) values(?,?,?,?,?)";

		Object[] params = { funcionario.getNome(), funcionario.getCpf(), funcionario.getMatricula(),
				funcionario.getSalario(), funcionario.getSituacao().toString() };

		jdbcTemplate.update(query, params); // executando..
	}

	@Override
	public void update(Funcionario funcionario) throws Exception {

		String query = "update funcionario set nome = ?, cpf = ?, matricula = ?, salario = ?, situacao = ? where idfuncionario = ?";

		Object[] params = { funcionario.getNome(), funcionario.getCpf(), funcionario.getMatricula(),
				funcionario.getSalario(), funcionario.getSituacao().toString(), funcionario.getIdFuncionario() };

		jdbcTemplate.update(query, params);
	}

	@Override
	public void delete(Funcionario funcionario) throws Exception {

		String query = "delete from funcionario where idfuncionario = ?";

		Object[] params = { funcionario.getIdFuncionario() };

		jdbcTemplate.update(query, params);

	}

	@Override
	public List<Funcionario> findAll() throws Exception {

		String query = "select * from funcionario";

		List<Funcionario> lista = jdbcTemplate.query(query, new RowMapper<Funcionario>() {

			@Override
			public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getFuncionario(rs);
			}
		});

		return lista;
	}

	@Override
	public Funcionario findById(Integer id) throws Exception {

		String query = "select * from funcionario where idfuncionario = ?";
		Object[] params = { id };

		List<Funcionario> lista = jdbcTemplate.query(query, params, new RowMapper<Funcionario>() {

			@Override
			public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getFuncionario(rs);
			}
		});
		
		if(lista.size() == 1) //verificando se o funcionário foi encontrado
			return lista.get(0); //retornando o funcionario contido na lista
		else
			return null; //retornando vazio
	}

	@Override
	public Funcionario findByCpf(String cpf) throws Exception {

		String query = "select * from funcionario where cpf = ?";
		Object[] params = { cpf };

		List<Funcionario> lista = jdbcTemplate.query(query, params, new RowMapper<Funcionario>() {

			@Override
			public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getFuncionario(rs);
			}
		});
		
		if(lista.size() == 1)
			return lista.get(0);
		else
			return null;
	}

	// método privado para fazer a leitura dos dados da consulta de funcionario
	private Funcionario getFuncionario(ResultSet rs) throws SQLException {

		Funcionario funcionario = new Funcionario();

		funcionario.setIdFuncionario(rs.getInt("idfuncionario"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setCpf(rs.getString("cpf"));
		funcionario.setMatricula(rs.getString("matricula"));
		funcionario.setSalario(rs.getDouble("salario"));
		funcionario.setSituacao(SituacaoFuncionario.valueOf(rs.getString("situacao")));

		return funcionario;
	}

}
