package br.com.api.states.controller.dto;

import org.springframework.data.domain.Page;

import br.com.api.states.modelo.Estados;
import br.com.api.states.modelo.StatusRegiao;

public class EstadosDto {

	private long id;
	private String nome;
	private StatusRegiao regiao;
	private int populacao;
	private String capital;
	private float area;

	public EstadosDto(Estados estados) {

		this.id = estados.getId();
		this.nome = estados.getNome();
		this.regiao = estados.getRegiao();
		this.populacao = estados.getPopulacao();
		this.capital = estados.getCapital();
		this.area = estados.getArea();

	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public StatusRegiao getRegiao() {
		return regiao;
	}

	public int getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public float getArea() {
		return area;
	}

	public static Page<EstadosDto> converter(Page<Estados> estados) {
		return estados.map(EstadosDto::new);
	}

}
