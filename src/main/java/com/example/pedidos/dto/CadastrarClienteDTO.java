package com.example.pedidos.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarClienteDTO(
        @NotBlank
        String nome,
        String observacao,
        String telefone) {
}
