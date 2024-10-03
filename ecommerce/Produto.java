package ecommerce;

public class Produto {
    private String nome;
    private int preco;
    private int quantidade;
    private int vendas;

    public Produto(String nome, int preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.vendas = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void decrementarQuantidade(int valor) {
        this.quantidade -= valor;
    }

    public void incrementarVendas(int valor) {
        this.vendas += valor;
    }

    public int getVendas() {
        return vendas;
    }
}
