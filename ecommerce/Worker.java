package ecommerce;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {
    private final BlockingQueue<Pedido> filaPedidos;
    private final Estoque estoque;

    public Worker(BlockingQueue<Pedido> filaPedidos, Estoque estoque) {
        this.filaPedidos = filaPedidos;
        this.estoque = estoque;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido pedido = filaPedidos.take(); // Bloqueia até que haja um pedido
                System.out.println("Processando pedido do Cliente " + pedido.getIdCliente());
                boolean sucesso = estoque.processarPedido(pedido);
                if (sucesso) {
                    System.out.println("Pedido processado com sucesso.");
                } else {
                    System.out.println("Pedido rejeitado por falta de estoque.");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Marca a thread como interrompida
        }
    }
}
