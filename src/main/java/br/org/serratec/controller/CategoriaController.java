package br.org.serratec.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.CategoriaDTO;
import br.org.serratec.model.Categoria;
import br.org.serratec.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    @ApiOperation(value = "Lista todos as categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos as categorias"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Lista todos as categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos as categorias"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<CategoriaDTO> buscar(@PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.buscar(id);

        if(categoria != null) {
            return ResponseEntity.ok(categoria);
        }

        return  ResponseEntity.notFound().build();
    }


    @PostMapping
    @ApiOperation(value = "Lista todos as categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos as categorias"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<Object> update(@Valid @RequestBody Categoria categoria){
        CategoriaDTO categoriaDTO = categoriaService.inserir(categoria);

        if(categoriaDTO != null){
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(categoriaDTO.getId())
            .toUri();

            return  ResponseEntity.created(uri).body(categoriaDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta uma categoria", notes = "preencha com o id da categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        Boolean response = categoriaService.delete(id);
        System.out.println(response);
        if (response != true) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
