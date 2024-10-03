package ecommerce;

public class RelatorioDeVendas implements Runnable {
    private Ecommerce ecommerce;

    public RelatorioDeVendas(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(30000);
                ecommerce.gerarRelatorioDeVendas();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
