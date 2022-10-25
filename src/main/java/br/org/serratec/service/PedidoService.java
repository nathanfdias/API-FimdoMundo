package br.org.serratec.service;

import java.lang.module.FindException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.PedidoDTO;
import br.org.serratec.dto.PedidoInserirDTO;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Pedido;
import br.org.serratec.repository.ClienteRepository;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<PedidoDTO> listar() {
        List<Pedido> result = pedidoRepository.findAll();
        return result.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
    }

    public PedidoDTO buscar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        
        if (!pedido.isPresent()) {
            return null;
        }

        return new PedidoDTO(pedido.get());
    }

    public PedidoDTO inserir (PedidoInserirDTO pedido) {

        Optional<Cliente> clt = clienteRepository.findById(pedido.getCliente().getId());

        if (!clt.isPresent()) {
            throw new FindException("Id inválido");
        }
        
        Pedido ped = new Pedido();
        ped.setDataPedido(pedido.getDataPedido());
        ped.setDataEnvio(pedido.getDataEnvio());
        // ped.setDataPedido(pedido.getDataPedido());
        ped.setDataEntrega(pedido.getDataEntrega());
        ped.setStatus(pedido.getStatus());
        ped.setCliente(clt.get());
        ped = pedidoRepository.save(ped);

        return new PedidoDTO(ped);
    }

    public PedidoDTO atualizar(Long id, PedidoInserirDTO pedido) {

        Optional<Cliente> clt = clienteRepository.findById(pedido.getCliente().getId());
        
        if (!clt.isPresent()) {
            throw new FindException("Id de cliente não encontrado");
        }
        
        pedido.setId(id);

        Pedido ped = new Pedido();
        ped.setId(pedido.getId());
        ped.setDataPedido(LocalDate.now());
        ped.setDataEnvio(pedido.getDataEnvio());
        ped.setDataEntrega(pedido.getDataEntrega());
        ped.setStatus(pedido.getStatus());
        ped.setCliente(clt.get());
        ped = pedidoRepository.save(ped);
    
        return new PedidoDTO(ped);
    }

    public Boolean delete(Long id) {

        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isPresent()) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
