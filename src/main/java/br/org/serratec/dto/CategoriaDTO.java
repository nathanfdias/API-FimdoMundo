package br.org.serratec.dto;

import br.org.serratec.model.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class CategoriaDTO {
	
	private Long id;
	private String nome;
	private String descricao;

	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }
}
