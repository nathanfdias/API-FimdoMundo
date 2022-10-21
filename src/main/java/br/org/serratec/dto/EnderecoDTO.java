package br.org.serratec.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EnderecoDTO {

    private Long idEndereco;

    @NotBlank
    private String cep;
    
    @NotBlank
	@Size(min=4, max=60)
    private String rua;
    
    @NotBlank
    @Size(max=50)
    private String bairro;
    
    @NotBlank
    @Size(max=80)
    private String cidade;
    
    @NotBlank
    @Size(max=20)
    private String numero;
    
    @Size(max=80)
    private String complemento;
    
    @NotBlank
    @Size(min=2, max=2)
    private String uf;
  
}
