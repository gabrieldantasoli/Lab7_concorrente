package ecommerce;

public class Payment {
    private Order order;

    public Payment(Order order) {
        this.order = order;
    }

    public void confirmar() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Pagamento confirmado para o pedido " + order.getId());
    }
}
