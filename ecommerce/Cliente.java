package ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente implements Runnable {
    private Ecommerce ecommerce;
    private String nome;
    private Random random;
    private int maxPedidos;
    private int pedidosFeitos;

    public Cliente(Ecommerce ecommerce, String nome, int maxPedidos) {
        this.ecommerce = ecommerce;
        this.nome = nome;
        this.random = new Random();
        this.maxPedidos = maxPedidos;
        this.pedidosFeitos = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((random.nextInt(5) + 1) * 1000);

                int numProdutos = random.nextInt(3) + 1;
                List<Produto> produtosDoPedido = new ArrayList<>();

                for (int i = 0; i < numProdutos; i++) {
                    String tipoProduto;

                    // Lógica para definir o produto a ser adicionado
                    int probabilidade = random.nextInt(100); // Gera um número aleatório entre 0 e 99

                    // A lógica abaixo garante que o Produto2 seja o mais vendido, depois o Produto1, e depois o Produto3
                    if (probabilidade < 50) {
                        tipoProduto = "Produto2"; // 60% de chance para Produto2
                    } else if (probabilidade < 90) {
                        tipoProduto = "Produto1"; // 30% de chance para Produto1
                    } else {
                        tipoProduto = "Produto3"; // 10% de chance para Produto3
                    }

                    int preco = (random.nextInt(500) + 100);
                    int quantidade = random.nextInt(3) + 1;
                    produtosDoPedido.add(new Produto(tipoProduto, preco, quantidade));
                }

                int prioridade = random.nextInt(10) + 1;
                Pedido pedido = new Pedido(produtosDoPedido, this.nome, pedidosFeitos, prioridade);

                ecommerce.adicionarPedido(pedido);
                pedidosFeitos++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
