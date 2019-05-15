package fatec.poo.model;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    private double limiteCred,limiteDisp;
    private ArrayList<Pedido> arrObjPedido = new ArrayList<Pedido>();
    
    public Cliente(String cpf, String nome, double limiteCred) {
        super(cpf, nome);
        this.limiteCred = limiteCred;
        this.limiteDisp = limiteCred;
    }

    public void setLimiteCred(double limiteCred) {
        this.limiteCred = limiteCred;
    }

    public double getLimiteCred() {
        return limiteCred;
    }

    public double getLimiteDisp() {
        return limiteDisp;
    }
    
    public void addPedido(Pedido pedido) {
        this.arrObjPedido.add(pedido);
    }
    
    public void subLimiteDisp(double valor) {
        this.limiteDisp -= valor;
    }
}
