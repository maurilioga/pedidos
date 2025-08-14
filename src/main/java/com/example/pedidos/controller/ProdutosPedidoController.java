package com.example.pedidos.controller;

import com.example.pedidos.dto.produto.ProdutoDTO;
import com.example.pedidos.dto.produtospedidos.DetalheProdutoPedidoAdicionadoDTO;
import com.example.pedidos.service.ProdutosPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtosPedido")
public class ProdutosPedidoController {

    @Autowired
    private ProdutosPedidoService produtosPedidoService;

    @PostMapping("/pedido/{idPedido}")
    @Transactional
    public ResponseEntity<DetalheProdutoPedidoAdicionadoDTO> adicionar(@PathVariable Long idPedido, @RequestBody ProdutoDTO produtoDTO) {

        DetalheProdutoPedidoAdicionadoDTO detalheProdutoPedidoAdicionadoDTO = produtosPedidoService.adicionar(idPedido, produtoDTO);

        return ResponseEntity.ok(detalheProdutoPedidoAdicionadoDTO);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable Long id) {

        produtosPedidoService.remover(id);

        return ResponseEntity.noContent().build();
    }
}
