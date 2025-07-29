package com.example.pedidos.service;

import com.example.pedidos.dto.AtualizarClienteDTO;
import com.example.pedidos.dto.CadastrarClienteDTO;
import com.example.pedidos.dto.DetalheClienteDTO;
import com.example.pedidos.entity.Cliente;
import com.example.pedidos.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return new DetalheClienteDTO(cliente.get());
    }

    public DetalheClienteDTO atualizar(Long id, AtualizarClienteDTO atualizarClienteDTO) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty()) {
            throw new EntityNotFoundException();
        }

        cliente.get().atualizar(atualizarClienteDTO);

        return new DetalheClienteDTO(cliente.get());

    }
}
