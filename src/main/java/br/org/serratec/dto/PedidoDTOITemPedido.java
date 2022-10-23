package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.enums.PedidoStatus;
import br.org.serratec.model.Pedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PedidoDTOITemPedido {
    
    private LocalDate dataPedido;
    private LocalDate dataEntrega;
    private LocalDate dataEnvio;
    private PedidoStatus pedidoStatus;

    private Double valorTotal;

    private ClienteDTOItemPedido cliente;
    
    public PedidoDTOITemPedido(Pedido pedido){
        this.dataPedido = pedido.getDataEnvio();
        this.dataEntrega = pedido.getDataEntrega();
        this.dataEnvio = pedido.getDataEnvio();
        this.pedidoStatus = pedido.getPedidoStatus();
        this.cliente = new ClienteDTOItemPedido(pedido.getCliente());
    }
    
    
}
