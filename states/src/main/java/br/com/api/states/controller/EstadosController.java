package br.com.api.states.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.api.states.controller.dto.EstadosDto;
import br.com.api.states.controller.form.AtualizacaoForm;
import br.com.api.states.modelo.Estados;
import br.com.api.states.modelo.StatusRegiao;
import br.com.api.states.repository.EstadosRepository;

@RestController
@RequestMapping("/api/states")
public class EstadosController {

	@Autowired
	private EstadosRepository estadosRepository;

	@GetMapping
	public Page<EstadosDto> lista(@RequestParam(required = false) StatusRegiao regiao,
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao) {

		if (regiao == null) {
			Page<Estados> estados = estadosRepository.findAll(paginacao);
			return EstadosDto.converter(estados);
		} else {
			Page<Estados> estados = estadosRepository.findByRegiao(regiao, paginacao);
			return EstadosDto.converter(estados);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<EstadosDto> cadastrar(@RequestBody @Valid Estados estados, UriComponentsBuilder uriBuilder) {
		estadosRepository.save(estados);

		URI uri = uriBuilder.path("/api/states{id}").buildAndExpand(estados.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadosDto(estados));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EstadosDto> detalhar(@PathVariable Long id) {
		Optional<Estados> estados = estadosRepository.findById(id);
		if (estados.isPresent()) {
			return ResponseEntity.ok(new EstadosDto(estados.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstadosDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoForm form) {
		Optional<Estados> optional = estadosRepository.findById(id);
		if (optional.isPresent()) {
			Estados estado = form.atualizar(id, estadosRepository);
			return ResponseEntity.ok(new EstadosDto(estado));

		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Estados> optional = estadosRepository.findById(id);
		if (optional.isPresent()) {
			estadosRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
