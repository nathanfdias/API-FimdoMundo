package br.org.serratec.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long idCliente;

    @NotBlank
	@Size(min = 5, max = 60)
    private String nomeCompleto;

    @NotBlank
	@Size(min=4,max=50)
	@Email
    private String email;
    
	@CPF
    @NotBlank
	@Size(min=11,max=11)
    private String cpf;
    
    @NotBlank
    @Size(max=40)
    private String telefone;
    
    @Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Valid
    private EnderecoDTO endereco;

}
