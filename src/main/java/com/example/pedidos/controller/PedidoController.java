package com.example.pedidos.controller;

import com.example.pedidos.dto.CadastrarPedidoDTO;
import com.example.pedidos.dto.DetalhePedidoDTO;
import com.example.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CadastrarPedidoDTO cadastrarPedidoDTO, UriComponentsBuilder uriBuilder) {

        DetalhePedidoDTO detalhePedidoDTO = pedidoService.cadastrar(cadastrarPedidoDTO);

        var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(detalhePedidoDTO.id()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
