package ecommerce;

import java.util.Map;

public class Pedido {
    private final int idCliente;
    private final Map<String, Integer> produtos;

    public Pedido(int idCliente, Map<String, Integer> produtos) {
        this.idCliente = idCliente;
        this.produtos = produtos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Map<String, Integer> getProdutos() {
        return produtos;
    }
}
