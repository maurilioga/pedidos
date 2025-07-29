package com.example.pedidos.dto;

import com.example.pedidos.entity.Cliente;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.ProdutosPedido;

import java.time.LocalDateTime;
import java.util.List;

public record DetalhePedidoDTO(
        Long id,
        List<ProdutosPedido> produtosPedido,
        Cliente cliente,
        String observacao,
        LocalDateTime dataEntrega,
        LocalDateTime dataCriacao,
        Boolean pago,
        Boolean entregue
) {

    public DetalhePedidoDTO(Pedido pedido, Cliente cliente, List<ProdutosPedido> produtosPedido){
        this(pedido.getId(), produtosPedido, cliente, pedido.getObservacao(), pedido.getDataEntrega(), pedido.getDataCriacao(), pedido.getPago(), pedido.getEntregue());
    }
}
