package com.example.pedidos.service;

import com.example.pedidos.dto.cliente.AtualizarClienteDTO;
import com.example.pedidos.dto.cliente.CadastrarClienteDTO;
import com.example.pedidos.dto.cliente.ClientePendenciasDTO;
import com.example.pedidos.dto.cliente.DetalheClienteDTO;
import com.example.pedidos.entity.Cliente;
import com.example.pedidos.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public DetalheClienteDTO cadastrar(CadastrarClienteDTO cadastrarClienteDTO) {

        Cliente cliente = new Cliente(cadastrarClienteDTO);
        clienteRepository.save(cliente);

        return new DetalheClienteDTO(cliente);
    }

    public Page<DetalheClienteDTO> buscarTodos(Pageable pageable) {

        Page<Cliente> clientes = clienteRepository.findAll(pageable);

        return clientes.map(DetalheClienteDTO::new);
    }

    public DetalheClienteDTO buscarPorId(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        return new DetalheClienteDTO(cliente);
    }

    public DetalheClienteDTO atualizar(Long id, AtualizarClienteDTO atualizarClienteDTO) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        cliente.atualizar(atualizarClienteDTO);

        return new DetalheClienteDTO(cliente);

    }

    public List<ClientePendenciasDTO> buscarPendencias() {

        return clienteRepository.buscarPendencias();
    }
}
