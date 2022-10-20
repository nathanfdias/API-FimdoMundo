package br.org.serratec.relacaoPedido;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.org.serratec.model.Pedido;
import br.org.serratec.model.Produto;

@Embeddable
public class ItemPedidoPk {

    @ManyToOne
	@JoinColumn (name = "id_produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn (name = "id_pedido")
	private Pedido pedido;

    public ItemPedidoPk(){
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
	

}
