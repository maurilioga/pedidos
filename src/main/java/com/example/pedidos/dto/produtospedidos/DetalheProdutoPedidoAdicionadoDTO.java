package com.example.pedidos.dto.produtospedidos;

import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.ProdutosPedido;

public record DetalheProdutoPedidoAdicionadoDTO(
        Long idPedido,
        ProdutoPedidoDTO produtoPedido
) {

    public DetalheProdutoPedidoAdicionadoDTO(Pedido pedido, ProdutosPedido produtoPedido) {
        this(
                pedido.getId(),
                new ProdutoPedidoDTO(produtoPedido)
        );
    }
}
