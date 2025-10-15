package com.br.pdvpostocombustivel.api.pessoa;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.*;
import com.br.pdvpostocombustivel.domain.entity.Pessoa;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaControllerJpa {

    private final PessoaServiceJpa service;

    public PessoaControllerJpa(PessoaServiceJpa service) {
        this.service = service;
    }

    @GetMapping
    public List<Pessoa> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa body) {
        Pessoa created = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa body) {
        return service.update(id, body)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
