package com.example.pedidos.controller;

import com.example.pedidos.dto.AtualizarPedidoDTO;
import com.example.pedidos.dto.CadastrarPedidoDTO;
import com.example.pedidos.dto.DetalhePedidoDTO;
import com.example.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DetalhePedidoDTO>> buscarTodos(Pageable pageable) {

        Page<DetalhePedidoDTO> detalhePedidoDTO = pedidoService.buscarTodos(pageable);

        return ResponseEntity.ok(detalhePedidoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhePedidoDTO> buscarPorId(@PathVariable Long id) {

        DetalhePedidoDTO detalhePedidoDTO = pedidoService.buscarPorId(id);

        return ResponseEntity.ok(detalhePedidoDTO);
    }

    @PatchMapping("/{id}/pagar")
    @Transactional
    public ResponseEntity pagar(@PathVariable Long id) {

        pedidoService.pagar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/entregar")
    @Transactional
    public ResponseEntity entregar(@PathVariable Long id) {

        pedidoService.entregar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhePedidoDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarPedidoDTO atualizarPedidoDTO) {

        DetalhePedidoDTO detalhePedidoDTO = pedidoService.atualizar(id, atualizarPedidoDTO);

        return ResponseEntity.ok(detalhePedidoDTO);

    }

    @DeleteMapping("/{id}/cancelar")
    @Transactional
    public ResponseEntity cancelar(@PathVariable Long id) {

        pedidoService.cancelar(id);

        return ResponseEntity.noContent().build();
    }
}
