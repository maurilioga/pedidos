package com.example.pedidos.dto.pedido;

import com.example.pedidos.dto.produto.ProdutoDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CadastrarPedidoDTO(
        @NotNull
        List<ProdutoDTO> produtos,

        @NotNull
        Long idCliente,

        String observacao,

        LocalDateTime dataEntrega

) {
}
