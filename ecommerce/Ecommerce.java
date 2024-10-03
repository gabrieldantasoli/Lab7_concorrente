package ecommerce;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Ecommerce {
    private PriorityBlockingQueue<Pedido> filaDePedidos;
    private Estoque estoque;
    private AtomicInteger pedidosProcessados;
    private AtomicInteger valorTotalVendas;
    private AtomicInteger pedidosRejeitados;

    public Ecommerce(int capacidadeFila) {
        this.filaDePedidos = new PriorityBlockingQueue<>(capacidadeFila);
        this.estoque = new Estoque();
        this.pedidosProcessados = new AtomicInteger(0);
        this.valorTotalVendas = new AtomicInteger(0);
        this.pedidosRejeitados = new AtomicInteger(0);
    }

    public void adicionarPedido(Pedido pedido) throws InterruptedException {
        filaDePedidos.put(pedido);
    }

    public Pedido pegarProximoPedido() throws InterruptedException {
        return filaDePedidos.take();
    }

    public void processarPedido(Pedido pedido) {
        if (estoque.verificarDisponibilidade(pedido)) {
            estoque.retirarProdutos(pedido);
            pedidosProcessados.incrementAndGet();
            valorTotalVendas.addAndGet(pedido.getValorTotal());
            System.out.println("PRIORIDADE: " + pedido.getPrioridade() + " <-> Pedido " + pedido.getNumero() + " do Cliente " + pedido.getNomeCliente() + " foi processado");
        } else {
            pedidosRejeitados.incrementAndGet();
        }
    }

    public void gerarRelatorioDeVendas() {
        System.out.println("===========================");
        System.out.println("Relatório de Vendas:");
        System.out.println("===========================");
        System.out.println("Pedidos processados: " + pedidosProcessados.get());
        System.out.println("Valor total das vendas: " + valorTotalVendas.get());
        System.out.println("Pedidos rejeitados: " + pedidosRejeitados.get());
        System.out.println("===========================");
    }

    public void reabastecerEstoque() {
        estoque.reabastecer();
    }
    public PriorityBlockingQueue<Pedido> getFilaDePedidos() {
        return this.filaDePedidos;
    }
}
