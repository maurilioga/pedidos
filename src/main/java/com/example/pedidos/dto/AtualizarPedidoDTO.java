package com.example.pedidos.dto;

import java.time.LocalDateTime;

public record AtualizarPedidoDTO(
        String observacao,
        LocalDateTime dataEntrega) {
}
