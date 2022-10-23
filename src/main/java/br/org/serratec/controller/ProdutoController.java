package br.org.serratec.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ProdutoDTO;
import br.org.serratec.dto.ProdutoInserirDTO;
import br.org.serratec.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @ApiOperation(value = "Lista produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Lista produto Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.buscar(id);

        if (produto != null) {
            return ResponseEntity.ok(produto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation(value = "Cadastro produto", notes = "preencha com os dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastrado"),
            @ApiResponse(responseCode = "400", description = "Imagem vazia"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "422", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<Object> inserir(@RequestParam("file") MultipartFile file,
            @Valid @RequestPart ProdutoInserirDTO produto) throws IOException {

        try {
            ProdutoDTO produtoDTO = produtoService.inserir(produto, file);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(produtoDTO.getId()).toUri();

            return ResponseEntity.created(uri).body(produtoDTO);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualizado", notes = "preencha com os dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Atualizado"),
            @ApiResponse(responseCode = "400", description = "Imagem vazia"),
            @ApiResponse(responseCode = "422", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<Object> atualizar(@RequestParam("file") MultipartFile file,
            @Valid @RequestPart ProdutoInserirDTO produto, @PathVariable Long id) throws IOException {

        ProdutoDTO produt = produtoService.buscar(id);

        if (produt != null) {
            try {
                ProdutoDTO produtoDTO = produtoService.atualizar(id, produto, file);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(produtoDTO.getId()).toUri();

                return ResponseEntity.created(uri).body(produtoDTO);
            } catch (Exception e) {
                return ResponseEntity.unprocessableEntity().body(e.getMessage());
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta Produto", notes = "preencha com o id do produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Boolean response = produtoService.delete(id);
        System.out.println(response);
        if (response != true) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
