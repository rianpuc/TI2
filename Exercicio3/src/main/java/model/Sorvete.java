package model;


public class Sorvete {
	private String sabor;
	private double preco;
	private int quantidade;
	
	public Sorvete() {
		this.sabor = "";
		this.preco = 0;
		this.quantidade = 0;
	}
	
	public Sorvete(String sabor, double preco, int quantidade) {
		this.sabor = sabor; this.preco = preco; this.quantidade = quantidade;
	}
	
	public String getSabor() {
		return this.sabor;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public void setSabor(String new_sabor) {
		this.sabor = new_sabor;
	}	
	
	public void setPreco(double new_preco) {
		this.preco = new_preco;
	}
	
	public void setQuantidade(int new_quantidade) {
		this.quantidade = new_quantidade;
	}
	
	@Override
	public String toString() {
		return "Sorvete sabor: " + sabor + "\npreço: " + preco + "\nquantidade: " + quantidade;
	}	
}