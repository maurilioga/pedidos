package com.example.pedidos.repository;

import com.example.pedidos.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByIdAndAtivoIsTrue(Long idProduto);
}
