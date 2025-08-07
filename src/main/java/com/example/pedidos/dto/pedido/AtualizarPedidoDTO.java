package com.example.pedidos.dto.pedido;

import java.time.LocalDateTime;

public record AtualizarPedidoDTO(
        String observacao,
        LocalDateTime dataEntrega) {
}
