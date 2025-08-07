package com.example.pedidos.dto.produto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CadastrarProdutoDTO(
        @NotBlank
        String nome,
        BigDecimal valorSugerido,
        Integer quantidade
) {
}
