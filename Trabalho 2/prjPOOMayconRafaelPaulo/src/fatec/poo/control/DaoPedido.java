/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fatec.poo.control;

import fatec.poo.model.Cliente;
import fatec.poo.model.ItemPedido;
import fatec.poo.model.Pedido;
import fatec.poo.model.Produto;
import fatec.poo.model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 0030481723010
 */
public class DaoPedido {
    private Connection conn;
    
    public DaoPedido(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO PEDIDO_POO(numero, cpf_cliente, cpf_vendedor, data_emissao, data_pagto, forma_pagto, situacao) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, pedido.getNumero());
            ps.setString(2, pedido.getCliente().getCpf());
            ps.setString(3, pedido.getVendedor().getCpf());
            ps.setString(4, pedido.getDataEmissao());
            ps.setString(5, pedido.getDataPagto());
            if (pedido.getFormaPagto()) {
                ps.setString(6, "1");
            } else {
                ps.setString(6, "0");
            }
            if (pedido.getSituacao()) {
                ps.setString(7, "1");
            } else {
                ps.setString(7, "0");
            }
            
            ps = conn.prepareStatement("UPDATE CLIENTE_POO set LIMITE_DISP = ? where CPF = ?");
            
            ps.setDouble(1, pedido.getCliente().getLimiteDisp());
            ps.setString(2, pedido.getCliente().getCpf());
            
            ps.execute();
            
            for (int i = 0; i < pedido.getItemPedidoSize(); i++) {
                ps = conn.prepareStatement("INSERT INTO ITEM_PEDIDO_POO(sequencia, cod_produto, num_pedido, qtde_vendida) VALUES(sq_item_pedido_sequencia.nextval, ?, ?, ?)");
                ps.setString(1, pedido.getItemPedido(i).getProduto().getCodigo());
                ps.setString(2, pedido.getNumero());
                ps.setDouble(3, pedido.getItemPedido(i).getQtdeVendida());
                
                ps.execute();
            }
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE PEDIDO_POO SET cpf_cliente = ?, cpf_vendedor = ?, data_emissao = ?, data_pagto = ?, forma_pagto = ?, situacao = ? WHERE numero = ?");
            ps.setString(1, pedido.getCliente().getCpf());
            ps.setString(2, pedido.getVendedor().getCpf());
            ps.setString(3, pedido.getDataEmissao());
            ps.setString(4, pedido.getDataPagto());
            if (pedido.getFormaPagto()) {
                ps.setString(5, "1");
            } else {
                ps.setString(5, "0");
            }
            if (pedido.getSituacao()) {
                ps.setString(6, "1");
            } else {
                ps.setString(6, "0");
            }
            ps.setString(7, pedido.getNumero());
            
            ps = conn.prepareStatement("DELETE FROM ITEM_PEDIDO_POO where num_pedido = ?");
            
            ps.setString(1, pedido.getNumero());
                      
            ps.execute();
            
            ps = conn.prepareStatement("UPDATE CLIENTE_POO set LIMITE_DISP = ? where CPF = ?");
            
            ps.setDouble(1, pedido.getCliente().getLimiteDisp());
            ps.setString(2, pedido.getCliente().getCpf());
            
            ps.execute();
            
            for (int i = 0; i < pedido.getItemPedidoSize(); i++) {
                ps = conn.prepareStatement("INSERT INTO ITEM_PEDIDO_POO(sequencia, cod_produto, num_pedido, qtde_vendida) VALUES(sq_item_pedido_sequencia.nextval, ?, ?, ?)");
                ps.setString(1, pedido.getItemPedido(i).getProduto().getCodigo());
                ps.setString(2, pedido.getNumero());
                ps.setDouble(3, pedido.getItemPedido(i).getQtdeVendida());
                
                ps.execute();
            }
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public Pedido consultar (String numero) {
        Pedido pedido = null;
        Cliente cliente = null;
        Vendedor vendedor = null;
        ItemPedido itemPedido = null;
        Produto produto = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT data_emissao, data_pagto, forma_pagto, situacao, "
                                    + "cpf_cliente, CLIENTE_POO.nome as cliente_nome, CLIENTE_POO.limite_cred as cliente_limite_cred, "
                                    + "cpf_vendedor, VENDEDOR_POO.nome as vendedor_nome, VENDEDOR_POO.salario_base as vendedor_salario_base "
                                    + "from PEDIDO_POO "
                                    + "JOIN CLIENTE_POO ON cpf_cliente = CLIENTE_POO.cpf "
                                    + "JOIN VENDEDOR_POO ON cpf_vendedor = VENDEDOR_POO.cpf "
                                    + "WHERE numero = ?");
            
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
                pedido = new Pedido(numero, rs.getString("data_emissao"));
                cliente = new Cliente(rs.getString("cpf_cliente"), rs.getString("cliente_nome"), rs.getDouble("cliente_limite_cred"));
                vendedor = new Vendedor(rs.getString("cpf_vendedor"), rs.getString("vendedor_nome"), rs.getDouble("vendedor_salario_base"));
                
                pedido.setCliente(cliente);
                pedido.setVendedor(vendedor);
                pedido.setDataPagto(rs.getString("data_pagto"));
                pedido.setFormaPagto((rs.getInt("forma_pagto") == 1));
                pedido.setSituacao((rs.getInt("situacao") == 1));
                
                ps = conn.prepareStatement("SELECT * from ITEM_PEDIDO_POO "
                                    + "JOIN PRODUTO_POO ON cod_produto = codigo "
                                    + "WHERE num_pedido = ?");
                
                ps.setString(1, numero);
                ResultSet rsItem = ps.executeQuery();
                
                while(rsItem.next()) {
                    produto = new Produto(rsItem.getString("cod_produto"), rsItem.getString("descricao"));
                    produto.setPreco(rsItem.getDouble("preco"));
                    
                    itemPedido = new ItemPedido(rsItem.getInt("sequencia"), rsItem.getDouble("qtde_vendida"), produto);
                    pedido.addItemPedido(itemPedido);
                }
                
                cliente.setLimiteDisp(rs.getDouble("cliente_limite_disp"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (pedido);
    }    
     
     public void excluir(Pedido pedido) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE CLIENTE_POO set LIMITE_DISP = ? where CPF = ?");
            
            ps.setDouble(1, pedido.getCliente().getLimiteDisp());
            ps.setString(2, pedido.getCliente().getCpf());
            
            ps.execute();
            
            ps = conn.prepareStatement("DELETE FROM ITEM_PEDIDO_POO where num_pedido = ?");
            
            ps.setString(1, pedido.getNumero());
                      
            ps.execute();
            
            ps = conn.prepareStatement("DELETE FROM PEDIDO_POO where numero = ?");
            
            ps.setString(1, pedido.getNumero());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}
