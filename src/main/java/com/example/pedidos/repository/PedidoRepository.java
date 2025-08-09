package com.example.pedidos.repository;

import com.example.pedidos.dto.pedido.DetalhePedidoDTO;
import com.example.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEntregueFalseOrPagoFalseOrderByDataEntrega();
}
