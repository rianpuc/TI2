package exercicio.crud;

import java.sql.*;

public class DAO {
	private Connection conexao;
	public DAO() {
		conexao = null;
	}
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "sorvetes";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	public boolean close() {
		boolean status = true;
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	public boolean inserirSorvete(Sorvete sorvete) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO sorvetes (sabor, preco, quantidade) " +
			"VALUES ('"+sorvete.getSabor()+ "', '" + sorvete.getPreco() + "', '" +
					sorvete.getQuantidade() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean atualizarPreco(String sabor, double new_price) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE sorvetes SET preco = '" + new_price + "' WHERE sabor = '" + sabor + "';";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean atualizarQuantidade(String sabor, int new_quantidade) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE sorvetes SET quantidade = '" + new_quantidade + "' WHERE sabor = '" + sabor + "';";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean removerSabor(String sabor) {
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
	public Sorvete[] getSorvetes() {
		Sorvete[] sorvetes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM sorvetes");		
	         if(rs.next()){
	             rs.last();
	             sorvetes = new Sorvete[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                sorvetes[i] = new Sorvete(rs.getString("sabor"), rs.getDouble("preco"), 
	                		                  rs.getInt("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return sorvetes;
	}
	public Sorvete[] getEstoquesVazios() {
		Sorvete[] sorvetes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM sorvetes WHERE sorvetes.quantidade = 0");		
	         if(rs.next()){
	             rs.last();
	             sorvetes = new Sorvete[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
		                sorvetes[i] = new Sorvete(rs.getString("sabor"), rs.getDouble("preco"), 
                         		                  rs.getInt("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return sorvetes;
	}
}
