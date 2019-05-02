package fatec.poo.model;
import fatec.poo.model.Produto;


public class ItemPedido {
    private int sequencia;
    private double qtdeVendida;
    private Produto produto;
    private Pedido pedido;
    
    public ItemPedido(int sequencia, double qtdeVendida, Produto produto, Pedido pedido) {
        this.sequencia = sequencia;
        this.qtdeVendida = qtdeVendida;
        this.produto = produto;
        this.pedido = pedido;
        
        this.produto.setQtdeEstoque(this.produto.getQtdeEstoque()-this.qtdeVendida);
    }

    public void setQtdeVendida(double qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
    }

    public int getSequencia() {
        return sequencia;
    }

    public double getQtdeVendida() {
        return qtdeVendida;
    }
    
        
}
