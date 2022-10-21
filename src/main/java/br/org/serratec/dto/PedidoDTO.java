package br.org.serratec.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.org.serratec.enums.PedidoStatus;
import lombok.Data;

@Data
public class PedidoDTO {
    
    private Long id;

    @Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date dataPedido;

    @Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date dataEntrega;

    @Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date dataEnvio;

    private PedidoStatus pedidoStatus;

    private Double valorTotal;

    private Long idCliente;
    private String nomeCliente;

    @Valid
    private Set<ItemPedidoDTO> itens = new HashSet<ItemPedidoDTO>(); 

}
