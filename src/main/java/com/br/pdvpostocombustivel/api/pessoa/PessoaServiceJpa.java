package com.br.pdvpostocombustivel.api.pessoa;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import com.br.pdvpostocombustivel.repository.PessoaRepository;

@Service
public class PessoaServiceJpa {

    private final PessoaRepository repo;

    @Autowired
    public PessoaServiceJpa(PessoaRepository repo) {
        this.repo = repo;
    }

    public List<Pessoa> findAll() {
        return repo.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return repo.findById(id);
    }

    public Pessoa create(Pessoa p) {
        return repo.save(p);
    }

    public Optional<Pessoa> update(Long id, Pessoa p) {
        return repo.findById(id).map(existing -> {
            existing.setNomeCompleto(p.getNomeCompleto());
            existing.setCpfCnpj(p.getCpfCnpj());
            existing.setDataNascimento(p.getDataNascimento());
            return repo.save(existing);
        });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
