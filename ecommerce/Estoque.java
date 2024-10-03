package ecommerce;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Estoque {
    private Map<String, Produto> produtos; // a incrementação tem que ser feita aqui
    private ReadWriteLock lock;
    private Random random = new Random();

    public Estoque() {
        this.produtos = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
        inicializarEstoque();
    }

    private void inicializarEstoque() {
        produtos.put("Produto1", new Produto("Produto1", 100, 10));
        produtos.put("Produto2", new Produto("Produto2", 200, 15));
        produtos.put("Produto3", new Produto("Produto3", 50, 20));
    }

    public boolean verificarDisponibilidade(Pedido pedido) {
        Lock readLock = lock.readLock();
        readLock.lock();
        try {
            for (Produto produtoDoPedido : pedido.getProdutos()) {
                Produto produtoNoEstoque = produtos.get(produtoDoPedido.getNome());
                if (produtoNoEstoque == null || produtoNoEstoque.getQuantidade() < produtoDoPedido.getQuantidade()) {
                    return false;
                }
            }
            return true;
        } finally {
            readLock.unlock();
        }
    }

    public void retirarProdutos(Pedido pedido) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            for (Produto produtoDoPedido : pedido.getProdutos()) {
                Produto produtoNoEstoque = produtos.get(produtoDoPedido.getNome());
                if (produtoNoEstoque != null) {
                    produtoNoEstoque.decrementarQuantidade(produtoDoPedido.getQuantidade());

                    produtoNoEstoque.incrementarVendas(produtoDoPedido.getQuantidade());
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void reabastecer(String nomeProduto) {
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            Produto produto = produtos.get(nomeProduto);

            if (produto.getQuantidade() < 10) {
                int quantidadeReabastecida = random.nextInt(91) + 10;
                produto.decrementarQuantidade(-quantidadeReabastecida);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\nReabastecimento Inteligente: " + nomeProduto + " reabastecido com " + quantidadeReabastecida + " itens.\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }

        } finally {
            writeLock.unlock();
        }
    }

    public Map<String, Produto> getProdutos() {
        return produtos;
    }

    public int getQtdProdutos(String nomeProduto) {
        return produtos.get(nomeProduto).getVendas();
    }
}
