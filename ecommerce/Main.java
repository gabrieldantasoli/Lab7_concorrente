package ecommerce;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int capacidadeFila = 100;
        Ecommerce ecommerce = new Ecommerce(capacidadeFila);

        int numWorkers = 5;
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(ecommerce);
            Thread workerThread = new Thread(worker, "Worker-" + i);
            workerThread.start();
        }

        Map<String, Produto> produtos = ecommerce.getProdutos();
        ExecutorService reabastecimentoThreadPool = Executors.newFixedThreadPool(produtos.size());

        for (String nomeProduto : produtos.keySet()) {
            ReabastecimentoAutomatico reabastecimento = new ReabastecimentoAutomatico(ecommerce, nomeProduto);
            reabastecimentoThreadPool.submit(reabastecimento);
        }

        RelatorioDeVendas relatorio = new RelatorioDeVendas(ecommerce);
        Thread relatorioThread = new Thread(relatorio);
        relatorioThread.start();

        int numClientes = 50;
        ExecutorService clienteThreadPool = Executors.newFixedThreadPool(numClientes);

        for (int i = 0; i < numClientes; i++) {
            final int orderId = i;

            Cliente cliente = new Cliente(ecommerce, "Cliente-" + i, 10);
            
            clienteThreadPool.submit(() -> {

                Order order = new Order(orderId);

                PaymentProcessor paymentProcessor = new PaymentProcessor();
                CompletableFuture<Void> future = paymentProcessor.processPayment(order);

                future.thenRun(() -> {
                    processOrder(order);
                });
            });
        }

        clienteThreadPool.shutdown();
        reabastecimentoThreadPool.shutdown();
    }

    private static void processOrder(Order order) {
        System.out.println("Pedido " + order.getId() + " foi processado após a confirmação do pagamento.");
    }
}
