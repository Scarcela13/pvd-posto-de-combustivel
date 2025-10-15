package com.br.pdvpostocombustivel.api.produto.dto;

public record ProdutoResponse(
    Long id,
    String nome,
    String codigo
) {}