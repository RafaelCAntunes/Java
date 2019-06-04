package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Cliente;
import fatec.poo.model.Pessoa;
import java.util.HashSet;

public class DaoCliente {

    private Connection conn;
    
    public DaoCliente(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO CLIENTE_POO(CPF,NOME,ENDERECO,CIDADE,UF,CEP,DDD,TELEFONE,LIMITE_CRED,LIMITE_DISP) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
                      
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getCidade());
                      
            ps.setString(5, cliente.getUf());
            ps.setString(6, cliente.getCep());
                      
            ps.setString(7, cliente.getDdd());
            ps.setString(8, cliente.getTelefone());
                      
            ps.setDouble(9, cliente.getLimiteCred());
            ps.setDouble(10, cliente.getLimiteDisp());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE CLIENTE_POO set NOME = ?, ENDERECO = ?," +
            "CIDADE = ?, UF = ?,CEP = ?, DDD = ?, TELEFONE = ?, LIMITE_CRED = ?, LIMITE_DISP = ?" +
            "where CPF = ?");
            
            
            ps.setString(1, cliente.getNome());
                      
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getCidade());
                      
            ps.setString(4, cliente.getUf());
            ps.setString(5, cliente.getCep());
                      
            ps.setString(6, cliente.getDdd());
            ps.setString(7, cliente.getTelefone());
                      
            ps.setDouble(8, cliente.getLimiteCred());
            ps.setDouble(9, cliente.getLimiteDisp());
           
            ps.setString(10, cliente.getCpf());
            
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public  Cliente consultar (String cpf) {
        Cliente cliente = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * from CLIENTE_POO where " +
                                                 "CPF = ?");
            
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
                cliente = new Cliente (cpf, rs.getString("NOME"),rs.getDouble("LIMITE_CRED"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setUf(rs.getString("UF"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setDdd(rs.getString("DDD"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setLimiteDisp(rs.getDouble("LIMITE_CRED"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (cliente);
    }    
     
     public void excluir(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM CLIENTE_POO where CPF = ?");
            
            ps.setString(1, cliente.getCpf());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}





