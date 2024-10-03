package ecommerce;

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

        ReabastecimentoAutomatico reabastecimento = new ReabastecimentoAutomatico(ecommerce);
        Thread reabastecimentoThread = new Thread(reabastecimento);
        reabastecimentoThread.start();

        RelatorioDeVendas relatorio = new RelatorioDeVendas(ecommerce);
        Thread relatorioThread = new Thread(relatorio);
        relatorioThread.start();

        int numClientes = 50;
        ExecutorService clienteThreadPool = Executors.newFixedThreadPool(numClientes);

        for (int i = 0; i < numClientes; i++) {
            Cliente cliente = new Cliente(ecommerce, "Cliente-" + i, 10);
            clienteThreadPool.submit(cliente);
        }
    }
}

