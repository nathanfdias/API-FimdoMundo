package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.PedidoDTO;
import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> listar() {
        
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            pedidosDTO.add(new PedidoDTO(pedido));
        }

        return pedidosDTO;
    }

    public PedidoDTO buscar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(!pedido.isPresent()){
            return null;
        }

        return new PedidoDTO(pedido.get());
    }

    
}
