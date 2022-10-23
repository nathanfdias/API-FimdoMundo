package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedidoDTO> listar() {
        
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        List<ItemPedidoDTO> itemPedidosDTO = new ArrayList<>();

        for (ItemPedido itempedido : itemPedidos) {
            itemPedidosDTO.add(new ItemPedidoDTO(itempedido));
        }

        return itemPedidosDTO;
    }

    public ItemPedidoDTO buscar(Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        if(!itemPedido.isPresent()){
            return null;
        }

        return new ItemPedidoDTO(itemPedido.get());
    }
}
