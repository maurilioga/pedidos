package com.example.pedidos.entity;

import com.example.pedidos.dto.AtualizarProdutoDTO;
import com.example.pedidos.dto.CadastrarProdutoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_PRODUTOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal valorSugerido;

    private Integer quantidade;

    private Boolean ativo;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ProdutosPedido> produtosPedido;

    public Produto(CadastrarProdutoDTO cadastrarProdutoDTO) {
        this.nome = cadastrarProdutoDTO.nome();
        this.valorSugerido = cadastrarProdutoDTO.valorSugerido();
        this.quantidade = cadastrarProdutoDTO.quantidade();
        this.ativo = true;
    }

    public void atualizar(AtualizarProdutoDTO atualizarProdutoDTO) {
        if(atualizarProdutoDTO.nome() != null) {
            this.nome = atualizarProdutoDTO.nome();
        }

        if(atualizarProdutoDTO.valorSugerido() != null) {
            this.valorSugerido = atualizarProdutoDTO.valorSugerido();
        }

        if(atualizarProdutoDTO.quantidade() != null) {
            this.quantidade = atualizarProdutoDTO.quantidade();
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
