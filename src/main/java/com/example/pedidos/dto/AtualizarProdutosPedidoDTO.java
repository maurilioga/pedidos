package com.example.pedidos.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtualizarProdutosPedidoDTO(
        @NotNull
        List<ProdutosPedidoDTO> produtosPedidos
) {
}
