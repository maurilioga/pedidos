package com.example.pedidos.dto.pedido;

import com.example.pedidos.dto.produtospedidos.ProdutosPedidoDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CadastrarPedidoDTO(
        @NotNull
        List<ProdutosPedidoDTO> produtos,

        @NotNull
        Long idCliente,

        String observacao,

        LocalDateTime dataEntrega

) {
}
