package com.example.pedidos.dto.produtospedidos;

import com.example.pedidos.dto.produto.DetalheProdutoDTO;
import com.example.pedidos.entity.ProdutosPedido;

public record ProdutoPedidoDTO(
        Long idProdutoPedido,
        DetalheProdutoDTO produtoPedido
) {

    public ProdutoPedidoDTO(ProdutosPedido produtoPedido){
        this(
                produtoPedido.getId(),
                new DetalheProdutoDTO(
                        produtoPedido.getProduto().getId(),
                        produtoPedido.getProduto().getNome(),
                        produtoPedido.getProduto().getValorSugerido(),
                        produtoPedido.getQuantidade())
        );
    }
}
