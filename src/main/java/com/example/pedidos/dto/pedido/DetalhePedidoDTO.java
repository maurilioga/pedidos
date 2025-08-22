package com.example.pedidos.dto.pedido;

import com.example.pedidos.dto.produto.DetalheProdutoDTO;
import com.example.pedidos.dto.produtospedidos.ProdutoPedidoDTO;
import com.example.pedidos.entity.Cliente;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.ProdutosPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhePedidoDTO(
        Long id,
        List<ProdutoPedidoDTO> produtosPedido,
        String nomeCliente,
        String observacaoCliente,
        String observacao,
        LocalDateTime dataEntrega,
        LocalDateTime dataCriacao,
        Boolean pago,
        Boolean entregue,
        BigDecimal valorTotal
) {

    public DetalhePedidoDTO(Pedido pedido, Cliente cliente, List<ProdutosPedido> produtosPedido){
        this(pedido.getId(),
                produtosPedido.stream().map(
                        ProdutoPedidoDTO::new)
                        .collect(Collectors.toList()),
                cliente.getNome(),
                cliente.getObservacao(),
                pedido.getObservacao(),
                pedido.getDataEntrega(),
                pedido.getDataCriacao(),
                pedido.getPago(),
                pedido.getEntregue(),
                pedido.getValorTotal()
        );
    }
}
