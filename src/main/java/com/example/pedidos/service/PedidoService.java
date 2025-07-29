package com.example.pedidos.service;

import com.example.pedidos.dto.CadastrarPedidoDTO;
import com.example.pedidos.dto.DetalhePedidoDTO;
import com.example.pedidos.dto.ProdutosPedidoDTO;
import com.example.pedidos.entity.Cliente;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.Produto;
import com.example.pedidos.entity.ProdutosPedido;
import com.example.pedidos.repository.ClienteRepository;
import com.example.pedidos.repository.PedidoRepository;
import com.example.pedidos.repository.ProdutoRepository;
import com.example.pedidos.repository.ProdutosPedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutosPedidoRepository produtosPedidoRepository;

    public DetalhePedidoDTO cadastrar(CadastrarPedidoDTO cadastrarPedidoDTO) {

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ProdutosPedido> produtosPedidos = new ArrayList<>();

        Cliente cliente = clienteRepository.findById(cadastrarPedidoDTO.idCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        for (ProdutosPedidoDTO produtoDTO : cadastrarPedidoDTO.produtos()) {
            Produto produto = produtoRepository.findByIdAndAtivoIsTrue(produtoDTO.idProduto());

            if(produto == null) {
                throw new EntityNotFoundException("Produto não encontrado ou inativo: id " + produtoDTO.idProduto());
            }

            BigDecimal subtotal = produto.getValorSugerido().multiply(BigDecimal.valueOf(produtoDTO.quantidade()));
            valorTotal = valorTotal.add(subtotal);

            produtosPedidos.add(new ProdutosPedido(produtoDTO, null, produto));
        }

        Pedido pedido = new Pedido(cadastrarPedidoDTO, cliente, valorTotal, produtosPedidos);

        for (ProdutosPedido produtosPedido : produtosPedidos) {
            produtosPedido.setPedido(pedido);
        }

        pedidoRepository.save(pedido);

        return new DetalhePedidoDTO(pedido, cliente, produtosPedidos);

    }
}
