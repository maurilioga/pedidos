package com.example.pedidos.service;

import com.example.pedidos.dto.produto.ProdutoDTO;
import com.example.pedidos.dto.produtospedidos.DetalheProdutoPedidoAdicionadoDTO;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.Produto;
import com.example.pedidos.entity.ProdutosPedido;
import com.example.pedidos.repository.PedidoRepository;
import com.example.pedidos.repository.ProdutoRepository;
import com.example.pedidos.repository.ProdutosPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ProdutosPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutosPedidoRepository produtosPedidoRepository;

    public DetalheProdutoPedidoAdicionadoDTO adicionar(Long id, ProdutoDTO produtoDTO) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));

        Produto produto = produtoRepository.findByIdAndAtivoIsTrue(produtoDTO.idProduto());

        if(produto == null) {
            throw new EntityNotFoundException("Produto não encontrado ou inativo: id " + produtoDTO.idProduto());
        }

        BigDecimal valorTotal = pedido.getValorTotal();
        BigDecimal valorProdutoPedido = produto.getValorSugerido().multiply(BigDecimal.valueOf(produtoDTO.quantidade()));

        ProdutosPedido produtoPedido = new ProdutosPedido(produtoDTO, pedido, produto);
        pedido.setValorTotal(valorTotal.add(valorProdutoPedido));

        produtosPedidoRepository.save(produtoPedido);

        return new DetalheProdutoPedidoAdicionadoDTO(pedido, produtoPedido);
    }

    public void remover(Long id) {

        ProdutosPedido produtosPedido = produtosPedidoRepository.getReferenceById(id);

        BigDecimal valorTotal = produtosPedido.getPedido().getValorTotal();
        BigDecimal valorProdutosPedido = produtosPedido.getProduto().getValorSugerido()
                .multiply(BigDecimal.valueOf(produtosPedido.getQuantidade()))
                .setScale(2, RoundingMode.HALF_UP);

        produtosPedido.getPedido().setValorTotal(valorTotal.subtract(valorProdutosPedido));

        produtosPedidoRepository.delete(produtosPedido);
    }
}
