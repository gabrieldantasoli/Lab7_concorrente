package ecommerce;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Estoque {
    private final Map<String, Integer> produtos = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Estoque(Map<String, Integer> estoqueInicial) {
        this.produtos.putAll(estoqueInicial);
    }

    public boolean processarPedido(Pedido pedido) {
        lock.writeLock().lock();
        try {
            for (Map.Entry<String, Integer> entry : pedido.getProdutos().entrySet()) {
                String produto = entry.getKey();
                int quantidadeSolicitada = entry.getValue();
                int quantidadeEmEstoque = produtos.getOrDefault(produto, 0);
                
                if (quantidadeEmEstoque < quantidadeSolicitada) {
                    System.out.println("Estoque insuficiente para o produto: " + produto);
                    return false;
                }
            }
            for (Map.Entry<String, Integer> entry : pedido.getProdutos().entrySet()) {
                produtos.put(entry.getKey(), produtos.get(entry.getKey()) - entry.getValue());
            }
            return true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void reabastecerEstoque(Map<String, Integer> novosProdutos) {
        lock.writeLock().lock();
        try {
            for (Map.Entry<String, Integer> entry : novosProdutos.entrySet()) {
                produtos.put(entry.getKey(), produtos.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
            System.out.println("Estoque reabastecido.");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void imprimirEstoque() {
        lock.readLock().lock();
        try {
            System.out.println("Estoque Atual: " + produtos);
        } finally {
            lock.readLock().unlock();
        }
    }
}
