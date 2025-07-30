package com.example.pedidos.dto;

import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.ProdutosPedido;

import java.util.List;
import java.util.stream.Collectors;

public record DetalheProdutosPedidoDTO(
        Long id,
        List<DetalheProdutoDTO> produtosPedido
) {

    public DetalheProdutosPedidoDTO(Pedido pedido, List<ProdutosPedido> produtosPedido){
        this(pedido.getId(),
                produtosPedido.stream().map(
                                prod -> new DetalheProdutoDTO(
                                        prod.getProduto().getId(),
                                        prod.getProduto().getNome(),
                                        prod.getProduto().getValorSugerido(),
                                        prod.getProduto().getQuantidade()))
                        .collect(Collectors.toList()));
    }
}
