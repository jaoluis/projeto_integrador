package modelo;

public class endereco {

	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	
	public endereco() {
		
	}
	
	public endereco(String cidade, String bairro, String rua, int numero) {
		this.bairro = bairro;
		this.cidade = cidade;
		this.rua = rua;
		this.numero = numero;
		
	}
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
