package com.br.pdvpostocombustivel.api.pessoa;

import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<PessoaResponse> create(@RequestBody PessoaRequest request) {
        PessoaResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // READ - por ID
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> getById(@PathVariable Long id) {
        PessoaResponse response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    // READ - por CPF/CNPJ
    @GetMapping("/cpf/{cpfCnpj}")
    public ResponseEntity<PessoaResponse> getByCpfCnpj(@PathVariable String cpfCnpj) {
        PessoaResponse response = service.getByCpfCnpj(cpfCnpj);
        return ResponseEntity.ok(response);
    }

    // LIST PAGINADO
    @GetMapping
    public ResponseEntity<Page<PessoaResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Page<PessoaResponse> response = service.list(page, size, sortBy, direction);
        return ResponseEntity.ok(response);
    }

    // UPDATE (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> update(
            @PathVariable Long id,
            @RequestBody PessoaRequest request
    ) {
        PessoaResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    // PATCH (atualiza parcialmente)
    @PatchMapping("/{id}")
    public ResponseEntity<PessoaResponse> patch(
            @PathVariable Long id,
            @RequestBody PessoaRequest request
    ) {
        PessoaResponse response = service.patch(id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
