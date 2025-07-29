package com.example.pedidos.dto;

import com.example.pedidos.entity.Produto;

import java.math.BigDecimal;

public record DetalheProdutoDTO(
        Long id,
        String nome,
        BigDecimal valorSugerido,
        Integer quantidade
) {

    public DetalheProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getValorSugerido(), produto.getQuantidade());
    }
}
