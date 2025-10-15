package com.br.pdvpostocombustivel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.pdvpostocombustivel.domain.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
