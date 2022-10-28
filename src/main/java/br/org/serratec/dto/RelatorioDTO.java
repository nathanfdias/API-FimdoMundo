package br.org.serratec.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.org.serratec.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RelatorioDTO {
    
    private Long id;
	private LocalDate dataPedido;
	private Double valorTotal;
    private String email;


    private List<ItemPedidoRelatorioDTO> items = new ArrayList<>();


    public RelatorioDTO(PedidoDTO pedido){
        this.id = pedido.getId();
        this.dataPedido = pedido.getDataPedido();
        this.valorTotal = pedido.getValorTotal();
        this.email = pedido.getCliente().getEmail();
        for (ItemPedido item : pedido.getItens()) {
            ItemPedidoRelatorioDTO itemDTO = new ItemPedidoRelatorioDTO(item);
            items.add(itemDTO);
        }
    }

    @Override
    public String toString() {
        return "Relatório de vendas" + "\nId Pedido: " + getId() + 
        "\nData Pedido: " + getDataPedido() +
        "\nValor Total: " + getValorTotal() +
        "\nItens: " + getItems().toString();
    }
}
