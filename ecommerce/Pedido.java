package ecommerce;

import java.util.List;

public class Pedido {
    private List<Produto> produtos;
    private String nomeCliente;
    private int numero;

    public Pedido(List<Produto> produtos, String nomeCliente, int numero) {
        this.produtos = produtos;
        this.nomeCliente = nomeCliente;
        this.numero = numero;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public int getValorTotal() {
        return produtos.stream().mapToInt(p -> p.getPreco() * p.getQuantidade()).sum();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getNumero() {
        return numero;
    }
}
