package br.org.serratec.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProdutoDTO {
    
    private Long id;

	@NotNull
	@Size(min=5, max=30)
	private String nome;
	
    @NotNull
	@Size(min=5, max=200)
	private String descricao;
	
    @NotNull
	private Integer quantidadeEstoque;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Past
	private Date dataCadastro;
    
    @NotNull
	private Double valorUnitario;
	
    @NotNull
    private Integer idCategoria;

    private Byte[] imagem;
	
    private String nomeCategoria;
}
