package com.example.pedidos.dto.produto;

import java.math.BigDecimal;

public record AtualizarProdutoDTO(
        String nome,
        BigDecimal valorSugerido,
        Integer quantidade
) {
}
