package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Vendedor;
import java.sql.ResultSetMetaData;

public class DaoVendedor {
    
    private Connection conn;
    
    public DaoVendedor(Connection conn) {
        this.conn = conn;
    }
    
    public Vendedor consultar(String cpf) {
        Vendedor vendedor = null;
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement("SELECT * FROM vendedor_poo WHERE cpf = ? ");
                        
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                vendedor = new Vendedor (cpf, rs.getString("nome"), rs.getDouble("salario_base"));
                vendedor.setEndereco(rs.getString("ENDERECO"));
                vendedor.setCidade(rs.getString("CIDADE"));
                vendedor.setUf(rs.getString("UF"));
                vendedor.setCep(rs.getString("CEP"));
                vendedor.setDdd(rs.getString("DDD"));
                vendedor.setTelefone(rs.getString("TELEFONE"));
                vendedor.setTaxaComissao(rs.getDouble("TAXA_COMISSAO"));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return(vendedor);
    }
    
    public void inserir(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" INSERT INTO vendedor_poo(cpf,nome,endereco,cidade,uf,cep,ddd,telefone,salario_base,taxa_comissao)" 
                                     + " VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, vendedor.getCpf());
            ps.setString(2, vendedor.getNome());
            ps.setString(3, vendedor.getEndereco());
            ps.setString(4, vendedor.getCidade());
            ps.setString(5, vendedor.getUf());
            ps.setString(6, vendedor.getCep());
            ps.setString(7, vendedor.getDdd());
            ps.setString(8, vendedor.getTelefone());
            ps.setDouble(9, vendedor.getSalarioBase());
            ps.setDouble(10, vendedor.getTaxaComissao());
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void alterar(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" UPDATE vendedor_poo      "
                                     + "    SET nome = ?,         "
                                     + "        endereco = ?,     "
                                     + "        cidade = ?,       "
                                     + "        uf = ?,           "
                                     + "        cep = ?,          "
                                     + "        ddd = ?,          "
                                     + "        telefone = ?,     "
                                     + "        salario_base = ?, "
                                     + "        taxa_comissao = ? "
                                     + "  WHERE cpf = ?           ");
            
            ps.setString(1, vendedor.getNome());
            ps.setString(2, vendedor.getEndereco());
            ps.setString(3, vendedor.getCidade());
            ps.setString(4, vendedor.getUf());
            ps.setString(5, vendedor.getCep());
            ps.setString(6, vendedor.getDdd());
            ps.setString(7, vendedor.getTelefone());
            ps.setDouble(8, vendedor.getSalarioBase());
            ps.setDouble(9, vendedor.getTaxaComissao());
            ps.setString(10, vendedor.getCpf());
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void excluir(Vendedor vendedor) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(" DELETE FROM vendedor_poo WHERE cpf = ?");
            
            ps.setString(1, vendedor.getCpf());
            
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
}
