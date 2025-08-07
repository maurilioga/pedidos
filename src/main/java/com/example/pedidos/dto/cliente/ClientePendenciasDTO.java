package com.example.pedidos.dto.cliente;

import java.math.BigDecimal;

public record ClientePendenciasDTO(
        Long id,
        String nome,
        String observacao,
        String telefone,
        Boolean isPago,
        BigDecimal valorTotal) {
}
