package ecommerce;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RelatorioDeVendas implements Runnable {
    private int totalPedidosProcessados = 0;
    private double valorTotalVendas = 0.0;
    private int pedidosRejeitados = 0;

    public void registrarPedidoProcessado(double valor) {
        totalPedidosProcessados++;
        valorTotalVendas += valor;
    }

    public void registrarPedidoRejeitado() {
        pedidosRejeitados++;
    }

    @Override
    public void run() {
        System.out.println("Relatório de Vendas:");
        System.out.println("Total de Pedidos Processados: " + totalPedidosProcessados);
        System.out.println("Valor Total de Vendas: " + valorTotalVendas);
        System.out.println("Pedidos Rejeitados: " + pedidosRejeitados);
    }

    public static void agendarRelatorio(RelatorioDeVendas relatorio) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(relatorio, 30, 30, TimeUnit.SECONDS);
    }
}
