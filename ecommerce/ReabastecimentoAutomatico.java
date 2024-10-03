package ecommerce;

public class ReabastecimentoAutomático implements Runnable {
    private Ecommerce ecommerce;

    public ReabastecimentoAutomático(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                ecommerce.reabastecerEstoque();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
