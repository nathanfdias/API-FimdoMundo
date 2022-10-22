package br.org.serratec.dto;

import br.org.serratec.model.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProdutoInserirCategoria {
    private Long id;

    public ProdutoInserirCategoria(Categoria categoria){
        this.id = categoria.getId();
    }
}
