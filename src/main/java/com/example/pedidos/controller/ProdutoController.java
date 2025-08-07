package com.example.pedidos.controller;

import com.example.pedidos.dto.produto.AtualizarProdutoDTO;
import com.example.pedidos.dto.produto.CadastrarProdutoDTO;
import com.example.pedidos.dto.produto.DetalheProdutoDTO;
import com.example.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody CadastrarProdutoDTO cadastrarProdutoDTO, UriComponentsBuilder uriBuilder) {

        DetalheProdutoDTO detalheProdutoDTO = produtoService.cadastrar(cadastrarProdutoDTO);

        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(detalheProdutoDTO.id()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DetalheProdutoDTO>> buscarTodos(Pageable pageable) {

        Page<DetalheProdutoDTO> detalheProdutoDTO = produtoService.buscarTodos(pageable);

        return ResponseEntity.ok(detalheProdutoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoDTO> buscarPorId(@PathVariable Long id) {

        DetalheProdutoDTO detalheProdutoDTO = produtoService.buscarPorId(id);

        return ResponseEntity.ok(detalheProdutoDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalheProdutoDTO> atualizar(@PathVariable Long id, @RequestBody AtualizarProdutoDTO atualizarProdutoDTO) {

        DetalheProdutoDTO detalheProdutoDTO = produtoService.atualizar(id, atualizarProdutoDTO);

        return ResponseEntity.ok(detalheProdutoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {

        produtoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
