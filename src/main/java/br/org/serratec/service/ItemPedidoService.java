package br.org.serratec.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.dto.ItemPedidoInserirDTO;
import br.org.serratec.dto.ProdutoItemPedidoListDTO;
import br.org.serratec.exception.PedidoIdException;
import br.org.serratec.exception.QuantidadeEstoqueException;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.model.Pedido;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ItemPedidoRepository;
import br.org.serratec.repository.PedidoRepository;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public ItemPedidoDTO InserirUrlImagemItem(ItemPedido itemPedido) {

        Optional<Produto> produtos = produtoRepository.findById(itemPedido.getProduto().getId());

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/foto/{id}")
                .buildAndExpand(produtos.get().getId()).toUri();

        ProdutoItemPedidoListDTO produtoUrl = new ProdutoItemPedidoListDTO();
        produtoUrl.setId(produtos.get().getId());
        produtoUrl.setCategoria(produtos.get().getCategoria());
        produtoUrl.setDataCadastro(produtos.get().getDataCadastro());
        produtoUrl.setDescricao(produtos.get().getDescricao());
        produtoUrl.setNome(produtos.get().getNome());
        produtoUrl.setValorUnitario(produtos.get().getValorUnitario());
        produtoUrl.setLinkImagem(uri.toString());

        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(itemPedido);
        itemPedidoDTO.setId(itemPedido.getId());
        itemPedidoDTO.setProduto(produtoUrl);

        return itemPedidoDTO;

    }

    public List<ItemPedidoDTO> listar() {
        List<ItemPedido> items = itemPedidoRepository.findAll();
        List<ItemPedidoDTO> itemDTO = new ArrayList<>();

        for (ItemPedido item : items) {
            itemDTO.add(InserirUrlImagemItem(item));
        }

        return itemDTO;
    }

    public ItemPedidoDTO buscar(Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);

        ItemPedidoDTO itemPedidoDTO = InserirUrlImagemItem(itemPedido.get());

        if (!itemPedido.isPresent()) {
            return null;
        }

        return itemPedidoDTO;
    }

    public ItemPedidoDTO inserir(ItemPedidoInserirDTO itemPedido) {
        // Optional<Produto> produtosListados = produtoRepository.findById(itemPedido.getProduto().getIdProduto());
        Optional<Pedido> pedido = pedidoRepository.findById(itemPedido.getPedido().getId());
        Optional<Produto> produtos = produtoRepository.findById(itemPedido.getProduto().getIdProduto());

        if (produtos.get().getQuantidadeEstoque() < itemPedido.getQuantidade() || itemPedido.getQuantidade() <= 0) {
            throw new QuantidadeEstoqueException("Quantidade Invalida !");
        }

        Double desconto = 0.0;
        Double valorBruto = 0.0;
        Double valorLiquido = 0.0;

        desconto = 1 - (itemPedido.getPercentualDesconto() / 100);
        //TOTAL DE TODOS PRODUTOS_LISTADOS DO CLIENTE
        valorBruto = produtos.get().getValorUnitario() * itemPedido.getQuantidade();
        valorLiquido = (produtos.get().getValorUnitario() * itemPedido.getQuantidade())* desconto;
        // total += (produtos.get().getValorUnitario() * itemPedido.getQuantidade())* desconto;


        produtos.get().setQuantidadeEstoque(produtos.get().getQuantidadeEstoque() - itemPedido.getQuantidade());
        produtoRepository.save(produtos.get());

        ItemPedido item = new ItemPedido();
        item.setQuantidade(itemPedido.getQuantidade());
        item.setPrecoVenda(pedido.get().getValorTotal());
        item.setPercentualDesconto(itemPedido.getPercentualDesconto());
        item.setValorBruto(valorBruto);
        item.setValorLiquido(valorLiquido);
        item.setPedido(pedido.get());
        item.setProduto(produtos.get());
        item = itemPedidoRepository.save(item);

        return InserirUrlImagemItem(item);
    }

    public ItemPedidoDTO atualizar(ItemPedidoInserirDTO itemPedido, Long id) {

        itemPedido.setId(id);

        Optional<ItemPedido> itemPedidos = itemPedidoRepository.findById(id);
        Optional<Produto> produtosListados = produtoRepository.findById(itemPedido.getProduto().getIdProduto());
        Optional<Pedido> pedido = pedidoRepository.findById(itemPedido.getPedido().getId());
        Optional<Produto> produtos = produtoRepository.findById(itemPedido.getProduto().getIdProduto());

        if (!itemPedido.getPedido().getId().equals(itemPedidos.get().getPedido().getId())){
            throw new PedidoIdException("Não é possível alterar o id !");
        }

        if (!itemPedido.getQuantidade().equals(itemPedidos.get().getProduto().getQuantidadeEstoque())) {
            if (itemPedido.getQuantidade() > itemPedidos.get().getProduto().getQuantidadeEstoque()) {
                if (produtos.get().getQuantidadeEstoque() < itemPedido.getQuantidade() || itemPedido.getQuantidade() <= 0) {
                    throw new QuantidadeEstoqueException("Quantidade Invalida !");
                }
                produtos.get().setQuantidadeEstoque(produtos.get().getQuantidadeEstoque() - itemPedido.getQuantidade());
            }
            if (itemPedido.getQuantidade() < produtos.get().getQuantidadeEstoque()) {
                 
                Integer produtoQuantidade = produtos.get().getQuantidadeEstoque()  - itemPedido.getQuantidade();
    
                System.out.println(produtos.get().getQuantidadeEstoque() + produtoQuantidade);
    
                produtos.get().setQuantidadeEstoque(produtoQuantidade);
            }
        }

        produtoRepository.save(produtos.get());

        Double subTotal = 0.0;

        subTotal += produtosListados.get().getValorUnitario() * itemPedido.getQuantidade();

        ItemPedido item = new ItemPedido();
        item.setId(itemPedido.getId());
        item.setQuantidade(itemPedido.getQuantidade());
        item.setPrecoVenda(subTotal);
        item.setPedido(pedido.get());
        item.setProduto(produtos.get());
        item = itemPedidoRepository.save(item);

        return InserirUrlImagemItem(item);
    }

    public Boolean delete(Long id) {

        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);

        if (itemPedido.isPresent()) {
            itemPedidoRepository.deleteById(id);
            return true;
        }

        return false;

    }

}
