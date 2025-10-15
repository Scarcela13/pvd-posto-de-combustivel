package com.br.pdvpostocombustivel.api.acesso.dto;

public record AcessoRequest(
    String usuario,
    String senha
) {}