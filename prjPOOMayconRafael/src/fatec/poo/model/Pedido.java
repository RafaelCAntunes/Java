package fatec.poo.model;
import fatec.poo.model.ItemPedido;
import java.util.ArrayList;


public class Pedido {
 private String numero,dataEmissao,dataPagto;
 private boolean formaPagto,situacao;
 private Cliente cliente;
 private Vendedor vendedor;
private ArrayList<ItemPedido> itemPedido = new ArrayList<ItemPedido>();
private ItemPedido itemPedidoAux;
 
 
    public Pedido(String numero, String dataEmissao, Cliente cliente, Vendedor vendedor, int sequencia, double qtdeVendida, Produto produto) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.cliente = cliente;
        this.vendedor = vendedor;
        addItemPedido(sequencia,qtdeVendida,produto,this);
    }

    public void setDataPagto(String dataPagto) {
        this.dataPagto = dataPagto;
    }

    public void setFormaPagto(boolean formaPagto) {
        this.formaPagto = formaPagto;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getNumero() {
        return numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public String getDataPagto() {
        return dataPagto;
    }

    public boolean getFormaPagto() {
        return formaPagto;
    }

    public boolean getSituacao() {
        return situacao;
    }
 
    public final void addItemPedido(int sequencia, double qtdeVendida, Produto produto, Pedido pedido){
        this.itemPedidoAux = new ItemPedido(sequencia,qtdeVendida,produto,pedido);
        itemPedido.add(itemPedidoAux);
        double valor = qtdeVendida * produto.getPreco();
        if (formaPagto) {
            this.cliente.subLimiteDisp(valor);
        }
    }
}
