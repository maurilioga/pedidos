package com.example.pedidos.repository;

import com.example.pedidos.entity.ProdutosPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosPedidoRepository extends JpaRepository<ProdutosPedido, Long> {
}
