package com.example.pedidos.controller;

import com.example.pedidos.dto.produtospedidos.AtualizarProdutosPedidoDTO;
import com.example.pedidos.dto.produtospedidos.DetalheProdutosPedidoDTO;
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
    public ResponseEntity<DetalheProdutosPedidoDTO> adicionar(@PathVariable Long idPedido, @RequestBody AtualizarProdutosPedidoDTO atualizarProdutosPedidoDTO) {

        DetalheProdutosPedidoDTO detalheProdutosPedidoDTO = produtosPedidoService.adicionar(idPedido, atualizarProdutosPedidoDTO);

        return ResponseEntity.ok(detalheProdutosPedidoDTO);

    }

    @DeleteMapping("/pedido/{idPedido}")
    @Transactional
    public ResponseEntity<DetalheProdutosPedidoDTO> remover(@PathVariable Long idPedido, @RequestBody AtualizarProdutosPedidoDTO atualizarProdutosPedidoDTO) {

        DetalheProdutosPedidoDTO detalheProdutosPedidoDTO = produtosPedidoService.remover(idPedido, atualizarProdutosPedidoDTO);

        return ResponseEntity.ok(detalheProdutosPedidoDTO);
    }
}
