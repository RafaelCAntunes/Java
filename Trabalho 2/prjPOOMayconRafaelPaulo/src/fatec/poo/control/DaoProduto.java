package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import fatec.poo.model.Produto;
import java.util.HashSet;
import java.util.Set;

public class DaoProduto {
    private Connection conn;
    
    public DaoProduto(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO produto_poo("
                    + "codigo, descricao, qtde_estoque, preco, estoque_minimo) "
                    + "VALUES(?,?,?,?,?)");
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getQtdeEstoque());
            ps.setDouble(4, produto.getPreco());
            ps.setDouble(5, produto.getEstoqueMinimo());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE produto_poo set descricao = ?, "
                    + "qtde_estoque = ?, "
                    + "preco = ?, "
                    + "estoque_minimo = ? "
                    + "where codigo = ?");
            
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getQtdeEstoque());
            ps.setDouble(3, produto.getPreco());
            ps.setDouble(4, produto.getEstoqueMinimo());
            ps.setString(5, produto.getCodigo());
           
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public Produto consultar (String codigo) {
        Produto p = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * from produto_poo where " +
                                                 "codigo = ?");
            
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                p = new Produto (codigo, rs.getString("descricao"));
                p.setQtdeEstoque(rs.getDouble("qtde_estoque"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoqueMinimo(rs.getDouble("estoque_minimo"));
                
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (p);
    }    
     
     public void excluir(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM produto_poo where codigo = ?");
            
            ps.setString(1, produto.getCodigo());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}
