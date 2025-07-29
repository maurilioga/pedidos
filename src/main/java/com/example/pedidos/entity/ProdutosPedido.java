package com.example.pedidos.entity;

import com.example.pedidos.dto.ProdutosPedidoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTOS_PEDIDO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutosPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    private BigDecimal valorUnitario;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    public ProdutosPedido(ProdutosPedidoDTO produtoDTO, Pedido pedido, Produto produto) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = produtoDTO.quantidade();
        this.valorUnitario = produto.getValorSugerido();
    }
}
