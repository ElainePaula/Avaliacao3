package br.com.api.states.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.states.modelo.Estados;
import br.com.api.states.modelo.StatusRegiao;

public interface EstadosRepository extends JpaRepository<Estados, Long> {

	Page<Estados> findByRegiao(StatusRegiao regiao, Pageable paginacao);

}
