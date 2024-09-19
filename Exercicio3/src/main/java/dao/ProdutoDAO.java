package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Sorvete;

public class ProdutoDAO extends DAO {	
	public ProdutoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Sorvete produto) {
		boolean status = false;
		try {
			String sql = "INSERT INTO sorvetes (sabor, preco, quantidade) "
		               + "VALUES ('" + produto.getSabor() + "', "
		               + produto.getPreco() + ", " + produto.getQuantidade() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Sorvete getSingle(String id) {
		Sorvete produto = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM sorvetes WHERE sabor = '" + id + "';";
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 produto = new Sorvete(rs.getString("sabor"), rs.getDouble("preco"), rs.getInt("quantidade"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produto;
	}
	
	private List<Sorvete> get(String orderBy) {
		List<Sorvete> produtos = new ArrayList<Sorvete>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM sorvetes" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Sorvete p = new Sorvete(rs.getString("sabor"), rs.getDouble("preco"), rs.getInt("quantidade"));
	            produtos.add(p);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return produtos;
	}
	
	public List<Sorvete> get() {
		return get("");
	}

	
	public List<Sorvete> getOrderBySabor() {
		return get("sabor");		
	}
	
	
	public List<Sorvete> getOrderByPreco() {
		return get("preco");		
	}
	
	
	public List<Sorvete> getOrderByQuantidade() {
		return get("quantidade");	
	}
	
	
	public boolean update(Sorvete produto) {
		boolean status = false;
		try {  
			String sql = "UPDATE sorvetes SET sabor = '" + produto.getSabor() + "', "
					   + "preco = " + produto.getPreco() + ", " 
					   + "quantidade = " + produto.getQuantidade() + " WHERE sabor = '" + produto.getSabor() + "';";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean delete(String sabor) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM sorvetes WHERE sabor = '" + sabor + "';");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}