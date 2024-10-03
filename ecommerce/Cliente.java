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
    private String[] tiposDeProdutos = {"Produto1", "Produto2", "Produto3"}; 
    
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
                    String tipoProduto = tiposDeProdutos[random.nextInt(tiposDeProdutos.length)];
                    int preco = (random.nextInt(500) + 100);
                    int quantidade = random.nextInt(3) + 1;
                    produtosDoPedido.add(new Produto(tipoProduto, preco, quantidade));
                }

                Pedido pedido = new Pedido(produtosDoPedido, this.nome, pedidosFeitos);

                ecommerce.adicionarPedido(pedido);
                pedidosFeitos++;
                System.out.println(".");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
