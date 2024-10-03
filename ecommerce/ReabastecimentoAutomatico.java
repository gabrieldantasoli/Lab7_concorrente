package ecommerce;

public class ReabastecimentoAutomatico implements Runnable {
    private Ecommerce ecommerce;
    private String nomeProduto;

    public ReabastecimentoAutomatico(Ecommerce ecommerce, String nomeProduto) {
        this.ecommerce = ecommerce;
        this.nomeProduto = nomeProduto;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int quantidadeProduto = ecommerce.getQtdProduto(nomeProduto);

                int tempoMinimo = 3000;
                int tempoMaximo = 10000;
                int tempoSleep;

                if (quantidadeProduto > 0) {
                    tempoSleep = Math.max(tempoMinimo, (tempoMaximo - (quantidadeProduto * 25)));
                } else {
                    tempoSleep = tempoMaximo;
                }

                Thread.sleep(tempoSleep);

                ecommerce.reabastecerEstoque(nomeProduto);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
