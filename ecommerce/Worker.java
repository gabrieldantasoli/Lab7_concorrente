package ecommerce;

public class Worker implements Runnable {
    private Ecommerce ecommerce;

    public Worker(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Pedido pedido = ecommerce.pegarProximoPedido();
                ecommerce.processarPedido(pedido);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
