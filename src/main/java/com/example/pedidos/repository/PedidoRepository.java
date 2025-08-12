package com.example.pedidos.repository;

import com.example.pedidos.dto.pedido.DetalhePedidoDTO;
import com.example.pedidos.dto.pedido.PedidosPendentesDTO;
import com.example.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEntregueFalseOrPagoFalseOrderByDataEntrega();

    @Query(value = """
            SELECT
                COUNT(CASE WHEN ENTREGUE = FALSE THEN 1 END) AS pendenteEntrega,
                COUNT(CASE WHEN PAGO = FALSE THEN 1 END) AS pendentePagamento
            FROM TB_PEDIDOS
            WHERE ENTREGUE = FALSE OR PAGO = FALSE
            """, nativeQuery = true)
    PedidosPendentesDTO buscarContagemPendentes();
}
