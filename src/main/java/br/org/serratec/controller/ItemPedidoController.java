package br.org.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.service.ItemPedidoService;

@RestController
@RequestMapping("/itempedidos")
public class ItemPedidoController {
    
    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    public ResponseEntity<List<ItemPedidoDTO>> listar(){
        return ResponseEntity.ok(itemPedidoService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemPedidoDTO> buscar(@PathVariable Long id){
        ItemPedidoDTO itemPedido = itemPedidoService.buscar(id);

        if(itemPedido != null){
            return ResponseEntity.ok(itemPedido);
        }
        return ResponseEntity.notFound().build();
    }
}
