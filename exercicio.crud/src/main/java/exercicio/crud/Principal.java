package exercicio.crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

class MyIO {

	   private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.forName("ISO-8859-1")));
	   private static String charset = "ISO-8859-1";

	   public static void setCharset(String charset_){
	      charset = charset_;
	      in = new BufferedReader(new InputStreamReader(System.in, Charset.forName(charset)));
	   }

	   public static void print(){
	   }

	   public static void print(int x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }
	   
	   public static void print(float x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }
	   
	   public static void print(double x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void print(String x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void print(boolean x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void print(char x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void println(){
	   }

	   public static void println(int x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.println(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void println(float x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.println(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }
	   
	   public static void println(double x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.println(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void println(String x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.println(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void println(boolean x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.println(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void println(char x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.println(x);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static void printf(String formato, double x){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.printf(formato, x);// "%.2f"
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	   }

	   public static double readDouble(){
	      double d = -1;
	      try{
	         d = Double.parseDouble(readString().trim().replace(",","."));
	      }catch(Exception e){}
	      return d;
	   }

	   public static double readDouble(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      return readDouble();
	   }

	   public static float readFloat(){
	      return (float) readDouble();
	   }

	   public static float readFloat(String str){
	      return (float) readDouble(str);
	   }

	   public static int readInt(){
	      int i = -1;
	      try{
	         i = Integer.parseInt(readString().trim());
	      }catch(Exception e){}
	      return i;
	   }

	   public static int readInt(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      return readInt();
	   }

	   public static String readString(){
	      String s = "";
	      char tmp;
	      try{
	         do{
	            tmp = (char)in.read();
	            if(tmp != '\n' && tmp != ' ' && tmp != 13){
	               s += tmp;
	            }
	         }while(tmp != '\n' && tmp != ' ');
	      }catch(IOException ioe){
	         System.out.println("lerPalavra: " + ioe.getMessage());
	      }
	      return s;
	   }

	   public static String readString(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      return readString();
	   }

	   public static String readLine(){
	      String s = "";
	      char tmp;
	      try{
	         do{
	            tmp = (char)in.read();
	            if(tmp != '\n' && tmp != 13){
	               s += tmp;
	            }
	         }while(tmp != '\n');
	      }catch(IOException ioe){
	         System.out.println("lerPalavra: " + ioe.getMessage());
	      }
	      return s;
	   }

	   public static String readLine(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      return readLine();
	   }

	   public static char readChar(){
	      char resp = ' ';
	      try{
	         resp  = (char)in.read();
	      }catch(Exception e){}
	      return resp;
	   }

	   public static char readChar(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      return readChar();
	   }

	   public static boolean readBoolean(){
	      boolean resp = false;
	      String str = "";

	      try{
	         str = readString();
	      }catch(Exception e){}

	      if(str.equals("true") || str.equals("TRUE") || str.equals("t") || str.equals("1") || 
	            str.equals("verdadeiro") || str.equals("VERDADEIRO") || str.equals("V")){
	         resp = true;
	            }

	      return resp;
	   }

	   public static boolean readBoolean(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      return readBoolean();
	   }

	   public static void pause(){
	      try{
	         in.read();
	      }catch(Exception e){}
	   }

	   public static void pause(String str){
	      try {
	         PrintStream out = new PrintStream(System.out, true, charset);
	         out.print(str);
	      }catch(UnsupportedEncodingException e){ System.out.println("Erro: charset invalido"); }
	      pause();
	   }
	}

public class Principal {
	public static void printMenu() {
		System.out.println("Bem-vindo ao gestor de sorvetes.");
		System.out.println("1. Inserir sorvete");
		System.out.println("2. Atualizar estoque");
		System.out.println("3. Atualizar preço");
		System.out.println("4. Excluir sabor");
		System.out.println("5. Listar os sorvetes.");
		System.out.println("0. Sair");
	}
	public static void main(String[] args) {
		DAO dao = new DAO(); String sabor = ""; double preco = -1.0; int quantidade = -1;
		dao.conectar();
		int escolha = -1;
		while(escolha != 0) {
			printMenu();
			escolha = MyIO.readInt();
			switch(escolha) {
			//Inserir um sabor de sorvetes.
			case 1:
				sabor = MyIO.readLine("Favor digitar o sabor do sorvete: ");
				preco = MyIO.readDouble("Favor digitar o preço: ");
				quantidade = MyIO.readInt("Favor digitar a quantidade: ");
				Sorvete sorvete = new Sorvete(sabor, preco, quantidade);
				if(dao.inserirSorvete(sorvete) == true) {
					System.out.println("Inserção feita com sucesso: " + sorvete.toString());
				}
				break;
			//Atualizar o estoque de sorvetes.	
			case 2:
				sabor = MyIO.readLine("Sabor do sorvete que você deseja alterar a quantidade: ");
				quantidade = MyIO.readInt("Nova quantidade: ");
				while(quantidade <= 0) {
					MyIO.println("A nova quantidade não pode ser negativa e nem nula!");
					quantidade = MyIO.readInt("Nova quantidade: ");
				}
				if(dao.atualizarQuantidade(sabor, quantidade) == true) {
					MyIO.println("A quantidade foi atualizada com sucesso!");
				}
				break;
			//Atualizar preço do sorvetes.
			case 3:
				double new_preco = -1.0;
				sabor = MyIO.readLine("Sabor do sorvete que você deseja mudar o preço: ");
				preco = MyIO.readDouble("Novo preço: ");
				while(preco <= 0) {
					MyIO.println("O novo valor não pode ser negativo e nem nulo!");
					preco = MyIO.readDouble("Novo preço: ");
				}
				if(dao.atualizarPreco(sabor, preco) == true) {
					MyIO.println("O preço foi atualizado com sucesso!");
				}
				break;
			//Excluir um sabor de sorvete.
			case 4:
				sabor = MyIO.readLine("Qual sabor você deseja excluir: ");
				if(dao.removerSabor(sabor) == true) {
					MyIO.println("Sabor removido com sucesso");
				}
				break;
			//Listar os sorvetes.
			case 5:
				Sorvete sorvetes[] = dao.getSorvetes();
				System.out.println("--- LISTA DE TODOS SABORES ---");
				sorvetes = dao.getSorvetes();
				if(sorvetes == null) {
					System.out.println("Não há sorvetes cadastrados!");
				} else {
					for(int i = 0; i < sorvetes.length; i++) {
						System.out.println(sorvetes[i].toString());
					}
				}
				break;
			case 0:
				escolha = 0;
				break;
			default:
				break;
			}
		}
	}
}
