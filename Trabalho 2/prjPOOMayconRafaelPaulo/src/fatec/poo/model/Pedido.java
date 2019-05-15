package fatec.poo.model;
import fatec.poo.model.ItemPedido;
import java.util.ArrayList;


public class Pedido {
 private String numero,dataEmissao,dataPagto;
 private boolean formaPagto,situacao;
 private Cliente cliente;
 private Vendedor vendedor;
private ArrayList<ItemPedido> itemPedido = new ArrayList<ItemPedido>();

 
 
    public Pedido(String numero, String dataEmissao) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
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
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }
 
    public void addItemPedido(ItemPedido itemPedido){
        
        this.itemPedido.add(itemPedido);
        //double valor = itemPedido.getQtdeVendida() * itemPedido.getProduto().getPreco();
        if (formaPagto) {
            this.cliente.subLimiteDisp(itemPedido.getQtdeVendida() * itemPedido.getProduto().getPreco());
        }
    }
}
