package ecommerce;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Pedido implements Comparable<Pedido> {
    private List<Produto> produtos;
    private String nomeCliente;
    private int numero;
    private int prioridade; // prioridade => menor numero = mais rápido deve ser processado.
    private Instant timestamp; // para garantir starvation free. Além da prioridade, tempo de espera na fila será importante

    public Pedido(List<Produto> produtos, String nomeCliente, int numero, int prioridade) {
        this.produtos = produtos;
        this.nomeCliente = nomeCliente;
        this.numero = numero;
        this.prioridade = prioridade;
        this.timestamp = Instant.now();
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public int compareTo(Pedido outroPedido) {
        // Lógica de revisão de prioridade com base no tempo
        long tempoMaximoEspera = 10;
        long tempoEspera = Duration.between(this.timestamp, Instant.now()).getSeconds();

        if (tempoEspera > tempoMaximoEspera) {
            int novaPrioridade = Math.max(1, Math.min(10, this.prioridade - (int)(tempoEspera / 2)));
            return Integer.compare(novaPrioridade, outroPedido.getPrioridade());
        }

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

    public Instant getTimestamp() {
        return timestamp;
    }
}
