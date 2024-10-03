package ecommerce;

import java.util.*;
import java.util.concurrent.*;

public class ECommerceSimulacao {
    private static final int CAPACIDADE_FILA = 1000;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Pedido> filaPedidos = new LinkedBlockingQueue<>(CAPACIDADE_FILA);
        Map<String, Integer> estoqueInicial = new HashMap<>();
        estoqueInicial.put("Produto A", 100);
        estoqueInicial.put("Produto B", 50);

        Estoque estoque = new Estoque(estoqueInicial);

        int numeroDeWorkers = 3;
        ExecutorService workersPool = Executors.newFixedThreadPool(numeroDeWorkers);
        for (int i = 0; i < numeroDeWorkers; i++) {
            workersPool.submit(new Worker(filaPedidos, estoque));
        }

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Map<String, Integer> produtos = new HashMap<>();
            produtos.put("Produto A-" + i, random.nextInt(5) + 1); // Quantidades aleatórias
            produtos.put("Produto B-" + i, random.nextInt(3) + 1);
            Pedido pedido = new Pedido(i, produtos);
            filaPedidos.put(pedido);
            System.out.println("Pedido do Cliente " + i + " foi colocado na fila.");
            Thread.sleep(10);
        }

        workersPool.shutdown();
        workersPool.awaitTermination(1, TimeUnit.MINUTES);
    }
}
