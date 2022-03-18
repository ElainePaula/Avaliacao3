package br.com.api.states.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.api.states.modelo.Estados;
import br.com.api.states.modelo.StatusRegiao;
import br.com.api.states.repository.EstadosRepository;

public class AtualizacaoForm {

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	private StatusRegiao regiao;
	@NotNull
	private int populacao;
	@NotNull
	private String capital;
	@NotNull
	private float area;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusRegiao getRegiao() {
		return regiao;
	}

	public void setRegiao(StatusRegiao regiao) {
		this.regiao = regiao;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public Estados atualizar(Long id, EstadosRepository estadosRepository) {

		Estados estados = estadosRepository.getById(id);

		estados.setNome(nome);
		estados.setRegiao(regiao);
		estados.setPopulacao(populacao);
		estados.setCapital(capital);
		estados.setArea(area);

		return estados;
	}

}
