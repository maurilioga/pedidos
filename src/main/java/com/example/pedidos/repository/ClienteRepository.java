package com.example.pedidos.repository;

import com.example.pedidos.dto.cliente.ClientePendenciasDTO;
import com.example.pedidos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = """
            SELECT CLI.ID,
                   CLI.NOME,
                   CLI.OBSERVACAO,
                   CLI.TELEFONE,
                   PED.PAGO,
                   CASE
                       WHEN PED.PAGO = FALSE THEN SUM(PED.VALOR_TOTAL)
                       ELSE NULL
                   END AS TOTAL
            FROM TB_CLIENTES CLI
            LEFT JOIN TB_PEDIDOS PED ON CLI.ID = PED.CLIENTE_ID
            WHERE PED.PAGO = FALSE
             OR NOT EXISTS
              (SELECT 1
               FROM TB_PEDIDOS P2
               WHERE P2.CLIENTE_ID = CLI.ID
                AND P2.PAGO = FALSE)
            GROUP BY CLI.ID,
                     CLI.NOME,
                     CLI.OBSERVACAO,
                     CLI.TELEFONE,
                     PED.PAGO
            ORDER BY PED.PAGO,
                     CLI.NOME
            """, nativeQuery = true)
    List<ClientePendenciasDTO> buscarPendencias();
}
