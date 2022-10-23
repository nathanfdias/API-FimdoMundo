package br.org.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.dto.PedidoDTO;
import br.org.serratec.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listar(){
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id){
        PedidoDTO pedido = pedidoService.buscar(id);

        if(pedido != null){
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }
}
