package br.org.serratec.controller;

import java.lang.module.FindException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.dto.ItemPedidoInserirDTO;
import br.org.serratec.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("itemPedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    @ApiOperation(value = "Todos itens pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<List<ItemPedidoDTO>> listar() {
        return ResponseEntity.ok(itemPedidoService.listar());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Item Pedido por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<ItemPedidoDTO> buscar(@PathVariable Long id) {
        ItemPedidoDTO itemPedido = itemPedidoService.buscar(id);

        if (itemPedido != null) {
            return ResponseEntity.ok(itemPedido);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation(value = "Cadastro", notes = "preencha os dados" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados já existentes"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<Object> inserir(@Valid @RequestBody ItemPedidoInserirDTO itemPedido) {
        ItemPedidoDTO itemPedidoDTO = itemPedidoService.inserir(itemPedido);

        if (itemPedidoDTO != null) {

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(itemPedidoDTO.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(itemPedidoDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    
    @PutMapping("{id}")
    @ApiOperation(value = "Atualizando", notes = "preencha os dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados já existentes"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<Object> atualizar(@Valid @RequestBody ItemPedidoInserirDTO itemPedido, @PathVariable Long id) {
        ItemPedidoDTO pet = itemPedidoService.buscar(id);

        if (pet != null) {
            try {
                return ResponseEntity.ok(itemPedidoService.atualizar(itemPedido, id));
            } catch (FindException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletar", notes = "Selecione o Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Boolean response = itemPedidoService.delete(id);
        if (response != true) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    
}
