package ecommerce;

import java.util.List;

public class Pedido implements Comparable<Pedido> {
    private List<Produto> produtos;
    private String nomeCliente;
    private int numero;
    private int prioridade; // prioridade => menor numero = mais rápido deve ser processado.

    public Pedido(List<Produto> produtos, String nomeCliente, int numero, int prioridade) {
        this.produtos = produtos;
        this.nomeCliente = nomeCliente;
        this.numero = numero;
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return prioridade;
    }

    @Override
    public int compareTo(Pedido outroPedido) {
        return Integer.compare(this.prioridade, outroPedido.getPrioridade());
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
