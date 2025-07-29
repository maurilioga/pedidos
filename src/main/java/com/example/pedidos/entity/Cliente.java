package com.example.pedidos.entity;

import com.example.pedidos.dto.AtualizarClienteDTO;
import com.example.pedidos.dto.CadastrarClienteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TB_CLIENTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String observacao;

    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente(CadastrarClienteDTO dto) {
        this.nome = dto.nome();
        this.observacao = dto.observacao();
        this.telefone = dto.telefone();
    }

    public void atualizar(AtualizarClienteDTO atualizarClienteDTO) {
        if(atualizarClienteDTO.nome() != null) {
            this.nome = atualizarClienteDTO.nome();
        }

        if(atualizarClienteDTO.observacao() != null) {
            this.observacao = atualizarClienteDTO.observacao();
        }

        if(atualizarClienteDTO.telefone() != null) {
            this.telefone = atualizarClienteDTO.telefone();
        }
    }
}
