package ecommerce;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.Map;

public class ReabastecimentoAutomatico implements Runnable {
    private final Estoque estoque;

    public ReabastecimentoAutomatico(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public void run() {
        Map<String, Integer> reabastecimento = new HashMap<>();
        reabastecimento.put("Produto A", 50);
        reabastecimento.put("Produto B", 30);
        estoque.reabastecerEstoque(reabastecimento);
    }

    public static void agendarReabastecimento(Estoque estoque) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ReabastecimentoAutomatico(estoque), 10, 10, TimeUnit.SECONDS);
    }
}
