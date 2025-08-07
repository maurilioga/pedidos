package com.example.pedidos.service;

import com.example.pedidos.dto.pedido.AtualizarPedidoDTO;
import com.example.pedidos.dto.pedido.CadastrarPedidoDTO;
import com.example.pedidos.dto.pedido.DetalhePedidoDTO;
import com.example.pedidos.dto.produtospedidos.ProdutosPedidoDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<DetalhePedidoDTO> buscarTodos(Pageable pageable) {

        Page<Pedido> pedidos = pedidoRepository.findAll(pageable);

        return pedidos.map(pedido -> new DetalhePedidoDTO(pedido, pedido.getCliente(), pedido.getProdutosPedido()));
    }

    public DetalhePedidoDTO buscarPorId(Long id) {

        Pedido pedido = this.buscar(id);

        return new DetalhePedidoDTO(pedido, pedido.getCliente(), pedido.getProdutosPedido());
    }

    public void pagar(Long id) {

        Pedido pedido = this.buscar(id);
        pedido.pagar();
    }

    public void entregar(Long id) {

        Pedido pedido = this.buscar(id);
        pedido.entregar();
    }


    public DetalhePedidoDTO atualizar(Long id, AtualizarPedidoDTO atualizarPedidoDTO) {

        Pedido pedido = this.buscar(id);
        pedido.atualizar(atualizarPedidoDTO);

        return new DetalhePedidoDTO(pedido, pedido.getCliente(), pedido.getProdutosPedido());
    }

    public void cancelar(Long id) {

        Pedido pedido = this.buscar(id);
        pedido.cancelar();
    }

    private Pedido buscar(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }
}
