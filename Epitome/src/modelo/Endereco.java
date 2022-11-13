package modelo;

public class Endereco {
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private int id;
	
	public Endereco() {
		this.id = 0;
		this.cidade = "";
		this.bairro = "";
		this.rua = "";
		this.numero = 0;
	}
	
	public Endereco(String cidade, String bairro, String rua, int numero) {
		super();
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	
	public boolean isBlank() {
		if (this.cidade.equals("")) {
			return true;
		}
		if (this.bairro.equals("")) {
			return true;
		}
		if (this.rua.equals("")) {
			return true;
		}
		if (this.numero == 0) {
			return true;
		}
		return false;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
