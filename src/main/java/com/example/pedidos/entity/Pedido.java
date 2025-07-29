package com.example.pedidos.entity;

import com.example.pedidos.dto.CadastrarPedidoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PEDIDOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataEntrega;

    private String observacao;

    private BigDecimal valorTotal;

    private Boolean pago;

    private Boolean entregue;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProdutosPedido> produtosPedido;

    public Pedido(CadastrarPedidoDTO cadastrarPedidoDTO, Cliente cliente, BigDecimal valorTotal, List<ProdutosPedido> produtosPedidos) {
        this.cliente = cliente;
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = cadastrarPedidoDTO.dataEntrega();
        this.observacao = cadastrarPedidoDTO.observacao();
        this.valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP);
        this.produtosPedido = produtosPedidos;
        this.pago = false;
        this.entregue = false;
    }
}
