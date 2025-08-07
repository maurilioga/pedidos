package com.example.pedidos.service;

import com.example.pedidos.dto.produtospedidos.AtualizarProdutosPedidoDTO;
import com.example.pedidos.dto.produtospedidos.DetalheProdutosPedidoDTO;
import com.example.pedidos.dto.produtospedidos.ProdutosPedidoDTO;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutosPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutosPedidoRepository produtosPedidoRepository;

    public DetalheProdutosPedidoDTO adicionar(Long id, AtualizarProdutosPedidoDTO atualizarProdutosPedidoDTO) {

        List<ProdutosPedido> produtosPedidos = new ArrayList<>();
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));

        for (ProdutosPedidoDTO produtoDTO : atualizarProdutosPedidoDTO.produtosPedidos()) {
            Produto produto = produtoRepository.findByIdAndAtivoIsTrue(produtoDTO.idProduto());

            if(produto == null) {
                throw new EntityNotFoundException("Produto não encontrado ou inativo: id " + produtoDTO.idProduto());
            }

            for (ProdutosPedido produtosPedido : pedido.getProdutosPedido()) {
                if (produtosPedido.getProduto().getId() == produto.getId()) {
                    produtosPedido.setQuantidade(produtosPedido.getQuantidade() + produtoDTO.quantidade());

                    pedido.setValorTotal(produtosPedido.getProduto().getValorSugerido()
                            .multiply(BigDecimal.valueOf(produtosPedido.getQuantidade()))
                            .setScale(2, RoundingMode.HALF_UP));

                    produtosPedidos.add(produtosPedido);
                } else {

                    pedido.setValorTotal(produtosPedido.getProduto().getValorSugerido()
                            .multiply(BigDecimal.valueOf(produtosPedido.getQuantidade()))
                            .setScale(2, RoundingMode.HALF_UP));
                    ProdutosPedido produtoPedido = new ProdutosPedido(produtoDTO, pedido, produto);
                    produtosPedidoRepository.save(new ProdutosPedido(produtoDTO, pedido, produto));
                    produtosPedidos.add(produtoPedido);
                }
            }
        }

        return new DetalheProdutosPedidoDTO(pedido, produtosPedidos);
    }

    public DetalheProdutosPedidoDTO remover(Long id, AtualizarProdutosPedidoDTO atualizarProdutosPedidoDTO) {

        List<ProdutosPedido> produtosPedidos = new ArrayList<>();
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado!"));

        for (ProdutosPedidoDTO produtoDTO : atualizarProdutosPedidoDTO.produtosPedidos()) {
            Produto produto = produtoRepository.findByIdAndAtivoIsTrue(produtoDTO.idProduto());

            if(produto == null) {
                throw new EntityNotFoundException("Produto não encontrado ou inativo: id " + produtoDTO.idProduto());
            }

            for (ProdutosPedido produtosPedido : pedido.getProdutosPedido()) {
                if (produtosPedido.getProduto().getId() == produto.getId()) {
                    produtosPedido.setQuantidade(produtosPedido.getQuantidade() - produtoDTO.quantidade());

                    pedido.setValorTotal(produtosPedido.getProduto().getValorSugerido()
                            .multiply(BigDecimal.valueOf(produtosPedido.getQuantidade()))
                            .setScale(2, RoundingMode.HALF_UP));

                    produtosPedidos.add(produtosPedido);
                } else {

                    pedido.setValorTotal(produtosPedido.getProduto().getValorSugerido()
                            .multiply(BigDecimal.valueOf(produtosPedido.getQuantidade()))
                            .setScale(2, RoundingMode.HALF_UP));
                    ProdutosPedido produtoPedido = new ProdutosPedido(produtoDTO, pedido, produto);
                    produtosPedidoRepository.save(new ProdutosPedido(produtoDTO, pedido, produto));
                    produtosPedidos.add(produtoPedido);
                }
            }
        }

        return new DetalheProdutosPedidoDTO(pedido, produtosPedidos);
    }
}
