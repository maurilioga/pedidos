package com.example.pedidos.entity;

import com.example.pedidos.dto.pedido.AtualizarPedidoDTO;
import com.example.pedidos.dto.pedido.CadastrarPedidoDTO;
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

    private Boolean cancelado;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ProdutosPedido> produtosPedido;

    public Pedido(CadastrarPedidoDTO cadastrarPedidoDTO, Cliente cliente, BigDecimal valorTotal, List<ProdutosPedido> produtosPedidos) {
        this.dataCriacao = LocalDateTime.now();
        this.dataEntrega = cadastrarPedidoDTO.dataEntrega();
        this.observacao = cadastrarPedidoDTO.observacao();
        this.valorTotal = valorTotal.setScale(2, RoundingMode.HALF_UP);
        this.pago = false;
        this.entregue = false;
        this.cancelado = false;
        this.cliente = cliente;
        this.produtosPedido = produtosPedidos;
    }

    public void pagar() {
        this.pago = true;
    }

    public void entregar() {
        this.entregue = true;
    }

    public void atualizar(AtualizarPedidoDTO atualizarPedidoDTO) {
        if (atualizarPedidoDTO.observacao() != null) {
            this.observacao = atualizarPedidoDTO.observacao();
        }

        if(atualizarPedidoDTO.dataEntrega() != null) {
            this.dataEntrega = atualizarPedidoDTO.dataEntrega();
        }
    }

    public void cancelar() {
        this.cancelado = true;
    }
}
