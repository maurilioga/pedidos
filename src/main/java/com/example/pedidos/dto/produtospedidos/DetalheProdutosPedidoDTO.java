package com.example.pedidos.dto.produtospedidos;

import com.example.pedidos.entity.Cliente;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.ProdutosPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DetalheProdutosPedidoDTO(
        Long idPedido,
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

    public DetalheProdutosPedidoDTO(Pedido pedido, Cliente cliente, List<ProdutosPedido> produtosPedido) {
        this(
                pedido.getId(),
                produtosPedido.stream()
                        .map(ProdutoPedidoDTO::new)
                        .toList(),
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
