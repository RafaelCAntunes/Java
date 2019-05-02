package fatec.poo.model;
import fatec.poo.model.Produto;


public class ItemPedido {
    private int sequencia;
    private double qtdeVendida;
    private Produto produto;
    private Pedido pedido;
    
    public ItemPedido(int sequencia, double qtdeVendida, Produto produto) {
        this.sequencia = sequencia;
        this.qtdeVendida = qtdeVendida;
        this.produto = produto;
        }

    public void setQtdeVendida(double qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
        this.produto.setQtdeEstoque(this.produto.getQtdeEstoque()-this.qtdeVendida);
    }

    public int getSequencia() {
        return sequencia;
    }

    public double getQtdeVendida() {
        return qtdeVendida;
    }
    
    public Produto getProduto() {
        return produto;
    }
        
    
    public void setPedido(Pedido pedido){
        
        this.pedido = pedido;
    }

    
}
