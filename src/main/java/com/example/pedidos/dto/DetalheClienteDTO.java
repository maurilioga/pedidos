package com.example.pedidos.dto;

import com.example.pedidos.entity.Cliente;

public record DetalheClienteDTO (
        Long id,
        String nome,
        String observacao,
        String telefone) {

    public DetalheClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getObservacao(), cliente.getTelefone());
    }
}
