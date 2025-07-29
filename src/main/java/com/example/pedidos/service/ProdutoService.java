package com.example.pedidos.service;

import com.example.pedidos.dto.AtualizarProdutoDTO;
import com.example.pedidos.dto.CadastrarProdutoDTO;
import com.example.pedidos.dto.DetalheProdutoDTO;
import com.example.pedidos.entity.Produto;
import com.example.pedidos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public DetalheProdutoDTO cadastrar(CadastrarProdutoDTO cadastrarProdutoDTO) {

        Produto produto = new Produto(cadastrarProdutoDTO);
        produtoRepository.save(produto);

        return new DetalheProdutoDTO(produto);
    }

    public Page<DetalheProdutoDTO> buscarTodos(Pageable pageable) {

        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(DetalheProdutoDTO::new);
    }

    public DetalheProdutoDTO buscarPorId(Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return new DetalheProdutoDTO(produto.get());
    }

    public DetalheProdutoDTO atualizar(Long id, AtualizarProdutoDTO atualizarProdutoDTO) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()) {
            throw new EntityNotFoundException();
        }

        produto.get().atualizar(atualizarProdutoDTO);

        return new DetalheProdutoDTO(produto.get());
    }

    public void excluir(Long id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()) {
            throw new EntityNotFoundException();
        }

        produto.get().excluir();
    }
}
