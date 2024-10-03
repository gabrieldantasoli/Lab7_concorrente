package ecommerce;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PaymentProcessor {
    
    public CompletableFuture<Void> processPayment(Order order) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Pagamento confirmado para o pedido " + order.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
