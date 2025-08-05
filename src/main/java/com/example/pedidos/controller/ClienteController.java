package com.example.pedidos.controller;

import com.example.pedidos.dto.AtualizarClienteDTO;
import com.example.pedidos.dto.CadastrarClienteDTO;
import com.example.pedidos.dto.DetalheClienteDTO;
import com.example.pedidos.repository.ClienteRepository;
import com.example.pedidos.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastrarClienteDTO cadastrarClienteDTO, UriComponentsBuilder uriBuilder) {

        DetalheClienteDTO detalheClienteDTO = clienteService.cadastrar(cadastrarClienteDTO);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(detalheClienteDTO.id()).toUri();

        return ResponseEntity.created(uri).body(detalheClienteDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DetalheClienteDTO>> buscar(Pageable pageable) {

        Page<DetalheClienteDTO> detalheClienteDTO = clienteService.buscarTodos(pageable);

        return ResponseEntity.ok(detalheClienteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheClienteDTO> buscarPorId(@PathVariable Long id) {

        DetalheClienteDTO detalheClienteDTO = clienteService.buscarPorId(id);

        return ResponseEntity.ok(detalheClienteDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalheClienteDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarClienteDTO atualizarClienteDTO) {

        DetalheClienteDTO detalheClienteDTO = clienteService.atualizar(id, atualizarClienteDTO);

        return ResponseEntity.ok(detalheClienteDTO);
    }

}
