package br.org.serratec.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class CategoriaDTO {
	
	@NotBlank
	@Size(min = 2, max = 30)
	private String nome;

	@Size(min = 2, max = 200)
	private String descricao;
}
